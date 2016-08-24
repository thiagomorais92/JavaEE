package org.livroJEE.loja.daos;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.livroJEE.loja.models.Book;

/** Essa annotation é o mesmo que não ter annotation, mas é importante 
 * frizar que o escopo do bookDAO seguirá o escopo de onde ele estiver definido.
 * no caso de AdminBooksBean, significa que terá o escopo de uma request pois
 * já foi definido como -> @model 
 *  **/
@Dependent
public class BookDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void save(Book product) {
		manager.persist(product);
	}

}
