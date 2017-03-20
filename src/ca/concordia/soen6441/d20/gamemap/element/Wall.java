package ca.concordia.soen6441.d20.gamemap.element;
/**
 * This class creates walls that are on the game map. 
 * They are considered as obstacles for player to go to certain coordinations.
 */



public class Wall extends GameObject {

	private WallEntity wallEntity;
	
	/**
	 * This consturctor creates a wall and assigns only name of it.
	 * The relative location will be set to (0,0)
	 * @param name
	 */
	public Wall(String name){		
		initEmptyWallEntity();
		setName(name);
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
