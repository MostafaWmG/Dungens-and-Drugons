package ca.concordia.soen6441.d20.gamemap.element;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;

public abstract class GameObject {

	/**
	 * current selected field. Note that we don't store it in database.
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
	
	public String getTag(){
		return tag;
	}
	
	public void setTag(String tag){
		this.tag = tag;
	}
	
	/**
	 * @return the field
	 */
	public GameMap getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(GameMap field) {
		this.field = field;
	}

	
	/**
	 * @return the location
	 */
	public Location getLocation() {
		return getEntity().getLocation();
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		getEntity().setLocation(location);
	}
	
	public String getName()
	{
		return getEntity().getName();
	}
	
	public void setName(String name)
	{
		getEntity().setName(name);
	}
}
