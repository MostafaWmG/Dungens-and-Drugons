package ca.concordia.soen6441.d20.item;



import ca.concordia.soen6441.d20.ability.AbilityEnum;
import ca.concordia.soen6441.d20.attribute.AttributeEnum;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;
import ca.concordia.soen6441.d20.item.decorator.IItem;

/**
 * every new Item class represent an actual item . 
 * @author wmg
 */
public class Item extends GameObject implements ILevelUp,IItem{
	
	private ItemEntity itemEntity;
	
	public Item(String name,ItemEnum item,AbilityEnum enchantmentType, int point) {
		initEmptyEntity();
		setName(name);
		setTag("Item");
		getItemEntity().setItemEnum(item);
		getItemEntity().setEnchantmentType(enchantmentType);
		getItemEntity().setAttributeType(null);
		getItemEntity().setPoint(point);
	}
	
	public Item(String name,ItemEnum item,AttributeEnum attributeType, int point){
		initEmptyEntity();
		setTag("Item");
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
		setTag("Item");
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
		setTag("Item");
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
	/**
	 * sets given entity to the ItemEntity
	 */
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

	/**
	 * 
	 * @return point of the 
	 */
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
	
	/**
	 * this function will update the items if some of related attributes changes. using LevelUp interface
	 */
	@Override
	public void update(int modifier) {
		if(modifier >= 17){
			setEnchantmentPoint(5);
		}else if (modifier <=0){
			setEnchantmentPoint(1);
		}else {
			setEnchantmentPoint((int)Math.ceil(modifier/4) + 1);
		}
		
	}

	@Override
	public GameObjectEntity getEntity() {
		return getItemEntity();
	}
	
	/**
	 * show item
	 */
	public String show(){
		return show(true);
	}
	
	/**
	 * show item
	 */
	public String show(boolean consol){
		String s = "";
		if(consol){
			if(getItemEntity().getAttributeType() !=null){
				 System.out.println("Item name: "+ getItemEntity().getName()+" Item model: "+getItemEntity().getItemEnum()+" Type: "+getItemEntity().getAttributeType()+" Point: "+ getItemEntity().getPoint());
				}else if (getItemEntity().getEnchantmentType() !=null){
					System.out.println("Item name: "+ getItemEntity().getName()+" Item model: "+getItemEntity().getItemEnum()+" Type: "+getItemEntity().getEnchantmentType()+" Point: "+ getItemEntity().getPoint());
				}	
		}else{
			if(getItemEntity().getAttributeType() !=null){
				 s = s.concat("Item name: "+ getItemEntity().getName()+" Item model: "+getItemEntity().getItemEnum()+" Type: "+getItemEntity().getAttributeType()+" Point: "+ getItemEntity().getPoint() + "\n"); 
				}else if (getItemEntity().getEnchantmentType() !=null){
				s = s.concat("Item name: "+ getItemEntity().getName()+" Item model: "+getItemEntity().getItemEnum()+" Type: "+getItemEntity().getEnchantmentType()+" Point: "+ getItemEntity().getPoint()+"\n");
				}
		}
		
		return s;
	}
	
	/**
	 * check if item is valid or not
	 * @param item selected item
	 * @return if item is valid or not
	 */
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
	
	/**
	 * set the tag
	 */
	@Override
	public void setTag(String tag){
		getItemEntity().setItemTag(tag);
	}
	
	/**
	 * return the tag
	 */
	@Override
	public String getTag(){
		return getItemEntity().getItemTag();
	}

	@Override
	public Fighter specialEffect(Fighter attacker,Fighter target) {
		return target;
	}
}
