package ca.concordia.soen6441.d20.gamemap.element;

import java.io.Serializable;
import javax.persistence.*;

import ca.concordia.soen6441.d20.common.Location;

/**
 * Entity implementation class for Entity: GameObjectEntity
 * @author Saman Saadi
 */

@NamedQueries({
	@NamedQuery(
			name="findGameObjectEntityByName",
			query="SELECT goe FROM GameObjectEntity goe WHERE goe.name = :name"
			),
	@NamedQuery(
			name="findGameObjectEntityAll",
			query="SELECT goe FROM GameObjectEntity goe")
})

@Entity
@Inheritance
@DiscriminatorColumn(name="OBJ_TYPE")
@Table(name="GAME_OBJECT")
public abstract class GameObjectEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	//TODO Test that id is generated without any problem
	@Id
	@GeneratedValue
	@Column(name="ID")
	private long Id;
	
	private Location location;
	
	@Column(unique=true)
	private String name;

	public GameObjectEntity() {
		super();
		location = new Location();
	}
	
	/**
	 * All sub-classes must implement this function. By using it, GameMapEntity
	 * is able to construct GameMap for loading data.
	 * @return
	 */
	public abstract GameObject createModel();
	
	public long getId() {
		return this.Id;
	}

	public void setId(long Id) {
		this.Id = Id;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
