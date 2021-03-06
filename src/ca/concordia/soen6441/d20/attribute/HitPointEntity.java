package ca.concordia.soen6441.d20.attribute;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: HitPointEntity
 *
 */
@Entity
@Table(name="HITPOINT")
public class HitPointEntity implements Serializable {

		
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private long id;
	private int base;
	private int modifier;
	private int level;

	public HitPointEntity() {
		super();
	}
	/**
	 * 
	 * @return HitPoint object for storing data retrieved from database.
	 */
	public HitPoint createHitPoint()
	{
		return new HitPoint(this);
	}
	
	/**
	 * 
	 * @return id of object
	 */
	public long getId() {
		return this.id;
	}
	
	/**
	 * 
	 * @param id for this entity to use it in database queries
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the base
	 */
	public int getBase() {
		return base;
	}
	/**
	 * @param base the base to set
	 */
	public void setBase(int base) {
		this.base = base;
	}
	/**
	 * @return the modifier
	 */
	public int getModifier() {
		return modifier;
	}
	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(int modifier) {
		this.modifier = modifier;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}	
}
