package test.ca.concordia.soen6441.d20.gamemap;

import ca.concordia.soen6441.d20.gamemap.element.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test.*;
import org.junit.Before.*;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectInstance;
/**
 * This is a test case class for validating map of the game
 * @author negar
 *
 */

public class MapValidationTest {
	private GameMap gameMap;
	
	@Before public void setUp(){
		Location wallLocation = new Location(40,40);
		Location enteryLocation = new Location(60,60);
		int width = 100 ;
		int height = 100 ;
		gameMap = new GameMap("Test",width,height);
		Wall wall = new Wall("testWall");
		Entery entery = new Entery("entery");
		GameObjectInstance enteryInstance = new GameObjectInstance(entery,gameMap);
		GameObjectInstance wallInstnce = new GameObjectInstance(wall,gameMap);
		gameMap.setGameObjectInstanceAtLocation(wallLocation,wallInstnce);
		gameMap.setGameObjectInstanceAtLocation(enteryLocation,enteryInstance);
	}
	
	@Test
	public void mapValidatorMethodTest(){
		//assertEquals(this.gameMap.mapValidator(),);
	}
	

}
