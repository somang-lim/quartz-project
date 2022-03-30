package com.api.quartz.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SchedulerConfig {

	@Autowired
	private DataSource datasource;
	
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setOverwriteExistingJobs(true);
		factory.setDataSource(datasource);
		factory.setSchedulerName("QuartzTestSM");
		factory.setWaitForJobsToCompleteOnShutdown(true);
		return factory;
	}
	
//	@Bean
//	public QuartzInitializerListener executorListener() {
//		return new QuartzInitializerListener();
//	}
	
//	@Bean
//	public Scheduler scheduler() throws IOException {
//		return schedulerFactoryBean().getScheduler();
//	}
}
