/**
 * 
 */
package ca.concordia.soen6441.persistence.dao.interfaces;

import java.io.Serializable;

/**
 * An interface for CRUD operations. The underlying persistence technology should implement this methods
 * @author Saman Saadi
 *
 */
public interface IGenericDao<T, PK extends Serializable> {
	/**
	 * Create a new record into non-volatile storage
	 * @param t the record that should be persist
	 * @return persistent record 
	 */
	T create(T t);
	/**
	 * Read a new record form non-volatile storage
	 * @param id The primary key of the record
	 * @return persistent record
	 */
	T read(PK id);
	/**
	 * Update an existing persisting record
	 * @param t the record that should be persist
	 * @return persistent record
	 */
	T update(T t);
	
	/**
	 * Delete a record from non-volatile storage
	 * @param t The record that should be deleted from the storage
	 */
	void delete(T t);

}
