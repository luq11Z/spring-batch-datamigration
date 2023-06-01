package com.lucaslearning.datamigration.writers;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.lucaslearning.datamigration.domain.Person;

@Configuration
public class InvalidPersonFileWriterConfig {

	@Bean
	public FlatFileItemWriter<Person> invalidPersonFileWriter() {
		return new FlatFileItemWriterBuilder<Person>()
				.name("invalidPersonFileWriter")
				.resource(new FileSystemResource("files/pessoas_invalidas.csv"))
				.delimited()
				.names("id")
				.build();
	}
	
}
