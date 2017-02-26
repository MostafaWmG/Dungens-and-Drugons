package ca.concordia.soen6441.d20.common;

import javax.persistence.*;

/**
 * this class represents the location in the map where an element is situated.
 * @author alvaro
 *
 */
@Embeddable
public class Location {
	private int x;
	private int y;
	
	public Location()
	{
		x = 0;
		y = 0;
	}
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
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
