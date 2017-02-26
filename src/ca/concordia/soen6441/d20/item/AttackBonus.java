package ca.concordia.soen6441.d20.item;

public class AttackBonus implements LevelUp{
	private int modifier;
	private int base;
	
	public AttackBonus(int modifier){
		this.modifier = modifier;
		this.base = 0;
	}
	
	public int getPoint(){
		return (base + modifier);
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
		return modifier;
	}

	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(int modifier) {
		this.modifier = modifier;
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
}
