/**
 * 
 */
package ca.concordia.soen6441.persistence.dao;

import java.io.Serializable;

/**
 * @author Saman Saadi
 *
 */
public interface IGenericDao<T, PK extends Serializable> {
	/**
	 * 
	 * @param t
	 * @return
	 */
	T create(T t);
	/**
	 * 
	 * @param id
	 * @return
	 */
	T read(PK id);
	/**
	 * 
	 * @param t
	 * @return
	 */
	T update(T t);
	
	void delete(T t);

}
