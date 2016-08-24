package org.livroJEE.loja.managedbeans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.livroJEE.loja.daos.BookDAO;
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
	
	private Book product = new Book();
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
	
	@Transactional
	public void save(){
		bookDao.save(product);
	}

	public Book getProduct() {
		return product;
	}

	
}
