package br.com.mbs.repositorio;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//url: endereco de onde esta o servico.
@FeignClient(value = "bibliotecaSenai-estoque", 
				url = "http://localhost:9092/Inventory/"
				)
public interface InventoryRepository {
	
	 @RequestMapping(value = "/verificar-estoque", method = RequestMethod.GET, produces="application/json")	 
	  public ResponseEntity<Boolean> verifyInventory(
			  @RequestParam("id_book") Integer id_book, 
			  @RequestParam("quantity") Integer quantity) ;
		 	
	 @RequestMapping(value = "/atualizar-estoque", method = RequestMethod.POST, produces="application/json")	 
	  public ResponseEntity<Boolean> atualizarEstoque(
			  @RequestParam("id_book") Integer id_book, 
			  @RequestParam("quantity") Integer quantity) ;
}