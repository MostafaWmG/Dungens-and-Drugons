package ca.concordia.soen6441.d20.item;

import ca.concordia.soen6441.d20.dice.Dice;
/**
 * 
 *Hit points measure how hard character is to kill. This class attempt to implement this concept. 
 *
 */
public class HitPoint implements LevelUp {

	/**
	 * This object is used to read hit point from data base or store hit point to database.
	 */
	private HitPointEntity hitPointEntity;
	//TODO we have Dice both in HitPoint and Character
	private Dice dice ;
		
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
		init();
	}
	
	/**
	 * 
	 * @param entity used by this constructor to save/load from database
	 */
	public HitPoint(HitPointEntity entity)
	{
		setHitPointEntity(entity);
		init();
	}
	/**
	 * declares Dice object.
	 */
	private void init()
	{
		dice = new Dice();
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
