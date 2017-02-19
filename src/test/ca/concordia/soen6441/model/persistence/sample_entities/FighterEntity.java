/**
 * 
 */
package test.ca.concordia.soen6441.model.persistence.sample_entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This is a mock class which represents a fighter. It is created for testing
 * persistent functionality
 * @author Saman Saadi
 *
 */

@Entity
//@Table(name="FIGHTER")
public class FighterEntity extends GameObjectEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<ItemEntity> items;
	
	/**
	 * @return the items
	 */
	public ArrayList<ItemEntity> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<ItemEntity> items) {
		this.items = items;
	}

	public FighterEntity() {
		super();
		init();
	}
	
	public FighterEntity(int id, String name)
	{
		super(id, name);
		init();
	}
	
	private void init()
	{
		items = new ArrayList<ItemEntity>();
	}
}
