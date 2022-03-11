package br.com.mbs.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mbs.entidades.Books;
import io.swagger.annotations.Api;

@RestController(value="API para manipulacao de uma biblioteca")
@RequestMapping("Livros")
@Api(description="Api de livros")
public class BookController {
	
    
	Map<Integer, Books> mapBook = new HashMap<Integer, Books>();
	Integer counter = 1;
	
	  
	@RequestMapping(value = "/save/", method = RequestMethod.POST)	 
	public ResponseEntity<Integer> saveUser(
			@RequestParam("Title: ") String title,
			@RequestParam("Author: ") String author,
			@RequestParam("Synopsis: ") String synopsis,
			@RequestParam("Year Book: ") Integer yearBook,
			@RequestParam("Number Pages: ") Integer numPage)
			throws Exception {		 
		System.out.println("Processing saveUser...");
		
		Books book = new Books(numPage, numPage, numPage, synopsis, synopsis, synopsis);
		book.setTitle(title);
		book.setAuthor(author);
		book.setSynopsis(synopsis);
		book.setYearBook(yearBook);
		book.setNumPage(numPage);
		book.setId(counter);
		
		mapBook.put(counter, book);
		
		counter++;
		
		return ResponseEntity.ok(book.getId());
	}
	
	@RequestMapping(value = "/search-id/{id}", method = RequestMethod.GET)	 
	public ResponseEntity<Books> search(
			@PathVariable("id") Integer id) 
			throws Exception {		 
		System.out.println("Processing search id...");
		return ResponseEntity.ok(mapBook.get(id));
	}
	
	
	 @RequestMapping(value = "/list/", method = RequestMethod.GET)	 
	 public ResponseEntity<List<Books>> list()			  
		     throws Exception {		 
		 
		 System.out.println("Searching list ");
		 ArrayList<Books> list = new ArrayList<>(mapBook.values());
		 		 
		 return ResponseEntity.ok(list);
	  }
	
	
}