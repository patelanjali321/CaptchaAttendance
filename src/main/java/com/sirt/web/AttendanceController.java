package com.sirt.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import com.sirt.config.SirtConstants;
import com.sirt.entities.Role;
import com.sirt.entities.User;
import com.sirt.service.AttendanceService;

@Controller
public class AttendanceController {
	
	@Autowired
	private AttendanceService attendanceService;

	@Value("${nextCaptcha}")
	private Integer NEXT_CAPTCHA;

	@GetMapping("/registerPage")
	public String registerPage(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@GetMapping("/captchaPage")
	public String captchaPage(Model model) {
		model.addAttribute("user", new User());
		return "captcha";
	}

	@PostMapping("/captchaPage")
	public String captchaVerify(Model model, WebRequest request) {
		if(attendanceService.markRecord(request.getParameter("g-recaptcha-response"))) { //if success wait for next captcha if not show captcha again.
			model.addAttribute("nextCaptcha", NEXT_CAPTCHA);
		}
		return "captcha";
	}
	
	@PostMapping("/registerPage")
	public String register(Model model, WebRequest request) throws ParseException {
		Set<Role> roles = new HashSet<>();
		User user = User.builder().enabled(true).name(request.getParameter("name"))
				.enrolledon(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("enrolledon")))
				.username(request.getParameter("username"))
				.password(request.getParameter("password"))
				.roles(roles).build();
		
		if("on".equals(request.getParameter("faculty"))) {
			roles.add(attendanceService.getRoleByName("ROLE_"+ SirtConstants.ROLE_FACULTY));
		}
		else {
			roles.add(attendanceService.getRoleByName("ROLE_"+ SirtConstants.ROLE_STUDENT));
		}
		
		attendanceService.saveUser(user);
		
		model.addAttribute("result", "success");
		return "register";
	}

	@GetMapping("/")
	public String index(SecurityContextHolderAwareRequestWrapper request, Model model) {
		if(request.isUserInRole(SirtConstants.ROLE_ADMIN) || request.isUserInRole(SirtConstants.ROLE_FACULTY)) {
			return "redirect:/listUser";
		}
		else if(request.isUserInRole(SirtConstants.ROLE_STUDENT)){
			return "redirect:/captchaPage";
		}
		else {
			return "/index";
		}
	}
	
	@GetMapping("/listUser")
	public String listUser(Model model) {
		model.addAttribute("users", attendanceService.getUsers());
		return "listUser";
	}
	
	@GetMapping("/attendance")
	public String attendance(Model model, WebRequest request) throws ParseException {
		int batch = request.getParameter("batch") == null ? LocalDate.now().getYear(): // default to current year batch
			Integer.parseInt(request.getParameter("batch"));
		Date date = request.getParameter("date") == null ? new Date(): // default to today's date
			new Date(Long.parseLong(request.getParameter("date")));
		
		model.addAttribute("attendance", attendanceService.getAttendance(batch,date));
		return "attendance";
	}
}
