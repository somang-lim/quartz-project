package com.api.quartz.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SchedulerConfig {

	private final DataSource datasource;
	
	
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setOverwriteExistingJobs(true);
		factory.setDataSource(datasource);
		factory.setSchedulerName("QuartzTestSM");
		factory.setWaitForJobsToCompleteOnShutdown(true);
		return factory;
	}
	
}
