package ca.concordia.soen6441.d20.gamemap;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;
import test.ca.concordia.soen6441.model.persistence.sample_entities.GameObjectEntitySample;

/**
 * Entity implementation class for Entity: GameMapEntity
 * @author Saman Saadi
 *
 */
@Entity
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
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name="MAP_OBJ",
			joinColumns=@JoinColumn(name="MAP_ID", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="OBJECT_ID", referencedColumnName="ID"))
	private List<GameObjectEntity> objects;

	public GameMapEntity() {
		super();
	}
	
	public void addGameObjectEntity(GameObjectEntity entity)
	{
		if (getObjects() == null)
			setObjects(new ArrayList<>());
		getObjects().add(entity);
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
	 * @return the objects
	 */
	public List<GameObjectEntity> getObjects() {
		return objects;
	}

	/**
	 * @param objects the objects to set
	 */
	public void setObjects(List<GameObjectEntity> objects) {
		this.objects = objects;
	}
   
}
