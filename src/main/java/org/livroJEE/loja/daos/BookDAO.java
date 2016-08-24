package org.livroJEE.loja.daos;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.livroJEE.loja.models.Author;
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

	public ArrayList<Author> list() {
		Query query = manager.createQuery("from Author");
		 List<Author> list = new ArrayList<Author>(0);
		 list = query.getResultList();
		return (ArrayList<Author>) list;
	}

}
