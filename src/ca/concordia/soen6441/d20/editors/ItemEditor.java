package ca.concordia.soen6441.d20.editors;

import java.util.List;
import java.util.Scanner;

import ca.concordia.soen6441.d20.ability.AbilityEnum;
import ca.concordia.soen6441.d20.attribute.AttributeEnum;
import ca.concordia.soen6441.d20.factory.ItemFactory;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEntity;
import ca.concordia.soen6441.d20.item.ItemEnum;
import ca.concordia.soen6441.persistence.dao.DaoFactory;
/**
 * this a class for create/edit Items.
 * @author wmg
 *
 */
public class ItemEditor {
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
	
	private void editChest(){
		
	}
	
	private void createChest(){
		System.out.println("Please Enter Your Chest Name: ");
		String itemName = scanner.nextLine();
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
		
		try {
			AttributeEnum.valueOf(itemType.toUpperCase());
		} catch (IllegalArgumentException e) {
			enumChecker = true;
		}
		
		ItemFactory itemFactory = new ItemFactory();
		
		try {
			if(!enumChecker){
				Item item = itemFactory.createItem(itemName,ItemEnum.valueOf(itemEnumString.toUpperCase()),AttributeEnum.valueOf(itemType.toUpperCase()));
				item.show();
				saveItemFactory(item,"create");
			}else{
				Item item = itemFactory.createItem(itemName,ItemEnum.valueOf(itemEnumString.toUpperCase()), AbilityEnum.valueOf(itemType.toUpperCase()));
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
			//TODO use appropriate procedure
			System.out.println("Invalid map name");
			return;
		}
		Item item = (Item) list.get(0).createModel();
		
		System.out.println("Choose to change : (Item Point : Type p), (Item Type : Type t)");
		String change = scanner.nextLine();
		if(change.equals("p")){
			changeItemPoint(item);
		}else if (change.equals("t")){
			changeItemType(item);
		}else{
			System.out.println("Error");
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
//			System.out.println("phase1: " +enumChecker);
		} catch (IllegalArgumentException e) {
			enumChecker = true;
//			System.out.println("phase2: "+ enumChecker);
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
}
