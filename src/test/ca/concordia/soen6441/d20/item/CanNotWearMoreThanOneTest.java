package test.ca.concordia.soen6441.d20.item;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.ability.AbilityEnum;
import ca.concordia.soen6441.d20.attribute.AttributeEnum;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;

/**
 *class for testing that character cannot w ear more than one item of each kind
 * @author wmg
 *
 */
public class CanNotWearMoreThanOneTest {
	Fighter figher;
	Item item;
	Item itemSecond;
	
	@Before
	public void beforeMethod(){
		figher = new Fighter("Player","test");
	}
	
	/**
	 * if putonItem returns true it means the character has already put on that item.
	 */
	@Test
	public void testAssertionHelmet(){
		item = new Item("test", ItemEnum.HELMET,AbilityEnum.INTELLIGENCE,1);
		itemSecond = new Item("testSecond",ItemEnum.HELMET,AbilityEnum.WISDOM,1);
		
		assertFalse(figher.putOnItem(item));
		
		assertTrue(figher.putOnItem(itemSecond));
	}
	
	/**
	 * if putonItem returns true it means the character has already put on that item.
	 */
	@Test
	public void testAssertionRing(){
		item = new Item("test", ItemEnum.RING,AttributeEnum.ARMORCLASS,1);
		itemSecond = new Item("testSecond",ItemEnum.RING,AttributeEnum.ARMORCLASS,1);
		
		assertFalse(figher.putOnItem(item));
		
		assertTrue(figher.putOnItem(itemSecond));
	}
	
	/**
	 * if putonItem returns true it means the character has already put on that item.
	 */
	@Test
	public void testAssertionShiled(){
		item = new Item("test", ItemEnum.SHIELD,AttributeEnum.ARMORCLASS,1);
		itemSecond = new Item("testSecond",ItemEnum.SHIELD,AttributeEnum.ARMORCLASS,1);
		
		assertFalse(figher.putOnItem(item));
		
		assertTrue(figher.putOnItem(itemSecond));
	}
	
	/**
	 * if putonItem returns true it means the character has already put on that item.
	 */
	@Test
	public void testAssertionBelt(){
		item = new Item("test", ItemEnum.BELT,AbilityEnum.STRENGTH,1);
		itemSecond = new Item("testSecond",ItemEnum.BELT,AbilityEnum.STRENGTH,1);
		
		assertFalse(figher.putOnItem(item));
		
		assertTrue(figher.putOnItem(itemSecond));
	}
	
	/**
	 * if putonItem returns true it means the character has already put on that item.
	 */
	@Test
	public void testAssertionBoots(){
		item = new Item("test", ItemEnum.BOOTS,AttributeEnum.ARMORCLASS,1);
		itemSecond = new Item("testSecond",ItemEnum.BOOTS,AttributeEnum.ARMORCLASS,1);
		
		assertFalse(figher.putOnItem(item));
		
		assertTrue(figher.putOnItem(itemSecond));
	}
	
	/**
	 * if putonItem returns true it means the character has already put on that item.
	 */
	@Test
	public void testAssertionWeapon(){
		item = new Item("test", ItemEnum.WEAPON,AttributeEnum.ARMORCLASS,1);
		itemSecond = new Item("testSecond",ItemEnum.WEAPON,AttributeEnum.ARMORCLASS,1);
		
		assertFalse(figher.putOnItem(item));
		
		assertTrue(figher.putOnItem(itemSecond));
	}
	
	/**
	 * if putonItem returns true it means the character has already put on that item.
	 */
	@Test
	public void testAssertionArmor(){
		item = new Item("test", ItemEnum.ARMOR,AttributeEnum.ARMORCLASS,1);
		itemSecond = new Item("testSecond",ItemEnum.ARMOR,AttributeEnum.ARMORCLASS,1);
		
		assertFalse(figher.putOnItem(item));
		
		assertTrue(figher.putOnItem(itemSecond));
	}
}
