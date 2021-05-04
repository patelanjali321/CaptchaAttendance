package com.sirt.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.sirt.dto.ClassAttendance;
import com.sirt.entities.Role;
import com.sirt.entities.User;

public interface AttendanceService {

	void saveUser(User user);

	List<User> getUsers();

	ClassAttendance getAttendance(int semester, Date startDate) throws ParseException;

	Role getRoleByName(String string);

	boolean markRecord(String parameter);

}
