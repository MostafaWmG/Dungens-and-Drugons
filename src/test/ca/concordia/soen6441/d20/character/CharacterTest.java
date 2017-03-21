package test.ca.concordia.soen6441.d20.character;
import ca.concordia.soen6441.d20.ability.Ability;
import ca.concordia.soen6441.d20.ability.AbilityEnum;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.fighter.FighterEntity;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectInstance;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;
import ca.concordia.soen6441.persistence.dao.DaoFactory;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CharacterTest {
	private Fighter characterWithInitialPosition;
	private GameMap map;

	@Before
	public void setUp() throws Exception {
		characterWithInitialPosition = new Fighter("tag","name");
		map = new GameMap("mapName", 10, 5);
		characterWithInitialPosition.setField(map);
		characterWithInitialPosition.getField().place(new GameObjectInstance(characterWithInitialPosition, map), new Location(0,0));
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
	Fighter fighter1;
	Fighter fighter2;
	final String fighter1Name = "CharacterTest:testFighter1";
	final String fighter2Name = "CharacterTest:testFighter2";
	
	private void deleteFightersFromDB()
	{
		String[] array = {fighter1Name, fighter2Name};
		for (String name : array)
		{
			List<FighterEntity> list = DaoFactory.getFighterDao().findByName(name); 
			if (list.size() > 0)
				DaoFactory.getFighterDao().delete(list.get(0));
		}
	}
	
	@Before
	public void initFighters()
	{
		fighter1 = new Fighter("Enemy", fighter1Name);
		fighter2 = new Fighter("Player", fighter2Name);		
	}

	/**
	 * Test method for {@link ca.concordia.soen6441.d20.fighter.Fighter#Character(java.lang.String, java.lang.String, int, int)}.
	 */
	@Test
	public void testCharacterStringStringIntInt() {
		deleteFightersFromDB();
		fighter1 = new Fighter("Enemy", fighter1Name);
		fighter2 = new Fighter("Player", fighter2Name);
		assertTrue(fighter1.getName() == fighter1Name);
		assertTrue(fighter2.getName() == fighter2Name);
		assertTrue(fighter1.getTag() == "Enemy");
		assertTrue(fighter2.getTag() == "Player");
		DaoFactory.getFighterDao().create(fighter1.getCharacterEntity());
		DaoFactory.getFighterDao().create(fighter2.getCharacterEntity());
		testCharacterCharacterEntity();
	}

	/**
	 * Test method for {@link ca.concordia.soen6441.d20.fighter.Fighter#Character(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCharacterStringString() {
		deleteFightersFromDB();
		fighter1 = new Fighter("Enemy", fighter1Name);
		fighter2 = new Fighter("Player", fighter2Name);
		assertTrue(fighter1.getName() == fighter1Name);
		assertTrue(fighter2.getName() == fighter2Name);
		assertTrue(fighter1.getTag() == "Enemy");
		assertTrue(fighter2.getTag() == "Player");
		DaoFactory.getFighterDao().create(fighter1.getCharacterEntity());
		DaoFactory.getFighterDao().create(fighter2.getCharacterEntity());
		testCharacterCharacterEntity();
	}

	/**
	 * Test method for {@link ca.concordia.soen6441.d20.fighter.Fighter#Character(ca.concordia.soen6441.d20.fighter.FighterEntity)}.
	 */
	@Test
	public void testCharacterCharacterEntity() {
		List<FighterEntity> list1 = DaoFactory.getFighterDao().findByName(fighter1Name);
		List<FighterEntity> list2 = DaoFactory.getFighterDao().findByName(fighter2Name);
		if (list1.isEmpty() || list2.isEmpty())
			testCharacterStringStringIntInt();
		Fighter dbFighter1 = (Fighter) list1.get(0).createModel();
		Fighter dbFighter2 = (Fighter) list2.get(0).createModel();
		assertTrue(dbFighter1.getName() == fighter1Name);
		assertTrue(dbFighter1.getTag() == "Enemy");
		
		assertTrue(dbFighter2.getName() == fighter2Name);
		assertTrue(dbFighter2.getTag() == "Player");
	}

}
