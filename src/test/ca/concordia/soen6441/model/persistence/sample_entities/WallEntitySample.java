package test.ca.concordia.soen6441.model.persistence.sample_entities;

import java.io.Serializable;
import javax.persistence.*;
import test.ca.concordia.soen6441.model.persistence.sample_entities.GameObjectEntitySample;

/**
 * This is a mock class which represnets a wall in game map. It is created for
 * testing persistent functionality
 * @author Saman Saadi
 *
 */
@Entity
//@Table(name="WALL")
public class WallEntitySample extends GameObjectEntitySample implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public WallEntitySample() {
	super();	
	}
	
	public WallEntitySample(int id, String name) {
		super(id, name);
	}
   
}
