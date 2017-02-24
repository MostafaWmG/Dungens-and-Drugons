package ca.concordia.soen6441.d20.gamemap.element;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: GameObjectEntity
 * @author Saman Saadi
 */

@Entity
@Inheritance
@DiscriminatorColumn(name="OBJ_TYPE")
@Table(name="GAME_OBJECT")
public abstract class GameObjectEntity implements Serializable {

	//TODO Test that id is generated without any problem
	@Id
	@GeneratedValue
	private long Id;
	private static final long serialVersionUID = 1L;

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
   
}
