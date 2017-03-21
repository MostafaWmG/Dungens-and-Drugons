package ca.concordia.soen6441.d20.gamemap.element;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ExitEntity
 *
 */
@Entity
public class ExitEntity extends GameObjectEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public ExitEntity() {
		super();
	}
	/**
	 * @return a game object as exit for the game map
	 */
	public GameObject createModel(){
		
		return new Exit(this);
	}
   
}
