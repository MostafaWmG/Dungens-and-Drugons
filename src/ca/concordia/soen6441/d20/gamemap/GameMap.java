package ca.concordia.soen6441.d20.gamemap;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Set;



import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.element.Entery;
import ca.concordia.soen6441.d20.gamemap.element.Exit;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;
import ca.concordia.soen6441.d20.gamemap.element.Wall;
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
			place(entity.createModel(), entity.getLocation(), false);
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
		setGameObjectAtLocation(location, gameObject, true);
	}
	
	public void setGameObjectAtLocation(Location location,GameObject gameObject, boolean saveEntity) {
		 elements.put(location, gameObject);
		gameObject.setLocation(location);
		gameObject.setField(this);
		if (saveEntity)
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
		place(element, location, true);
	}
	
	private void place(GameObject element, Location location, boolean saveEntity) {
		if(location.getX() < 0 || location.getX() >= getWidth()) return;
		if(location.getY() < 0 || location.getY() >= getHeight()) return;
		
		if(field[location.getY()][location.getX()] != EMPTY) return;
		
		field[location.getY()][location.getX()] = element;
		setGameObjectAtLocation(location, element, saveEntity);		
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
	
	/**
	 * check if map is valid or not 
	 * @return if map is valid or not
	 */
	public boolean mapValidator(){
		Map<Location, Wall> walls = new HashMap<Location, Wall>();
		Map<Location, Entery> enterDoor = new HashMap<Location, Entery>();
		Map<Location, Exit> exitDoor = new HashMap<Location, Exit>();
		
		for(Map.Entry<Location,GameObject> mapElement : elements.entrySet()){
			if(mapElement.getValue().getTag().equals("Wall")){
				walls.put(mapElement.getKey(),(Wall) mapElement.getValue());
			}else if (mapElement.getValue().getTag().equals("Enter") ) {
				enterDoor.put(mapElement.getKey(),(Entery) mapElement.getValue());
			}else if (mapElement.getValue().getTag().equals("Exit")){
				exitDoor.put(mapElement.getKey(), (Exit)mapElement.getValue());	
			}
		}
		
		if(enterDoor.size() == 0){
			System.out.println("not a valid map it has no entery door");
			return false;
		}else if (enterDoor.size() >= 2){
			System.out.println("not a valid map it has more than one entery door");
			return false;
		}
		
		if(exitDoor.size() == 0){
			System.out.println("not a valid map it has no exit door");
			return false;
		}else if (exitDoor.size() >=2){
			System.out.println("not a valid map it has more than one exit door");
			return false;
		}
		
		//copy map
		Map<Location,GameObject> mapCopy = new HashMap<Location, GameObject>();
		for(Map.Entry<Location,GameObject> mapElement : elements.entrySet()){
			mapCopy.put(mapElement.getKey(), mapElement.getValue());
			System.out.println("map element : " +mapCopy.get(mapElement.getKey()).getTag()+" key: " + mapElement.getKey() );
		}
		
		Set<Location> enterPointSet = enterDoor.keySet();
		Location enterPoint =(Location)enterPointSet.toArray()[0];
		
		
		return exploreAlgorithm(mapCopy,enterPoint);

	}
	
	/**
	 * recursive Algorithm that we use to explore map 
	 * @param map copy of the original map
	 * @param enterPoint the enter door
	 * @return return if map is valid or not
	 */
	private boolean exploreAlgorithm(Map<Location, GameObject> map,Location enterPoint){
		
		map.get(enterPoint).setTag("Wall");
		Location currentLocation = new Location(enterPoint.getX(),enterPoint.getY());
		Location finderUp = new Location(enterPoint.getX(),enterPoint.getY());
		Location finderDown = new Location(enterPoint.getX(),enterPoint.getY());
		Location finderRight = new Location(enterPoint.getX(),enterPoint.getY());
		Location finderLeft = new Location(enterPoint.getX(),enterPoint.getY());
		List<Location> conditionList = new ArrayList<Location>();
		boolean valid = false;
		
		return explore(finderUp, finderDown, finderRight, finderLeft, currentLocation, map,(ArrayList<Location>)conditionList,valid);	
	}
	
	/**
	 * function which use by explore algorithm to find out if there is a way between enteryPoint to exitPoint or not
	 * @param finderUp move currentPositoion to up in map
	 * @param finderDown move currentPositoion to down in map
	 * @param finderRight move currentPositoion to right in map
	 * @param finderLeft move currentPositoion to left in map
	 * @param currentLocation current Position in map start from entry point
	 * @param map copy of the main map
	 * @param conditionList list of the positions that represent more than one patch. function will check each patch recursively .
	 * @param valid map is valid or not
	 * @return if map is valid or not
	 */
	public boolean explore(Location finderUp,Location finderDown,Location finderRight,Location finderLeft, Location currentLocation,Map<Location,GameObject> map,ArrayList<Location> conditionList,boolean valid){

		if(valid)
			return true;

		finderUp.setY(currentLocation.getY() - 1);
		finderUp.setX(currentLocation.getX());

		finderDown.setY(currentLocation.getY() + 1);
		finderUp.setX(currentLocation.getX());

		finderRight.setX(currentLocation.getX() + 1);
		finderRight.setY(currentLocation.getY());

		finderLeft.setX(currentLocation.getX() - 1);
		finderRight.setY(currentLocation.getY());

		int conditon = 0;
		boolean up = false;
		boolean down = false;
		boolean right = false;
		boolean left = false;

		if(finderUp.getY() >= 0){
			if(map.get(finderUp).getTag().equals("Exit")){
				valid = true;
			}else if(map.get(finderUp).getTag().equals("Ground") ||map.get(finderUp).getTag().equals("Enemy") || map.get(finderUp).getTag().equals("Item") || map.get(finderUp).getTag().equals("Player")){
				conditon ++ ;
				up =true;
			}

		}
		if(finderDown.getY() < getHeight()){
			if(map.get(finderDown).getTag().equals("Exit")){
				valid = true;
			}else if(map.get(finderDown).getTag().equals("Ground") ||map.get(finderDown).getTag().equals("Enemy") || map.get(finderDown).getTag().equals("Item") || map.get(finderDown).getTag().equals("Player")){
				conditon ++ ;
				down = true;
			}
		}
		if(finderRight.getX() < getWidth()){
			if(map.get(finderRight).getTag().equals("Exit")){
				valid = true;
			}else if(map.get(finderRight).getTag().equals("Ground") ||map.get(finderRight).getTag().equals("Enemy") || map.get(finderRight).getTag().equals("Item") || map.get(finderRight).getTag().equals("Player")){
				conditon ++ ;
				right = true;
			}
		}
		if(finderLeft.getX() >= 0){
			if(map.get(finderLeft).getTag().equals("Exit")){
				valid = true;
			}else if(map.get(finderLeft).getTag().equals("Ground") ||map.get(finderLeft).getTag().equals("Enemy") || map.get(finderLeft).getTag().equals("Item") || map.get(finderLeft).getTag().equals("Player")){
				conditon ++ ;
				left = true;
			}
		}
		
//		System.out.println("Conditon: " + conditon);
		if(!valid){
			if(conditon == 0 ){
				map.get(currentLocation).setTag("Wall");
				if(conditionList.size() != 0){
					currentLocation = conditionList.get(0);
					conditionList.remove(0);
					return explore(finderUp, finderDown, finderRight, finderLeft, currentLocation, map,conditionList,valid);	
				}else{
//					System.out.println("false");
					return false;
				}


			}else if (conditon == 1){
				map.get(currentLocation).setTag("Wall");
				if(up){
					currentLocation.setY(finderUp.getY());
					return explore(finderUp, finderDown, finderRight, finderLeft, currentLocation, map,conditionList,valid);
				}else if(down){
					currentLocation.setY(finderDown.getY());
					return explore(finderUp, finderDown, finderRight, finderLeft, currentLocation, map,conditionList,valid);
				}else if(right){
					currentLocation.setX(finderRight.getX());
					return explore(finderUp, finderDown, finderRight, finderLeft, currentLocation, map,conditionList,valid);
				}else if (left){
					currentLocation.setX(finderLeft.getX());
					return explore(finderUp, finderDown, finderRight, finderLeft, currentLocation, map,conditionList,valid);
				}

			}else if (conditon >=2){
				conditionList.add(currentLocation);
				map.get(currentLocation).setTag("Condition");
				if(up){
					currentLocation.setY(finderUp.getY());
					return explore(finderUp, finderDown, finderRight, finderLeft, currentLocation, map,conditionList,valid);
				}else if(down){
					currentLocation.setY(finderDown.getY());
					return explore(finderUp, finderDown, finderRight, finderLeft, currentLocation, map,conditionList,valid);
				}else if(right){
					currentLocation.setX(finderRight.getX());
					return explore(finderUp, finderDown, finderRight, finderLeft, currentLocation, map,conditionList,valid);
				}else if (left){
					currentLocation.setX(finderLeft.getX());
					return explore(finderUp, finderDown, finderRight, finderLeft, currentLocation, map,conditionList,valid);
				}
			}
		}
		return explore(finderUp, finderDown, finderRight, finderLeft, currentLocation, map, conditionList, valid);

	}
	
}