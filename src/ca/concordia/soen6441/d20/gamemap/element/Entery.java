package ca.concordia.soen6441.d20.gamemap.element;

public class Entery extends GameObject{
	
	private EnteryEntity enteryEntity;

	public  Entery(){
		setEnteryEntity(new EnteryEntity());
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
