package ca.concordia.soen6441.d20.gamemap.element;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entry: EntryEntity
 *@author Negar Sadeghi
 */
@Entity
public class EntryEntity extends GameObjectEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	public EntryEntity() {
		// TODO Auto-generated constructor stub
		super();	
		}
		
	public GameObject getModel(){
		
		return null;
		}
	   
	}
