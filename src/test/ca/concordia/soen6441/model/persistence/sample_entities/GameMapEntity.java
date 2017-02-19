/**
 * 
 */
package test.ca.concordia.soen6441.model.persistence.sample_entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * This is a mock class which represents a game map. It is created  only for
 * testing persistent functionality
 * @author Saman Saadi
 *
 */

@Entity
@Table(name="GAME_MAP")
public class GameMapEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID")
	private int id;
	@ManyToMany
	@JoinTable(
			name="MAP_OBJ",
			joinColumns=@JoinColumn(name="MAP_ID", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="OBJECT_ID", referencedColumnName="ID"))
	private List<GameObjectEntity> objects;
	private String name;

	private void init()
	{
		objects = new ArrayList<GameObjectEntity>();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameMapEntity [id=" + id + ", objects=" + objects + ", name=" + name + "]";
	}

	public GameMapEntity() {
		init();
	}
	
	public GameMapEntity(int id, String name, List<GameObjectEntity> objects)
	{
		setId(id);
		setName(name);
		setObjects(objects);		
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
