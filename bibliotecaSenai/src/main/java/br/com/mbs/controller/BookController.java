package br.com.mbs.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import br.com.mbs.entidades.Books;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController(value="API para manipulacao de uma biblioteca")
@RequestMapping("Livros")
@Api(description="Api de livros")
public class BookController {
	
	
	Map<Integer, Books> mapBook = new HashMap<Integer, Books>();
	Integer counter = 1;
	
	
	@ApiOperation (value = "Cadastro de de livros")
	@ApiResponses(value = {
			@ApiResponse(code=200 , message="O livro foi salvado com sucesso!")

	})
	@RequestMapping(value = "/book/", method = RequestMethod.POST, produces = "application/json")	 
	public ResponseEntity<Integer> saveBook(
			@RequestBody Books book)

			throws Exception {		 
		System.out.println("Processing saveBook...");
		
		book.id = counter;
		
		mapBook.put(counter, book);
		
		counter++;
		
		return ResponseEntity.ok(book.id);
	}
	
	@RequestMapping(value = "/list-books/", method = RequestMethod.GET)	 
	public ResponseEntity<List<Books>> listBooks()			  
		    throws Exception {		 
		 
		System.out.println("Searching list of books ");
		ArrayList<Books> list = new ArrayList<>(mapBook.values());
		 
		 
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)	 
	public ResponseEntity<Books> search(
			@PathVariable("id") Integer id) 
			throws Exception {		 
		System.out.println("Processing search id...");
		return ResponseEntity.ok(mapBook.get(id));
	}
	
	@RequestMapping(value = "/buyBook/{id}", method = RequestMethod.PUT)	 
	public ResponseEntity<Books> buyBook(@PathVariable("id") Integer id)

			throws Exception {		 
				System.out.println("Processing buyBook...");

				
				if(mapBook.containsKey(id)) {
					
					System.out.println("Found book");			
				}else {
					System.out.println("Don't found book");		
				}

				
				return ResponseEntity.ok(mapBook.get(id));
		
			}
}