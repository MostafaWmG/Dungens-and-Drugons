package ca.concordia.soen6441.d20.gamemap.element;

import ca.concordia.soen6441.d20.common.Location;
/**
 * This class implements exit element of the game map
 * @author negar
 *
 */

public class Exit extends GameObject{
	
	private ExitEntity exitEntity;
	/**
	 * creates exit object with given name and location
	 * @param name to set for the element
	 * @param x to set as x-coordinate of the exit element
	 * @param y to set as y-coordinate of the exit element
	 */
	public Exit(String name,int x , int y){
		setExitEntity(new ExitEntity());
		init();
		setName(name);
		setLocation(new Location(x, y));
	}
	
	/**
	 *  creates exit object with given name and location
	 * @param name to set for the element
	 * @param location for the exit element
	 */
	public Exit(String name,Location location){
		setExitEntity(new ExitEntity());
		init();
		setName(name);
		setLocation(location);
	}
	
	/**
	 * creates exit object with given name
	 * @param name to set for the element
	 */
	public Exit(String name){
		setExitEntity(new ExitEntity());
		init();
		setName(name);
		setLocation(new Location(0,0));
	}
	/**
	 * create exit object from the ExitEntity
	 * @param entity to set for the ExitEntity object
	 */
	public Exit(ExitEntity entity)
	{
		setExitEntity(entity);
		init();
	}
	
	/**
	 * set the exit tag
	 */
	private void init()
	{
		setTag("Exit");
	}
	
	@Override
	public GameObjectEntity getEntity() {
		// TODO Auto-generated method stub
		return exitEntity;
	}

	/**
	 * @return the exitEntity
	 */
	public ExitEntity getExitEntity() {
		return exitEntity;
	}

	/**
	 * @param exitEntity the exitEntity to set
	 */
	public void setExitEntity(ExitEntity exitEntity) {
		this.exitEntity = exitEntity;
	}
}
