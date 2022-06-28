package br.com.mbs.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.ResponseEntity;


public class BuyBook extends Books{

	private Integer id, quantity;
	//private Books books;
	private String datePurchase = new SimpleDateFormat("dd-mm-yyyy").format(new Date());
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getDatePurchase() {
		return datePurchase;
	}
	public void datePurchase(String datePurchase) {
		this.datePurchase = datePurchase;
	}
	
}
