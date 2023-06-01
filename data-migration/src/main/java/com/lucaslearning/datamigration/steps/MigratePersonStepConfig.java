package com.lucaslearning.datamigration.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.lucaslearning.datamigration.domain.Person;

@Configuration
public class MigratePersonStepConfig {

	/**
	 * 
	 * @param jobRepository
	 * @param transactionManager
	 * @param personFileReader   Person reader.
	 * @param personWriter       Person writer.
	 * @return Returns the Step of reading and writing Person data.
	 */
	@Bean
	public Step migratePersonStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
			ItemReader<Person> personFileReader, ClassifierCompositeItemWriter<Person> personWriterClassifier,
			FlatFileItemWriter<Person> invalidPersonFileWriter) {
		return new StepBuilder("migratePersonStep", jobRepository)
				.<Person, Person>chunk(10000, transactionManager)
				.reader(personFileReader)
				.writer(personWriterClassifier) // enable choosing between writers (invalid/valid)
				.stream(invalidPersonFileWriter) // write to a file invalid entries
				.build();
	}

}
