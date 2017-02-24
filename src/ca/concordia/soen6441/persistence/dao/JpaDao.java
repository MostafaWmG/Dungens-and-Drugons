/**
 * 
 */
package ca.concordia.soen6441.persistence.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * @author Saman Saadi
 *
 */
public class JpaDao<T, PK extends Serializable> implements IGenericDao<T, PK> {
		
	static final EntityManagerFactory emf; 
	static final EntityManager em;
	
	protected Class<T> type;
	
	static
	{
		emf = Persistence.createEntityManagerFactory("dungeons");
		em  = emf.createEntityManager();
	}
	
	public JpaDao(Class<T> type)
	{
		this.type = type;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}	

	@Override
	public T create(T t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.flush();
		return t;
	}

	@Override
	public T read(PK id) {
		return em.find(type, id);
	}

	@Override
	public T update(T t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.flush();
		return t;
	}

	@Override
	public void delete(T t) {
		t = update(t);
		em.remove(t);		
	}


}
