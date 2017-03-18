package ca.concordia.soen6441.d20.attribute;

import ca.concordia.soen6441.d20.item.ILevelUp;

/**
 * 
 * This class represents damage bonus that character gets from his combats.
 * When character's attack succeeds, it deals damage. The type of weapon used determines the amount of damage. 
 *
 */

public class DamageBonus implements ILevelUp{

	/**
	 * damageBonusEntity: this object is used to store data retrieved from database.
	 */
	private DamageBonusEntity damageBonusEntity;
	
	/**
	 * This constructor creates object with damage modifier.
	 * @param modifier
	 */
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
	
	/**
	 * 
	 * @return points that character has gained
	 */
	public int getPoint(){
		return (getModifier() + getBase());
	}
	
	/**
	 * Shows damage bonus point
	 */
	public String showPoint(boolean console){
		String s ="DamageBonusPoint:" + getPoint() + "\n";  
		if(console)
			System.out.println("DamageBonusPoint:" + getPoint());
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
