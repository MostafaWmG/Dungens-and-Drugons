package ca.concordia.soen6441.d20.item;

import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;

/**
 * every new Item class represent an actual item . 
 * @author wmg
 * @author alvaro
 */
public class Item extends GameObject implements LevelUp{
	
	private ItemEntity itemEntity;

	public Item(ItemEnum item,AbilityEnum enchantmentType, int point) {
		initEmptyEntity();
		getItemEntity().setItemEnum(item);
		getItemEntity().setEnchantmentType(enchantmentType);
		getItemEntity().setAttributeType(null);
		getItemEntity().setPoint(point);
	}
	
	public Item(ItemEnum item,AttributeEnum attributeType, int point){
		initEmptyEntity();
		getItemEntity().setItemEnum(item);
		getItemEntity().setAttributeType(attributeType);
		getItemEntity().setEnchantmentType(null);
		getItemEntity().setPoint(point);
	}
	
	/**
	 * Usually we use this constructor for loading data from database
	 * @param entity
	 */
	public Item(ItemEntity entity)
	{
		setItemEntity(entity);
	}
	
	private void initEmptyEntity()
	{
		setItemEntity(new ItemEntity());
	}
	
	/**
	 * @return the itemEntity
	 */
	public ItemEntity getItemEntity() {
		return itemEntity;
	}

	/**
	 * @param itemEntity the itemEntity to set
	 */
	public void setItemEntity(ItemEntity itemEntity) {
		this.itemEntity = itemEntity;
	}

	public int getEnchantmentPoint() {
		return getItemEntity().getPoint();
	}
	public void setEnchantmentPoint(int point) {
		getItemEntity().setPoint(point);
	}
	public AbilityEnum getEnchantmentType(){
		return getItemEntity().getEnchantmentType();
	}
	
	public void setEnchantmentType(AbilityEnum enchantmentType){
		getItemEntity().setEnchantmentType(enchantmentType);
	}
	public ItemEnum getItemEnum(){
		return getItemEntity().getItemEnum();
	}
	
	public void setItemEum(ItemEnum itemEnum){
		getItemEntity().setItemEnum(itemEnum);
	}

	/**
	 * @return the attributeEnum
	 */
	public AttributeEnum getAttributeType() {
		return getItemEntity().getAttributeType();
	}

	/**
	 * @param attributeEnum the attributeEnum to set
	 */
	public void setAttributeType(AttributeEnum attributeEnum) {
		getItemEntity().setAttributeType(attributeEnum);
	}

	@Override
	public void update(int modifier) {
		setEnchantmentPoint(getEnchantmentPoint() +  (int)(modifier/5));
	}

	@Override
	public GameObjectEntity getEntity() {
		return getItemEntity();
	}
	
	public void show(){
		if(getItemEntity().getAttributeType() !=null){
		 System.out.println("Item name: "+getItemEntity().getItemEnum()+" Type: "+getItemEntity().getAttributeType()+" Point: "+ getItemEntity().getPoint());
		}else if (getItemEntity().getEnchantmentType() !=null){
			System.out.println("Item name: "+getItemEntity().getItemEnum()+" Type: "+getItemEntity().getEnchantmentType()+" Point: "+ getItemEntity().getPoint());
		}
	}
}
