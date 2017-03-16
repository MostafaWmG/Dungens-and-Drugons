package ca.concordia.soen6441.d20.gamemap;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectInstanceEntity;


/**
 * Entity implementation class for Entity: GameMapEntity
 * @author Saman Saadi
 *
 */
@NamedQueries({
	@NamedQuery(
			name="findGameMapEntityByName",
			query="SELECT gmp FROM GameMapEntity gmp WHERE gmp.name = :name"
			),
	@NamedQuery(
			name="findGameMapEntityAll",
			query="SELECT gmp FROM GameMapEntity gmp")
})
@Entity
@Table(name="GAME_MAP")
public class GameMapEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private long id;
	private int width;
	private int height;
	@Column(unique=true)
	private String name;
	
	@OneToMany(mappedBy="gameMapEntity")
	private List<GameObjectInstanceEntity> gameObjectInstances;

	public GameMapEntity() {
		super();
	}
	
	public void addGameObjectInstanceEntity(GameObjectInstanceEntity entity)
	{
		if (getGameObjectInstances() == null)
			setGameObjectInstances(new ArrayList<>());
		getGameObjectInstances().add(entity);
	}
	
	public GameMap createModel()
	{
		return new GameMap(this);
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}   
	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}   
	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the gameObjectInstances
	 */
	public List<GameObjectInstanceEntity> getGameObjectInstances() {
		return gameObjectInstances;
	}

	/**
	 * @param gameObjectInstances the gameObjectInstances to set
	 */
	public void setGameObjectInstances(List<GameObjectInstanceEntity> gameObjectInstances) {
		this.gameObjectInstances = gameObjectInstances;
	}   
}
