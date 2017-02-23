package ca.concordia.soen6441.d20.item;

public class Ability {
	
	protected int score;
	protected int modifier;
	protected  AbilityEnum ability; 
	
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
	
	public AbilityEnum getAbility(){
		return ability;
	}
	
	public void setAbility(AbilityEnum ability){
		this.ability = ability;
	}
}
