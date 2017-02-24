package ca.concordia.soen6441.d20.gamemap.element;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;

public abstract class GameObject {
	// TODO location : to be reFactored
	protected Location location;
	protected GameMap field;
	protected String tag ;
	
	public GameObject(Location location) {
		this.location = location;
	}
	
	public GameObject() {
		this(0, 0);		
	}
	
	public GameObject(int x, int y) {
		this(new Location(x,y));
	}
	
	public Location getLocation() {
		return location;
	}
	
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
}
