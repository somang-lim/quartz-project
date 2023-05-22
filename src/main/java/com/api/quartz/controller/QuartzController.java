package com.api.quartz.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.api.quartz.domain.Schedule;
import com.api.quartz.service.QuartzService;
import com.api.quartz.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="/job")
@RequiredArgsConstructor
public class QuartzController {

	private final QuartzService quartzService;
	
	private final ScheduleService scheduleService;

	
	@PostMapping("/addJob")
	public ModelAndView addJob(Schedule schedule, ModelAndView mv) throws Exception {
		
		quartzService.addJob(schedule);
		
		mv.setViewName("redirect:/job");
		
		return mv;
		
	}
	
	@PutMapping("/pauseJob/{triggerName}")
	public void pauseJob(@PathVariable("triggerName") String triggerName,
			@RequestParam("triggerGroup") String triggerGroup) throws Exception {

		quartzService.pauseJob(triggerName, triggerGroup);
		
	}


	@PutMapping("/resumeJob/{triggerName}")
	public void resumeJob(@PathVariable("triggerName") String triggerName,
			@RequestParam("triggerGroup") String triggerGroup) throws Exception {
		
		quartzService.resumeJob(triggerName, triggerGroup);
		
		
	}

	@PutMapping("/rescheduleJob/{triggerName}")
	public void rescheduleJob(@PathVariable("triggerName") String triggerName,
			@RequestParam("triggerGroup") String triggerGroup,
			@RequestParam("cronExpression") String cronExpression) throws Exception {
		
		quartzService.rescheduleJob(triggerName, triggerGroup, cronExpression);
		
	}
	
	@DeleteMapping("/deleteJob/{triggerName}")
	public void deleteJob(@PathVariable("triggerName") String triggerName,
			@RequestParam("triggerGroup") String triggerGroup) throws Exception {
		
		Schedule jobInfo = scheduleService.jobInfo(triggerName, triggerGroup);
		int countJobName = scheduleService.countJobName(jobInfo);
		
		if(countJobName == 1) {
			quartzService.deleteJob(jobInfo);
		} else {
			quartzService.deleteTrigger(jobInfo);
		}
		
	}

}
