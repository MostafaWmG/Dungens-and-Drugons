package test.ca.concordia.soen6441.d20.gamemap.validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.element.Door;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.validator.DoorValidator;

public class DoorValidatorTest {

	private GameMap gameMap;
	private DoorValidator validator;
	
	@Before
	public void setUp() {
		gameMap = new GameMap("test",20, 10);
		validator = new DoorValidator(gameMap);
	}

	@Test
	public void whenGamemapHasADoorItShouldBeValid() {
		GameObject door = new Door();
		gameMap.place(door, door.getLocation());
		
		boolean valid = this.validator.validate();
		
		assertTrue(valid);
	}
	
	@Test
	public void invalidGameMapWhenThereIsNoDoor() {
		boolean valid = this.validator.validate();
		
		assertFalse(valid);
	}
	
	@Test
	public void whenGamemapHasMoreThanOneDoorItShouldBeValid() {
		GameObject door = new Door();
		GameObject secondDoor = new Door(9, 7);
		gameMap.place(door, door.getLocation());
		gameMap.place(secondDoor, secondDoor.getLocation());
		
		boolean valid = this.validator.validate();
		
		assertTrue(valid);
	}

}
