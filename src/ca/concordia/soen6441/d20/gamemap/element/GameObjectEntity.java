package ca.concordia.soen6441.d20.gamemap.element;

import java.io.Serializable;
import javax.persistence.*;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;

/**
 * Entity implementation class for Entity: GameObjectEntity
 * @author Saman Saadi
 */

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

	// TODO location : to be reFactored
	@Transient
	private Location location;
	// TODO remove Transient
	@Transient
	private GameMap field;
	//TODO Do we really need tag?
	private String tag ;

	public GameObjectEntity() {
		super();
	}
	
	/**
	 * All sub-classes must implement this function. By using it, GameMapEntity
	 * is able to construct GameMap for loading data.
	 * @return
	 */
	public abstract GameObject getModel();
	
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
	 * @return the field
	 */
	public GameMap getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(GameMap field) {
		this.field = field;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}   
}
