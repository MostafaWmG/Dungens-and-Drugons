package ca.concordia.soen6441.d20.gamemap.element;

public class Wall extends GameObject {

	private WallEntity wallEntity;
	
	public Wall(){
		setWallEntity(new WallEntity());
		setTag("Wall");
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
