package ca.concordia.soen6441.d20.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;
import ca.concordia.soen6441.d20.item.*;


/**
 * Entity implementation class for Entity: CharacterEntity
 * @author negar
 */
@Entity
public class CharacterEntity extends GameObjectEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//TODO Fighters, armors and some other game objects have level. So I don't think we should put level here
	//Maybe we need to put it in GameObject
	private int level;
	protected List <Item> wearItems;

	public CharacterEntity() {
		super();
		level = 1;
		wearItems = new ArrayList<Item>();
		
	}
   public GameObject getModel(){
	   //TODO
	   return null;
   }
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return the wearItems
	 */
	public List<Item> getWearItems() {
		return wearItems;
	}
	/**
	 * @param wearItems the wearItems to set
	 */
	public void setWearItems(List<Item> wearItems) {
		this.wearItems = wearItems;
	}      
}
