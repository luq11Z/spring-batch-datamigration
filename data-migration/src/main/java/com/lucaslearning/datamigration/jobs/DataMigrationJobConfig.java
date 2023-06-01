package com.lucaslearning.datamigration.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
		return new JobBuilder("oddOrEvenJob", jobRepository)
				.start(migratePersonStep)
				.next(migrateBankDataStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}

}
