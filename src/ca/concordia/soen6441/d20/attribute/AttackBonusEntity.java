package ca.concordia.soen6441.d20.attribute;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: AttackBonusEntity
 *
 */
@Entity
@Table(name="ATTACK_BONUS")
public class AttackBonusEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private long id;
	
	private int modifier;
	private int base;
	private int level;

	public AttackBonusEntity() {
		super();
	}   
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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
	 * 
	 * @return an attack bonus object.
	 */
	public AttackBonus createAttackBonus()
	{
		return new AttackBonus(this);
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
