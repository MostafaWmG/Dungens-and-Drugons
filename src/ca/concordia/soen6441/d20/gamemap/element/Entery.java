package ca.concordia.soen6441.d20.gamemap.element;

import ca.concordia.soen6441.d20.common.Location;

public class Entery extends GameObject{
	
	private EnteryEntity enteryEntity;

	public  Entery(int x , int y){
		setEnteryEntity(new EnteryEntity());
		init();
		setLocation(new Location(x, y));
	}
	
	public  Entery(Location location){
		setEnteryEntity(new EnteryEntity());
		init();
		setLocation(location);
	}

	public  Entery(){
		setEnteryEntity(new EnteryEntity());
		init();
		setLocation(new Location(0, 0));
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
	 * @param enteryEntity the enteryEntity to set
	 */
	public void setEnteryEntity(EnteryEntity enteryEntity) {
		this.enteryEntity = enteryEntity;
	}


}
