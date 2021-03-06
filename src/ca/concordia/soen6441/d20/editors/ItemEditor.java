package ca.concordia.soen6441.d20.editors;

import java.util.List;
import java.util.Scanner;

import ca.concordia.soen6441.d20.ability.AbilityEnum;
import ca.concordia.soen6441.d20.attribute.AttributeEnum;
import ca.concordia.soen6441.d20.factory.ItemFactory;
import ca.concordia.soen6441.d20.item.Chest;
import ca.concordia.soen6441.d20.item.ChestEntity;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEntity;
import ca.concordia.soen6441.d20.item.ItemEnum;
import ca.concordia.soen6441.persistence.dao.DaoFactory;
/**
 * This a class for is used to create/edit Items.
 * @author wmg
 *
 */
public class ItemEditor {
	/**
	 * read the input from the keyboard
	 */
	private Scanner scanner;

	
	public ItemEditor() {
		System.out.println("Item Builder Section: ");
		System.out.println("Choose Section : ");
		System.out.println("EditItem:(Type e) , CreateItem : (Type C) ,CreateChest:(Type T),EditChest:(Type P) " );
		scanner = new Scanner(System.in);
		String hitButton = scanner.nextLine();
		
		if(hitButton.equals("c")){
			createItem();
		}else if (hitButton.equals("e")){
			editItem();
		}else if (hitButton.equalsIgnoreCase("t")){
			createChest();
		}else if(hitButton.equalsIgnoreCase("p")){
			editChest();
		}else {
			System.out.println("Error");
		}
	}
	/**
	 * This method is used to add item to chest or remove item from it.
	 */
	private void editChest(){
		System.out.println("Please Enter Your Chest Name: ");
		String itemName = scanner.nextLine();
		
		List<ChestEntity> list = DaoFactory.getChestDao().findByName(itemName);
		if (list.isEmpty())
		{
			System.out.println("Invalid item name");
			return;
		}
		Chest chest = (Chest) list.get(0).createModel();
		chest.show();
		
		System.out.println("Choose : (add to Chest : Type a), (Remve from Chest : Type R)");
		String change = scanner.nextLine();
		if(change.equalsIgnoreCase("a")){
			addToChest(chest);
		}else if (change.equalsIgnoreCase("r")){
			removeFromChest(chest);
		}else{
			System.out.println("Error");
		}
		
	}
	/**
	 * This method is used to add an item to chest
	 * @param chest the chest that we want to add an item to.
	 */
	private void addToChest(Chest chest){
		System.out.println("Please Enter Your Item Name:");
		String itemName = scanner.nextLine();
		
		List<ItemEntity> list = DaoFactory.getItemDao().findByName(itemName);
		if (list.isEmpty())
		{
			System.out.println("Invalid Item name");
			return;
		}
		Item item = (Item) list.get(0).createModel();
		
		chest.addItem(item);
		chest.show();
		saveChestFactory(chest, "edit");
	}
	/**
	 * Removes an item from the specified chest.
	 * @param chest gets chest that we want to remove an item from
	 */
	private void removeFromChest(Chest chest){
		System.out.println("Please Enter slot number:");
		String chestStr = scanner.nextLine();
		int chestInt = Integer.parseInt(chestStr);
		
		chest.removeFromChest(chest.getChestItems().get(chestInt),chestInt);
		chest.show();
		saveChestFactory(chest, "edit");
		
	}
	/**
	 * This method is used to create a chest
	 */
	private void createChest(){
		System.out.println("Please Enter Your Chest Name: ");
		String itemName = scanner.nextLine();
		
		Chest chest = new Chest(itemName);
		saveChestFactory(chest,"create");
	}
	
	/**
	 * this is a method for creating an item.
	 */
	private void createItem(){
		boolean enumChecker = false;
		
		System.out.println("Please Enter Your Item Name: ");
		String itemName = scanner.nextLine();
		System.out.println("Please Enter Your Item : ");
		String itemEnumString = scanner.nextLine();
		System.out.println("Please Enter Your Item Type : ");
		String itemType = scanner.nextLine();
		System.out.println("Please Enter Your Item Range : ");
		String itemRange = scanner.nextLine();
		
		boolean mele = itemRange.equalsIgnoreCase("mele") ? true:false;
		
		try {
			AttributeEnum.valueOf(itemType.toUpperCase());
		} catch (IllegalArgumentException e) {
			enumChecker = true;
		}
		
		ItemFactory itemFactory = new ItemFactory();
		
		try {
			if(!enumChecker){
				Item item = itemFactory.createItem(itemName,ItemEnum.valueOf(itemEnumString.toUpperCase()),AttributeEnum.valueOf(itemType.toUpperCase()),mele);
				item.show();
				saveItemFactory(item,"create");
			}else{
				Item item = itemFactory.createItem(itemName,ItemEnum.valueOf(itemEnumString.toUpperCase()), AbilityEnum.valueOf(itemType.toUpperCase()),mele);
				item.show();
				saveItemFactory(item, "create");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Wrong Info");
		}catch (NullPointerException e) {
			System.out.println("It Is Not A Valid Item");
		}
	}
	
	/**
	 * this a method for editing items.
	 */
	private void editItem(){
		System.out.println("Please Enter Your Item Name:");
		String itemName = scanner.nextLine();
		
		List<ItemEntity> list = DaoFactory.getItemDao().findByName(itemName);
		if (list.isEmpty())
		{
			System.out.println("Invalid item name");
			return;
		}
		Item item = (Item) list.get(0).createModel();
		
		System.out.println("Choose to change : (Item Point : Type p), (Item Type : Type t) , (Item Range : Type R)");
		String change = scanner.nextLine();
		if(change.equals("p")){
			changeItemPoint(item);
		}else if (change.equals("t")){
			changeItemType(item);
		}else if (change.equalsIgnoreCase("r") ){
			changeItemRange(item);
		}else{
			System.out.println("Error");
		}
	}
	
	private void changeItemRange(Item item){
		item.show();
		System.out.println("Enter mele or range to change :");

		String mele = scanner.nextLine();
		if(mele.equalsIgnoreCase("mele")){
			item.setMele(true);
			item.show();
			saveItemFactory(item, "edit");
		}else{
			item.setMele(false);
			item.show();
			saveItemFactory(item, "edit");
		}
	}
	/**
	 * this is a method for changing itemPoint
	 * @param Item item
	 */
	public void changeItemPoint(Item item){
		item.show();
		System.out.println("Enter number to change Point:");

		String number = scanner.nextLine();
		int numberScore = Integer.parseInt(number);
		if(numberScore <=5 && numberScore >= 1){
			item.setEnchantmentPoint(numberScore);
			item.show();
			saveItemFactory(item, "edit");
		}else{
			System.out.println("Please Enter number between 1 t0 5!!");
			changeItemPoint(item);
		}
		

	}
	
	/**
	 * this a method for changing item type
	 * @param Item item 
	 */
	public void changeItemType(Item item){
		boolean enumChecker = false;
		
		item.show();
		System.out.println("Enter new item type : ");
		String itemTypeString = scanner.nextLine();
		
		try {
			AbilityEnum.valueOf(itemTypeString.toUpperCase());
		} catch (IllegalArgumentException e) {
			enumChecker = true;
		}
		
		try {
			//ability
			if(!enumChecker){
				if(item.getEnchantmentType() == null){
					item.setAttributeType(null);
					item.setEnchantmentType(AbilityEnum.valueOf(itemTypeString.toUpperCase()));
				}else{
					item.setEnchantmentType(AbilityEnum.valueOf(itemTypeString.toUpperCase()));	
				}
							
				if(item.validate(item)){
					item.show();
					saveItemFactory(item, "edit");
				}else{
					System.out.println("item is not valid by d20 rules");
				}

			}else if(enumChecker){
				if(item.getAttributeType() == null){
					item.setEnchantmentType(null);
					item.setAttributeType(AttributeEnum.valueOf(itemTypeString.toUpperCase()));	
				}else{
					item.setAttributeType(AttributeEnum.valueOf(itemTypeString.toUpperCase()));	
				}
			
				if(item.validate(item)){
					item.show();
					saveItemFactory(item, "edit");
				}else{
					System.out.println("item is not valid by d20 rules");
				}
			}
		} catch (Exception e) {
			System.out.println("Wrong input");
		}

	}
	
	/**
	 * this is a method that saves the item 
	 * @param Item item
	 * @param section which part of character is changing
	 */
	private void saveItemFactory(Item item , String section){
		System.out.println("Do you want to save item changes:(yes or no)");
		String answer = scanner.nextLine();
		
		if(answer.equals("yes")){
			DaoFactory.getItemDao().create(item.getItemEntity());
			reset(item, section);
		}else if (answer.equals("no")){
			reset(item, section);
		}else {
			System.out.println("Error IO");
		}
	}
	
	/**
	 * this is a method to back to the beginning of itemEditor
	 * @param item item
	 * @param section edit/create
	 */
	private void reset(Item item , String section){
		System.out.println("Do you want to "+section+" another item :(yes or no)");
		String answer = scanner.nextLine();
		
		if(answer.equals("yes")){
			if (section.equals("create")){
				createItem();
			}else if (section.equals("edit")){
				editItem();
			}
		}else if (answer.equals("no")){

		}else {
			System.out.println("Error IO");
		}
	}
	
	/**
	 * this is a method that saves the Chest 
	 * @param chest chest
	 * @param section which part of character is changing
	 */
	private void saveChestFactory(Chest chest , String section){
		System.out.println("Do you want to save Chest changes:(yes or no)");
		String answer = scanner.nextLine();
		
		if(answer.equals("yes")){
			DaoFactory.getChestDao().create(chest.getChestEntity());
			reset(chest, section);
		}else if (answer.equals("no")){
			reset(chest, section);
		}else {
			System.out.println("Error IO");
		}
	}
	
	/**
	 * this is a method to back to the beginning of chestEditor
	 * @param chest chest
	 * @param section edit/create
	 */
	private void reset(Chest chest , String section){
		System.out.println("Do you want to "+section+" another Chest :(yes or no)");
		String answer = scanner.nextLine();
		
		if(answer.equals("yes")){
			if (section.equals("create")){
				createChest();
			}else if (section.equals("edit")){
				editChest();
			}
		}else if (answer.equals("no")){

		}else {
			System.out.println("Error IO");
		}
	}
}
