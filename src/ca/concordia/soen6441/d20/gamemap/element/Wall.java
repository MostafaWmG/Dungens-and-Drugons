package ca.concordia.soen6441.d20.gamemap.element;
/**
 * This class creates walls that are on the game map. 
 * They are considered as obstacles for player to go to certain coordinations.
 */

import ca.concordia.soen6441.d20.common.Location;

public class Wall extends GameObject {

	private WallEntity wallEntity;
//	/**
//	 * This constructor creates a wall object with specified x and y coordinates
//	 * @param x x-coordinate of the wall
//	 * @param y y-coordinate of the wall
//	 */
//	public Wall(String name,int x , int y){
//		initEmptyWallEntity();
//		setName(name);
////		setLocation(new Location(x, y));
//	}
//	
//	/**
//	 * This constructor creates a wall object and assigns its name and location.
//	 * @param name to set for the wall
//	 * @param location which the wall will be located
//	 */
//	public Wall(String name,Location location){
//		initEmptyWallEntity();
//		setName(name);
////		setLocation(location);
//	}
	
	/**
	 * This consturctor creates a wall and assigns only name of it.
	 * The relative location will be set to (0,0)
	 * @param name
	 */
	public Wall(String name){		
		initEmptyWallEntity();
		setName(name);
//		setLocation(new Location(0, 0));
	}
	
	/**
	 * We usually use this constructor for loading from database
	 * @param entity
	 */
	public Wall(WallEntity entity)
	{
		setWallEntity(entity);
		init();
	}
	/**
	 * This method creates an emtpy wall entity
	 */
	private void initEmptyWallEntity()
	{
		setWallEntity(new WallEntity());
		init();
	}
	
	/**
	 * Sets the tag for the wall
	 */
	private void init()
	{
		setTag("Wall");
	}

	/**
	 * This method is used to get the value stored in database
	 */
	@Override
	public GameObjectEntity getEntity() {
		// TODO Auto-generated method stub
		return wallEntity;
	}

	/**
	 * @return the wallEntity
	 */
	public WallEntity getWallEntity() {
		return wallEntity;
	}

	/**
	 * @param wallEntity the wallEntity to set
	 */
	public void setWallEntity(WallEntity wallEntity) {
		this.wallEntity = wallEntity;
	}
}
