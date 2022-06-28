package br.com.mbs.batch.item;

import org.springframework.batch.item.ItemProcessor;

import br.com.mbs.entidades.BuyBook;


public class BookProcessor implements ItemProcessor<BuyBook, BuyBook> {

	@Override
	public BuyBook process(BuyBook item) throws Exception {
		System.out.println("Processor " + item);
		// logica de negocio (processaemnto)
		Boolean teste = item.getPrice() > 10;
		System.out.print("resposta " + teste);
		if(teste) { 
			return item;
		}
		
		return null;
	}

}
