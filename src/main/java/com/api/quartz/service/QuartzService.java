package com.api.quartz.service;

import com.api.quartz.domain.Schedule;

public interface QuartzService {

	public void addJob(Schedule schedule);
	
	public void pauseJob(String triggerName, String triggerGroup);
	
//	public void pauseAllJob(String jobName, String jobGroup);
	
	public void resumeJob(String triggerName, String triggerGroup);
	
//	public void resumeAllJob(String jobName, String jobGroup);
	
	public void rescheduleJob(String triggerName, String triggerGroup, String cronExpression);
	
	public void deleteTrigger(Schedule schedule);
	
	public void deleteJob(Schedule schedule);
}
