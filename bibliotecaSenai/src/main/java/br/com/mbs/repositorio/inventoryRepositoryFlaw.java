package br.com.mbs.repositorio;

import org.springframework.http.ResponseEntity;

public class inventoryRepositoryFlaw implements inventoryRepository {

	@Override
	public ResponseEntity<Boolean> verifyInventory(Integer id_book, Integer quantity) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(true);
	}

	@Override
	public ResponseEntity<Boolean> atualizarEstoque(Integer id_book, Integer quantity) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(true);
	}

}
