package com.api.quartz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.quartz.domain.Schedule;
import com.api.quartz.mapper.ScheduleMapper;
import com.api.quartz.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

	private final ScheduleMapper scheduleMapper;

	
	@Override
	public List<Schedule> scheduleList() throws Exception {
		return scheduleMapper.scheduleList();
	}

	@Override
	public Schedule jobInfo(String triggerName, String triggerGroup) throws Exception {
		return scheduleMapper.jobInfo(triggerName, triggerGroup);
	}
	
	@Override
	public int countJobName(Schedule schedule) throws Exception {
		return scheduleMapper.countJobName(schedule);
	}

}
