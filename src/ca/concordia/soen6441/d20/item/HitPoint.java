package ca.concordia.soen6441.d20.item;



import ca.concordia.soen6441.d20.dice.Dice;

public class HitPoint implements LevelUp {

	private HitPointEntity hitPointEntity;
	//TODO we have Dice both in HitPoint and Character
	private Dice dice ;
		
	public HitPoint(int modifier,int level){
		initEmptyEntity();
		setBase(10);
		setModifier(modifier);		
		setLevel(level);
		init();
	}
	
	/**
	 * 
	 * @param entity
	 */
	public HitPoint(HitPointEntity entity)
	{
		setHitPointEntity(entity);
		init();
	}
	
	private void init()
	{
		dice = new Dice();
	}
	
	private void initEmptyEntity()
	{
		setHitPointEntity(new HitPointEntity());
	}
	
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
