package ca.concordia.soen6441.d20.item;

public class AttackBonus implements LevelUp{
	
	AttackBonusEntity attackBonusEntity;
	
	public AttackBonus(int modifier){
		initEmptyEntity();
		setModifier(modifier);
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
	
	private void initEmptyEntity()
	{
		setAttackBonusEntity(new AttackBonusEntity());
	}
	
	public int getPoint(){
		return (getBase() + getModifier());
	}
	
	public void showPoint(){
		System.out.println("AttackBonusPoint:" +getPoint());
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
	 * @param modifier the modifier to set
	 */
	public void setModifier(int modifier) {
		getAttackBonusEntity().setModifier(modifier);
	}

	/**
	 * @return the base
	 */
	public int getBase() {
		return getAttackBonusEntity().getBase();
	}

	/**
	 * @param base the base to set
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
