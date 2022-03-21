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
import br.com.mbs.entidades.User;
import br.com.mbs.entidades.RentBook;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController(value = "API para manipulação de rented books da biblioteca" )
@RequestMapping("RentedBooks")
@Api(description="Api de rented books")
public class RentBookController {

	Map<Integer, RentBook> mapRentBook = new HashMap<Integer, RentBook>();
	Integer counter = 1;
	
	@ApiOperation(value = "User gets a book")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "The Book was rented sucessfully", response = String.class),
			@ApiResponse(code = 405, message = "Problem validating the rent", response = String.class),
			@ApiResponse(code = 500, message = "Error in the server")
	})
	@RequestMapping(value = "/save-rent/", method = RequestMethod.POST)	 
	public ResponseEntity<Integer> saveRent(
			@RequestParam("User: ") User user,
			@RequestParam("Book: ") Book book)

			throws Exception {		 
		
		System.out.println("Saving Book...");
		
		if(user.getId() == 1) {
			System.out.println("User name" + user.getName());			
		}
		
		
		RentBook rent = new RentBook();
		rent.setUser(user);
		rent.setBook(book);
		rent.setId(counter);

		
		mapRentBook.put(counter, rent);
		
		counter++;
		
		return ResponseEntity.ok(book.getId());
	}
	
	
	@ApiOperation(value = "Return all the rents by id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "returned sucessfully", response = String.class)
	})
	@RequestMapping(value = "/search-rented-book-id/{id}", method = RequestMethod.GET)	 
	public ResponseEntity<RentBook> searchRentBook(
			@PathVariable("id") Integer id) 
			throws Exception {		 
		System.out.println("Searching rented book id...");
		return ResponseEntity.ok(mapRentBook.get(id));
	}	
	
	 @RequestMapping(value = "/list-rented-books/", method = RequestMethod.GET)	 
	 public ResponseEntity<List<RentBook>> listBooks()			  
		     throws Exception {		 
		 
		 System.out.println("Searching list of books ");
		 ArrayList<RentBook> list = new ArrayList<>(mapRentBook.values());
		 
		 
		 return ResponseEntity.ok(list);
	  }
	 

	
	
}
