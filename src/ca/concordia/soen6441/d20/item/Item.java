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
	
	public Item(String name,ItemEnum item,AbilityEnum enchantmentType, int point) {
		initEmptyEntity();
		setName(name);
		getItemEntity().setItemEnum(item);
		getItemEntity().setEnchantmentType(enchantmentType);
		getItemEntity().setAttributeType(null);
		getItemEntity().setPoint(point);
	}
	
	public Item(String name,ItemEnum item,AttributeEnum attributeType, int point){
		initEmptyEntity();
		setName(name);
		getItemEntity().setItemEnum(item);
		getItemEntity().setAttributeType(attributeType);
		getItemEntity().setEnchantmentType(null);
		getItemEntity().setPoint(point);
	}
	
	/**
	 * null item
	 * @param name item
	 */
	public Item(String name,ItemEnum itemEnum){
		initEmptyEntity();
		setName(name);
		getItemEntity().setItemEnum(itemEnum);
		getItemEntity().setEnchantmentType(null);
		getItemEntity().setAttributeType(null);
		getItemEntity().setPoint(-1);
	}
	
	/**
	 * copy constructor
	 * @param item the item needed to be copied.
	 */
	public Item(Item item){
		initEmptyEntity();
		getItemEntity().setName(item.getName());
		getItemEntity().setItemEnum(item.getItemEnum() );
		getItemEntity().setAttributeType(item.getAttributeType());
		getItemEntity().setEnchantmentType(item.getEnchantmentType());
		getItemEntity().setPoint(item.getEnchantmentPoint());
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
		 System.out.println("Item name: "+ getItemEntity().getName()+" Item model: "+getItemEntity().getItemEnum()+" Type: "+getItemEntity().getAttributeType()+" Point: "+ getItemEntity().getPoint());
		}else if (getItemEntity().getEnchantmentType() !=null){
			System.out.println("Item name: "+ getItemEntity().getName()+" Item model: "+getItemEntity().getItemEnum()+" Type: "+getItemEntity().getEnchantmentType()+" Point: "+ getItemEntity().getPoint());
		}
	}
	
	public boolean validate(Item item){
		if(item.getItemEnum() == ItemEnum.HELMET){
			if(item.getEnchantmentType() == null){
				if(item.getAttributeType() == AttributeEnum.ARMORCLASS){
					return true;
				}else {
					return false;
				}
			}else{
				if(item.getEnchantmentType() == AbilityEnum.INTELLIGENCE || item.getEnchantmentType() == AbilityEnum.WISDOM){
					return true;
				}else{
					return false;
				}
			}
		}else if(item.getItemEnum() == ItemEnum.ARMOR){
			if(item.getEnchantmentType() == null){
				if(item.getAttributeType() == AttributeEnum.ARMORCLASS){
					return true;
				}else {
					return false;
				}
			}else{
				return false;
			}
		}else if (item.getItemEnum() == ItemEnum.SHIELD){
			if(item.getEnchantmentType() == null){
				if(item.getAttributeType() == AttributeEnum.ARMORCLASS){
					return true;
				}else {
					return false;
				}
			}else{
				return false;
			}
		}else if (item.getItemEnum() == ItemEnum.RING){
			if(item.getEnchantmentType() == null){
				if(item.getAttributeType() == AttributeEnum.ARMORCLASS){
					return true;
				}else {
					return false;
				}
			}else{
				if(item.getEnchantmentType() == AbilityEnum.STRENGTH || item.getEnchantmentType() == AbilityEnum.CONSTITUTION ||item.getEnchantmentType() == AbilityEnum.WISDOM ||item.getEnchantmentType() == AbilityEnum.CHARISMA){
					return true;
				}else{
					return false;
				}
			}
		}else if (item.getItemEnum() == ItemEnum.BELT){
			if(item.getEnchantmentType() == null){
				return false;
			}else{
				if(item.getEnchantmentType() == AbilityEnum.STRENGTH || item.getEnchantmentType() == AbilityEnum.CONSTITUTION){
					return true;
				}else{
					return false;
				}
			}
		}else if (item.getItemEnum() == ItemEnum.BOOTS) {
			if(item.getEnchantmentType() == null){
				if(item.getAttributeType() == AttributeEnum.ARMORCLASS){
					return true;
				}else {
					return false;
				}
			}else{
				if(item.getEnchantmentType() == AbilityEnum.DEXTERITY ){
					return true;
				}else{
					return false;
				}
			}
		}else if (item.getItemEnum() == ItemEnum.WEAPON) {
			if(item.getEnchantmentType() == null){
				if(item.getAttributeType() == AttributeEnum.ATTACKBONUS ||item.getAttributeType() == AttributeEnum.DAMAGEBONUS ){
					return true;
				}else {
					return false;
				}
			}else{
				return false;
			}
		}else {
			return false;
		}
	}
}
