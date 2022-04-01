package com.api.quartz.service;

import java.util.List;

import com.api.quartz.domain.Schedule;

public interface ScheduleService {

	public List<Schedule> scheduleList() throws Exception;
	
	public Schedule jobInfo(String triggerName, String triggerGroup) throws Exception;
	
	public int countJobName(Schedule schedule) throws Exception;
	
}
