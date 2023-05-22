package com.api.quartz.service;

import java.util.List;

import com.api.quartz.domain.Schedule;

public interface ScheduleService {

	List<Schedule> scheduleList() throws Exception;
	
	Schedule jobInfo(String triggerName, String triggerGroup) throws Exception;
	
	int countJobName(Schedule schedule) throws Exception;
	
}
