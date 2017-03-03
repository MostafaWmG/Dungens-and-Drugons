package test.ca.concordia.soen6441.d20.character;
import ca.concordia.soen6441.d20.character.Character;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.item.Ability;
import ca.concordia.soen6441.d20.item.AbilityEnum;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CharacterTest {
	private Character characterWithInitialPosition;

	@Before
	public void setUp() throws Exception {
		characterWithInitialPosition = new Character("tag","name", 0, 0);
		characterWithInitialPosition.setField(new GameMap("mapName", 10, 5));
		characterWithInitialPosition.getField().place(characterWithInitialPosition, characterWithInitialPosition.getLocation());
	}

	@Test
	public void testGetEntity() {
		assertNotNull(characterWithInitialPosition.getEntity());
	}

	@Test
	public void testGetTag() {
		assertEquals("tag", characterWithInitialPosition.getTag());
	}

	@Test
	public void testSetTag() {
		String expected = "new_tag";
		
		characterWithInitialPosition.setTag("new_tag");
		
		assertEquals(expected, characterWithInitialPosition.getTag());
	}


	@Test
	public void testMove() {
		Location original = characterWithInitialPosition.getLocation();
		Location expected = new Location(2, 2);
		
		characterWithInitialPosition.move(2, 2);
		
		assertEquals(new Location(0, 0), original);
		assertEquals(expected, characterWithInitialPosition.getLocation());
	}

	@Test
	public void testHasItem() {
		assertNotNull(characterWithInitialPosition.getWearItems());
	}

	@Test
	public void testPutOnItem() {
		characterWithInitialPosition.putOnItem(new Item("name", ItemEnum.ARMOR));
		
		assertNotNull(characterWithInitialPosition.getItem(ItemEnum.ARMOR));
	}


	@Test
	public void testRemoveItem() {
		Item item = new Item("name", ItemEnum.ARMOR);
		characterWithInitialPosition.putOnItem(item);
		
		characterWithInitialPosition.removeItem(item);
		
		assertFalse(characterWithInitialPosition.hasItem(ItemEnum.ARMOR));
	}

	@Test
	public void testAddAbilityAbility() {
		ArrayList<Ability> abilities = new ArrayList<Ability>();
		abilities.add(new Ability(AbilityEnum.CHARISMA, 1, 0));
		
		characterWithInitialPosition.setAbilities(abilities);
		
		assertEquals(1, characterWithInitialPosition.getAbilities().size());
	}

	@Test
	public void testSetBackPack() {
		characterWithInitialPosition.setBackPack(new ArrayList<>());
		
		assertEquals(0, characterWithInitialPosition.getBackPack().size());
	}

	@Test
	public void testGetBackPack() {
		characterWithInitialPosition.setBackPack(new ArrayList<>());
		
		List<Item>items = characterWithInitialPosition.getBackPack();
		
		assertEquals(0, items.size());
	}

	@Test
	public void testInitializeBackPack() {
		characterWithInitialPosition.setBackPack(new ArrayList<>());
		
		characterWithInitialPosition.initializeBackPack();
		assertEquals(10, characterWithInitialPosition.getBackPack().size());
		
	}

}
