package com.api.quartz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.quartz.domain.Schedule;
import com.api.quartz.service.ScheduleService;

@Controller
@RequestMapping(value="/job")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	@GetMapping
	public String index(Model model) throws Exception {
		List<Schedule> scheduleList = scheduleService.scheduleList();
		
		model.addAttribute("list", scheduleList);
		return "index";
	}
	
	@GetMapping("/addJob")
	public String addJob() {
		return "addJob";
	}
}
