package ca.concordia.soen6441.d20.item;

import java.util.List;
import java.util.Vector;

/**
 * this a Ability class.
 * @author wmg
 * @author alvaro
 */
public class Ability implements LevelUp{
	
	/**
	 *  score: the attribute that represent the abilityScore. 
	 *  modifier :the attribute that that represent abilityModifier.
	 *  ability :the attribute that represent the ability name.
	 */
	protected int score;
	protected int modifier;
	protected  AbilityEnum ability; 
	protected List<LevelUp> levelUpAction;
	
	public Ability(AbilityEnum ability,int  score, int modifier){
		this.ability = ability;
		this.score = score;
		this.modifier = modifier;
		levelUpAction = new Vector<LevelUp>();
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

	public void addListener(LevelUp levelUp) {
		levelUpAction.add(levelUp);
	}
	
	private void synceModifier(){
		setModifier((int)Math.floor( (getScore() - 10) /2 ) );
	}
	
	@Override
	public void update(int point) {
		setScore(getScore() + point);
		synceModifier();
		for(int i = 0 ; i < levelUpAction.size(); i++){
			levelUpAction.get(i).update(getModifier());
		}
	}
}
