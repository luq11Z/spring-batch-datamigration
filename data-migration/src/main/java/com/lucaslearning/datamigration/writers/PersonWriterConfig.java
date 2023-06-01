package com.lucaslearning.datamigration.writers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lucaslearning.datamigration.domain.Person;

@Configuration
public class PersonWriterConfig {

	
	@Bean
	public JdbcBatchItemWriter<Person> personDataWriter(@Qualifier("appDataSource") DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Person>()
				.dataSource(dataSource)
				.sql("INSERT INTO person (id, name, email, birth_date, age) VALUES (?, ?, ?, ?, ?)")
				.itemPreparedStatementSetter(itemPreparedStatementSetter()) // used in complex objs (dates, lists...)
				.build();
	}

	private ItemPreparedStatementSetter<Person> itemPreparedStatementSetter() {
		return new ItemPreparedStatementSetter<Person>() {
			@Override
			public void setValues(Person item, PreparedStatement ps) throws SQLException {
				ps.setInt(1, item.getId());
				ps.setString(2, item.getName());
				ps.setString(3, item.getEmail());
				ps.setDate(4, Date.valueOf(item.getBirthDate()));
				ps.setInt(5, item.getAge());
			}
		};
	}
}
