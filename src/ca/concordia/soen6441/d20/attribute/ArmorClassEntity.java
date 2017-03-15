package ca.concordia.soen6441.d20.attribute;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ArmorClassEntity
 * @author Saman Saadi
 */
@Entity
@Table(name="Armor")
public class ArmorClassEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private long id;
	private int base;
	private int modfier;

	public ArmorClassEntity() {
		super();
	}

	/**
	 * @return the base
	 */
	public int getBase() {
		return base;
	}

	/**
	 * @param base to set for armor object
	 */
	public void setBase(int base) {
		this.base = base;
	}

	/**
	 * @return the modfier
	 */
	public int getModfier() {
		return modfier;
	}

	/**
	 * @param modfier the modfier to set
	 */
	public void setModfier(int modfier) {
		this.modfier = modfier;
	}
			
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return declares ArmorClass properties for this ArmorClassEntity object
	 */
	public ArmorClass createArmorModel()
	{
		return new ArmorClass(this);
	}
}
