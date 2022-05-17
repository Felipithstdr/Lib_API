package br.com.mbs.repositorio;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.mbs.entidades.Inventory;
import io.swagger.annotations.ApiOperation;

//url: endereco de onde esta o servico.
@FeignClient(value = "bibliotecaSenai_estoque", 
url = "http://localhost:9092/Inventory/",
fallback = inventoryRepositoryFlaw.class)
public interface inventoryRepository {
	
	 @RequestMapping(value = "/verificar-estoque", method = RequestMethod.GET, produces="application/json")	 
	  public ResponseEntity<Boolean> verifyInventory(
			  @RequestParam("id_book") Integer id_book, 
			  @RequestParam("quantidade") Integer quantity) ;
		 	
	 @RequestMapping(value = "/atualizar-estoque", method = RequestMethod.POST, produces="application/json")	 
	  public ResponseEntity<Boolean> atualizarEstoque(
			  @RequestParam("id_book") Integer id_book, 
			  @RequestParam("quantidade") Integer quantity) ;
}