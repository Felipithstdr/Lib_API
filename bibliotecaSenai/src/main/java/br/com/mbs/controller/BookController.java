package br.com.mbs.controller;
import br.com.mbs.data.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.mbs.entidades.Books;
import br.com.mbs.entidades.BuyBook;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController(value="API for manipulating library")
@RequestMapping("Books")
@Api(description="Book API")
public class BookController {
	
	ListData listData = new ListData();
		
	//contador para ID;
	Integer counter = 1;
	Integer buyCounter = 1;
	
	
	//------------------------------------------------------------------------- INICIO
	@ApiOperation(value = "Register book")
	@ApiResponses(value = {
			@ApiResponse(code=200 , message="Successfully saved book!"),
			@ApiResponse(code=400 , message="Missing or invalid request body!")

	})
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")	 
	public ResponseEntity<Integer> saveBook(@RequestBody Books book)
			throws Exception {	
								
				System.out.println("Processing saveBook...");
				if( book.getTitle().equals(null)    || book.getTitle().equals("")    || book.getAuthor().equals(null)   || book.getAuthor().equals("")   || book.getNumPage() == null || book.getNumPage().equals("") || 
					book.getPrice().equals(null)    || book.getPrice().equals("")    || book.getSynopsis().equals(null) || book.getSynopsis().equals("") ||
					book.getYearBook().equals(null) || book.getYearBook().equals("") || book.getQuantity().equals(null) || book.getQuantity().equals("") ) {
					
					System.out.println("All data must be fillout! ");
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}else {
					book.setId_book(counter);	
					listData.getMapBook().put(counter, book);
					counter++;
					return ResponseEntity.ok(book.getId_book());
				}
	}
	
	//------------------------------------------------------------------------- LIST BOOKS
	@ApiOperation (value = "List book")
	@ApiResponses(value = {
			@ApiResponse(code=200 , message="Book found!"),
			@ApiResponse(code=404 , message="Book not found !")
	})
	@RequestMapping(value = "/list-books/", method = RequestMethod.GET)	 
	public ResponseEntity<List<Books>> listBooks()			  
		    throws Exception {		 
		 
				System.out.println("Searching list of books ");
				listData.showList();
				ArrayList<Books> list = new ArrayList<>(listData.getMapBook().values());
				 
				return ResponseEntity.ok(list);
	}
	
	//------------------------------------------------------------------------- ID_BOOK
	@ApiOperation (value = "List by book's id")
	@ApiResponses(value = {
			@ApiResponse(code=200 , message="Book Found!"),
			@ApiResponse(code=204 , message="Book not found!"),
			@ApiResponse(code=400 , message="Missing or invalid request body!")

	})
	@RequestMapping(value = "/{id_book}", method = RequestMethod.GET)	 
	public ResponseEntity<Books> search(@PathVariable("id_book") Integer id_book) 
			throws Exception {		 
				System.out.println("Processing search id...");
				if(id_book.equals(null) || id_book.equals("")) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				else {
					if(listData.getMapBook().containsKey(id_book)) {
						System.out.println("Book found");
						return ResponseEntity.ok(listData.getMapBook().get(id_book));
					}
					else {
						System.out.println("Book not found");
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
				}
	}
	
	//------------------------------------------------------------------------- BUY BOOK
	@ApiOperation (value = "Buy book")
	@ApiResponses(value = {
			@ApiResponse(code=200 , message="Book inserted for purchase!"),
			@ApiResponse(code=400 , message="Missing or invalid request body!"),
			@ApiResponse(code=204 , message="Book not found!"),

	})
	@RequestMapping(value = "/buyBook/{id}", method = RequestMethod.PUT)	 
	public ResponseEntity<Integer> buyBook(@PathVariable("id") Integer id, @RequestBody BuyBook buyBook)
			throws Exception {		 
				System.out.println("Processing buyBook...");
				
				if(id.equals(null) || id.equals("")) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}else {
					if(listData.getMapBook().containsKey(id)) {
						if(buyBook.getQuantity() == null || buyBook.getQuantity().equals("") ) {
							System.out.println("All data must be fillout! ");
							return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
						}else {
							System.out.println("Book found");
							buyBook.setId(buyCounter);
							listData.getMapBuyBook().put(buyCounter, buyBook);
							buyCounter++;
							return ResponseEntity.ok(buyBook.getId());
						}
					}else {
						System.out.println("Book not found");		
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
				}
	}
	
	//------------------------------------------------------------------------- LIST BUY BOOK
	@ApiOperation(value = "List of the bought booknn")
	@ApiResponses(value = {
			@ApiResponse(code=200 , message="Purchase found"),
			@ApiResponse(code=204 , message="Purchase not found !")
	})
	@RequestMapping(value = "/list-buyBook/", method = RequestMethod.GET)	 
	public ResponseEntity<List<BuyBook>> listBuyBooks()			  
		    throws Exception {		 
		 
		System.out.println("Searching list of buy books ");
		ArrayList<BuyBook> listPurchase = new ArrayList<>(listData.getMapBuyBook().values());
		return ResponseEntity.ok(listPurchase);
	}
	
	//------------------------------------------------------------------------- EXECUTE BUY LOT
	@RequestMapping(value = "/execute-buy-lot/", method = RequestMethod.GET)	 
	public ResponseEntity<Books> executeBuyLot()			  
		    throws Exception {		 
				System.out.println("Processing execute buy lot ");
				return new ResponseEntity<>(HttpStatus.OK);
	}
}