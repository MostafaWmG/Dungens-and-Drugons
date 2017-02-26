package ca.concordia.soen6441.d20.item;

public class DamageBonus implements LevelUp{

	private DamageBonusEntity damageBonusEntity;
	
	public DamageBonus(int modifier){
		initEmptyEntity();
		setModifier(modifier);
		setBase(0);
	}
	/**
	 * Usually we use this constructor for loading data
	 * @param entity
	 */
	public DamageBonus(DamageBonusEntity entity)
	{
		setDamageBonusEntity(entity);
	}
	
	private void initEmptyEntity()
	{
		setDamageBonusEntity(new DamageBonusEntity());
	}
	
	public int getPoint(){
		return (getModifier() + getBase());
	}
	
	public void showPoint(){
		System.out.println("DamageBonusPoint:" + getPoint());
	}

	@Override
	public void update(int modifier) {
		setModifier(modifier);
	}

	/**
	 * @return the base
	 */
	public int getBase() {
		return getDamageBonusEntity().getBase();
	}

	/**
	 * @param base the base to set
	 */
	public void setBase(int base) {
		getDamageBonusEntity().setBase(base);
	}

	/**
	 * @return the modifier
	 */
	public int getModifier() {
		return getDamageBonusEntity().getModifier();
	}

	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(int modifier) {
		getDamageBonusEntity().setModifier(modifier);
	}

	/**
	 * @return the damageBonusEntity
	 */
	public DamageBonusEntity getDamageBonusEntity() {
		return damageBonusEntity;
	}

	/**
	 * @param damageBonusEntity the damageBonusEntity to set
	 */
	public void setDamageBonusEntity(DamageBonusEntity damageBonusEntity) {
		this.damageBonusEntity = damageBonusEntity;
	}
	
}
