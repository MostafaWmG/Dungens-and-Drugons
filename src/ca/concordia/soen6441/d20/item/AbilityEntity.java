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
	@Column(name="ABILITY_ID")
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
	/**
	 * 
	 * @return id which is used for database queries
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id to set for using in database queries
	 */
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
	 * @param modifier the ability modifier to set
	 */
	public void setModifier(int modifier) {
		this.modifier = modifier;
	}
	/**
	 * @return the ability of character
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
	
	/**
	 * 
	 * @return declares Ability properties for this AbilityEntity object
	 */
	public Ability createAbility()
	{
		return new Ability(this);
	}
}
