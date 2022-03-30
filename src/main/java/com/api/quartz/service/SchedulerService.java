package com.api.quartz.service;


public interface SchedulerService {

	public void addJob(String jobName, String jobGroup, String cronExpression);
	
	public void pauseJob(String jobName, String jobGroup);
	
	public void resumeJob(String jobName, String jobGroup);
	
	public void rescheduleJob(String jobName, String jobGroup, String cronExpression);
	
	public void deleteJob(String jobName, String jobGroup) throws Exception;
}
