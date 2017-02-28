/**
 * 
 */
package ca.concordia.soen6441.d20.gamemap.element;

import ca.concordia.soen6441.d20.common.Location;

/**
 * @author Saman Saadi
 *
 */
public class Ground extends GameObject {
	
	private GroundEntity groundEntity;

	public Ground(String name,Location location)
	{
		initEmptyEntity();
		setName(name);
		groundEntity.setLocation(location);
		
	}
	
	public Ground(String name)
	{
		initEmptyEntity();
		setName(name);
		groundEntity.setLocation(new Location(0, 0));
	}
	
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
	
	private void initEmptyEntity()
	{
		setGroundEntity(new GroundEntity());
		init();
	}
	
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
