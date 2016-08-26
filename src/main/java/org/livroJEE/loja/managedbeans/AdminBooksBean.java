package org.livroJEE.loja.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.livroJEE.loja.daos.AuthorDAO;
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
	@Inject 
	private AuthorDAO authorDAO;
	private Book product = new Book();
	private List<Integer> selectedAuthorsIds = new ArrayList<>(0);
	
	private List<Author> authors = new ArrayList<Author>(0);
	
	@PostConstruct
	private void loadResources(){
		this.authors = authorDAO.list();
	}
	
	
	@Transactional
	public String save(){
		populateBookAuthor();
		bookDao.save(product);
		clearObjects();
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		 facesContext.getExternalContext().getFlash().setKeepMessages(true);
		 facesContext.addMessage("messages", new FacesMessage("Livro gravado com sucesso."));
		return "/produtos/lista?faces-redirect=true";
	}

	private void clearObjects() {
		this.product = new Book();
		this.selectedAuthorsIds.clear();
		
	}


	private void populateBookAuthor() {
		System.out.println(selectedAuthorsIds+ "=====" );
 		selectedAuthorsIds.stream().map( (strId) -> {
 			return new Author(strId);
 		}).forEach(product :: add);
 	}

	public Book getProduct() {
		return product;
	}

	public List<Integer> getSelectedAuthorsIds() {
		return selectedAuthorsIds;
	}

	public void setSelectedAuthorsIds(List<Integer> selectedAuthorsId) {
		this.selectedAuthorsIds = selectedAuthorsId;
	}


	public List<Author> getAuthors() {
		return authors;
	}


	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	
}
