package br.com.mbs.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.mbs.batch.item.BookProcessor;
import br.com.mbs.batch.item.BookReader;
import br.com.mbs.batch.item.BookWriter;
import br.com.mbs.entidades.BuyBook;

@Configuration
public class BatchConfiguration {


	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public BookReader bookReader() {
		return new BookReader(); 
	}
	
	@Bean
	public BookProcessor bookProcessor() {
		return new BookProcessor(); 
	}
	
	@Bean
	public BookWriter bookWriter() {
		return new BookWriter(); 
	}
	
	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer())
				.flow(orderStep1()).end().build();
	}

	@Bean
	public Step orderStep1() {
		return stepBuilderFactory.get("orderStep1").
				<BuyBook, BuyBook> chunk(1)
				.reader(bookReader())
				.processor(bookProcessor())
				.writer(bookWriter())
				.build();
	}	
}
