package ca.concordia.soen6441.d20.attribute;

import ca.concordia.soen6441.d20.item.ILevelUp;

/**
 * 
 * This class is used to compute attack bonus of the character in each combat.
 *
 */
public class AttackBonus implements ILevelUp{
	
	/**
	 * attackBonusEntity: is used as way to save/load the attack bonus of the character
	 */
	AttackBonusEntity attackBonusEntity;
	
	/**
	 * 
	 * @param modifier is used for creating AttackBonus object
	 */
	public AttackBonus(int modifier,int level){
		initEmptyEntity();
		setModifier(modifier);
		setLevel(level);
		setBase(0);
	}
	
	/**
	 * Usually we use this constructor to load data from database
	 * @param entity
	 */
	public AttackBonus(AttackBonusEntity entity)
	{
		setAttackBonusEntity(entity);
	}
	
	/**
	 * Creating empty Entity for this AttackBonus object.
	 */
	private void initEmptyEntity()
	{
		setAttackBonusEntity(new AttackBonusEntity());
	}
	
	/**
	 * 
	 * @return character's points
	 */
	public int getPoint(){
		return (getBase() + getModifier() + getLevel());
	}
	
	/**
	 * Prints AttackBonusPoint of the character
	 */
	public String showPoint(boolean console){
		String s ="AttackBonusPoint:" + getPoint() + "\n";  
		if(console)
			System.out.println("AttackBonusPoint:" + getPoint());
		return s;
	}

	/**
	 * Shows points of the character
	 */
	public String showPoint(){
		return showPoint(true);
	}
	
	@Override
	public void update(int modifier) {
		setModifier(modifier);
	}

	/**
	 * @return the modifier
	 */
	public int getModifier() {
		return getAttackBonusEntity().getModifier();
	}

	/**
	 * @param modifier to set
	 */
	public void setModifier(int modifier) {
		getAttackBonusEntity().setModifier(modifier);
	}
	
	/**
	 * @return the level
	 */
	public int getLevel() {
		return getAttackBonusEntity().getLevel();
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		getAttackBonusEntity().setLevel(level);
	}
	
	/**
	 * @return the base attack bonus
	 */
	public int getBase() {
		return getAttackBonusEntity().getBase();
	}

	/**
	 * @param base set as base attack bonus
	 */
	public void setBase(int base) {
		getAttackBonusEntity().setBase(base);
	}

	/**
	 * @return the attackBonusEntity
	 */
	public AttackBonusEntity getAttackBonusEntity() {
		return attackBonusEntity;
	}

	/**
	 * @param attackBonusEntity the attackBonusEntity to set
	 */
	public void setAttackBonusEntity(AttackBonusEntity attackBonusEntity) {
		this.attackBonusEntity = attackBonusEntity;
	}	
}
