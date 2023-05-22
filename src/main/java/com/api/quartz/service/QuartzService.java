package com.api.quartz.service;

import com.api.quartz.domain.Schedule;

public interface QuartzService {

	void addJob(Schedule schedule);
	
	void pauseJob(String triggerName, String triggerGroup);
	
	void resumeJob(String triggerName, String triggerGroup);
	
	void rescheduleJob(String triggerName, String triggerGroup, String cronExpression);
	
	void deleteTrigger(Schedule schedule);
	
	void deleteJob(Schedule schedule);

}
