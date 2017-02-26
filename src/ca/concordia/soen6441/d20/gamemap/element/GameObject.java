package ca.concordia.soen6441.d20.gamemap.element;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;

public abstract class GameObject {	
	
	/**
	 * All GameObject sub-classes must implement this method. By using this
	 * method GameMap is able to extract entities for saving data.
	 * @return Corresponding entity
	 */
	public abstract GameObjectEntity getEntity();
	
	public String getTag(){
		return getEntity().getTag();
	}
	
	public void setTag(String tag){
		getEntity().setTag(tag);
	}
	
	/**
	 * @return the field
	 */
	public GameMap getField() {
		return getEntity().getField();
	}

	/**
	 * @param field the field to set
	 */
	public void setField(GameMap field) {
		getEntity().setField(field);
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
