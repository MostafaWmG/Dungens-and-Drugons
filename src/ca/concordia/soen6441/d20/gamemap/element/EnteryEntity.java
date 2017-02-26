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
		// TODO Auto-generated constructor stub
		super();	
		}
		
	public GameObject createModel(){
		
		return null;
		}
	   
	}
