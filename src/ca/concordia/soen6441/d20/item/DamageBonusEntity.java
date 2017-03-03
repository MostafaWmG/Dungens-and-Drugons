package ca.concordia.soen6441.d20.item;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DamageBonusEntity
 *
 */
@Entity
@Table(name="DAMAGE_BONUS")
public class DamageBonusEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="ID")
	private long id;
	private int base;
	private int modifier;

	public DamageBonusEntity() {
		super();
	}   
	/**
	 * 
	 * @return id of the object in database tables
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id to set in order to use in database queries
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
	 * @param base to set
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
	 * 
	 * @return DamageBonus object that is used to interact between DamageBonus and DamageBonuseEntity classes.
	 */
	public DamageBonus createDamageBonus()
	{
		return new DamageBonus(this);
	}
}
