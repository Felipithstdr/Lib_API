package br.com.mbs.controller;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mbs.entidades.Inventory;
import io.swagger.annotations.Api;

@RestController(value="API para verificação de estoque de livros")
@RequestMapping("Estoque de livros")
@Api(description="Api de estoque livros")
public class InventoryController {
	
	Map<String, Inventory> mapaInventory = new HashMap<String, Inventory>();
	
	public InventoryController() {
		// TODO Auto-generated constructor stub
		Inventory inventory = new Inventory();
		mapaInventory.put("20", inventory);
		
		Inventory inventory2 = new Inventory();
		mapaInventory.put("25", inventory2);
	}
	
	@RequestMapping(value = "/{inventory}", method = RequestMethod.GET)	 
	public ResponseEntity<Boolean> verifyInventory(
			@PathVariable("inventory") String inventory
			) throws Exception {		 
		System.out.println("Processando verifica estoque");
		
		Boolean verifyInventory = mapaInventory.containsKey(inventory); 
		
		return ResponseEntity.ok(verifyInventory);
	}
	
	@RequestMapping(value = "/busca-cep/{cep}", method = RequestMethod.GET)	 
	public ResponseEntity<Inventory> buscaCep(
			@PathVariable("cep") String cep
			) throws Exception {		 
		System.out.println("Processando busca-cep");
		
		Inventory objCep = mapaCep.get(cep); 
		
		return ResponseEntity.ok(objCep);
	}
}