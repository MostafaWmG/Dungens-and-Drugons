package test.ca.concordia.soen6441.d20.gamemap;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;

public class GameMapTest {
	private GameMap gameMap;
	
	@Before
	public void setUp() {
		int width = 20;
		int height = 10;
		this.gameMap = new GameMap("test",width, height);
	}

	@Test
	public void createAGameObject() {
		assertNotNull(this.gameMap);
	}
	
	@Test
	public void placeAGameObjectInTheMapShouldAddAGameObjectToTheMap() {
		GameObject gameObject = new GameObject();
		
		this.gameMap.place(gameObject, gameObject.getLocation());
		
		assertEquals(gameObject, this.gameMap.getGameObjectAtLocation(gameObject.getLocation()));
	}
	
	@Test
	public void moveAnObjectInTheMapShoulChangeTheObjectPosition() {
		Location expectedLocation = new Location(5,5);
		GameObject gameObject = new GameObject();
		this.gameMap.place(gameObject, gameObject.getLocation());
		Location originalLocation = gameObject.getLocation();
		
		this.gameMap.move(originalLocation.getX(), originalLocation.getY(), 5, 5);
				
		assertNull(this.gameMap.getGameObjectAtLocation(originalLocation));
		assertNotNull(this.gameMap.getGameObjectAtLocation(expectedLocation));		
	}

}
