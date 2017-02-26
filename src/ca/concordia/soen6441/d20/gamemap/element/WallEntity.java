package ca.concordia.soen6441.d20.gamemap.element;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: WallEntity
 *
 */
@Entity

public class WallEntity extends GameObjectEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public WallEntity() {
		super();
	}
	
	public GameObject createModel(){
		
		return new Wall(this);
		}
   
}
