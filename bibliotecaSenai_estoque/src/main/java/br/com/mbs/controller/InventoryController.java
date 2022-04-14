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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController(value="API para verificação de estoque de livros")
@RequestMapping("Estoque de livros")
@Api(description="Api de estoque livros")
public class InventoryController {
	
	private Map<Integer,Integer> mapInventory = new HashMap();// chaveProduto,quantidade
	 
	 
	 @ApiOperation(value = "Verifica se existe um produto no estoque")
	 @ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Sucesso na verificacao do estoque"),
		    @ApiResponse(code = 204, message = "Produto nao encontrado")
	 })
	 @RequestMapping(value = "/verificar-estoque", method = RequestMethod.GET, produces="application/json")	 
	  public ResponseEntity<Boolean> verifyInventory(Integer id_book,Integer quantity) throws Exception {		 
		 System.out.println("Processando verificarEstoque");
		 if(mapInventory.containsKey(id_book)) { 
			 return new ResponseEntity<>(processaVerificarEstoque(id_book,quantity),HttpStatus.OK);	 
		 }else {
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 }
		 
	  }
	 	 
	 
	 @ApiOperation(value = "Atualiza um produto no estoque")
	 @ApiResponses(value = {
			    @ApiResponse(code = 200, message = "Sucesso na atualizacao do estoque")			    
			})
	 @RequestMapping(value = "/atualizar-estoque", method = RequestMethod.GET, produces="application/json")	 
	  public ResponseEntity<Boolean> atualizarEstoque(Integer id_book,Integer quantity) throws Exception {		 
		 System.out.println("Processando atualizarEstoque");
		 if(mapInventory.containsKey(id_book)) {
			 Integer quantidadeAtual = mapInventory.get(id_book);
			 Integer novaQuantidade = quantidadeAtual - quantity;
			 mapInventory.put(id_book, novaQuantidade < 0 ? quantidadeAtual : novaQuantidade);
		 }else {
			 mapInventory.put(id_book, quantity);
		 }
		 return  new ResponseEntity<>(true,HttpStatus.OK);	 
		 
	  }
	 
	 private boolean processaVerificarEstoque(Integer id_book,Integer quantity) {		 		 
		 boolean retorno = false;
		 if(quantity != 0) {
			Integer quantidadeAtual = mapInventory.get(id_book);
			retorno = (quantity - quantidadeAtual) <= 0;	  
		 }
		 return retorno;
		 
	 }
}