package ca.concordia.soen6441.d20.attribute;

import ca.concordia.soen6441.d20.item.ILevelUp;

/**
 * Each character of the game can wear an armor for protection and to use it in fights.
 * This class represent this concept of the game.
 * Your Armor Class represents how hard it is for opponents to land a solid, damaging blow on the character.
 * @author 
 *
 */

public class ArmorClass implements ILevelUp {
	
	/**
	 * armorClassEntity is used for saving/loading armor characteristic of the character from database
	 */
	private ArmorClassEntity armorClassEntity;
	
	/**
	 * This constructor is called when inly armor modifier is specified for the character.
	 * @param modifier armor modifier to set for character
	 */
	public ArmorClass(int modifier){
		initEmptyEntity();
		setBase(10);
		setModifier(modifier);
	}
	
	/**
	 * Usually we use this constructor to load data from database
	 * @param entity
	 */
	public ArmorClass(ArmorClassEntity entity)
	{
		setArmorClassEntity(entity);
	}
	
	/**
	 * A new ArmorClassEntity is created for database query use.
	 */
	private void initEmptyEntity()
	{
		setArmorClassEntity(new ArmorClassEntity());
	}
	
	/**
	 * @return points that character has earned
	 */
	public int getPoint(){
		return (getBase() + getModfier());
	}
		
	/**
	 * Shows points of the character
	 */
	public String showPoint(boolean console){
		String s ="ArmorPoint:" + getPoint() + "\n";  
		if(console)
			System.out.println("ArmorPoint:" + getPoint());
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
	 * @return the Armor modifier
	 */
	public int getModfier() {
		return getArmorClassEntity().getModfier();
	}

	/**
	 * @param modfier the modfier to set
	 */
	public void setModifier(int modfier) {
		getArmorClassEntity().setModfier(modfier);
	}

	/**
	 * @return the base
	 */
	public int getBase() {
		return getArmorClassEntity().getBase();
	}

	/**
	 * @param base the base to set
	 */
	public void setBase(int base) {
		getArmorClassEntity().setBase(base);
	}
	

	/**
	 * @return the armorClassEntity
	 */
	public ArmorClassEntity getArmorClassEntity() {
		return armorClassEntity;
	}

	/**
	 * @param armorClassEntity the armorClassEntity to set
	 */
	public void setArmorClassEntity(ArmorClassEntity armorClassEntity) {
		this.armorClassEntity = armorClassEntity;
	}
	
	/**
	 * This method is used to read Armor property of the character from database
	 * @return ArmorClassEntity of this armor object
	 */
	public ArmorClassEntity getEntity()
	{
		return getArmorClassEntity();
	}
}
