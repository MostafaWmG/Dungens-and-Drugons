package ca.concordia.soen6441.d20.gamemap.element;

import ca.concordia.soen6441.d20.common.Location;

public class Exit extends GameObject{
	
	private ExitEntity exitEntity;
	
	public Exit(int x , int y){
		setExitEntity(new ExitEntity());
		setTag("Exit");
		setLocation(new Location(x, y));
	}
	
	public Exit(Location location){
		setExitEntity(new ExitEntity());
		setTag("Exit");
		setLocation(location);
	}
	
	public Exit(){
		setExitEntity(new ExitEntity());
		setTag("Exit");
		setLocation(new Location(0,0));
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
