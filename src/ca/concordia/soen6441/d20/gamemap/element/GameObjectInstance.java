package ca.concordia.soen6441.d20.gamemap.element;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;

/**
 * 
 * @author Saman Saadi
 *
 */

public class GameObjectInstance
{
	private GameObjectInstanceEntity entity;
	private GameObject gameObject;
	private GameMap gameMap;
	
	
	public GameObjectInstance(GameObject gameObject, GameMap gamMap) {
		setEntity(new GameObjectInstanceEntity());
		setGameObject(gameObject);
		setGameMap(gamMap);
	}
	/**
	 * 
	 * @param entity
	 */
	public GameObjectInstance(GameObjectInstanceEntity entity, GameMap gameMap)
	{
		setEntity(entity);
		setGameObject(entity.getGameObjectEntity().createModel());
		setGameMap(gameMap);
	}
	/**
	 * @return the gameObjectInstanceEntity
	 */
	public GameObjectInstanceEntity getEntity() {
		return entity;
	}
	/**
	 * @param gameObjectInstanceEntity the gameObjectInstanceEntity to set
	 */
	public void setEntity(GameObjectInstanceEntity gameObjectInstanceEntity) {
		this.entity = gameObjectInstanceEntity;
	}
	/**
	 * @return the gameObject
	 */
	public GameObject getGameObject() {
		return gameObject;
	}
	/**
	 * @param gameObject the gameObject to set
	 */
	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
		getEntity().setGameObjectEntity(gameObject.getEntity());
	}
	/**
	 * @return the gameMap
	 */
	public GameMap getGameMap() {
		return gameMap;
	}
	/**
	 * @param gameMap the gameMap to set
	 */
	public void setGameMap(GameMap gameMap) {
		this.gameMap = gameMap;
		getEntity().setGameMapEntity(gameMap.getEntity());
	}
	
	public Location getLocation() {
		return getEntity().getLocation();
	}
	
	public void setLocation(Location location)
	{
		getEntity().setLocation(location);
	}
}
