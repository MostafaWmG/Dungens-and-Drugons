package ca.concordia.soen6441.d20.gamemap.element;

public class Exit extends GameObject{
	
	private ExitEntity exitEntity;
	
	public Exit(){
		setExitEntity(new ExitEntity());
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
