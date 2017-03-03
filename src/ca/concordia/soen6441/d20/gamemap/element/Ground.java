/**
 * 
 */
package ca.concordia.soen6441.d20.gamemap.element;

import ca.concordia.soen6441.d20.common.Location;

/**
 * This class creates an empty cell on the game map
 * @author Saman Saadi
 *
 */
public class Ground extends GameObject {
	/**
	 * groundEntity: it is used to save/load ground properties
	 */
	private GroundEntity groundEntity;

	/**
	 * Creates an object of the class using name and location
	 * @param name to set for the object
	 * @param location to set for the ground object
	 */
	public Ground(String name,Location location)
	{
		initEmptyEntity();
		setName(name);
		groundEntity.setLocation(location);
		
	}
	
	/**
	 * 
	 * Creates an object of the class using given name
	 * @param name to set for the object
	 */
	public Ground(String name)
	{
		initEmptyEntity();
		setName(name);
		groundEntity.setLocation(new Location(0, 0));
	}
	/**
	 * Creates an object of the class using given name and coordinates
	 * @param name to set for the object
	 * @param x to set for the x-coordinate of the ground object
	 * @param y set for the y-coordinate of the ground object
	 */
	public Ground(String name,int x, int y)
	{
		initEmptyEntity();
		setName(name);
		groundEntity.setLocation(new Location(x, y));
	}
	
	/**
	 * We usually use this constructor for loading from database
	 * @param entity
	 */
	public Ground(GroundEntity entity)
	{
		setGroundEntity(entity);
		init();
	}
	
	/**
	 * creates an empty entity for the ground object
	 */
	private void initEmptyEntity()
	{
		setGroundEntity(new GroundEntity());
		init();
	}
	
	/**
	 * sets the tag for the ground object
	 */
	private void init()
	{
		setTag("Ground");
	}
	
	@Override
	public GameObjectEntity getEntity() {
		return groundEntity;
	}

	/**
	 * @return the groundEntity
	 */
	public GroundEntity getGroundEntity() {
		return groundEntity;
	}

	/**
	 * @param groundEntity the groundEntity to set
	 */
	public void setGroundEntity(GroundEntity groundEntity) {
		this.groundEntity = groundEntity;
	}
	

}
