package com.api.quartz.service.impl;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.quartz.domain.Schedule;
import com.api.quartz.job.TestJob;
import com.api.quartz.mapper.ScheduleMapper;
import com.api.quartz.service.QuartzService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuartzServiceImpl implements QuartzService {

	private final Logger LOGGER = LoggerFactory.getLogger(QuartzServiceImpl.class);
	
	private final Scheduler scheduler;
	private final ScheduleMapper scheduleMapper;
	

	@Override
	public void addJob(Schedule schedule) {
		try {
			scheduler.start();
			
			JobKey jobKey = JobKey.jobKey(schedule.getJobName(), schedule.getJobGroup());
			
			TriggerKey triggerKey = TriggerKey.triggerKey(schedule.getTriggerName(), schedule.getTriggerGroup());
			
			JobDetail jobDetail =JobBuilder.newJob(TestJob.class)
					.withIdentity(jobKey)
					.build();
			
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(schedule.getCronExpression());
			
			scheduler.addJob(jobDetail, true, true);
			startJob(jobKey, triggerKey, scheduleBuilder);
			scheduleMapper.addJob(schedule);
		} catch (SchedulerException e) {
			LOGGER.error("addjob error : {}", e);
		} catch (Exception e) {
			LOGGER.error("startjob error : {}", e);
		}
	}

	private void startJob(JobKey jobKey, TriggerKey triggerKey, CronScheduleBuilder scheduleBuilder) throws Exception {
		CronTrigger trigger = TriggerBuilder.newTrigger()
				.forJob(jobKey)
				.withIdentity(triggerKey)
				.withSchedule(scheduleBuilder)
				.build();
		
		scheduler.scheduleJob(trigger);
		LOGGER.info("schedule add & start");	
	}

	@Override
	public void pauseJob(String triggerName, String triggerGroup) {
		Schedule jobInfo = scheduleMapper.jobInfo(triggerName, triggerGroup);
		try {
			scheduler.pauseTrigger(TriggerKey.triggerKey(jobInfo.getTriggerName(), jobInfo.getTriggerGroup()));
			scheduleMapper.pauseJob(jobInfo);
			LOGGER.info("scheduler ONE pause");
		} catch (SchedulerException e) {
			LOGGER.error("pausejob error : {}", e);
		}
	}

	@Override
	public void resumeJob(String triggerName, String triggerGroup) {
		Schedule jobInfo = scheduleMapper.jobInfo(triggerName, triggerGroup);
		try {
			scheduler.resumeTrigger(TriggerKey.triggerKey(jobInfo.getTriggerName(), jobInfo.getTriggerGroup()));
			scheduleMapper.resumeJob(jobInfo);
			LOGGER.info("scheduler ONE resume");
		} catch (Exception e) {
			LOGGER.error("resumejob error : {}", e);
		}
	}

	@Override
	public void rescheduleJob(String triggerName, String triggerGroup, String cronExpression) {
		Schedule jobInfo = scheduleMapper.jobInfo(triggerName, triggerGroup);
		jobInfo.setCronExpression(cronExpression);
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobInfo.getTriggerName(), jobInfo.getTriggerGroup());
			
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			
			trigger = trigger.getTriggerBuilder()
					.withIdentity(triggerKey)
					.withSchedule(scheduleBuilder)
					.build();
			
			scheduler.rescheduleJob(triggerKey, trigger);
			scheduleMapper.rescheduleJob(jobInfo);
			LOGGER.info("schedule reschedule");
		} catch (SchedulerException e) {
			LOGGER.error("reschedulejob error : {}", e);
		}
	}

	@Override
	public void deleteTrigger(Schedule schedule) {
		try {
			scheduler.unscheduleJob(TriggerKey.triggerKey(schedule.getTriggerName(), schedule.getTriggerGroup()));
			scheduleMapper.deleteJob(schedule);
			LOGGER.info("schedule(trigger) delete"); 
		} catch (SchedulerException e) {
			LOGGER.error("deletetrigger error : {}", e);
		}
	}
	
	@Override
	public void deleteJob(Schedule schedule) {
		try {
			scheduler.deleteJob(JobKey.jobKey(schedule.getJobName(), schedule.getJobGroup()));
			scheduleMapper.deleteJob(schedule);
			LOGGER.info("schedule(job) delete");
		} catch (SchedulerException e) {
			LOGGER.error("deletejob error : {}", e);
		}
	}

}
