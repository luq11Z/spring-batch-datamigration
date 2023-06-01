package com.lucaslearning.datamigration.writers;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lucaslearning.datamigration.domain.Person;

@Configuration
public class PersonClassifierWriter {

	@Bean
	public ClassifierCompositeItemWriter<Person> personWriterClassifier(JdbcBatchItemWriter<Person> personWriter,
			FlatFileItemWriter<Person> invalidPersonFileWriter) {
		return new ClassifierCompositeItemWriterBuilder<Person>()
				.classifier(classifier(personWriter, invalidPersonFileWriter))
				.build();
	}

	private Classifier<Person, ItemWriter<? super Person>> classifier(JdbcBatchItemWriter<Person> personWriter,
			FlatFileItemWriter<Person> invalidPersonFileWriter) {
		return new Classifier<Person, ItemWriter<? super Person>>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public ItemWriter<? super Person> classify(Person classifiable) {
				if (classifiable.isValid()) {
					return personWriter;
				} else {
					return invalidPersonFileWriter;
				}
			}
		};
	}

}
