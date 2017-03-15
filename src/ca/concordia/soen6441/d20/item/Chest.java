package ca.concordia.soen6441.d20.item;

import java.util.ArrayList;
import java.util.List;

import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;

/**
 * this is a container for items.
 * @author wmg
 *
 */
public class Chest extends GameObject {
	
	private ChestEntity chestEntity;
	
	protected List<Item> chestItems;
	
	public Chest() {
		setChestItems(new ArrayList<Item>());
	}
	
	/**
	 * We usually use this constructor to load data
	 * @param chestEntity entity we are using to load data
	 */
	public Chest(ChestEntity chestEntity){
		chestItems = new ArrayList<Item>();
	}
	
	@Override
	public GameObjectEntity getEntity() {
		return chestEntity;
	}
	/**
	 * @return the chestItems
	 */
	public List<Item> getChestItems() {
		return chestItems;
	}
	/**
	 * @param chestItems the chestItems to set
	 */
	public void setChestItems(List<Item> chestItems) {
		this.chestItems = chestItems;
		getChestEntity().getChestItems().clear();
		for (Item item : chestItems)
			getChestEntity().getChestItems().add(item.getItemEntity());
	}

	/**
	 * @return the chestEntity
	 */
	public ChestEntity getChestEntity() {
		return chestEntity;
	}

	/**
	 * @param chestEntity the chestEntity to set
	 */
	public void setChestEntity(ChestEntity chestEntity) {
		this.chestEntity = chestEntity;
	}

}
