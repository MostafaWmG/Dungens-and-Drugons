package test.ca.concordia.soen6441.d20.item;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.attribute.AttributeEnum;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.Chest;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;
/**
 * This test class is for validating items in the chest
 *
 */
public class ChestTest {
	private Chest chest;
	private Fighter fighter;

	/**
	 * This method is responsible for creating a chest and a fighter object
	 * with a given tang and name,before each test case
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		chest = new Chest("chest");
		fighter = new Fighter("fighter", "Assassin");
	}

	/**
	 * This test case checks that after adding an item to the chest
	 * it is actually added to chest
	 */
	@Test
	public void whenAddItemsToChest_chestHasItems() {
		Item armor = new Item("armor",ItemEnum.ARMOR,AttributeEnum.ARMORCLASS, 0,false);
		Item belt = new Item("armor",ItemEnum.BELT,AttributeEnum.ARMORCLASS, 0,false);
		
		chest.addItem(armor);
		chest.addItem(belt);
				
		assertEquals(ItemEnum.ARMOR, chest.getChestItems().get(0).getItemEnum());
		assertEquals(ItemEnum.BELT, chest.getChestItems().get(1).getItemEnum());
	}
	/**
	 * This test case is for checking that if the backpack
	 * has enough space, chest can be put in the backpack
	 */
	@Test
	public void whenPuttingChestIntoBackpack_ItemsGetIntoBackpackIfthereIsRoom() {
		Item armor = new Item("armor",ItemEnum.ARMOR,AttributeEnum.ARMORCLASS, 1,false);
		chest.addItem(armor);
		
		chest.putRootedIntoBackPack(fighter);

		assertEquals("armor", fighter.getBackPack().get(0).getName());
	}

}
