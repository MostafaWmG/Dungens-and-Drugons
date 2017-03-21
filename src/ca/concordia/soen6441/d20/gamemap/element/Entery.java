package ca.concordia.soen6441.d20.gamemap.element;


/**
 * This class implements Entry of the game map
 * @author Saman Saadi
 *
 */
public class Entery extends GameObject{
	
	private EnteryEntity enteryEntity;

	/**
	 * 
	 * @param name creates entry object with the given name
	 */
	public  Entery(String name){
		setEnteryEntity(new EnteryEntity());
		init();
		setName(name);
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
