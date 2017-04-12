package ca.concordia.soen6441.d20.gamemap.element;



import java.util.Observable;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;
/**
 * This abstract class represents common features of game elements. Its subclasses are:
 * Character,Player,Wall,Ground,Exit and Entry.
 * @author Saman Saadi
 */
public abstract class GameObject extends Observable {

	/**
	 * current selected field. Note that it is not stored in database.
	 */
	private GameMap field;
	private String tag ;
	
	private Location location;
	
	/**
	 * All GameObject sub-classes must implement this method. By using this
	 * method GameMap is able to extract entities for saving data.
	 * @return Corresponding entity
	 */
	public abstract GameObjectEntity getEntity();
	
	/**
	 * 
	 * @return tag that is assigned to an object of the class
	 */
	public String getTag(){
		return tag;
	}
	
	/**
	 * 
	 * @param tag that is needed to be set for an object of the class
	 */
	public void setTag(String tag){
		this.tag = tag;
	}
	
	/**
	 * @return the field of the game element
	 */
	public GameMap getField() {
		return field;
	}

	/**
	 * @param field the field to set for the game element
	 */
	public void setField(GameMap field) {
		this.field = field;
	}

	
	/**
	 * @return the location of the game element
	 */
	@Deprecated
	public Location getLocation() {
		//throw new RuntimeException("Don't use this function");
		return this.location;
	}

	/**
	 * @param location of the element to be set
	 */
	//TODO Remove this function
	@Deprecated
	public void setLocation(Location location) {
		this.location = location;
		//throw new RuntimeException("Don't use this function");
	}
	
	/**
	 * 
	 * @return name of the game element
	 */
	public String getName()
	{
		return getEntity().getName();
	}
	
	/**
	 * 
	 * @param name to set for game character
	 */
	public void setName(String name)
	{
		getEntity().setName(name);
	}
}
