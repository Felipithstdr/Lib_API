package br.com.mbs.batch.item;

import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.mbs.entidades.BuyBook;
import br.com.mbs.service.BookService;


public class BookReader implements ItemReader<BuyBook>{
	
	@Autowired
	private BookService bookService;
	
	private Iterator<BuyBook> it ;
	
	private BuyBook buyBook;
	
	@PostConstruct
	public void postConstruct() {				       
	}

	@Override
	public BuyBook read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
System.out.println("Reader");
		
		if(buyBook == null) {
			it = bookService.getMapBuyBook().values().iterator();
		}
			
		while(it.hasNext()) {
			return buyBook = it.next();			
		}	
		buyBook = null;
		return buyBook;
	}

}
