package br.com.mbs.entidades;

import java.util.ArrayList;
import java.util.List;

public class Books {
	public Integer id, numPage, yearBook;
	public String title, author, synopsis;
	
	public Books(Integer id, Integer numPage, Integer yearBook, String title, String author, String synopsis) {
		this.id = id;
		this.numPage = numPage;
		this.yearBook = yearBook;
		this.title = title;
		this.author = author;
		this.synopsis = synopsis;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	
}
