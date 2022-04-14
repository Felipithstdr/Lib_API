package br.com.mbs.data;

import java.util.HashMap;
import java.util.Map;

import br.com.mbs.entidades.Books;
import br.com.mbs.entidades.BuyBook;

public class ListData{
	
	//Funcao map, onde salva os dados;
	Map<Integer, Books>   mapBook    = new HashMap<Integer, Books>  ();
	Map<Integer, BuyBook> mapBuyBook = new HashMap<Integer, BuyBook>();
	
	
	public Map<Integer, Books> getMapBook() {
		return mapBook;
	}
	public void setMapBook(Map<Integer, Books> mapBook) {
		this.mapBook = mapBook;
	}
	
	public Map<Integer, BuyBook> getMapBuyBook() {
		return mapBuyBook;
	}
	public void setMapBuyBook(Map<Integer, BuyBook> mapBuyBook) {
		this.mapBuyBook = mapBuyBook;
	}	
	
//	Books books = new Books();
	public void showList(){		
//		mapBook.values().forEach(b -> System.out.println(b));
		
		 System.out.println(mapBook.get(1).toString());
	}
}

