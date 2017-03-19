package ca.concordia.soen6441.d20.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;
/**
 * Entity implementation class for Entity: ChestEntity
 * @author wmg
 *
 */
@NamedQueries({
	@NamedQuery(
			name="findChestEntityByName",
			query="SELECT chest FROM ChestEntity chest WHERE chest.name = :name"
			),
	@NamedQuery(
			name="findChestEntityAll",
			query="SELECT chest FROM ChestEntity chest")
})
@Entity
public class ChestEntity extends GameObjectEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name="CHEST_ITEMS",
			joinColumns=@JoinColumn(name="CHEST_ID", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="CHEST_ITEMS_ID", referencedColumnName="ID"))
	private List <ItemEntity> chestItems;

	public ChestEntity() {
		super();
		chestItems = new ArrayList<ItemEntity>();
	}
	
	@Override
	public GameObject createModel() {
		return new Chest(this);
	}
	
	/**
	 * @return the wearItems
	 */
	public List<ItemEntity> getChestItems() {
		return chestItems;
	}
	/**
	 * @param wearItems the wearItems to set
	 */
	public void setChestItems(List<ItemEntity> chestItems) {
		this.chestItems = chestItems;
	}
}
