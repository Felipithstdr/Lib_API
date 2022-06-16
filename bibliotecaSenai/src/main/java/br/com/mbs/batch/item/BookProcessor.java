package br.com.mbs.batch.item;

import org.springframework.batch.item.ItemProcessor;

import br.com.mbs.entidades.Books;

public class BookProcessor implements ItemProcessor<Books, Books>{

	@Override
	public Books process(Books item) throws Exception {
		System.out.println("Processor " + item);
		// logica de negocio (processaemnto)
		item.setTitle(item.getTitle().toUpperCase());
		
		return item;
	}

}
