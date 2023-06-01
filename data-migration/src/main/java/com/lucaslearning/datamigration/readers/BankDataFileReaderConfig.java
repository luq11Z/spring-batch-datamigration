package com.lucaslearning.datamigration.readers;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.lucaslearning.datamigration.domain.BankData;

@Configuration
public class BankDataFileReaderConfig {

	/**
	 * 
	 * @return Returns the items read in the file.
	 */
	@Bean
	public FlatFileItemReader<BankData> bankDataFileReader() {
		return new FlatFileItemReaderBuilder<BankData>()
				.name("bankDataFileReader")
				.resource(new FileSystemResource("files/dados_bancarios.csv"))
				.delimited()
				.names("personId", "agency", "account", "bank", "id")
				.addComment("--")
				.targetType(BankData.class)
				.build();
	}

}
