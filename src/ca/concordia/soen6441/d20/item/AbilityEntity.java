package ca.concordia.soen6441.d20.item;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: AbilityEntity
 * @author Saman Saadi
 */
@Entity
@Table(name="ABILITY")
public class AbilityEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="ID")
	private long id;
	/**
	 * the attribute that represent the abilityScore.
	 */
	protected int score;
	/**
	 * the attribute that that represent abilityModifier.
	 */
	protected int modifier;
	/**
	 * the attribute that represent the ability name.
	 */
	@Enumerated(EnumType.STRING)
	protected  AbilityEnum ability;

	public AbilityEntity() {
		super();
	}   
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
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
	 * @return the ability
	 */
	public AbilityEnum getAbility() {
		return ability;
	}
	/**
	 * @param ability the ability to set
	 */
	public void setAbility(AbilityEnum ability) {
		this.ability = ability;
	}
	
	public Ability createAbility()
	{
		return new Ability(this);
	}
}
