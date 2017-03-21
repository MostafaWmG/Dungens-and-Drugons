/**
 * 
 * @author Saman Saadi
 */
package ca.concordia.soen6441.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import ca.concordia.soen6441.persistence.dao.interfaces.INamedEntityDao;

/**
 * This generic class is responsible for querying all entities which have a field named "name"
 * @author Saman Saadi
 *
 */
public class JpaNamedEntityDao<T, PK extends Serializable> 
			 extends JpaGenericDao<T, PK> 
			 implements INamedEntityDao<T, PK> {
	
	private Query byNameQuery;
	private Query allQuery;

	public JpaNamedEntityDao(Class<T> type, String byNameQueryName, String allQueryName) {
		super(type);
		setByNameQuery(em.createNamedQuery(byNameQueryName, type));
		setAllQuery(em.createNamedQuery(allQueryName, type));
	}

	/**
	 * find an object in the database by name
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByName(String name) {		
		getByNameQuery().setParameter("name", name);
		return getByNameQuery().getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findall() {
		return getAllQuery().getResultList();
	}

	/**
	 * @return the byNameQuery
	 */
	public Query getByNameQuery() {
		return byNameQuery;
	}

	/**
	 * @param byNameQuery the byNameQuery to set
	 */
	private void setByNameQuery(Query byNameQuery) {
		this.byNameQuery = byNameQuery;
	}

	/**
	 * @return the allQuery
	 */
	private Query getAllQuery() {
		return allQuery;
	}

	/**
	 * @param allQuery the allQuery to set
	 */
	private void setAllQuery(Query allQuery) {
		this.allQuery = allQuery;
	}
	
}
