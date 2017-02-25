package ca.concordia.soen6441.d20.gamemap.element;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DoorEntity
 *
 */
@Entity

public class DoorEntity extends GameObjectEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public DoorEntity() {
		super();
	}
	
	public GameObject getModel(){
		
		return null;
	}
   
}
