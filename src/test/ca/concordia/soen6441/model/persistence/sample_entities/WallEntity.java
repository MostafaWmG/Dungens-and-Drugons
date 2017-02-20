package test.ca.concordia.soen6441.model.persistence.sample_entities;

import java.io.Serializable;
import javax.persistence.*;
import test.ca.concordia.soen6441.model.persistence.sample_entities.GameObjectEntity;

/**
 * This is a mock class which represnets a wall in game map. It is created for
 * testing persistent functionality
 * @author Saman Saadi
 *
 */
@Entity
//@Table(name="WALL")
public class WallEntity extends GameObjectEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public WallEntity() {
	super();	
	}
	
	public WallEntity(int id, String name) {
		super(id, name);
	}
   
}
