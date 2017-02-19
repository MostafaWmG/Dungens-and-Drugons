/**
 * 
 */
package test.ca.concordia.soen6441.model.persistence.sample_entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * This is a mock class which represnets an object in game map. It is created for
 * testing persistent functionality
 * @author Saman Saadi
 *
 */

@Entity
@Inheritance
@DiscriminatorColumn(name="GAMEOBJ_TYPE")
@Table(name="GAME_OBJECT")
public abstract class GameObjectEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID")
	private int id;
	private String name;

	public GameObjectEntity()
	{
		super();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameObjectEntity [id=" + id + ", name=" + name + "]";
	}

	public GameObjectEntity(int id, String name) {
		setId(id);
		setName(name);
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
