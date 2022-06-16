package br.com.mbs.batch.item;

import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import br.com.mbs.entidades.Books;

public class BookReader implements ItemReader<Books>{
	
	private Iterator<Books> it ;
	
	private boolean jaBuscado;
	
	 @PostConstruct
	 public void postConstruct() {       
	 }

	@Override
	public Books read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("Reader");
		if(jaBuscado == false) {
			//it = usuarioServico.listar().iterator(); verificar essa parte
			jaBuscado = true;
		}
		while(it.hasNext()) {
			return it.next();
		}
		
		return null;
	}

}
