package ca.concordia.soen6441.d20.gamemap.element;

import ca.concordia.soen6441.d20.common.Location;
/**
 * This class implements Entry of the game map
 * @author Saman Saadi
 *
 */

public class Entery extends GameObject{
	
	private EnteryEntity enteryEntity;

//	/**
//	 * 
//	 * @param name to set for the game element
//	 * @param x to set as x-coordinate of the entry object
//	 * @param y to set as y-coordinate of the entry object
//	 */
//	public  Entery(String name,int x , int y){
//		setEnteryEntity(new EnteryEntity());
//		init();
//		setName(name);
//		setLocation(new Location(x, y));
//	}
//	
//	/**
//	 * 
//	 * @param name to set for the game element
//	 * @param location to set for the entry object
//	 */
//	public  Entery(String name ,Location location){
//		setEnteryEntity(new EnteryEntity());
//		init();
//		setName(name);
//		setLocation(location);
//	}

	/**
	 * 
	 * @param name creates entry object with the given name
	 */
	public  Entery(String name){
		setEnteryEntity(new EnteryEntity());
		init();
		setName(name);
//		setLocation(new Location(0, 0));
	}
	
	/**
	 * We usually use this constructor for loading data from database
	 * @param entity
	 */
	public Entery(EnteryEntity entity)
	{
		setEnteryEntity(entity);
		init();
	}
	/**
	 * this method invokes setTag to set the tag
	 */
	private void init()
	{
		setTag("Enter");
	}

	@Override
	public GameObjectEntity getEntity() {
		// TODO Auto-generated method stub
		return enteryEntity;
	}

	/**
	 * @return the enteryEntity
	 */
	public EnteryEntity getEnteryEntity() {
		return enteryEntity;
	}

	/**
	 * @param enteryEntity the enteryEntity to set for entery object
	 */
	public void setEnteryEntity(EnteryEntity enteryEntity) {
		this.enteryEntity = enteryEntity;
	}


}
