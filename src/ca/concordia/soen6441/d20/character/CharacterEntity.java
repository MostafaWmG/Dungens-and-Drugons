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
	
	public int level;
	protected List <Item> wearItems;

	public CharacterEntity() {
		super();
		level = 1;
		wearItems = new ArrayList<Item>();
		
	}
   public GameObject getModel(){
	   return null;
   }
   
   
}
