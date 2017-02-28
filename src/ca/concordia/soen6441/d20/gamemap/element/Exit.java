package ca.concordia.soen6441.d20.gamemap.element;

import ca.concordia.soen6441.d20.common.Location;

public class Exit extends GameObject{
	
	private ExitEntity exitEntity;
	
	public Exit(String name,int x , int y){
		setExitEntity(new ExitEntity());
		init();
		setName(name);
		setLocation(new Location(x, y));
	}
	
	public Exit(String name,Location location){
		setExitEntity(new ExitEntity());
		init();
		setName(name);
		setLocation(location);
	}
	
	public Exit(String name){
		setExitEntity(new ExitEntity());
		init();
		setName(name);
		setLocation(new Location(0,0));
	}
	
	public Exit(ExitEntity entity)
	{
		setExitEntity(entity);
		init();
	}
	
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
