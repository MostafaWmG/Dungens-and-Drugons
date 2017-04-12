package ca.concordia.soen6441.d20.attribute;

import ca.concordia.soen6441.d20.item.ILevelUp;
/**
 * 
 *Hit points measure how hard character is to kill. This class attempt to implement this concept. 
 *
 */
public class HitPoint implements ILevelUp {

	/**
	 * This object is used to read hit point from data base or store hit point to database.
	 */
	private HitPointEntity hitPointEntity;
		
	/**
	 * 
	 * @param modifier to set for the character.
	 * @param level to set the character's level. 
	 */
	public HitPoint(int modifier,int level){
		initEmptyEntity();
		setBase(10);
		setModifier(modifier);		
		setLevel(level);
	}
	
	/**
	 * 
	 * @param entity used by this constructor to save/load from database
	 */
	public HitPoint(HitPointEntity entity)
	{
		setHitPointEntity(entity);
	}

	/**
	 * declares a new HitPointEntity object.
	 */
	private void initEmptyEntity()
	{
		setHitPointEntity(new HitPointEntity());
	}
	/**
	 * 
	 * @return hit point of the character
	 */
	public int getPoint(){
		return (getBase() + getModifier());
	}
		

	/**
	 * Prints hit point of the character
	 */
	public String showPoint(boolean console){
		String s ="HitPoint:" + getPoint() + "\n";  
		if(console)
			System.out.println("HitPoint:" + getPoint());
		return s;
	}

	/**
	 * Shows points of the character
	 */
	public String showPoint(){
		return showPoint(true);
	}
	
	@Override
	public void update(int modfier) {
		setModifier(modfier * getLevel());
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return getHitPointEntity().getLevel();
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		getHitPointEntity().setLevel(level);
	}

	/**
	 * @return the base
	 */
	public int getBase() {
		return getHitPointEntity().getBase();
	}

	/**
	 * @param base the base to set
	 */
	public void setBase(int base) {
		getHitPointEntity().setBase(base);
	}

	/**
	 * @return the modifier
	 */
	public int getModifier() {
		return getHitPointEntity().getModifier();
	}

	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(int modifier) {
		getHitPointEntity().setModifier(modifier);
	}

	/**
	 * @return the hitPointEntity
	 */
	public HitPointEntity getHitPointEntity() {
		return hitPointEntity;
	}

	/**
	 * @param hitPointEntity the hitPointEntity to set
	 */
	public void setHitPointEntity(HitPointEntity hitPointEntity) {
		this.hitPointEntity = hitPointEntity;
	}
	
}
