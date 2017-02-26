package ca.concordia.soen6441.d20.item;

import java.util.List;
import java.util.Vector;

/**
 * this a Ability class.
 * @author wmg
 * @author alvaro
 */
public class Ability implements LevelUp{
	
	private AbilityEntity abilityEntity;
	protected List<LevelUp> levelUpAction;
	
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
	
	private void initEmptyEntity()
	{
		setAbilityEntity(new AbilityEntity());
	}
	
	private void init()
	{
		levelUpAction = new Vector<LevelUp>();
	}
	
	public int getScore() {
		return getAbilityEntity().getScore();
	}
	public void setScore(int score) {
		getAbilityEntity().setScore(score);
	}
	public int getModifier() {
		return getAbilityEntity().getModifier();
	}
	public void setModifier(int modifier) {
		getAbilityEntity().setModifier(modifier);
	}
	
	public AbilityEnum getAbilityEnum(){
		return getAbilityEntity().getAbility();
	}
	
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
