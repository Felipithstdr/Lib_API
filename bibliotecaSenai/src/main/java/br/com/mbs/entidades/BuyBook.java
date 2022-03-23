package br.com.mbs.entidades;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;


public class BuyBook {

	public int id, quantity;
	
	public BuyBook(int id, int  quantity) {
		this.id = id;
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
