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

	public Ground(Location location)
	{
		super(location);
		init();
	}
	
	public Ground()
	{
		super();
		init();
	}
	
	public Ground(int x, int y)
	{
		super(x, y);
		init();
	}
	
	private void init()
	{
		setTag("Ground");
		setGroundEntity(new GroundEntity());
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
