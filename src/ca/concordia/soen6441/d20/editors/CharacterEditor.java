package ca.concordia.soen6441.d20.editors;

import java.util.Scanner;

import ca.concordia.soen6441.d20.character.Character;
import ca.concordia.soen6441.d20.character.factory.PlayerFactory;
import ca.concordia.soen6441.d20.item.AbilityEnum;
import ca.concordia.soen6441.d20.item.ArmorClass;
import ca.concordia.soen6441.d20.item.AttackBonus;
import ca.concordia.soen6441.d20.item.AttributeEnum;
import ca.concordia.soen6441.d20.item.DamageBonus;
import ca.concordia.soen6441.d20.item.HitPoint;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;
/**
 * this is a characterEditor userInterface class
 */
public class CharacterEditor {

	private Scanner scanner;
	private ArmorClass armorClass;
	private DamageBonus damageBonus;
	private AttackBonus attackBonus;
	private HitPoint hitPoint;
	private int level;
	
	public CharacterEditor() {
		System.out.println("Character Edtior Section: ");
		System.out.println("Choose Section : ");
		System.out.println("EditCharacter:(Type e) , CreateCharacter : (Type C) " );
		scanner = new Scanner(System.in);
		String hitButton = scanner.nextLine();

		if(hitButton.equals("c")){
			createCharacter();
		}else if (hitButton.equals("e")){
			editCharacter();
		}else {
			System.out.println("Error");
		}
	}

	/**
	 * this a method for creating character
	 */
	private void createCharacter(){
		System.out.println("Please Enter Your Character Name : ");
		String charName = scanner.nextLine();
		System.out.println("Please Enter Your Character Type : ");
		String charType = scanner.nextLine();
		PlayerFactory PlayerFactory = new PlayerFactory();
		Character character = PlayerFactory.create(charType,charName);

		if(character != null){
			System.out.println("Do you want to save your character (yes or no )?");
			String result = scanner.nextLine();

			if(result.equals("yes")){
				// save the character into file  . you need to save character object into file @saman @negar.
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
	 * this a method for editing character
	 */
	private void editCharacter(){
		System.out.println("Load the character:");
		System.out.println("Name:");
		String characterLoaded = scanner.nextLine();
		// here we need to call the load function to load character from file 
		// Character character = load(characterLoaded);
		System.out.println("Edit item :(Type i) , Edit Ability :(Type a) , Edit Attribute: (Type t) ,Add Own Items:(Type o)");
		String hitButton = scanner.nextLine();
	
		// we need to create all this characters throw character factory;
		if(hitButton.equals("i")){
			//			editItem(character)
			editItem(new Character("Player","randomName"));
		}else if (hitButton.equals("a")){
			//editAbility(character)
				editAbility( new Character("Player","randomName"));
		}else if (hitButton.equals("t")){
//						editAttribute(character);
			editAttribute(new Character("Player","randomName"));
		}else if (hitButton.equals("o")){
			//addItem(character);
			addItem(new Character("Player","randomName"));
		}else{
			System.out.println("Error");
		}
		//		Scanner.close();
	}
	
	/**
	 * this is a method for adding items from file to character.
	 * @param character character
	 */
	private void addItem(Character character){
		System.out.println("Load the Item:");
		System.out.println("Name:");
		String itemLoaded = scanner.nextLine();
		// here we need to call the load function to load item from file 
		// Item item = load(itemLoaded);
		// this is just for a test.
		Item item = new Item(itemLoaded,ItemEnum.BELT,AbilityEnum.STRENGTH,+4);
		character.putOnItem(item);
		character.show();
		saveCharacterChanges(character, "add");
	}
	
	/**
	 * edit a specific item on a character 
	 * @param character character
	 */
	private void editItem(Character character){
		character.showItems();
		System.out.println("Enter Item Name:");
		String itemName = scanner.nextLine();
		ItemEnum itemEnum = ItemEnum.valueOf(itemName.toUpperCase());
		if(character.getItem(itemEnum) == null){
			System.out.println(itemEnum + " empty slot !!!");
		}else{
			character.getItem(itemEnum).show();
			
			System.out.println("Choose to change : (Item Point : Type p), (Item Type : Type t)");
			String change = scanner.nextLine();
			if(change.equals("p")){
				changeItemPoint(character,itemEnum);
			}else if (change.equals("t")){
				changeItemType(character,itemEnum);
			}else{
				System.out.println("Error");
			}
		}
	}
	
	/**
	 * this a method for changing item type
	 * @param character character 
	 * @param itemEnum	item that is changing
	 */
	public void changeItemType(Character character,ItemEnum itemEnum){
		boolean enumChecker = false;
		
		System.out.println("Enter new item type : ");
		String itemTypeString = scanner.nextLine();
		
		try {
			AbilityEnum.valueOf(itemTypeString.toUpperCase());
//			System.out.println("phase1: " +enumChecker);
		} catch (IllegalArgumentException e) {
			enumChecker = true;
//			System.out.println("phase2: "+ enumChecker);
		}
		
		try {
			//ability
			if(!enumChecker){
				Item newItem = new Item(character.getItem(itemEnum));
				character.removeItem(character.getItem(itemEnum));
				if(newItem.getEnchantmentType() == null){
					newItem.setAttributeType(null);
					newItem.setEnchantmentType(AbilityEnum.valueOf(itemTypeString.toUpperCase()));
				}else {
					newItem.setEnchantmentType(AbilityEnum.valueOf(itemTypeString.toUpperCase()));
				}
				
				if(newItem.validate(newItem)){
					character.putOnItem(newItem);
					character.getItem(itemEnum).show();
					character.showAbilities();
					character.showAttributes();
					saveCharacterChanges(character, "Item");
				}else{
					System.out.println("item is not valid by d20 rules");
				}

			}else if(enumChecker){
				Item newItem = new Item(character.getItem(itemEnum));
				character.removeItem(character.getItem(itemEnum));
				if(newItem.getAttributeType() == null){
					newItem.setEnchantmentType(null);
					newItem.setAttributeType(AttributeEnum.valueOf(itemTypeString.toUpperCase()));
				}else {
					newItem.setAttributeType(AttributeEnum.valueOf(itemTypeString.toUpperCase()));
				}
				
				if(newItem.validate(newItem)){
					character.putOnItem(newItem);
					character.getItem(itemEnum).show();
					character.showAbilities();
					character.showAttributes();
					saveCharacterChanges(character, "Item");
				}else{
					System.out.println("item is not valid by d20 rules");
				}
			}
		} catch (Exception e) {
			System.out.println("Wrong input");
		}

	}
	
	/**
	 * this is a method for changing itemPoint
	 * @param character character
	 * @param itemEnum item that is changing
	 */
	public void changeItemPoint(Character character,ItemEnum itemEnum){
		System.out.println("Enter number to change Point:");


		String number = scanner.nextLine();
		int numberScore = Integer.parseInt(number);
		Item newItem = new Item(character.getItem(itemEnum));
		newItem.setEnchantmentPoint(numberScore);
		character.removeItem(character.getItem(itemEnum));
		character.putOnItem(newItem);
		character.getItem(itemEnum).show();
		//test only
		character.showAbilities();
		saveCharacterChanges(character, "Item");

	}
	
	/**
	 * this method is for changing the ability of a character [intelligence,wisdom,etc..]
	 * @param character
	 */
	private void editAbility(Character character){
		character.showAbilities();
		System.out.println("Enter Ability Name:");
		String abilityName = scanner.nextLine();
		AbilityEnum abilityEnum = AbilityEnum.valueOf(abilityName.toUpperCase());
		character.getAbility(abilityEnum).show();
		

			System.out.println("Enter number to change Score:");

			try{
				String number = scanner.nextLine();
				int numberScore = Integer.parseInt(number);
				character.getAbility(abilityEnum).setScore(numberScore);
				character.getAbility(abilityEnum).synceModifier();
				character.getAbility(abilityEnum).show();
				
				saveCharacterChanges(character, "ability");
				
			}catch(Exception e){
				System.out.println("Error not int");
			}
	}
	
	/**
	 * edit attribute of the character [ArmorClass,DamageBonus,AttackBonus,HitPoint,Level]
	 * @param character character
	 */
	private void editAttribute(Character character){
		String armorCheck = "armorClassArmorClassarmorclass";
		String attackCheck = "attackBonusAttackBonusattackbonus";
		String damageCheck = "damageBonusDamageBonusdamagebonus";
		String hitPointCheck = "hitPointHitPointhitpoin";
		String leveCheck = "levelLevel";
		
		character.showAttributes();
		System.out.println("Level: " + character.getLevel());
		System.out.println("Enter Attribute Name:");
		String attributeName = scanner.nextLine();
		
		if(armorCheck.contains(attributeName)){
			armorClass = character.getArmor();
			armorClass.showPoint();
			changeAttribute(character,0);
		}else if (attackCheck.contains(attributeName)){
			attackBonus = character.getAttack();
			attackBonus.showPoint();
			changeAttribute(character,1);
		}else if (damageCheck.contains(attributeName)){
			damageBonus = character.getDamage();
			damageBonus.showPoint();
			changeAttribute(character,2);
		}else if (hitPointCheck.contains(attributeName)){
			hitPoint = character.getHitPoint();
			hitPoint.showPoint();
			changeAttribute(character,3);
		}else if (leveCheck.contains(attributeName)){
			level = character.getLevel();
			System.out.println("Level: "+ level);
			changeAttribute(character,4);
		}
	}
	
	// Because we don't define a Attribute class earlier its a messy code we need to re factor later
	/**
	 *  this a method that choose which attribute should changes.
	 * @param character character
	 * @param i which attribute we are changing
	 */
	private void changeAttribute(Character character,int i ){
		
		String number;
		int numberScore;
		System.out.println("Enter number to change Score:");

		try{
			number = scanner.nextLine();
		    numberScore = Integer.parseInt(number);
			
			switch (i) {
			case 0:
				armorClass.setBase(numberScore);
				armorClass.setModifier(0);
				armorClass.showPoint();
				break;
			case 1:
				attackBonus.setBase(numberScore);
				attackBonus.setModifier(0);
				attackBonus.showPoint();
				break;
			case 2:
				damageBonus.setBase(numberScore);
				damageBonus.setModifier(0);
				damageBonus.showPoint();
				break;
			case 3:
				hitPoint.setBase(numberScore);
				hitPoint.setModifier(0);
				hitPoint.showPoint();
				break;
			case 4:
				character.setLevel(numberScore);
				System.out.println("Level :" + character.getLevel());
				break;
			default:
				break;
			}
			
			saveCharacterChanges(character, "attribute");
			
		}catch(Exception e){
			System.out.println("Error not int");
		}

	}
	
	/**
	 * this is a method that saves the character changes
	 * @param character character
	 * @param section which part of character is changing
	 */
	private void saveCharacterChanges(Character character , String section){
		if(section.equals("add")){
			System.out.println("Do you want to add another item :(yes or no)");
		}else{
			System.out.println("Do you want to change another "+section+":(yes or no)");
		}

		String answer = scanner.nextLine();
		
		if(answer.equals("yes")){
			if(section.equals("ability")){
				editAbility(character);
			}else if (section.equals("Item")){
				editItem(character);
			}else if (section.equals("attribute")){
				editAttribute(character);
			}else if (section.equals("add")){
				addItem(character);
			}
		}else if (answer.equals("no")){
			System.out.println("Do you want to save character changes:(yes or no)");
			answer = scanner.nextLine();
			if(answer.equals("yes")){
				// save(character.name);
			}else {
				
			}
		}else {
			System.out.println("Error IO");
		}
	}
}


