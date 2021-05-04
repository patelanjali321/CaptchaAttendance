package com.sirt.dto;

import java.util.Date;
import java.util.Map;

import com.sirt.entities.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ClassAttendance {
	private Date startTime;
	private Date endTime;
	private Map<User, Map<Integer, Integer>> userPresent;
}
