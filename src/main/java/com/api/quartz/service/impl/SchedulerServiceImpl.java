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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.quartz.controller.JobController;
import com.api.quartz.job.TestJob;
import com.api.quartz.service.SchedulerService;

@Service
public class SchedulerServiceImpl implements SchedulerService {

	private static Logger log = LoggerFactory.getLogger(JobController.class);
	
	@Autowired
	private Scheduler scheduler;
	
	
	@Override
	public void addJob(String jobName, String jobGroup, String cronExpression) {
		try {
			scheduler.start();
			
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			
			JobDetail jobDetail =JobBuilder.newJob(TestJob.class)
					.withIdentity(jobKey)
					.build();
			
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
			
			scheduler.addJob(jobDetail, true, true);
			createJob(jobKey, scheduleBuilder);
		} catch (SchedulerException e) {
			log.error("error : {}", e);
			System.out.println("작업을 실행하지 못했습니다." + e);
		} catch (Exception e) {
			System.out.println("createJob을 실행하지 못했습니다.");
		}	
	}

	private void createJob(JobKey jobKey, CronScheduleBuilder scheduleBuilder) throws Exception {
		CronTrigger trigger = TriggerBuilder.newTrigger()
				.forJob(jobKey)
				.withIdentity(jobKey.getName(), jobKey.getGroup())
				.withSchedule(scheduleBuilder)
				.build();
		
		scheduler.scheduleJob(trigger);
		log.info("schedule create + action");		
	}

	@Override
	public void pauseJob(String jobName, String jobGroup) {
		try {
			scheduler.pauseJob(JobKey.jobKey(jobName, jobGroup));
			log.info("scheduler pause");
		} catch (SchedulerException e) {
			log.error("error : {}", e);
		}
	}

	@Override
	public void resumeJob(String jobName, String jobGroup) {
		try {
			scheduler.resumeJob(JobKey.jobKey(jobName, jobGroup));
			log.info("scheduler resume");
		} catch (Exception e) {
			log.error("error : {}", e);
		}
	}

	@Override
	public void rescheduleJob(String jobName, String jobGroup, String cronExpression) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			
			trigger = trigger.getTriggerBuilder()
					.withIdentity(triggerKey)
					.withSchedule(scheduleBuilder)
					.build();
			
			scheduler.rescheduleJob(triggerKey, trigger);
			log.info("schedule reschedule");
		} catch (SchedulerException e) {
			log.error("error : {}", e);
		}
	}

	@Override
	public void deleteJob(String jobName, String jobGroup) throws Exception {
		scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
	}


}
