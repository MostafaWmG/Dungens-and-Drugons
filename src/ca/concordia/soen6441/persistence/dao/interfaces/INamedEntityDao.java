package ca.concordia.soen6441.persistence.dao.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * This inteface define new functionality that should be implemented in the underlying persistence technology
 * @author Saman Saadi
 *
 * @param <T>
 * @param <PK>
 */
public interface INamedEntityDao<T, PK extends Serializable> extends IGenericDao<T, PK> {
	
	/**
	 * This method is like read. Instead of primary key, it uses "name" field. It is obvious the record
	 * should have a field named "name"
	 * @param name the value of "name" field that we need to run query against
	 * @return The list of all records with name "name"
	 */
	List<T> findByName(String name);
	/**
	 * find all records
	 * @return the list of all records
	 */
	List<T> findall();

}
