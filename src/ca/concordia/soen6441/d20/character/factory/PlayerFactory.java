package ca.concordia.soen6441.d20.character.factory;

import ca.concordia.soen6441.d20.character.Character;
import ca.concordia.soen6441.d20.character.Player;
import ca.concordia.soen6441.d20.item.AbilityEnum;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;

/**
 * this a Factory class for creating character and items of the character.
 * @author wmg
 * @author alvaro
 */
public class PlayerFactory implements CharacterFactory {

	//TODOI: here we ned to implement the 3d20+1 dice to create a player randomly
	@Override
	public Character create(String tag) {
		return new Player(tag);
	}
	
	@Override
	public Item createItem(ItemEnum itemEnum, AbilityEnum enchantmentType) {
		
			if(itemEnum == ItemEnum.HELMET){
				if(enchantmentType == AbilityEnum.INTELLIGENCE ||enchantmentType == AbilityEnum.WISDOM || enchantmentType == AbilityEnum.ARMORCLASS){
					return new Item(itemEnum,enchantmentType,+1);
				}else{
					return null;
				}
			}else if(itemEnum == ItemEnum.ARMOR){
				if(enchantmentType == AbilityEnum.ARMORCLASS ){
					return new Item(itemEnum,enchantmentType,+1);
				}else{
					return null;
				}
			}else if(itemEnum == ItemEnum.SHIELD){
				if(enchantmentType == AbilityEnum.ARMORCLASS ){
					return new Item(itemEnum,enchantmentType,+1);
				}else{
					return null;
				}
			}else if(itemEnum == ItemEnum.RING){
				if(enchantmentType == AbilityEnum.ARMORCLASS || enchantmentType == AbilityEnum.STRENGTH || enchantmentType == AbilityEnum.CONSTITUTION || enchantmentType == AbilityEnum.WISDOM || enchantmentType == AbilityEnum.CHARISMA){
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
				if(enchantmentType == AbilityEnum.ARMORCLASS || enchantmentType == AbilityEnum.DEXTERITY){
					return new Item(itemEnum,enchantmentType,+1);
				}else{
					return null;
				}
			}else if(itemEnum == ItemEnum.WEAPON){
				if(enchantmentType == AbilityEnum.ATTACKBOUNS || enchantmentType == AbilityEnum.DAMAGEBOUNS){
					return new Item(itemEnum,enchantmentType,+1);
				}else{
					return null;
				}
			}else{
				return null;
			}
			
	}

}
