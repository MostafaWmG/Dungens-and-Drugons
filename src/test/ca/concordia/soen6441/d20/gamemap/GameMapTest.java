package test.ca.concordia.soen6441.d20.gamemap;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import ca.concordia.soen6441.d20.gamemap.GameMap;

/**
 * This is a test class for validation of the map created for the game
 * @author negar
 *
 */
public class GameMapTest {
	private GameMap gameMap;
	
	/**
	 * setUp method creates a map for size indicated by "height" and "width"
	 * before each test case
	 */
	@Before
	public void setUp() {
		int width = 20;
		int height = 10;
		this.gameMap = new GameMap("test",width, height);
	}

	/**
	 * By implementing this test case we want to
	 * test that an object created from the GameMap class is not null
	 */
	@Test
	public void createAGameObject() {
		assertNotNull(this.gameMap);
	}
	
	@Test
	public void placeAGameObjectInTheMapShouldAddAGameObjectToTheMap() {
//		GameObject gameObject = new GameObject();
//		
//		this.gameMap.place(gameObject, gameObject.getLocation());
//		
//		assertEquals(gameObject, this.gameMap.getGameObjectAtLocation(gameObject.getLocation()));
	}
	
	//move this method to another test case
	@Test
	public void moveAnObjectInTheMapShoulChangeTheObjectPosition() {
//		Location expectedLocation = new Location(5,5);
//		GameObject gameObject = new GameObject();
//		this.gameMap.place(gameObject, gameObject.getLocation());
//		Location originalLocation = gameObject.getLocation();
//		
//		this.gameMap.move(originalLocation.getX(), originalLocation.getY(), 5, 5);
//				
//		assertNull(this.gameMap.getGameObjectAtLocation(originalLocation));
//		assertNotNull(this.gameMap.getGameObjectAtLocation(expectedLocation));		
	}

}
