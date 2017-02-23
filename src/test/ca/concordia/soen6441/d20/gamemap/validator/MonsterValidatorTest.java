package test.ca.concordia.soen6441.d20.gamemap.validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.validator.MonsterValidator;
import ca.concordia.soen6441.d20.character.Character;;

public class MonsterValidatorTest {
	private GameMap gameMap;
	private MonsterValidator validator;
	
	@Before
	public void setUp() {
		gameMap = new GameMap("test",20, 10);
		validator = new MonsterValidator(gameMap);
	}

	@Test
	public void whenAGameMapHasAMonsterItShouldBeValid() {
		Character monster = new Character();
		gameMap.place(monster, monster.getLocation());
		
		boolean valid = this.validator.validate();
		
		assertTrue(valid);
	}
	
	@Test
	public void invalidGameMapWhenThereIsNoMonster() {
		boolean valid = this.validator.validate();
		
		assertFalse(valid);
	}
	
	@Test
	public void whenGamemapHasMoreThanOneMonsterItShouldBeValid() {
		GameObject monster = new Character();
		GameObject secondMonster = new Character(9, 7);
		gameMap.place(monster, monster.getLocation());
		gameMap.place(secondMonster, secondMonster.getLocation());
		
		boolean valid = this.validator.validate();
		
		assertTrue(valid);
	}

}
