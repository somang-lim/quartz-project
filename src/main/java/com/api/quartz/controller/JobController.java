package com.api.quartz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.quartz.service.SchedulerService;

@RestController
@RequestMapping(value="/job")
public class JobController {

	@Autowired
	private SchedulerService schedulerService;

	
	@PostMapping("/addjob")
	public void addjob(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup,
			@RequestParam("cronExpression") String cronExpression) throws Exception {
		schedulerService.addJob(jobName, jobGroup, cronExpression);
	}
	
	@PutMapping("/pausejob")
	public void jobPause(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup) throws Exception {
		schedulerService.pauseJob(jobName, jobGroup);
	}
	
	@PutMapping("/resumejob")
	public void jobResume(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup) throws Exception {
		schedulerService.resumeJob(jobName, jobGroup);
	}
	
	@PutMapping("/reschedulejob")
	public void reschedulejob(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup,
			@RequestParam("cronExpression") String cronExpression) throws Exception {
		schedulerService.rescheduleJob(jobName, jobGroup, cronExpression);
	}
	
	
	@DeleteMapping("/deletejob")
	public void deletejob(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup) throws Exception {
		schedulerService.deleteJob(jobName, jobGroup);
	}
}
