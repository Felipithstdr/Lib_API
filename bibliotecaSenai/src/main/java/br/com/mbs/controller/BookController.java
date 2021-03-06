package br.com.mbs.controller;
import br.com.mbs.data.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.mbs.entidades.Books;
import br.com.mbs.entidades.BuyBook;
import br.com.mbs.repositorio.InventoryRepository;
import br.com.mbs.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController(value="API for manipulating library")
@RequestMapping("Books")
@Api(description="Book API")
public class BookController {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
    private JobLauncher jobLauncher;

	@Autowired
    private Job processJob;
	
	//ListData listData = new ListData();
		
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
				String dataRegister = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
				book.setDateRegister(dataRegister);
				if( book.getTitle().equals(null)    || book.getTitle().equals("")    || book.getAuthor().equals(null)   || book.getAuthor().equals("")   || book.getNumPage() == null || book.getNumPage().equals("") || 
					book.getPrice().equals(null)    || book.getPrice().equals("")    || book.getSynopsis().equals(null) || book.getSynopsis().equals("") ||
					book.getYearBook().equals(null) || book.getYearBook().equals("") || book.getQuantity().equals(null) || book.getQuantity().equals("") ) {
					
					System.out.println("All data must be fillout! ");
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}else {
					book.setId_book(counter);	
					bookService.getMapBook().put(counter, book);
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
				bookService.showList();
				ArrayList<Books> list = new ArrayList<>(bookService.getMapBook().values());
				 
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
					if(bookService.getMapBook().containsKey(id_book)) {
						System.out.println("Book found");
						return ResponseEntity.ok(bookService.getMapBook().get(id_book));
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
				String datePurchase = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
				buyBook.datePurchase(datePurchase);;
				if(id.equals(null) || id.equals("")) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}else {
					if(bookService.getMapBook().containsKey(id)) {

						ResponseEntity<Boolean> verifyInventoryRE =  inventoryRepository.verifyInventory(id, buyBook.getQuantity());
						Boolean existeInventario = verifyInventoryRE.getBody();
						System.out.println("exteInventario " +  existeInventario);

						if(existeInventario) {
							System.out.println("Book found");
							buyBook.setId(buyCounter);
							//listData.getMapBuyBook().put(buyCounter, buyBook);
							Map<Integer, BuyBook> myMap = bookService.getMapBuyBook();
							myMap.put(buyCounter, buyBook);

							buyCounter++;
							inventoryRepository.atualizarEstoque(id, buyBook.getQuantity());
							return ResponseEntity.ok(buyBook.getId());
						}
						else {
							return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
						}											
					}
					else {
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
		ArrayList<BuyBook> listPurchase = new ArrayList<>(bookService.getMapBuyBook().values());
		return ResponseEntity.ok(listPurchase);
	}
	
	//------------------------------------------------------------------------- EXECUTE BUY LOT
	@RequestMapping(value = "/execute-buy-lot/", method = RequestMethod.GET)	 
	public ResponseEntity<Books> executeBuyLot() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{		 
					System.out.println("Processing execute buy lot ");
					JobParameters jobParameters = new JobParametersBuilder()
							.addLong("time", System.currentTimeMillis())
			                .toJobParameters();
			        jobLauncher.run(processJob, jobParameters); 
					return new ResponseEntity<>(HttpStatus.OK);
		}
}