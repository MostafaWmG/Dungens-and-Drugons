package ca.concordia.soen6441.d20.item;

public class ArmorClass implements LevelUp {
	private int base;
	private int modfier;
	
	public ArmorClass(int modifier){
		base = 10;
		this.modfier = modifier;
	}
	
	public int getPoint(){
		return (base + modfier);
	}
		
	public void showPoint(){
		System.out.println("ArmorPoint:" + getPoint());
	}

	@Override
	public void update(int modifier) {
		setModfier(modifier);		
	}

	/**
	 * @return the modfier
	 */
	public int getModfier() {
		return modfier;
	}

	/**
	 * @param modfier the modfier to set
	 */
	public void setModfier(int modfier) {
		this.modfier = modfier;
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
