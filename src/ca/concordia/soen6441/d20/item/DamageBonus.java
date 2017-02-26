package ca.concordia.soen6441.d20.item;

public class DamageBonus implements LevelUp{
	private int base;
	private int modifier;
	
	public DamageBonus(int modifier){
		this.modifier = modifier;
		base = 0;
	}
	
	public int getPoint(){
		return (modifier + base);
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
		return base;
	}

	/**
	 * @param base the base to set
	 */
	public void setBase(int base) {
		this.base = base;
	}

	/**
	 * @return the modifier
	 */
	public int getModifier() {
		return modifier;
	}

	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(int modifier) {
		this.modifier = modifier;
	}
}
