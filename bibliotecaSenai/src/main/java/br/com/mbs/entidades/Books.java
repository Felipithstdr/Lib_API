package br.com.mbs.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Books {
	
	private Integer id_book, numPage, yearBook, quantity;
	private String title, author, synopsis;
	private Double price;
	private String data =  new SimpleDateFormat("dd/MM/yyyy").format(new Date()); 

	public Integer getId_book() {
		return id_book;
	}
	public void setId_book(Integer id_book) {
		this.id_book = id_book;
	}
	public Integer getNumPage() {
		return numPage;
	}
	public void setNumPage(Integer numPage) {
		this.numPage = numPage;
	}
	public Integer getYearBook() {
		return yearBook;
	}
	public void setYearBook(Integer yearBook) {
		this.yearBook = yearBook;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
