package ca.concordia.soen6441.d20.item;

import java.util.List;
import java.util.Vector;

/**
 * This is the Ability class. It represents abilities of the game characters.
 * @author wmg
 * @author alvaro
 */
public class Ability implements LevelUp{
	/**
	 * abilityEntity is used to load or save abilities from/to database
	 * levelUpAction is useful for upgrading level of the characters in the game
	 */
	private AbilityEntity abilityEntity;
	protected List<LevelUp> levelUpAction;
	
	/**
	 * This constructor creates an object with the following properties
	 * @param ability to set for the character
	 * @param score that character gets
	 * @param modifier The bonus or penalty associated with a particular ability score. 
	 * Ability modifiers apply to die rolls for character actions involving the corresponding abilities.
	 */
	public Ability(AbilityEnum ability,int  score, int modifier){
		initEmptyEntity();
		setAbilityEnum(ability);
		setScore(score);
		setModifier(modifier);
		init();
	}
	
	public Ability(){
		initEmptyEntity();
		init();
	}
	
	/**
	 * Usually we use this constructor to load data from database
	 * @param entity
	 */
	public Ability(AbilityEntity entity)
	{
		setAbilityEntity(entity);
		init();
	}
	
	/**
	 * creates a new AbilityEntity
	 */
	private void initEmptyEntity()
	{
		setAbilityEntity(new AbilityEntity());
	}
	
	/**
	 * declares a vector to levelUpAction 
	 */
	private void init()
	{
		levelUpAction = new Vector<LevelUp>();
	}
	
	/**
	 * 
	 * @return the score of the character
	 */
	public int getScore() {
		return getAbilityEntity().getScore();
	}
	/**
	 * 
	 * @param score to set for the character
	 */
	public void setScore(int score) {
		getAbilityEntity().setScore(score);
	}
	/**
	 * 
	 * @return ability modifier of the character
	 */
	public int getModifier() {
		return getAbilityEntity().getModifier();
	}
	/**
	 * 
	 * @param modifier to set as ability modifier of the character
	 */
	public void setModifier(int modifier) {
		getAbilityEntity().setModifier(modifier);
	}
	
	/**
	 * 
	 * @return the type of ability of the character from AbilityEntity
	 */
	public AbilityEnum getAbilityEnum(){
		return getAbilityEntity().getAbility();
	}
	
	/**
	 * 
	 * @param ability to set from AbilityEnum to the character
	 */
	public void setAbilityEnum(AbilityEnum ability){
		getAbilityEntity().setAbility(ability);
	}
	

	/**
	 * @return the abilityEntity
	 */
	public AbilityEntity getAbilityEntity() {
		return abilityEntity;
	}

	/**
	 * @param abilityEntity the abilityEntity to set
	 */
	public void setAbilityEntity(AbilityEntity abilityEntity) {
		this.abilityEntity = abilityEntity;
	}

	/**
	 * 
	 * @param levelUp is the new level to set for character
	 */
	public void addListener(LevelUp levelUp) {
		levelUpAction.add(levelUp);
	}
	
	public void synceModifier(){
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
	
	/**
	 * This method prints abilities' names and scores and modifiers related to that ability
	 */
	public void show(){
		System.out.println("Ability Name: "+getAbilityEnum()+" Score: "+getScore()+" Modifier: "+getModifier() );
	}
}
