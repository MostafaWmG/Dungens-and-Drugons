package ca.concordia.soen6441.d20.factory;

import ca.concordia.soen6441.d20.ability.AbilityEnum;
import ca.concordia.soen6441.d20.attribute.AttributeEnum;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;

public class ItemFactory {

	/**
	 * this method creates new items.
	 * @param name the name of the item it is also pk(primary key) for hibernet.
	 * @param itemEnum the type of the item we will create.
	 * @param enchantmentType the ability of character that this item will enchant.
	 * @return Item the new item that created user.
	 */
	public Item createItem(String name,ItemEnum itemEnum, AbilityEnum enchantmentType) {
		
			if(itemEnum == ItemEnum.HELMET){
				if(enchantmentType == AbilityEnum.INTELLIGENCE ||enchantmentType == AbilityEnum.WISDOM){
					return new Item(name,itemEnum,enchantmentType,+1);
				}else{
					return null;
				}
			}else if(itemEnum == ItemEnum.ARMOR){
				return null;
			}else if(itemEnum == ItemEnum.SHIELD){
				return null;
			}else if(itemEnum == ItemEnum.RING){
				if(enchantmentType == AbilityEnum.STRENGTH || enchantmentType == AbilityEnum.CONSTITUTION || enchantmentType == AbilityEnum.WISDOM || enchantmentType == AbilityEnum.CHARISMA){
					return new Item(name,itemEnum,enchantmentType,+1);
				}else{
					return null;
				}
			}else if(itemEnum == ItemEnum.BELT){
				if(enchantmentType == AbilityEnum.CONSTITUTION || enchantmentType == AbilityEnum.STRENGTH){
					return new Item(name,itemEnum,enchantmentType,+1);
				}else{
					return null;
				}
			}else if(itemEnum == ItemEnum.BOOTS){
				if(enchantmentType == AbilityEnum.DEXTERITY){
					return new Item(name,itemEnum,enchantmentType,+1);
				}else{
					return null;
				}
			}else if(itemEnum == ItemEnum.WEAPON){
				return null;
			}else{
				return null;
			}
			
	}

	/**
	 * this method creates new items.
	 * @param itemEnum the type of the item we will create.
	 * @param attributeType the ability of character that this item will enchant.
	 * @return Item the new item that created user.
	 */
	public Item createItem(String name,ItemEnum itemEnum, AttributeEnum attributeType) {
		
		if(itemEnum == ItemEnum.HELMET){
			if(attributeType == AttributeEnum.ARMORCLASS){
				return new Item(name,itemEnum,attributeType,+1);
			}else{
				return null;
			}
		}else if(itemEnum == ItemEnum.ARMOR){
			if(attributeType == AttributeEnum.ARMORCLASS ){
				return new Item(name,itemEnum,attributeType,+1);
			}else{
				return null;
			}
		}else if(itemEnum == ItemEnum.SHIELD){
			if(attributeType == AttributeEnum.ARMORCLASS ){
				return new Item(name,itemEnum,attributeType,+1);
			}else{
				return null;
			}
		}else if(itemEnum == ItemEnum.RING){
			if(attributeType == AttributeEnum.ARMORCLASS ){
				return new Item(name,itemEnum,attributeType,+1);
			}else{
				return null;
			}
		}else if(itemEnum == ItemEnum.BELT){
			return null;
		}else if(itemEnum == ItemEnum.BOOTS){
			if(attributeType == AttributeEnum.ARMORCLASS){
				return new Item(name,itemEnum,attributeType,+1);
			}else{
				return null;
			}
		}else if(itemEnum == ItemEnum.WEAPON){
			if(attributeType == AttributeEnum.ATTACKBONUS || attributeType == AttributeEnum.DAMAGEBONUS){
				return new Item(name,itemEnum,attributeType,+1);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
}
