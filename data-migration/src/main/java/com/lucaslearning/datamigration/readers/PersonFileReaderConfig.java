package com.lucaslearning.datamigration.readers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import com.lucaslearning.datamigration.domain.Person;

@Configuration
public class PersonFileReaderConfig {

	/**
	 * 
	 * @return Returns the items read in the file.
	 */
	@Bean
	public FlatFileItemReader<Person> personFileReader() {
		return new FlatFileItemReaderBuilder<Person>()
				.name("personFileReader")
				.resource(new FileSystemResource("files/pessoas.csv"))
				.delimited()
				.names("name", "email", "birthDate", "age", "id")
				.addComment("--")
				.fieldSetMapper(fieldSetMapper())
				.build();
	}

	/**
	 * 
	 * @return Returns a custom mapper to map the data read to the Person class.
	 */
	private FieldSetMapper<Person> fieldSetMapper() {
		return new FieldSetMapper<Person>() {
			@Override
			public Person mapFieldSet(FieldSet fieldSet) throws BindException {
				Person person = new Person();
				
				person.setName(fieldSet.readString("name"));
				person.setEmail(fieldSet.readString("email"));
				person.setBirthDate(LocalDate.parse(fieldSet.readString("birthDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				person.setAge(fieldSet.readInt("age"));
				person.setId(fieldSet.readInt("id"));
				
				return person;
			}
		};
	}
	
}
