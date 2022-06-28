package br.com.mbs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.mbs.entidades.Books;
import br.com.mbs.entidades.BuyBook;


@Service
public class BookService {
	
	//Funcao map, onde salva os dados;
	Map<Integer, Books>   mapBook    = new HashMap<Integer, Books>  ();
	Map<Integer, BuyBook> mapBuyBook = new HashMap<Integer, BuyBook>();
	private Integer counter = 1;
	
	public Integer getCounter() {
		return counter;
	}
	
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
	
	public void add(Books book) {
		book.setId_book(counter);
		mapBook.put(counter, book);		 
		counter++;
	}
	
	public Books serach(Integer id) {
		return mapBook.get(id);
	}
	
	
	public ArrayList<Books> showList(){
		return new ArrayList<>(mapBook.values());
	}

}
