package ca.concordia.soen6441.d20.item;



import ca.concordia.soen6441.d20.dice.Dice;

public class HitPoint implements LevelUp {
	private int base;
	private int modifier;
	private int level;
	private Dice dice ;
		
	public HitPoint(int modifier,int level){
		base = 10;
		this.modifier = modifier;
		dice = new Dice();
		this.level = level;
	}
	
	public int getPoint(){
		return (base+modifier);
	}
		
	public void showPoint(){
		System.out.println("HitPoint:" + getPoint());
	}

	@Override
	public void update(int modfier) {
		setBase(dice.roll10() * getLevel());
		setModifier(modfier * getLevel());
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
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
