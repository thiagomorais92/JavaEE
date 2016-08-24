package org.livroJEE.loja.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.livroJEE.loja.daos.BookDAO;
import org.livroJEE.loja.models.Author;
import org.livroJEE.loja.models.Book;

/**
 * Anotação do CDI
 * que indica: os objetos
 * dessa classe estarão no escopo de uma única request,
 * ou seja, estão disponíveis para serem usados na
 * expression Language.
 ***/
@Model 
public class AdminBooksBean {
	
/** 
 * Anotação do CDI
 * que indica: que esse atributo será injetado
 * não precisamos instanciar ele na mão e nem gerenciar
 * conexão.
 * (para que o CDI funcione, é necessário criar o arquivo
 * beans.xml, dentro da pasta WEB-INF [em branco mesmo] )
 ***/
	@Inject
	private BookDAO bookDao;
	private Book product = new Book();
	private List<Integer> selectedAuthorsId = new ArrayList<>(0);
	
	private List<Author> authors = new ArrayList<Author>(0);
	
	@PostConstruct
	public void loadResources(){
		this.authors = bookDao.list();
	}
	
	
	@Transactional
	public void save(){
		populateBookAuthor();
		bookDao.save(product);
	}

	private void populateBookAuthor() {
		 
		selectedAuthorsId.stream().map( (id) -> {
			 return new Author(id);
		 }).forEach((Consumer<? super Author>) product);
	}

	public Book getProduct() {
		return product;
	}

	public List<Integer> getSelectedAuthorsId() {
		return selectedAuthorsId;
	}

	public void setSelectedAuthorsId(List<Integer> selectedAuthorsId) {
		this.selectedAuthorsId = selectedAuthorsId;
	}


	public List<Author> getAuthors() {
		return authors;
	}


	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	
}
