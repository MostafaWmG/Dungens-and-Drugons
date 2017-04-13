package ca.concordia.soen6441.d20.gamemap;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;













import ca.concordia.soen6441.controller.Game;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.gamemap.element.Entery;
import ca.concordia.soen6441.d20.gamemap.element.Exit;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectInstance;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectInstanceEntity;
import ca.concordia.soen6441.d20.gamemap.element.Ground;
import ca.concordia.soen6441.d20.gamemap.element.Wall;
import ca.concordia.soen6441.d20.gamemap.exceptions.MoveNotValidException;
import ca.concordia.soen6441.d20.item.Chest;
import ca.concordia.soen6441.d20.item.Item;

/** 
 * this class represents a Map where different game element can be located
 * @author alvaro
 * @author Saman Saadi
 *
 */
public class GameMap extends Observable{
	public static final GameObjectInstance EMPTY = null;
	/**
	 * used to instantiate the map and easily position its elements
	 */
	private GameObjectInstance [][]field;
	/**
	 * used to store the map element by location
	 */
	private Map<Location, GameObjectInstance> elements;		
	/**
	 * @return the elements
	 */
	@Deprecated
	public Map<Location, GameObject> getElements() {
		throw new RuntimeException("Don't use this function");
	}

	/**
	 * @param elements the elements to set
	 */
	@Deprecated
	public void setElements(Map<Location, GameObject> elements) {
		throw new RuntimeException("Don't use this function");
	}

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
		for (GameObjectInstanceEntity entity : getGameMapEntity().getGameObjectInstances())
			place(entity.createModel(this), entity.getLocation(), false);
	}
	
	private void init()
	{
		//TODO usually the first dimension is width
		field = new GameObjectInstance[getGameMapEntity().getHeight()][getGameMapEntity().getWidth()];		
		elements = new HashMap<>();		
		emptyMap();
	}
	/**
	 * 
	 * @return GameObjectEntity to load from database
	 */
	public GameMapEntity getEntity()
	{
		return getGameMapEntity();
	}
	
	@Deprecated
	public GameObject getGameObjectAtLocation(Location location) {
		throw new RuntimeException("Don't use this function");
	}
	
	/**
	 * 
	 * @param location of objects on the map
	 * @return elements that are on the given location
	 */
	public GameObjectInstance getGameObjectInstanceAtLocation(Location location)
	{
		return elements.get(location);
	}
	
	//TODO Remove this function
	@Deprecated
	public void setGameObjectAtLocation(Location location,GameObject gameObject) {
		setGameObjectAtLocation(location, gameObject, true);
	}
	
	/**
	 * 
	 * @param location to set the object
	 * @param GameObjectInstance that is desired to locate on the specified location
	 */
	public void setGameObjectInstanceAtLocation(Location location,GameObjectInstance instance) {
		setGameObjectAtLocation(location, instance, true);
	}
	

	//TODO Remove this
	@Deprecated
	public void setGameObjectAtLocation(Location location,GameObject gameObject, boolean saveEntity) {
		throw new RuntimeException("Don't use this function");
	}
	/**
	 * 
	 * @param location location to set the object
	 * @param instance that is desired to locate on the specified location
	 * @param saveEntity true if we want to save the object on database
	 */
	public void setGameObjectAtLocation(Location location,GameObjectInstance instance, boolean saveEntity) {
		 elements.put(location, instance);
		instance.setLocation(location);
		if (saveEntity)
			getGameMapEntity().addGameObjectInstanceEntity(instance.getEntity());
	}
	/**
	 * 
	 * @return width of the game map
	 */
	public int getWidth() {
		return getGameMapEntity().getWidth();
	}
	/**
	 * 
	 * @param width to set for the game map
	 */
	public void setWidth(int width)
	{
		getGameMapEntity().setWidth(width);
	}
	
	/**
	 * 
	 * @return height of the game map
	 */
	public int getHeight() {
		return getGameMapEntity().getHeight();
	}
	
	/**
	 * 
	 * @param height to set for the game map
	 */
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

	//TODO Remove this
	@Deprecated
	public void place(GameObject element, Location location) {
		place(element, location, true);
	}
	
	/**
	 * this method allows us to place a GameObject into the map
	 * if the position is alredy used, no element is placed, 
	 * the same hapens if the location is out the map boundaries.
	 * @param GameObjectInstance instance to be add to the map
	 * @param location locaton where the element must be placed
	 */
	public void place(GameObjectInstance instance, Location location)
	{
		place(instance, location, true);
	}
	
	//TODO Remove this
	@Deprecated
	private void place(GameObject element, Location location, boolean saveEntity) {
		throw new RuntimeException("Don't use this function");
	}
	
	/**
	 * this method allows us to place a GameObject into the map
	 * if the position is already used, no element is placed, 
	 * the same happens if the location is out the map boundaries.
	 * @param instance game object instance to be added to the map
	 * @param location location where the element must be placed
	 * @param saveEntity true if we want to save the object to database
	 */
	private void place(GameObjectInstance instance, Location location, boolean saveEntity)
	{
		if(location.getX() < 0 || location.getX() >= getWidth()) return;
		if(location.getY() < 0 || location.getY() >= getHeight()) return;
		
		if(field[location.getY()][location.getX()] != EMPTY) return;
		
		field[location.getY()][location.getX()] = instance;
		setGameObjectAtLocation(location, instance, saveEntity);
	}
	
	public boolean move(int originX, int originY, int destinationX, int destinationY,Game game){
		return move(originX, originY, destinationX, destinationY, game,true);
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
	public boolean move(int originX, int originY, int destinationX, int destinationY,Game game,boolean isMainCharacter){
		if(! moveCanBeDone(originX, originY, destinationX, destinationY)) return false;
		Location origin = new Location(originX, originY);
		Location destination = new Location(destinationX, destinationY);
		boolean friendly = false;
		
		if(!isMainCharacter && getGameObjectInstanceAtLocation(origin).getGameObject().getTag().equals("Player")){
			friendly = true;
		}
		
		System.out.println("TAG IN MOVE: " + getGameObjectInstanceAtLocation(destination).getGameObject().getTag());
		if(getGameObjectInstanceAtLocation(destination).getGameObject().getTag().equals("Ground")){
			setGameObjectInstanceAtLocation(destination, getGameObjectInstanceAtLocation(origin));
			setGameObjectInstanceAtLocation(origin, new GameObjectInstance(new Ground(getMapName()+originX+originY+"dontduplicate"), this));
			
			if(isMainCharacter){
				game.setCurrentLocation(destination);
				setChanged();
				notifyObservers(this);
			}else if(friendly){
				game.getGameView().updateWithOutObserver(origin, destination,true);
				updateLocation(game,origin,destination);
			}else{
				game.getGameView().updateWithOutObserver(origin, destination);
				updateLocation(game,origin,destination);
			}

		}else if(getGameObjectInstanceAtLocation(destination).getGameObject().getTag().equals("Chest")){
			Chest chest = (Chest)getGameObjectInstanceAtLocation(destination).getGameObject();
			chest.putRootedIntoBackPack((Fighter)getGameObjectInstanceAtLocation(origin).getGameObject());
			setGameObjectInstanceAtLocation(destination, getGameObjectInstanceAtLocation(origin));
			setGameObjectInstanceAtLocation(origin, new GameObjectInstance(new Ground(getMapName()+originX+originY+"dontduplicate"), this));
			
			if(isMainCharacter){
				game.setCurrentLocation(destination);
				setChanged();
				notifyObservers(this);
			}else if (friendly){
				game.getGameView().updateWithOutObserver(origin, destination,true);
				updateLocation(game,origin,destination);
			}else{
				game.getGameView().updateWithOutObserver(origin, destination);
				updateLocation(game,origin,destination);
			}
		}else if(getGameObjectInstanceAtLocation(destination).getGameObject().getTag().equals("Player")){
			if(isMainCharacter){
				game.createViewExchange((Fighter)getGameObjectInstanceAtLocation(destination).getGameObject(), (Fighter)getGameObjectInstanceAtLocation(origin).getGameObject());
			}else if (friendly){
				System.out.println("EXCHANGE ITEM BETWEEN FRIENDLY");
			}else{
				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				Date dateobj = new Date();
				Fighter fighter =(Fighter) getGameObjectInstanceAtLocation(destination).getGameObject();
				Chest chest = new Chest(fighter.getName() +df.format(dateobj));
				putFromFighterToChest(fighter,chest);
				setGameObjectInstanceAtLocation(destination,new GameObjectInstance(chest, this));
				game.getGameView().updateWithOutObserver(origin, destination);
				game.die(fighter);
			}

		}else if(getGameObjectInstanceAtLocation(destination).getGameObject().getTag().equals("Enemy")){
			if(isMainCharacter){
				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				Date dateobj = new Date();
				Fighter fighter =(Fighter) getGameObjectInstanceAtLocation(destination).getGameObject();
				
				Fighter attacker =(Fighter)getGameObjectInstanceAtLocation(origin).getGameObject();
				attacker.attack(fighter);
				
				Chest chest = new Chest(fighter.getName() +df.format(dateobj));
				putFromFighterToChest(fighter,chest);
				setGameObjectInstanceAtLocation(destination,new GameObjectInstance(chest, this));
				game.setCurrentLocation(destination);
				setChanged();
				notifyObservers(this);
				game.die(fighter);
			}else if(friendly){
				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				Date dateobj = new Date();
				Fighter fighter =(Fighter) getGameObjectInstanceAtLocation(destination).getGameObject();
				Chest chest = new Chest(fighter.getName() +df.format(dateobj));
				putFromFighterToChest(fighter,chest);
				setGameObjectInstanceAtLocation(destination,new GameObjectInstance(chest, this));
				game.setCurrentLocation(destination);
				game.getGameView().updateWithOutObserver(origin, destination,true);
				game.die(fighter);
			}else{
//				game.createViewExchange((Fighter)getGameObjectInstanceAtLocation(destination).getGameObject(), (Fighter)getGameObjectInstanceAtLocation(origin).getGameObject());
				System.out.println("ITEM EXCHANGE BETWEEN ENEMY");
			}

		}else if(getGameObjectInstanceAtLocation(destination).getGameObject().getTag().equals("Exit")){
			if(isMainCharacter)
				game.reset();
		}
		return true;

	}
	
	private void updateLocation(Game game,Location origin,Location destination){
		for(Map.Entry<Fighter,Location> location : game.getLocations().entrySet()){
			if(location.getValue().equals(origin)){
				location.setValue(destination);
				System.out.println("LOCATION OF NPC UPDATED: " + destination.getX() + " : " + destination.getY());
			}
		}	
	}
	
	private void putFromFighterToChest(Fighter fighter,Chest chest){
		for(int i = 0 ; i < fighter.getWearItems().size() ; i++){
			Item refItem = fighter.getWearItems().get(i);
			if(!chest.isNullItem(refItem)){
				chest.addItem(refItem);
			}	
		}
		
		for(int i = 0 ; i < fighter.getBackPack().size(); i++){
			Item refItem = fighter.getBackPack().get(i);
			if(!chest.isNullItem(refItem)){
				chest.addItem(refItem);
			}	
		}
	}
	/**
	 * 
	 * @param originX x coordinate where the element currently is
	 * @param originY y coordinate where the element currently is
	 * @param destinationX x coordinate where the element wants to be
	 * @param destinationY y  coordinate where the element wants to be
	 * @return true if the element can move to the destination
	 */
	public boolean moveCanBeDone(int originX, int originY, int destinationX, int destinationY) {
		if(originX<0 || originY<0 || destinationX<0 ||destinationY<0) {
			System.out.println("debug1");
			return false;
		}
		
		if(originX >= getWidth() || destinationX >= getWidth() ){
			System.out.println("debug2");
			return false;
		}
		
		if(originY >= getHeight() || destinationY >= getHeight() ){
			System.out.println("debug3");
			return false;
		} 
		
		
		Location destination = new Location(destinationX, destinationY);
		
		if(getGameObjectInstanceAtLocation(destination).getGameObject().getTag().equals("Wall")){
			System.out.println("debug4");
			return false;
		}

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
	 * this method checks if map is valid or not 
	 * @return if map is valid or not
	 */
	public boolean mapValidator(){
		Map<Location, Wall> walls = new HashMap<Location, Wall>();
		Map<Location, Entery> enterDoor = new HashMap<Location, Entery>();
		Map<Location, Exit> exitDoor = new HashMap<Location, Exit>();
		
		for(Map.Entry<Location,GameObjectInstance> mapElement : elements.entrySet()){
			if(mapElement.getValue().getGameObject().getTag().equals("Wall")){
				walls.put(mapElement.getKey(),(Wall) mapElement.getValue().getGameObject());
			}else if (mapElement.getValue().getGameObject().getTag().equals("Enter") ) {
				enterDoor.put(mapElement.getKey(),(Entery) mapElement.getValue().getGameObject());
			}else if (mapElement.getValue().getGameObject().getTag().equals("Exit")){
				exitDoor.put(mapElement.getKey(), (Exit)mapElement.getValue().getGameObject());	
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
		Map<Location,String> mapCopy = new HashMap<Location, String>();
		for(Map.Entry<Location,GameObjectInstance> mapElement : elements.entrySet()){
			mapCopy.put(mapElement.getKey(), mapElement.getValue().getGameObject().getTag());
		}
		
		Set<Location> enterPointSet = enterDoor.keySet();
		Location enterPoint =(Location)enterPointSet.toArray()[0];
		
		Set<Location> exitPointSet = exitDoor.keySet();
		Location exitPoint =(Location)exitPointSet.toArray()[0];
		
		return exploreAlgorithm(mapCopy,enterPoint,exitPoint);

	}
	
	/**
	 * recursive Algorithm that we use to explore map 
	 * @param map copy of the original map
	 * @param enterPoint the enter door
	 * @return return if map is valid or not
	 */
	private boolean exploreAlgorithm(Map<Location, String> map,Location enterPoint,Location exitPoint){
		
		Location currentLocation;
		
		if(enterPoint.getX() > exitPoint.getX() && enterPoint.getY() < exitPoint.getY()){
			map.put(enterPoint, "Exit");
			map.put(exitPoint, "Wall");
			currentLocation = new Location(exitPoint.getX(),exitPoint.getY());
		}else{
			map.put(enterPoint, "Wall");
			currentLocation = new Location(enterPoint.getX(),enterPoint.getY());
		}
		
		Location finderUp = new Location(currentLocation.getX(),currentLocation.getY());
		Location finderDown = new Location(currentLocation.getX(),currentLocation.getY());
		Location finderRight = new Location(currentLocation.getX(),currentLocation.getY());
		Location finderLeft = new Location(currentLocation.getX(),currentLocation.getY());
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
	public boolean explore(Location finderUp,Location finderDown,Location finderRight,Location finderLeft, Location currentLocation,Map<Location,String> map,ArrayList<Location> conditionList,boolean valid){

		if(valid)
			return true;

		finderUp.setY(currentLocation.getY() - 1);
		finderUp.setX(currentLocation.getX());

		finderDown.setY(currentLocation.getY() + 1);
		finderDown.setX(currentLocation.getX());

		finderRight.setX(currentLocation.getX() + 1);
		finderRight.setY(currentLocation.getY());

		finderLeft.setX(currentLocation.getX() - 1);
		finderLeft.setY(currentLocation.getY());

		int conditon = 0;
		boolean up = false;
		boolean down = false;
		boolean right = false;
		boolean left = false;

		if(finderUp.getY() >= 0){
			if(map.get(finderUp).equals("Exit")){
				valid = true;
			}else if(map.get(finderUp).equals("Ground") ||map.get(finderUp).equals("Enemy") || map.get(finderUp).equals("Item") || map.get(finderUp).equals("Player")){
				conditon ++ ;
				up =true;
			}

		}
		if(finderDown.getY() < getHeight()){
			if(map.get(finderDown).equals("Exit")){
				valid = true;
			}else if(map.get(finderDown).equals("Ground") ||map.get(finderDown).equals("Enemy") || map.get(finderDown).equals("Item") || map.get(finderDown).equals("Player")){
				conditon ++ ;
				down = true;
			}
		}
		if(finderRight.getX() < getWidth()){
			if(map.get(finderRight).equals("Exit")){
				valid = true;
			}else if(map.get(finderRight).equals("Ground") ||map.get(finderRight).equals("Enemy") || map.get(finderRight).equals("Item") || map.get(finderRight).equals("Player")){
				conditon ++ ;
				right = true;
			}
		}
		if(finderLeft.getX() >= 0){
			if(map.get(finderLeft).equals("Exit")){
				valid = true;
			}else if(map.get(finderLeft).equals("Ground") ||map.get(finderLeft).equals("Enemy") || map.get(finderLeft).equals("Item") || map.get(finderLeft).equals("Player")){
				conditon ++ ;
				left = true;
			}
		}
		
		if(!valid){
			if(conditon == 0 ){
				map.put(currentLocation, "Wall");
				if(conditionList.size() != 0){
					currentLocation = conditionList.get(0);
					conditionList.remove(0);
					return explore(finderUp, finderDown, finderRight, finderLeft, currentLocation, map,conditionList,valid);	
				}else{
					return false;
				}


			}else if (conditon == 1){
				map.put(currentLocation, "Wall");
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
				conditionList.add( new Location(currentLocation.getX(), currentLocation.getY()));
				map.put(currentLocation, "Condition");
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