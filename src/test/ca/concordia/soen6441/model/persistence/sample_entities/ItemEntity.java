package test.ca.concordia.soen6441.model.persistence.sample_entities;

import java.io.Serializable;
import javax.persistence.*;
import test.ca.concordia.soen6441.model.persistence.sample_entities.GameObjectEntity;

/**
 * This is a mock class which represents an item. It is created for testing
 * persistent functionality
 * @author Saman Saadi
 *
 */
@Entity
//@Table(name="ITEM")
public class ItemEntity extends GameObjectEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public ItemEntity(int id, String name)
	{
		super(id, name);
	}
	public ItemEntity() {
		super();
	}
   
}
