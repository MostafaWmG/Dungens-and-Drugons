package test.ca.concordia.soen6441.d20.item;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.ability.AbilityEnum;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;

/**
 * simple update method check for item 
 * @author wmg
 *
 */
public class TestItem {
	
	Item item;
	/**
	 * This method creates an Item object
	 * that we want to check its update method functionality
	 */
	@Before
	public void beforeMethod(){
		item = new Item("dont care", ItemEnum.HELMET, AbilityEnum.INTELLIGENCE, 1);
	}
	/**
	 * this test case should return the expected enchantment point "5".
	 */
	@Test
	public void testAssert1(){
		item.update(20);
		assertEquals(5,item.getEnchantmentPoint());
	}
	/**
	 * this test case should return the expected enchantment point "1" for all negative inputs.
	 */
	@Test
	public void testAssert2(){
		item.update(-10);
		assertEquals(1,item.getEnchantmentPoint());
	}
	/**
	 * this test case should return the expected enchantment point "1".
	 */
	@Test
	public void testAssert3(){
		item.update(3);
		assertEquals(1, item.getEnchantmentPoint());
	}
	/**
	 * this test case returns true if getEnchantmentPoint returns "2"
	 */
	@Test
	public void testAssert4(){
		item.update(6);
		assertEquals(2, item.getEnchantmentPoint());
	}
	/**
	 * this test case returns true if getEnchantmentPoint returns "3"
	 */
	@Test
	public void testAssert5(){
		item.update(10);
		assertEquals(3, item.getEnchantmentPoint());
	}
	/**
	 * this test case returns true if getEnchantmentPoint returns "4"
	 */
	@Test
	public void testAssert6(){
		item.update(14);
		assertEquals(4, item.getEnchantmentPoint());
	}
}
