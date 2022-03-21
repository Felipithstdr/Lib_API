package br.com.mbs.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mbs.entidades.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController(value="API para manipulacao de livros da biblioteca")
@RequestMapping("Livros")
@Api(description="Api de livros")
public class BookController {   
	Map<Integer, Book> mapBook = new HashMap<Integer, Book>();
	Integer counter = 1;
	
	@ApiOperation(value = "Save a book")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Book saved sucessfully", response = String.class),
			@ApiResponse(code = 405, message = "Problem validating the book", response = String.class),
			@ApiResponse(code = 500, message = "Error in the server")
	})
	@RequestMapping(value = "/save/", method = RequestMethod.POST)	 
	public ResponseEntity<Integer> saveUser(
			@RequestParam("Title: ") String title,
			@RequestParam("Author: ") String author,
			@RequestParam("Synopsis: ") String synopsis,
			@RequestParam("Year Book: ") Integer yearBook,
			@RequestParam("Number Pages: ") Integer numPage)
			throws Exception {		 
		
		System.out.println("Saving Book...");
		
		Book book = new Book();
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
	
//	@RequestMapping(value = "/salvar2/", method = RequestMethod.POST, 
//			 consumes ={MediaType.APPLICATION_JSON})	 
//	  public ResponseEntity<String> salvarUsuario2(
//			  @RequestBody Usuario usuario)
//			   throws Exception {
//		 
//		 System.out.println("Processando salvarUsuario2");
//		 
//		 usuario.id = contador;
//		 	
//		 // error de negocio
//		 if(usuario.cep.equals("")) {
//			 return new ResponseEntity<>("Error no cep", HttpStatus.METHOD_NOT_ALLOWED); 
//		 }
//		 
//		 mapaUsuario.put(contador, usuario);
//		 
//		 contador++;
//		 
//		 return ResponseEntity.ok(usuario.id.toString());
//	  }
	
	@ApiOperation(value = "Return all the books by id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "returned sucessfully", response = String.class)
	})
	@RequestMapping(value = "/search-book-id/{id}", method = RequestMethod.GET)	 
	public ResponseEntity<Book> search(
			@PathVariable("id") Integer id) 
			throws Exception {		 
		System.out.println("Searching book id...");
		return ResponseEntity.ok(mapBook.get(id));
	}	
	
	 @RequestMapping(value = "/list-books/", method = RequestMethod.GET)	 
	 public ResponseEntity<List<Book>> listBooks()			  
		     throws Exception {		 
		 
		 System.out.println("Searching list of books ");
		 ArrayList<Book> list = new ArrayList<>(mapBook.values());
		 
		 
		 return ResponseEntity.ok(list);
	  }
	 

	
	
}