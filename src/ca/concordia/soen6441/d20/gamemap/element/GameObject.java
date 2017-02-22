package ca.concordia.soen6441.d20.gamemap.element;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;

public class GameObject {
	protected Location location;
	protected GameMap field;
	protected String tag ;
	
	public GameObject(Location location) {
		this.location = location;
		tag = "Ground";
	}
	
	public GameObject() {
		this(0, 0);
		tag = "Ground";
	}
	
	public GameObject(int x, int y) {
		this(new Location(x,y));
		tag = "Ground";
	}
	
	public Location getLocation() {
		return location;
	}
	
	public String getTag(){
		return tag;
	}
	
	public void setTag(String tag){
		this.tag = tag;
	}
}
