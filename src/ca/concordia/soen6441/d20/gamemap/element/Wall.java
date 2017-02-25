package ca.concordia.soen6441.d20.gamemap.element;

import ca.concordia.soen6441.d20.common.Location;

public class Wall extends GameObject {

	private WallEntity wallEntity;
	
	public Wall(int x , int y){
		setWallEntity(new WallEntity());
		setTag("Wall");
		setLocation(new Location(x, y));
	}
	
	public Wall(Location location){
		setWallEntity(new WallEntity());
		setTag("Wall");
		setLocation(location);
	}
	
	public Wall(){
		setWallEntity(new WallEntity());
		setTag("Wall");
		setLocation(new Location(0, 0));
	}

	@Override
	public GameObjectEntity getEntity() {
		// TODO Auto-generated method stub
		return wallEntity;
	}

	/**
	 * @return the wallEntity
	 */
	public WallEntity getWallEntity() {
		return wallEntity;
	}

	/**
	 * @param wallEntity the wallEntity to set
	 */
	public void setWallEntity(WallEntity wallEntity) {
		this.wallEntity = wallEntity;
	}
}
