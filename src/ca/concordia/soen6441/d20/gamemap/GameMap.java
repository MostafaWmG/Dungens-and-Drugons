package ca.concordia.soen6441.d20.gamemap;

import java.util.HashMap;
import java.util.Map;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;
import ca.concordia.soen6441.d20.gamemap.exceptions.MoveNotValidException;

/** 
 * this class represents a Map where different game element can be located
 * @author alvaro
 * @author Saman Saadi
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
	private GameMapEntity gameMapEntity;
	
	/**
	 * creates a map of the dimension required
	 * @param width the width of the map
	 * @param height the height of the map
	 */
	public GameMap(String mapName,int width, int height) {		
		setGameMapEntity(new GameMapEntity());
		setWidth(width);
		setHeight(height);
		setMapName(mapName);
		init();
	}
	
	/**
	 * We usually use this constructor when we want to load from database
	 * @param gameMapEntity
	 */
	public GameMap(GameMapEntity gameMapEntity)
	{
		setGameMapEntity(gameMapEntity);
		initVolatileAttirubtes();
	}
	
	/**
	 * This method is responsible to initialize all attributes which will not persist on database
	 */
	private void initVolatileAttirubtes()
	{
		init();
		for (GameObjectEntity entity : getGameMapEntity().getObjects())
			place(entity.createModel(), entity.getLocation());
	}
	
	private void init()
	{
		//TODO usually the first dimension is width		
		field = new GameObject[getGameMapEntity().getHeight()][getGameMapEntity().getWidth()];		
		elements = new HashMap<>();		
		emptyMap();
	}
	
	public GameMapEntity getEntity()
	{
		return getGameMapEntity();
	}
	
	public GameObject getGameObjectAtLocation(Location location) {
		return elements.get(location);
	}
	
	public void setGameObjectAtLocation(Location location,GameObject gameObject) {		
		 elements.put(location, gameObject);
		gameObject.setLocation(location);
		getGameMapEntity().addGameObjectEntity(gameObject.getEntity());
	}
	
	public int getWidth() {
		return getGameMapEntity().getWidth();
	}
	
	public void setWidth(int width)
	{
		getGameMapEntity().setWidth(width);
	}
	
	public int getHeight() {
		return getGameMapEntity().getHeight();
	}
	
	public void setHeight(int height)
	{
		getGameMapEntity().setHeight(height);
	}
	
	/**
	 * @return the mapName
	 */
	public String getMapName() {
		return getGameMapEntity().getName();
	}

	/**
	 * @param mapName the mapName to set
	 */
	public void setMapName(String mapName) {
		getGameMapEntity().setName(mapName);
	}
	
	
	
	/**
	 * @return the gameMapEntity
	 */
	public GameMapEntity getGameMapEntity() {
		return gameMapEntity;
	}

	/**
	 * @param gameMapEntity the gameMapEntity to set
	 */
	public void setGameMapEntity(GameMapEntity gameMapEntity) {
		this.gameMapEntity = gameMapEntity;
	}

	/**
	 * this class allows us to place a GameObject into the map
	 * if the position is alredy used, no element is placed, 
	 * the same hapens if the location is out the map boundaries.
	 * @param element element to be add to the map
	 * @param location locaton where the element must be placed
	 */
	public void place(GameObject element, Location location) {
		if(location.getX() < 0 || location.getX() >= getWidth()) return;
		if(location.getY() < 0 || location.getY() >= getHeight()) return;
		
		if(field[location.getY()][location.getX()] != EMPTY) return;
		
		field[location.getY()][location.getX()] = element;
		setGameObjectAtLocation(location, element);
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
		if(object != null)
		{
			Location location = new Location(destinationX, destinationY);
			setGameObjectAtLocation(location, object);
		}
		else
			throw new NullPointerException("I cannot find the game object in elements");

	}
	
	private boolean moveCanBeDone(int originX, int originY, int destinationX, int destinationY) {
		if(originX<0 || originY<0 || destinationX<0 ||destinationY<0) return false;
		
		if(originX >= getWidth() || destinationX >= getWidth() ) return false;
		
		if(originY >= getHeight() || destinationY >= getHeight() ) return false;
		
		if(this.field[originY][originX] == EMPTY) return false;
		
		if(this.field[destinationY][destinationX] != EMPTY) return false;
		return true;
	}
	
	private void emptyMap() {
		for(int i=0; i<this.getHeight(); i++){
			for(int j=0; j<this.getWidth(); j++) {
				this.field[i][j] = EMPTY;
			}
		}
	}
}