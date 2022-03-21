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

import br.com.mbs.entidades.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController(value = "API para manipulação de usuarios da biblioteca" )
@RequestMapping("Usuarios")
@Api(description="Api de usuarios")
public class UserController {
	Map<Integer, User> mapUser = new HashMap<Integer, User>();
	Integer counter = 1;
	
	@ApiOperation(value = "Save a user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User saved sucessfully", response = String.class),
			@ApiResponse(code = 405, message = "Problem validating the user", response = String.class),
			@ApiResponse(code = 500, message = "Errir ub tge server")
	})
	@RequestMapping(value= "save-user", method = RequestMethod.POST)
	public ResponseEntity<Integer> saveUser(
		@RequestParam("Nome: ") String nome)
 
		throws Exception {
		
		System.out.print("Saving User...");
		
		User user = new User();
		user.setName(nome);
		user.setId(counter);
		
		mapUser.put(counter, user);
		
		counter++;
		
		return ResponseEntity.ok(null);
	}
	
	@ApiOperation(value = "Return all users by id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "returned sucessfully", response = String.class)
	})
	@RequestMapping(value = "/search-user-id/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> searchUserId(
			@PathVariable("id") Integer id)
			throws Exception {
		System.out.println("Searching user id ");
		return ResponseEntity.ok(mapUser.get(id));
		
	}

	@RequestMapping(value = "/list-user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listUser() 
			throws Exception{
		 
		System.out.println("Searching list of users ");
		ArrayList<User> user = new ArrayList<>(mapUser.values());
		 
		return ResponseEntity.ok(user);
		 
	 }

}
