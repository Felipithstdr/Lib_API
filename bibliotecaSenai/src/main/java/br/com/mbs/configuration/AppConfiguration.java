package br.com.mbs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.mbs.service.BookService;

@Configuration
public class AppConfiguration {

	//@Bean
	public BookService bookService() {
		return new BookService();
	}
}
