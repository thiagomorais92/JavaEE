package org.livroJEE.loja.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.livroJEE.loja.daos.BookDAO;
import org.livroJEE.loja.models.Book;

@Model
public class AdminListBooksBean {

	
	@Inject
	private BookDAO bookDAO;
	
	private List<Book> books = new ArrayList<Book>(0);
	
	@PostConstruct
	private void loadObjects(){
		this.books = bookDAO.list();
	}

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	
}
