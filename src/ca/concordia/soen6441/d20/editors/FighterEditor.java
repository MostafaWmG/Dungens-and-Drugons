package ca.concordia.soen6441.d20.editors;

import java.util.List;
import java.util.Scanner;

import ca.concordia.soen6441.d20.ability.AbilityEnum;
import ca.concordia.soen6441.d20.attribute.ArmorClass;
import ca.concordia.soen6441.d20.attribute.AttackBonus;
import ca.concordia.soen6441.d20.attribute.AttributeEnum;
import ca.concordia.soen6441.d20.attribute.DamageBonus;
import ca.concordia.soen6441.d20.attribute.HitPoint;
import ca.concordia.soen6441.d20.factory.FighterDirector;
import ca.concordia.soen6441.d20.factory.BullyFighterBuilder;
import ca.concordia.soen6441.d20.factory.NimbleFighterBuilder;
import ca.concordia.soen6441.d20.factory.TankFighterBuilder;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.fighter.FighterEntity;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEntity;
import ca.concordia.soen6441.d20.item.ItemEnum;
import ca.concordia.soen6441.persistence.dao.DaoFactory;
/**
 * this is a characterEditor userInterface class
 */
public class FighterEditor {

	private Scanner scanner;
	private ArmorClass armorClass;
	private DamageBonus damageBonus;
	private AttackBonus attackBonus;
	private HitPoint hitPoint;
	private int level;
	
	public FighterEditor() {
		System.out.println("<<<Character Edtior Section>>>");
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
		FighterDirector fighterDirector;
		Fighter character = null;
		fighterDirector = new FighterDirector();
		System.out.println("Please Enter Your Character Name : ");
		String charName = scanner.nextLine();
		System.out.println("Do you want to create Enemy or Player? : ");
		String charEOP = scanner.nextLine();
		System.out.println("Pleas Enter Your Character Type :(Bully,Nimble,Tank) ");
		String charType = scanner.nextLine();

		if(charType.equalsIgnoreCase("Bully")){
			BullyFighterBuilder bullyFighterBuilder = new BullyFighterBuilder();
			fighterDirector.setFighterBuilder(bullyFighterBuilder);
			fighterDirector.constructFighter(charEOP, charName);
			character = fighterDirector.getFighterBuilder();
		}else if(charType.equalsIgnoreCase("Nimble")){
			NimbleFighterBuilder nimbleFighterBuilder = new NimbleFighterBuilder();
			fighterDirector.setFighterBuilder(nimbleFighterBuilder);
			fighterDirector.constructFighter(charEOP, charName);
			character = fighterDirector.getFighterBuilder();
		}else if(charType.equalsIgnoreCase("Tank")){
			TankFighterBuilder tankFighterBuilder = new TankFighterBuilder();
			fighterDirector.setFighterBuilder(tankFighterBuilder);
			fighterDirector.constructFighter(charEOP, charName);
			character = fighterDirector.getFighterBuilder();
		}

		if(character != null){
			System.out.println("Do you want to save your character (yes or no )?");
			String result = scanner.nextLine();

			if(result.equals("yes")){
				DaoFactory.getFighterDao().create(character.getCharacterEntity());
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

		List<FighterEntity> list = DaoFactory.getFighterDao().findByName(characterLoaded);
		if (list.isEmpty())
		{
			//TODO use appropriate procedure
			System.out.println("Invalid map name");
			return;
		}
		Fighter character = (Fighter) list.get(0).createModel();
		System.out.println("Edit WornItems :(Type i) , Edit Abilities :(Type a) , Edit Attributes: (Type t) , Inventory:(Type: n)");
		String hitButton = scanner.nextLine();
	
		// we need to create all this characters throw character factory;
		if(hitButton.equals("i")){
			editItem(character);
		}else if (hitButton.equals("a")){
				editAbility(character);
		}else if (hitButton.equals("t")){
			editAttribute(character);
		}else if (hitButton.equals("o")){
			addItem(character);
		}else if (hitButton.equals("n")){
			inventory(character);
		}else{
			System.out.println("Error");
		}
		//		Scanner.close();
	}
	
	/**
	 * this is a method for showing inventory and editing it
	 * @param character selected character
	 */
	private void inventory(Fighter character){
		character.showInvetory();
		
		System.out.println("Choose: move Item From Character To backPack(Type c)" );
		System.out.println("Choose: move Item From backPack To Character(Type i)" );
		System.out.println("Choose: switch Item Between Character To backPack (Type s)");
		System.out.println("Choose: add from your saved items into inventory (Type a )");
		String hitButton = scanner.nextLine();
		
		if(hitButton.equals("c")){
			characterToInventory(character);
		}else if (hitButton.equals("i")){
			inventoryToCharacter(character);
		}else if (hitButton.equals("s")){
			switchItems(character);
		}else if (hitButton.equals("a")){
			addItem(character);
		}else{
			System.out.println("Error");
		}
	}
	
	/**
	 * move item from character wearList to backPack
	 * @param character loaded character
	 */
	private void characterToInventory(Fighter character){
		System.out.println("Enter your Item Model from Character");
		String itemModel = scanner.nextLine();
		
		try {
			boolean reulst  = character.putWearItemIntoBackPack(character.getItem(ItemEnum.valueOf(itemModel.toUpperCase())));
			if(reulst){
				character.showInvetory();
				character.showAbilities();
				character.showAttributes();
			}

			saveCharacterChanges(character, "inventory");
		} catch (Exception e) {
			System.out.println("Wrong Info");
		}
	}
	
	/**
	 * move item from character backPack to wearList
	 * @param character loaded character
	 */
	private void inventoryToCharacter(Fighter character){
		System.out.println("Enter Slot Number Of Item In BackPack You Want To Wear: ");
		String itemModelString = scanner.nextLine();
		
//		try {
			int itemModel = Integer.parseInt(itemModelString);
			boolean result =character.putBackPackIntoWearList(character.getBackPack().get(itemModel));
			if(result){
				character.showInvetory();
				character.showAbilities();
				character.showAttributes();
			}

			saveCharacterChanges(character, "inventory");
//		} catch (Exception e) {
//			System.out.println("Wrong Info");
//		}
	}
	
	/**
	 * switch items between character and backPack
	 * @param character selected character
	 */
	private void switchItems(Fighter character){
		System.out.println("Enter Slot Number In backPack: ");
		String itemNumberString = scanner.nextLine();
		System.out.println("Enter your Item Model from Character: ");
		String itemModel = scanner.nextLine();
		
		try {
			int itemNumber = Integer.parseInt(itemNumberString);
			boolean result =character.switchWearItemWithBackPack(character.getItem(ItemEnum.valueOf(itemModel.toUpperCase())), character.getBackPack().get(itemNumber));
			if(result){
				character.showInvetory();
				character.showAbilities();
				character.showAttributes();
			}

			saveCharacterChanges(character, "inventory");
		} catch (Exception e) {
			System.out.println("Wrong Info");
		}
	}
	
	/**
	 * this is a method for adding items from file to character.
	 * @param character character
	 */
	private void addItem(Fighter character){
		System.out.println("Load the Item:");
		System.out.println("Name:");
		String itemLoaded = scanner.nextLine();
		System.out.println("Choose : Add to BackPack (Type b)");
		System.out.println("Choose : Add to Character (Type c)");

		List<ItemEntity> list = DaoFactory.getItemDao().findByName(itemLoaded);
		if (list.isEmpty())
		{
			//TODO use appropriate procedure
			System.out.println("Invalid map name");
			return;
		}
		Item item = (Item) list.get(0).createModel();
		String hitButton = scanner.nextLine();
		
		if(hitButton.equals("c")){
//			Item item = new Item(itemLoaded,ItemEnum.BELT,AbilityEnum.STRENGTH,+4);
			boolean result = character.putOnItem(item);
			if(!result){
				character.showInvetory();
				character.showAbilities();
				character.showAttributes();
			}
			saveCharacterChanges(character, "inventory");
		}else if (hitButton.equals("b")){
//			Item item = new Item(itemLoaded,ItemEnum.BELT,AbilityEnum.STRENGTH,+4);
//			boolean result = character.addBackPack(item);
			character.addBackPack(item);
//			if(result){
				character.showInvetory();
				character.showAbilities();
				character.showAttributes();
//			}
			saveCharacterChanges(character, "inventory");
		}else{
			System.out.println("Error");
		}
		

	}
	
	/**
	 * edit a specific item on a character 
	 * @param character character
	 */
	private void editItem(Fighter character){
		character.showItems();
		System.out.println("Enter Item Name:");
		String itemName = scanner.nextLine();
		ItemEnum itemEnum = ItemEnum.valueOf(itemName.toUpperCase());
		if(character.getItem(itemEnum).getAttributeType() ==null && character.getItem(itemEnum).getEnchantmentType() == null  ){
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
	public void changeItemType(Fighter character,ItemEnum itemEnum){
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
	public void changeItemPoint(Fighter character,ItemEnum itemEnum){
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
	private void editAbility(Fighter character){
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
	private void editAttribute(Fighter character){
		String armorCheck = "armorClassArmorClassarmorclass";
		String attackCheck = "attackBonusAttackBonusattackbonus";
		String damageCheck = "damageBonusDamageBonusdamagebonus";
		String hitPointCheck = "hitPointHitPointhitpoint";
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
	private void changeAttribute(Fighter character,int i ){
		
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
	private void saveCharacterChanges(Fighter character , String section){
		if (section.equals("inventory")){
			System.out.println("Do you want to back to inventory :(yes or no)");
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
			}else if (section.equals("inventory")){
				inventory(character);
			}
		}else if (answer.equals("no")){
			System.out.println("Do you want to save character changes:(yes or no)");
			answer = scanner.nextLine();
			if(answer.equals("yes")){
				DaoFactory.getFighterDao().create(character.getCharacterEntity());
			}else {
				
			}
		}else {
			System.out.println("Error IO");
		}
	}
}


