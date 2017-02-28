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
	public long getId() {
		return this.id;
	}

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
	
	DamageBonus createDamageBonus()
	{
		return new DamageBonus(this);
	}
}