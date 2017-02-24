package ca.concordia.soen6441.d20.item;
/**
 * this a Ability class.
 * @author wmg
 * @author alvaro
 */
public class Ability {
	
	/**
	 *  score: the attribute that represent the abilityScore. 
	 *  modifier :the attribute that that represent abilityModifier.
	 *  ability :the attribute that represent the ability name.
	 */
	protected int score;
	protected int modifier;
	protected  AbilityEnum ability; 
	
	public Ability(AbilityEnum ability,int  score, int modifier){
		this.ability = ability;
		this.score = score;
		this.modifier = modifier;
	}
	
	public Ability(){
		
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getModifier() {
		return modifier;
	}
	public void setModifier(int modifier) {
		this.modifier = modifier;
	}
	
	public AbilityEnum getAbilityEnum(){
		return ability;
	}
	
	public void setAbilityEnum(AbilityEnum ability){
		this.ability = ability;
	}
}
