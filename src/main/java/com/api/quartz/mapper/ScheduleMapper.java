package com.api.quartz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.api.quartz.domain.Schedule;

@Mapper
public interface ScheduleMapper {

	List<Schedule> scheduleList();

	void addJob(Schedule schedule);
	
	Schedule jobInfo(String triggerName, String triggerGroup);
	
	void pauseJob(Schedule schedule);
	
	void resumeJob(Schedule schedule);
	
	void rescheduleJob(Schedule schedule);
	
	int countJobName(Schedule schedule);
	
	void deleteJob(Schedule schedule);
	
}
