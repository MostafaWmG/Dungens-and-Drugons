package ca.concordia.soen6441.d20.gamemap.element;


/**
 * This class implements exit element of the game map
 * @author negar
 *
 */

public class Exit extends GameObject{
	
	private ExitEntity exitEntity;
	
	/**
	 * creates exit object with given name
	 * @param name to set for the element
	 */
	public Exit(String name){
		setExitEntity(new ExitEntity());
		init();
		setName(name);
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
