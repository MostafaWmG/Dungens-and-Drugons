package ca.concordia.soen6441.d20.character.factory;

import ca.concordia.soen6441.d20.character.Character;
import ca.concordia.soen6441.d20.character.Player;
import ca.concordia.soen6441.d20.dice.Dice;
import ca.concordia.soen6441.d20.item.Ability;
import ca.concordia.soen6441.d20.item.AbilityEnum;
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
		setCharacterAbility();
		character.updateAll();
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

	/**
	 * randomly determining the character abilites using dice6 and d20 rules
	 */
	private void setCharacterAbility(){
		
		int roll = 0;
		
		for (int i= 0 ; i < AbilityEnum.values().length ; i++ ){
			
			roll = 0 ;
			roll = dice.roll6() + dice.roll6() + dice.roll6() ;
			
			System.out.println("DEBUGLOG!! " + " character ability : " + AbilityEnum.values()[i] + " ,Score :  " + roll + " ,modifier : " + (int)Math.floor( (roll - 10) /2 ));
			// To determine an ability modifier without consulting the table, subtract 10 from the ability score and then divide the result by 2 (round down).
		    character.addAbility(new Ability(AbilityEnum.values()[i],roll,(int)Math.floor( (roll - 10) /2 )) );
		}
		
	}
	
}
