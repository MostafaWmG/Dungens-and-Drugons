package ca.concordia.soen6441.d20.gamemap.element;

/**
 * This abstract class represents common features of game elements. Its subclasses are:
 * Character,Player,Wall,Ground,Exit and Entry.
 * @author Saman Saadi
 */

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;

public abstract class GameObject {

	/**
	 * current selected field. Note that it is not stored in database.
	 */
	private GameMap field;
	//TODO Do we really need tag?
	private String tag ;
	
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
	public Location getLocation() {
		return getEntity().getLocation();
	}

	/**
	 * @param location of the element to be set
	 */
	public void setLocation(Location location) {
		getEntity().setLocation(location);
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
