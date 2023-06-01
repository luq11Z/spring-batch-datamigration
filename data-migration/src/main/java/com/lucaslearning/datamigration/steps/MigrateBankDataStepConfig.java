package com.lucaslearning.datamigration.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.lucaslearning.datamigration.domain.BankData;

@Configuration
public class MigrateBankDataStepConfig {

	/**
	 * 
	 * @param jobRepository
	 * @param transactionManager
	 * @param bankDataFileReader BankData reader.
	 * @param bankWriter BankData writer.
	 * @return Returns the Step of reading and writing BankData data.
	 */
	@Bean
	public Step migrateBankDataStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
			ItemReader<BankData> bankDataFileReader, ItemWriter<BankData> bankWriter) {
		return new StepBuilder("migrateBankDataStep", jobRepository)
				.<BankData, BankData>chunk(1, transactionManager)
				.reader(bankDataFileReader)
				.writer(bankWriter)
				.build();
	}

}
