package com.sirt.service.imp;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.sirt.config.MyUserDetails;
import com.sirt.config.SirtConstants;
import com.sirt.dto.ClassAttendance;
import com.sirt.entities.Arecord;
import com.sirt.entities.Role;
import com.sirt.entities.User;
import com.sirt.jpa.ArecordRepository;
import com.sirt.jpa.RoleRepository;
import com.sirt.jpa.UserRepository;
import com.sirt.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService{
	@Value("${threshhold}")
	private Integer THRESHHOLD;

	@Value("${google.captcha.url}")
	private String URL;

	@Value("${google.captcha.secret}")
	private String SECRET;

	@Value("${nextCaptcha}")
	private long NEXT_CAPTCHA;
	
	@Value("${lectureTime}")
	private String LECTURE_TIME;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ArecordRepository arecordRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public ClassAttendance getAttendance(int batch, Date date) throws ParseException{
		
		//Get list of users in batch
		LocalDateTime curYear = LocalDateTime.of(batch, 1, 1,0,0);
		Date firstDay = localDateTimeToDate(curYear.with(firstDayOfYear())); 
		Date lastDay = localDateTimeToDate(curYear.with(lastDayOfYear()));
		Map<User, Map<Integer,Integer>> userRecords = userRepository.findByEnrolledonBetweenAndRolesIn(
				firstDay,lastDay, Arrays.asList(this.getRoleByName("ROLE_"+ SirtConstants.ROLE_STUDENT)))
				.stream()
				.collect(Collectors.toMap(Function.identity(), user -> new TreeMap<>()));
		
		//Find give date record for users
		Date startDate = atStartOfDay(date);
		Date endDate = atEndOfDay(date);
		List<Arecord> arecords = arecordRepository.findByUserInAndTimeBetweenOrderByUserName(userRecords.keySet(), startDate, endDate);

		for(Arecord arecord : arecords) {
			Map<Integer, Integer> userRecord = userRecords.get(arecord.getUser());
			Integer lecture = getLecture(arecord.getTime());
			int recordCount = userRecord.getOrDefault(lecture, 0) + 1;
			userRecord.put(lecture, recordCount);
		}

		ClassAttendance classAttendance = ClassAttendance.builder().userPresent(userRecords)
				.startTime(startDate).endTime(endDate).build();

		return classAttendance;
	}

	private Integer getLecture(Date recordDate) throws ParseException {
		DateFormat parser = new SimpleDateFormat("HH:mm");
		parser.setTimeZone(TimeZone.getTimeZone("UTC"));
		long millPassed = recordDate.getTime() - atStartOfDay(recordDate).getTime();
		String[] slots = LECTURE_TIME.split(",");
		for(int i=0; i<slots.length; i++) {
			String[] boundary = slots[i].split("-");
			if(parser.parse(boundary[0]).getTime() < millPassed && millPassed < parser.parse(boundary[1]).getTime()) {
				return i+1;
			}
		}
		return 0;
	}

	@Override
	public Role getRoleByName(String role) {
		return roleRepository.findOneByName(role);
	}

	@Override
	public boolean markRecord(String catpchaResponse) {
		boolean result = false;
		try {
			//get Current user
			User user = userRepository.findByUsername(
					((MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
			
			//do not record if duration is lower than innterval
			Arecord lastArecord = arecordRepository.findTopByUserOrderByTimeDesc(user);
			if(lastArecord!=null && lastArecord.getTime().getTime() + NEXT_CAPTCHA > new Date().getTime()) {
				return true;
			}

			//verify response.
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
			map.add("secret", SECRET);
			map.add("response", catpchaResponse);

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

			ResponseEntity<Map> response = restTemplate.postForEntity( URL, request , Map.class );
			
			result = (Boolean)response.getBody().get("success");
			if(result) {
				Arecord arecord = Arecord.builder().time(new Date()).user(user).build();
				arecordRepository.save(arecord);
			}
		}	
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static Date atStartOfDay(Date date) {
	    LocalDateTime localDateTime = dateToLocalDateTime(date);
	    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
	    return localDateTimeToDate(startOfDay);
	}

	public static Date atEndOfDay(Date date) {
	    LocalDateTime localDateTime = dateToLocalDateTime(date);
	    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
	    return localDateTimeToDate(endOfDay);
	}

	private static LocalDateTime dateToLocalDateTime(Date date) {
	    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	private static Date localDateTimeToDate(LocalDateTime localDateTime) {
	    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
}
