package ca.concordia.soen6441.d20.character.factory;

import ca.concordia.soen6441.d20.character.Character;
import ca.concordia.soen6441.d20.character.Player;
import ca.concordia.soen6441.d20.dice.Dice;
import ca.concordia.soen6441.d20.item.AbilityEnum;
import ca.concordia.soen6441.d20.item.AttributeEnum;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;

/**
 * this a Factory class for creating character and items of the character.
 * @author wmg
 * @author alvaro
 */
public class PlayerFactory implements CharacterFactory {
	
	Dice dice ;
	Character character;
	
	/**
	 * constructor
	 * create a new dice 
	 */
	public PlayerFactory(){
		dice = new Dice();
	}
	
	/**
	 * create a character 
	 * @param name specify the name of the character
	 * @param tag  specify the type of the character
	 */
	@Override
	public Character create(String tag,String name) {
		character = new Player(tag,name);
		character.showAbilities();
		character.getArmor().showPoint();
		character.getAttack().showPoint();
		character.getDamage().showPoint();
		return character;
	}
	
	/**
	 * this method creates new items.
	 * @param itemEnum the type of the item we will create.
	 * @param enchantmentType the ability of character that this item will enchant.
	 * @return Item the new item that created user.
	 */
	@Override
	public Item createItem(ItemEnum itemEnum, AbilityEnum enchantmentType) {
		
			if(itemEnum == ItemEnum.HELMET){
				if(enchantmentType == AbilityEnum.INTELLIGENCE ||enchantmentType == AbilityEnum.WISDOM){
					return new Item(itemEnum,enchantmentType,+1);
				}else{
					return null;
				}
			}else if(itemEnum == ItemEnum.ARMOR){
				return null;
			}else if(itemEnum == ItemEnum.SHIELD){
				return null;
			}else if(itemEnum == ItemEnum.RING){
				if(enchantmentType == AbilityEnum.STRENGTH || enchantmentType == AbilityEnum.CONSTITUTION || enchantmentType == AbilityEnum.WISDOM || enchantmentType == AbilityEnum.CHARISMA){
					return new Item(itemEnum,enchantmentType,+1);
				}else{
					return null;
				}
			}else if(itemEnum == ItemEnum.BELT){
				if(enchantmentType == AbilityEnum.CONSTITUTION || enchantmentType == AbilityEnum.STRENGTH){
					return new Item(itemEnum,enchantmentType,+1);
				}else{
					return null;
				}
			}else if(itemEnum == ItemEnum.BOOTS){
				if(enchantmentType == AbilityEnum.DEXTERITY){
					return new Item(itemEnum,enchantmentType,+1);
				}else{
					return null;
				}
			}else if(itemEnum == ItemEnum.WEAPON){
				return null;
			}else{
				return null;
			}
			
	}

	@Override
	public Item createItem(ItemEnum itemEnum, AttributeEnum attributeType) {
		
		if(itemEnum == ItemEnum.HELMET){
			if(attributeType == AttributeEnum.ARMORCLASS){
				return new Item(itemEnum,attributeType,+1);
			}else{
				return null;
			}
		}else if(itemEnum == ItemEnum.ARMOR){
			if(attributeType == AttributeEnum.ARMORCLASS ){
				return new Item(itemEnum,attributeType,+1);
			}else{
				return null;
			}
		}else if(itemEnum == ItemEnum.SHIELD){
			if(attributeType == AttributeEnum.ARMORCLASS ){
				return new Item(itemEnum,attributeType,+1);
			}else{
				return null;
			}
		}else if(itemEnum == ItemEnum.RING){
			if(attributeType == AttributeEnum.ARMORCLASS ){
				return new Item(itemEnum,attributeType,+1);
			}else{
				return null;
			}
		}else if(itemEnum == ItemEnum.BELT){
			return null;
		}else if(itemEnum == ItemEnum.BOOTS){
			if(attributeType == AttributeEnum.ARMORCLASS){
				return new Item(itemEnum,attributeType,+1);
			}else{
				return null;
			}
		}else if(itemEnum == ItemEnum.WEAPON){
			if(attributeType == AttributeEnum.ATTACKBOUNS || attributeType == AttributeEnum.DAMAGEBOUNS){
				return new Item(itemEnum,attributeType,+1);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	
	
}
