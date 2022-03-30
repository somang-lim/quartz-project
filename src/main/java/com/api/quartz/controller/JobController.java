package com.api.quartz.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.quartz.job.BaseJob;
import com.api.quartz.job.TestJob;

@RestController
@RequestMapping(value="/job")
public class JobController {

	@Autowired
	private Scheduler scheduler;

	private static Logger log = LoggerFactory.getLogger(JobController.class);
	
	
	@PostMapping("/addjob")
	public void addjob(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup,
			@RequestParam("cronExpression") String cronExpression) throws Exception
	{
		addJob(jobName, jobGroup, cronExpression);
	}
	public void addJob(String jobName, String jobGroup, String cronExpression) throws Exception
	{
		scheduler.start();
		
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		
		JobDetail jobDetail =JobBuilder.newJob(TestJob.class)
				.withIdentity(jobKey)
				.build();
		
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
		
		try {
			scheduler.addJob(jobDetail, true, true);
			createJob(jobKey, scheduleBuilder);
		} catch (SchedulerException e) {
			log.error("error : {}", e);
			System.out.println("작업을 실행하지 못했습니다." + e);
		} catch (Exception e) {
			System.out.println("createJob을 실행하지 못했습니다.");
		}
	}
	
	private void createJob(JobKey jobKey, CronScheduleBuilder scheduleBuilder) throws Exception
	{
		CronTrigger trigger = TriggerBuilder.newTrigger()
				.forJob(jobKey)
				.withIdentity(jobKey.getName(), jobKey.getGroup())
				.withSchedule(scheduleBuilder)
				.build();
		
		scheduler.scheduleJob(trigger);
		log.info("schedule create + action");
	}
	
	@PutMapping("/pausejob")
	public void jobPause(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup) throws Exception
	{
		pauseJob(jobName, jobGroup);
	}
	public void pauseJob(String jobName, String jobGroup)
	{
		try {
			scheduler.pauseJob(JobKey.jobKey(jobName, jobGroup));
			log.info("scheduler pause");
		} catch (SchedulerException e) {
			log.error("error : {}", e);
		}
	}
	
	
	@PutMapping("/resumejob")
	public void jobResume(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup) throws Exception
	{
		resumeJob(jobName, jobGroup);
	}
	public void resumeJob(String jobName, String jobGroup)
	{
		try {
			scheduler.resumeJob(JobKey.jobKey(jobName, jobGroup));
			log.info("scheduler resume");
		} catch (Exception e) {
			log.error("error : {}", e);
		}
	}
	
	
	@PutMapping("/reschedulejob")
	public void reschedulejob(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup,
			@RequestParam("cronExpression") String cronExpression) throws Exception
	{
		rescheduleJob(jobName, jobGroup, cronExpression);
	}
	public void rescheduleJob(String jobName, String jobGroup, String cronExpression)
	{
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
	
	
	@DeleteMapping("/deletejob")
	public void deletejob(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup) throws Exception
	{
		deleteJob(jobName, jobGroup);
	}
	public void deleteJob(String jobName, String jobGroup) throws Exception
	{
		scheduler.pauseTrigger(TriggerKey.triggerKey(jobName));
		scheduler.unscheduleJob(TriggerKey.triggerKey(jobName));
		scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
		log.info("schedule delete");
	}
	
	public static BaseJob getClass(String classname) throws Exception {
		Class<?> class1 = Class.forName(classname);
		return (BaseJob) class1.newInstance();
	}
}
