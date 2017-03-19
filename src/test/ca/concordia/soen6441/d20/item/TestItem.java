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
	@Before
	public void beforeMethod(){
		item = new Item("dont care", ItemEnum.HELMET, AbilityEnum.INTELLIGENCE, 1);
	}
	
	@Test
	public void testAssert1(){
		item.update(20);
		assertEquals(5,item.getEnchantmentPoint());
	}
	
	@Test
	public void testAssert2(){
		item.update(-10);
		assertEquals(1,item.getEnchantmentPoint());
	}
	
	@Test
	public void testAssert3(){
		item.update(3);
		assertEquals(1, item.getEnchantmentPoint());
	}
	
	@Test
	public void testAssert4(){
		item.update(6);
		assertEquals(2, item.getEnchantmentPoint());
	}
	
	@Test
	public void testAssert5(){
		item.update(10);
		assertEquals(3, item.getEnchantmentPoint());
	}
	
	@Test
	public void testAssert6(){
		item.update(14);
		assertEquals(4, item.getEnchantmentPoint());
	}
}
