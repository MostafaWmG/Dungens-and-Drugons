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
	
	public GameObject createModel(){
		
		return new Exit(this);
	}
   
}
