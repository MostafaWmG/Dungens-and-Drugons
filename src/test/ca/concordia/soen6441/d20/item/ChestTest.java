package test.ca.concordia.soen6441.d20.item;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.attribute.AttributeEnum;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.Chest;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;

public class ChestTest {
	private Chest chest;
	private Fighter fighter;

	@Before
	public void setUp() throws Exception {
		chest = new Chest("chest");
		fighter = new Fighter("fighter", "Assassin");
	}

	@Test
	public void whenAddItemsToChest_chestHasItems() {
		Item armor = new Item("armor",ItemEnum.ARMOR,AttributeEnum.ARMORCLASS, 0);
		Item belt = new Item("armor",ItemEnum.BELT,AttributeEnum.ARMORCLASS, 0);
		
		chest.addItem(armor);
		chest.addItem(belt);
				
		assertEquals(ItemEnum.ARMOR, chest.getChestItems().get(0).getItemEnum());
		assertEquals(ItemEnum.BELT, chest.getChestItems().get(1).getItemEnum());
	}
	
	@Test
	public void whenPuttingChestIntoBackpack_ItemsGetIntoBackpackIfthereIsRoom() {
		Item armor = new Item("armor",ItemEnum.ARMOR,AttributeEnum.ARMORCLASS, 1);
		chest.addItem(armor);
		
		chest.putRootedIntoBackPack(fighter);

		assertEquals("armor", fighter.getBackPack().get(0).getName());
	}

}
