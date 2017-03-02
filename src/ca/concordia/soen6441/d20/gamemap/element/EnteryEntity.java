package ca.concordia.soen6441.d20.gamemap.element;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entry: EntryEntity
 *@author Negar Sadeghi
 */
@Entity
public class EnteryEntity extends GameObjectEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	public EnteryEntity() {
		super();	
		}
		
	/**
	 * @return a  game object as entry for the game map
	 */
	public GameObject createModel()
	{
		return new Entery(this);
	}	   
}
