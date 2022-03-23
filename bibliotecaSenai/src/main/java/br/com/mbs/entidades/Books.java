package br.com.mbs.entidades;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;


public class Books {

	public int id, numPage, yearBook, quantity;
	public String title, author, synopsis;
	public double price;
	
	public Books(int id, int  numPage, int  yearBook, int  quantity, String title, String author, String synopsis, double price) {
		this.id = id;
		this.numPage = numPage;
		this.yearBook = yearBook;
		this.quantity = quantity;
		this.title = title;
		this.author = author;
		this.synopsis = synopsis;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumPage() {
		return numPage;
	}
	public void setNumPage(int numPage) {
		this.numPage = numPage;
	}
	public int getYearBook() {
		return yearBook;
	}
	public void setYearBook(int yearBook) {
		this.yearBook = yearBook;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
