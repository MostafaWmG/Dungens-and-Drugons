package ca.concordia.soen6441.persistence.dao.interfaces;

import java.io.Serializable;
import java.util.List;

public interface INamedEntityDao<T, PK extends Serializable> extends IGenericDao<T, PK> {
	
	List<T> findByName(String name);
	List<T> findall();

}
