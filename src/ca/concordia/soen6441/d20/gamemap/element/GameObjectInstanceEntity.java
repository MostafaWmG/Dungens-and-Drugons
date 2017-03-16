package ca.concordia.soen6441.d20.gamemap.element;

import java.io.Serializable;
import javax.persistence.*;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMapEntity;

/**
 * Entity implementation class for Entity: GameObjectInstanceEntity
 * @author Saman Saadi
 */
@Entity
@Table(name="GAME_OBJECT_INSTANCE")
public class GameObjectInstanceEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="ID")
	private long id;
	private Location location;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GAME_MAP_ID")
	private GameMapEntity gameMapEntity;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GAME_OBJECT_ID")
	private GameObjectEntity gameObjectEntity;

	public GameObjectInstanceEntity() {
		super();
	}
	
	public GameObjectInstance createModel()
	{
		return new GameObjectInstance(this);
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the gameMapEntity
	 */
	public GameMapEntity getGameMapEntity() {
		return gameMapEntity;
	}

	/**
	 * @param gameMapEntity the gameMapEntity to set
	 */
	public void setGameMapEntity(GameMapEntity gameMapEntity) {
		this.gameMapEntity = gameMapEntity;
	}

	/**
	 * @return the gameObjectEntity
	 */
	public GameObjectEntity getGameObjectEntity() {
		return gameObjectEntity;
	}

	/**
	 * @param gameObjectEntity the gameObjectEntity to set
	 */
	public void setGameObjectEntity(GameObjectEntity gameObjectEntity) {
		this.gameObjectEntity = gameObjectEntity;
	}   		
   
}
