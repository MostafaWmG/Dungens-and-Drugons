/**
 * 
 * @author Saman Saadi
 */
package test.ca.concordia.soen6441.d20.character;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.character.Character;
import ca.concordia.soen6441.d20.character.CharacterEntity;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.persistence.dao.DaoFactory;

/**
 * @author Saman Saadi
 *
 */
public class CharacterTest {
	
	Character fighter1;
	Character fighter2;
	final String fighter1Name = "CharacterTest:testFighter1";
	final String fighter2Name = "CharacterTest:testFighter2";
	
	private void deleteFightersFromDB()
	{
		String[] array = {fighter1Name, fighter2Name};
		for (String name : array)
		{
			List<CharacterEntity> list = DaoFactory.getCharacterDao().findByName(name); 
			if (list.size() > 0)
				DaoFactory.getCharacterDao().delete(list.get(0));
		}
	}
	
	@Before
	public void initFighters()
	{
		fighter1 = new Character("Enemy", fighter1Name, 0, 7);
		fighter2 = new Character("Player", fighter2Name, 17, 3);		
	}

	/**
	 * Test method for {@link ca.concordia.soen6441.d20.character.Character#Character(java.lang.String, java.lang.String, int, int)}.
	 */
	@Test
	public void testCharacterStringStringIntInt() {
		deleteFightersFromDB();
		fighter1 = new Character("Enemy", fighter1Name, 0, 7);
		fighter2 = new Character("Player", fighter2Name, 17, 3);
		assertTrue(fighter1.getLocation().getX() == 0 && fighter1.getLocation().getY() == 7);
		assertTrue(fighter2.getLocation().getX() == 17 && fighter2.getLocation().getY() == 3);
		assertTrue(fighter1.getName() == fighter1Name);
		assertTrue(fighter2.getName() == fighter2Name);
		assertTrue(fighter1.getTag() == "Enemy");
		assertTrue(fighter2.getTag() == "Player");
		DaoFactory.getCharacterDao().create(fighter1.getCharacterEntity());
		DaoFactory.getCharacterDao().create(fighter2.getCharacterEntity());
		testCharacterCharacterEntity();
	}

	/**
	 * Test method for {@link ca.concordia.soen6441.d20.character.Character#Character(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCharacterStringString() {
		deleteFightersFromDB();
		fighter1 = new Character("Enemy", fighter1Name);
		fighter1.setLocation(new Location(0, 7));
		fighter2 = new Character("Player", fighter2Name);
		fighter2.setLocation(new Location(17, 3));
		assertTrue(fighter1.getLocation().getX() == 0 && fighter1.getLocation().getY() == 7);
		assertTrue(fighter2.getLocation().getX() == 17 && fighter2.getLocation().getY() == 3);
		assertTrue(fighter1.getName() == fighter1Name);
		assertTrue(fighter2.getName() == fighter2Name);
		assertTrue(fighter1.getTag() == "Enemy");
		assertTrue(fighter2.getTag() == "Player");
		DaoFactory.getCharacterDao().create(fighter1.getCharacterEntity());
		DaoFactory.getCharacterDao().create(fighter2.getCharacterEntity());
		testCharacterCharacterEntity();
	}

	/**
	 * Test method for {@link ca.concordia.soen6441.d20.character.Character#Character(ca.concordia.soen6441.d20.character.CharacterEntity)}.
	 */
	@Test
	public void testCharacterCharacterEntity() {
		List<CharacterEntity> list1 = DaoFactory.getCharacterDao().findByName(fighter1Name);
		List<CharacterEntity> list2 = DaoFactory.getCharacterDao().findByName(fighter2Name);
		if (list1.isEmpty() || list2.isEmpty())
			testCharacterStringStringIntInt();
		Character dbFighter1 = (Character) list1.get(0).createModel();
		Character dbFighter2 = (Character) list2.get(0).createModel();
		System.out.println(dbFighter1.getLocation());
		System.out.println(fighter1.getLocation());
		assertTrue(dbFighter1.getLocation().getX() == fighter1.getLocation().getX());
		assertTrue(dbFighter1.getLocation().getY() == fighter1.getLocation().getY());
		assertTrue(dbFighter1.getName() == fighter1Name);
		assertTrue(dbFighter1.getTag() == "Enemy");
		
		assertTrue(dbFighter2.getLocation().getX() == fighter2.getLocation().getX());
		assertTrue(dbFighter2.getLocation().getY() == fighter2.getLocation().getY());
		assertTrue(dbFighter2.getName() == fighter2Name);
		assertTrue(dbFighter2.getTag() == "Player");
	}

}
