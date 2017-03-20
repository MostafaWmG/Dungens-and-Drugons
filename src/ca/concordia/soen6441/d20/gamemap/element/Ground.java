/**
 * 
 */
package ca.concordia.soen6441.d20.gamemap.element;

//import ca.concordia.soen6441.d20.common.Location;

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
	 * 
	 * Creates an object of the class using given name
	 * @param name to set for the object
	 */
	public Ground(String name)
	{
		initEmptyEntity();
		setName(name);
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
