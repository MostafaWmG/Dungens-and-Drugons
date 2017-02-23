package ca.concordia.soen6441.d20.gamemap;

import java.util.HashMap;
import java.util.Map;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.exceptions.MoveNotValidException;

/** 
 * this class represents a Map where different game element can be located
 * @author alvaro
 *
 */
public class GameMap {
	public static final GameObject EMPTY = null;
	/**
	 * used to instantiate the map and easily position its elements
	 */
	private GameObject [][]field;
	/**
	 * used to store the map element by location
	 */
	private Map<Location, GameObject> elements;
	
	private int width;
	private int height;
	private String mapName;
	
	/**
	 * creates a map of the dimension required
	 * @param width the width of the map
	 * @param height the height of the map
	 */
	public GameMap(String mapName,int width, int height) {
		this.width = width;
		this.height = height;
		this.mapName = mapName;
		field = new GameObject[height][width];
		
		elements = new HashMap<>();
		
		emptyMap();
	}
	
	public GameObject getGameObjectAtLocation(Location location) {
		return elements.get(location);
	}
	
	public void setGameObjectAtLocation(Location location,GameObject gameObject) {
		 elements.put(location, gameObject);
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * @return the mapName
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * @param mapName the mapName to set
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	
	/**
	 * this class allows us to place a GameObject into the map
	 * if the position is alredy used, no element is placed, 
	 * the same hapens if the location is out the map boundaries.
	 * @param element element to be add to the map
	 * @param location locaton where the element must be placed
	 */
	public void place(GameObject element, Location location) {
		if(location.getX() < 0 || location.getX() >= width) return;
		if(location.getY() < 0 || location.getY() >= height) return;
		
		if(field[location.getY()][location.getX()] != EMPTY) return;
		
		field[location.getY()][location.getX()] = element;
		elements.put(location, element);
	}
	
	/** move an element from one point of the map to the other, 
	 * if the final position is not empty, the original position is empty,
	 * or the destination is not empty an MoveNotValidException is thrown.
	 * 
	 * @param originX x coordinate where the element currently is
	 * @param originY y coordinate where the element currently is
	 * @param destinationX x coordinate where the element wants to be
	 * @param destinationY y coordinate where the element wants to be
	 * @throws MoveNotValidException if the movement can't be done
	 */
	public void move(int originX, int originY, int destinationX, int destinationY){
		if(! moveCanBeDone(originX, originY, destinationX, destinationY)) throw new MoveNotValidException();
		
		this.field[destinationY][destinationX] = this.field[originY][originX];
		this.field[originY][originX] = EMPTY;
		GameObject object = this.elements.remove(new Location(originX, originY));
		if(object != null) this.elements.put(new Location(destinationX, destinationY), object);

	}
	
	private boolean moveCanBeDone(int originX, int originY, int destinationX, int destinationY) {
		if(originX<0 || originY<0 || destinationX<0 ||destinationY<0) return false;
		
		if(originX >= width || destinationX >= width ) return false;
		
		if(originY >= height || destinationY >= height ) return false;
		
		if(this.field[originY][originX] == EMPTY) return false;
		
		if(this.field[destinationY][destinationX] != EMPTY) return false;
		return true;
	}
	
	private void emptyMap() {
		for(int i=0; i<this.height; i++){
			for(int j=0; j<this.width; j++) {
				this.field[i][j] = EMPTY;
			}
		}
	}
}