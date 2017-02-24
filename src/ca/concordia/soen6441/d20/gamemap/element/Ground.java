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
	
	private GroundEntity entity;

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
		setEntity(new GroundEntity());
	}
	
	@Override
	public GameObjectEntity getEntity() {
		return entity;
	}
	
	public void setEntity(GroundEntity entity)
	{
		this.entity = entity;
	}

}
