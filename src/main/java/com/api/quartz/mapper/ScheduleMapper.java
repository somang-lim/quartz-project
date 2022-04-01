package com.api.quartz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.api.quartz.domain.Schedule;

@Mapper
public interface ScheduleMapper {

	public List<Schedule> scheduleList();

	public void addJob(Schedule schedule);
	
	public Schedule jobInfo(String triggerName, String triggerGroup);
	
	public void pauseJob(Schedule schedule);
	
	public void resumeJob(Schedule schedule);
	
	public void rescheduleJob(Schedule schedule);
	
	public int countJobName(Schedule schedule);
	
	public void deleteJob(Schedule schedule);
	
}
