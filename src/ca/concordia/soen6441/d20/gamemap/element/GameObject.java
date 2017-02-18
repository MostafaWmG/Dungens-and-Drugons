package ca.concordia.soen6441.d20.gamemap.element;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;

public class GameObject {
	protected Location location;
	protected GameMap field;
	
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
	
}
