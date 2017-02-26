package ca.concordia.soen6441.d20.gamemap.element;

import java.io.Serializable;

import javax.persistence.*;



/**
 * Entity implementation class for Entity: GroundEntity
 * @author Saman Saadi
 */

@Entity
public class GroundEntity extends GameObjectEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public GroundEntity() {
		super();
	}

	@Override
	public GameObject createModel() {
		return new Ground(this);
	}
   
}
