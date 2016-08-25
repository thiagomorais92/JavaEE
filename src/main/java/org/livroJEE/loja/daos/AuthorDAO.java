package org.livroJEE.loja.daos;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.livroJEE.loja.models.Author;


@Dependent
public class AuthorDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Author> list() {
		return manager.createQuery("select a from Author a order by a.name asc", Author.class).getResultList();
	}
}
