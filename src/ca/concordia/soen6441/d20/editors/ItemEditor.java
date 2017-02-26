package ca.concordia.soen6441.d20.editors;

import java.util.Scanner;

import ca.concordia.soen6441.d20.character.factory.ItemFactory;
import ca.concordia.soen6441.d20.item.AbilityEnum;
import ca.concordia.soen6441.d20.item.AttributeEnum;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;
/**
 * this a class for create/edit Items.
 * @author wmg
 *
 */
public class ItemEditor {
	private Scanner scanner;
	private ItemEnum itemEnum;
	
	public ItemEditor() {
		System.out.println("Item Builder Section: ");
		System.out.println("Choose Section : ");
		System.out.println("EditItem:(Type e) , CreateItem : (Type C) " );
		scanner = new Scanner(System.in);
		String hitButton = scanner.nextLine();
		
		if(hitButton.equals("c")){
			createItem();
		}else if (hitButton.equals("e")){
			editItem();
		}else {
			System.out.println("Error");
		}
	}
	
	/**
	 * this is a method for creating an item.
	 */
	private void createItem(){
		System.out.println("Please Enter Your Item Type : ");
		String itemType = scanner.nextLine();
		System.out.println("Please Enter Your enchantment Type : ");
		String enchantmentType = scanner.nextLine();
		convertStringToEnum(itemType);
		Item item = builder(enchantmentType);
		if(item !=null){
			System.out.println("Do you want to save your character (yes or no )?");
			String result = scanner.nextLine();
			
			if(result.equals("yes")){
				// save the character into file  . you need to save character object into file @saman @negar.
				// save(item);
				System.out.println("Character saved successfully");
			}else if (result.equals("no")){
				System.out.println("Character removed");
				
			}else {
				System.out.println("Error");
			}
		}else {
			System.out.println("Error null");
		}

		
//		scanner.close();
	}
	
	/**
	 * this a method for editing items.
	 */
	private void editItem(){

	}
	
	//need re factor  
	/**
	 * this a method that convert string from keyboard to enum that we needed.
	 * @param string a string from keyboard
	 */
	private void convertStringToEnum(String string){
		
		if(string .equals("helmet") || string.equals("Helmet")){
			itemEnum = ItemEnum.HELMET;
		}else if(string .equals("armor") || string.equals("Armor")){
			itemEnum = ItemEnum.ARMOR;
		}else if(string .equals("shield") || string.equals("Shield")){
			itemEnum = ItemEnum.SHIELD;
		}else if(string .equals("ring") || string.equals("Ring")){
			itemEnum = ItemEnum.RING;
		}else if(string .equals("belt") || string.equals("Belt")){
			itemEnum = ItemEnum.BELT;
		}else if(string .equals("boots") || string.equals("Boots")){
			itemEnum = ItemEnum.BOOTS;
		}else if(string .equals("weapon") || string.equals("Weapon")){
			itemEnum = ItemEnum.WEAPON;
		}
	}
	
	// need re factor 
	/**
	 * this a method that create items and save them
	 * @param string a string from keyboard
	 * @return Item that we need 
	 */
	private Item builder(String string){
		
		ItemFactory itemFactory = new ItemFactory();
		AbilityEnum enchantmentTypeEnum;
		AttributeEnum attributeEnum;
		
		if(string.equals("intelligence") || string.equals("Intelligence")){
			enchantmentTypeEnum =  AbilityEnum.INTELLIGENCE;
			return itemFactory.createItem(itemEnum,enchantmentTypeEnum);
		}else if ( string.equals("strength") || string.equals("Strength")){
			enchantmentTypeEnum =  AbilityEnum.STRENGTH;
			return itemFactory.createItem(itemEnum,enchantmentTypeEnum);
		}else if ( string.equals("constitution") || string.equals("Constitution")){
			enchantmentTypeEnum =  AbilityEnum.CONSTITUTION;
			return itemFactory.createItem(itemEnum,enchantmentTypeEnum);
		}else if ( string.equals("wisdom") || string.equals("Wisdom")){
			enchantmentTypeEnum =  AbilityEnum.WISDOM;
			return itemFactory.createItem(itemEnum,enchantmentTypeEnum);
		}else if ( string.equals("charisma") || string.equals("Charisma")){
			enchantmentTypeEnum =  AbilityEnum.CHARISMA;
			return itemFactory.createItem(itemEnum,enchantmentTypeEnum);
		}else if ( string.equals("dexterity") || string.equals("Dexterity")){
			enchantmentTypeEnum =  AbilityEnum.DEXTERITY;
			return itemFactory.createItem(itemEnum,enchantmentTypeEnum);
		}else if ( string.equals("armorClass") || string.equals("ArmorClass")){
			attributeEnum =  AttributeEnum.ARMORCLASS;
			return itemFactory.createItem(itemEnum,attributeEnum);
		}else if ( string.equals("attackBonus") || string.equals("AttackBonus")){
			attributeEnum =  AttributeEnum.ATTACKBONUS;
			return itemFactory.createItem(itemEnum,attributeEnum);
		}else if ( string.equals("damageBonus") || string.equals("DamageBonus")){
			attributeEnum =  AttributeEnum.DAMAGEBONUS;
			return itemFactory.createItem(itemEnum,attributeEnum);
		}else {
			return null;
		}
	}
}
