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
	}
	
	@Override
	public GameObjectEntity getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
