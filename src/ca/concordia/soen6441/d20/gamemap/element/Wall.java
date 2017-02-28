package ca.concordia.soen6441.d20.gamemap.element;

import ca.concordia.soen6441.d20.common.Location;

public class Wall extends GameObject {

	private WallEntity wallEntity;
	/**
	 * This constructor creates a wall object with specified x and y coordinates
	 * @param x x-coordinate of the wall
	 * @param y y-coordinate of the wall
	 */
	public Wall(int x , int y){
		initEmptyWallEntity();
		setLocation(new Location(x, y));
	}
	
	public Wall(Location location){
		initEmptyWallEntity();
		setLocation(location);
	}
	
	public Wall(){		
		initEmptyWallEntity();
		setLocation(new Location(0, 0));
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
	
	private void initEmptyWallEntity()
	{
		setWallEntity(new WallEntity());
		init();
	}
	
	private void init()
	{
		setTag("Wall");
	}

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
