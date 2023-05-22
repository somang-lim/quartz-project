package com.api.quartz.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Schedule {

	private int id;
	
	private String triggerName;
	
	private String triggerGroup;
	
	private String cronExpression;
	
	private String jobName;
	
	private String jobGroup;
	
	private String isRunning;
	
	private String createTime;
	
	private Date updateTime;
}
