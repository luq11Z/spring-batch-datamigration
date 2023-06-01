package com.lucaslearning.datamigration.writers;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lucaslearning.datamigration.domain.BankData;

@Configuration
public class BankDataWriterConfig {

	@Bean
	public JdbcBatchItemWriter<BankData> bankDataWriter(@Qualifier("appDataSource") DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<BankData>()
				.dataSource(dataSource)
				.sql("INSERT INTO bank_data (id, person_id, agency, account, bank) VALUES (:id, :personId, :agency, :account, :bank)")
				.beanMapped() // used on simple objs
				.build();
	}
	
}
