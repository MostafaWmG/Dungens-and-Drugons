package ca.concordia.soen6441.d20.item;

import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;

public class ArmorClass extends GameObject implements LevelUp {
	
	private ArmorClassEntity armorClassEntity;
	
	public ArmorClass(int modifier){
		initEmptyEntity();
		setBase(10);
		setModfier(modifier);
	}
	
	/**
	 * Usually we use this constructor to load data from database
	 * @param entity
	 */
	public ArmorClass(ArmorClassEntity entity)
	{
		setArmorClassEntity(entity);
	}
	
	private void initEmptyEntity()
	{
		setArmorClassEntity(new ArmorClassEntity());
	}
	
	public int getPoint(){
		return (getBase() + getModfier());
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
		return getArmorClassEntity().getModfier();
	}

	/**
	 * @param modfier the modfier to set
	 */
	public void setModfier(int modfier) {
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

	@Override
	public GameObjectEntity getEntity() {
		return getArmorClassEntity();
	}
}
