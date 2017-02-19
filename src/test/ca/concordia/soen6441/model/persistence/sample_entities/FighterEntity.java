/**
 * 
 */
package test.ca.concordia.soen6441.model.persistence.sample_entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * This is a mock class which represents a fighter. It is created for testing
 * persistent functionality
 * @author Saman Saadi
 *
 */

@Entity
//@Table(name="FIGHTER")
public class FighterEntity extends GameObjectEntity implements Serializable {


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FighterEntity [items=" + items + ", getId()=" + getId() + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name="FIGHTER_ITEMS",
			joinColumns=@JoinColumn(name="MAP_ID", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="OBJECT_ID", referencedColumnName="ID"))
	private List<ItemEntity> items;
	
	/**
	 * @return the items
	 */
	public List<ItemEntity> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<ItemEntity> items) {
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
