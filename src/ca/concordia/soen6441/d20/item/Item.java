package ca.concordia.soen6441.d20.item;

import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;

/**
 * every new Item class represent an actual item . 
 * @author wmg
 * @author alvaro
 */
public class Item extends GameObject implements LevelUp{
	/**
	 *  point: the attribute that represent the enchantment point. 
	 *  itemEnum :the attribute that that represent item's name.
	 *  enchantmentType :the attribute that represent the enchantmentType.
	 */
	private int point;
	private ItemEnum itemEnum;
	private AbilityEnum enchantmentType;
	private AttributeEnum attributeType;

	public Item(ItemEnum item,AbilityEnum enchantmentType, int point) {
		itemEnum = item;
		this.enchantmentType = enchantmentType;
		this.attributeType = null;
		this.point = point;
	}
	
	public Item(ItemEnum item,AttributeEnum attributeType, int point){
		itemEnum = item;
		this.attributeType = attributeType;
		this.enchantmentType = null;
		this.point = point;
	}
	
	public int getEnchantmentPoint() {
		return point;
	}
	public void setEnchantmentPoint(int point) {
		this.point = point;
	}
	public AbilityEnum getEnchantmentType(){
		return enchantmentType;
	}
	
	public void setEnchantmentType(AbilityEnum enchantmentType){
		this.enchantmentType = enchantmentType;
	}
	public ItemEnum getItemEnum(){
		return itemEnum;
	}
	
	public void setItemEum(ItemEnum itemEnum){
		this.itemEnum = itemEnum;
	}

	/**
	 * @return the attributeEnum
	 */
	public AttributeEnum getAttributeType() {
		return attributeType;
	}

	/**
	 * @param attributeEnum the attributeEnum to set
	 */
	public void setAttributeType(AttributeEnum attributeEnum) {
		this.attributeType = attributeEnum;
	}

	@Override
	public void update(int modifier) {
		setEnchantmentPoint(getEnchantmentPoint() +  (int)(modifier/5));
	}

	@Override
	public GameObjectEntity getEntity() {
		// TODO Auto-generated method stub
		return null;
	}
}
