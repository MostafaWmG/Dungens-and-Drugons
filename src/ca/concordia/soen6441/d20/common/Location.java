package ca.concordia.soen6441.d20.common;

import javax.persistence.*;

/**
 * this class represents the location in the map where an element is located.
 * @author alvaro
 * Each element of the game has a coordination on the map
 * x: is the x-coordination of the element
 * y: is the y-coordination of the element
 */
@Embeddable

public class Location {
	private int x;
	private int y;
	
	/**
	 * set the default value for x and y
	 */
	public Location()
	{
		x = 0;
		y = 0;
	}
	
	/**
	 * 
	 * @param x coordination of the element to set
	 * @param y coordination of the element to set
	 */
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @return x coordination of the element
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @param x to set as x coordination of the element
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * 
	 * @return y coordination of the element
	 */
	public int getY() {
		return y;
	}

	/**
	 * 
	 * @param y to set as y coordination of the element
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * check to see if 2 objects are on the same location
	 */
	@Override
	public boolean equals(Object other) {
		if (other == this) return true;
		
		if(!(other instanceof Location)) return false;
		
		Location otherLocation = (Location) other;
		
		return otherLocation.getX() == this.getX() && otherLocation.getY() == this.getY();
	}
	
	@Override
	public int hashCode() {
		return String.format("%d:%d", this.getX(), this.getY()).hashCode();
	}
	
}
