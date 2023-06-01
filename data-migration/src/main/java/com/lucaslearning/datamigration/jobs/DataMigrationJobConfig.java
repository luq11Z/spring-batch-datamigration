package com.lucaslearning.datamigration.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class DataMigrationJobConfig {

	/**
	 * 
	 * @param jobRepository
	 * @param migratePersonStep Step to migrate Person data.
	 * @param migrateBankDataStep Step to migrate BankData data. 
	 * @return returns the job to execute the data migration of Person and BankData.
	 */
	@Bean
	public Job dataMigrationJob(JobRepository jobRepository, @Qualifier("migratePersonStep") Step migratePersonStep, 
			@Qualifier("migrateBankDataStep") Step migrateBankDataStep) {
		return new JobBuilder("dataMigrationJob", jobRepository)
				.start(asyncSteps(migratePersonStep, migrateBankDataStep))
				.end()
				.incrementer(new RunIdIncrementer())
				.build();
	}

	/**
	 * Execute Steps in different threads (async programming).
	 * @param migratePersonStep 
	 * @param migrateBankDataStep
	 * @return Returns the flow configured, managed by Spring Batch. 
	 */
	private Flow asyncSteps(Step migratePersonStep, Step migrateBankDataStep) {
		Flow migrateBankDataFlow = new FlowBuilder<Flow>("migrateBankDataFlow")
				.start(migrateBankDataStep)
				.build();
		
		Flow asyncStepsFlow = new FlowBuilder<Flow>("parallelStepsFlow")
				.start(migratePersonStep)
				.split(new SimpleAsyncTaskExecutor()) // enable async threads
				.add(migrateBankDataFlow)
				.build();
		
		return asyncStepsFlow;
	}

}
