package com.api.quartz.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJob implements BaseJob {

	private static Logger log = LoggerFactory.getLogger(TestJob.class);
	
	public TestJob() {
//		System.out.println("Test Job Started");
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.error("Test Job - " + new Date());
	}

}
