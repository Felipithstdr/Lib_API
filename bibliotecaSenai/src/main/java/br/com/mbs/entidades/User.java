package br.com.mbs.entidades;

public class User {
	private int id;
	private String name;
	private Integer books;
	
	public void rentBook(Books books) {
		System.out.println(books);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBooks() {
		return books;
	}
	public void setBooks(Integer integer) {
		this.books = integer;
	}
	
	
}
