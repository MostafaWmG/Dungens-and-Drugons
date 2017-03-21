package test.ca.concordia.soen6441.d20.gamemap;

import ca.concordia.soen6441.d20.gamemap.element.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


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
	
	@Before 
	public void setUp(){
		Location wallLocation = new Location(4,4);
		Location enteryLocation = new Location(6,6);
		Location exitLocation = new Location(1,1);
		int width = 10 ;
		int height = 10 ;
		gameMap = new GameMap("Test",height,width);
		
		for(int i = 0; i < height ; i++){
			for(int j = 0; j < width ; j++){
					Ground ground = new Ground("testGround" +i+j);
					GameObjectInstance groundInstance = new GameObjectInstance(ground,gameMap);
					gameMap.setGameObjectInstanceAtLocation(new Location(j,i),groundInstance);	
			}
		}
		
		Wall wall = new Wall("testWall");
		GameObjectInstance wallInstnce = new GameObjectInstance(wall,gameMap);
		gameMap.setGameObjectInstanceAtLocation(wallLocation,wallInstnce);
		
		Entery entery = new Entery("entery");
		GameObjectInstance enteryInstance = new GameObjectInstance(entery,gameMap);
		gameMap.setGameObjectInstanceAtLocation(enteryLocation,enteryInstance);
		
		Exit exit = new Exit("exit");
		GameObjectInstance exitInstance = new GameObjectInstance(exit,gameMap);
		gameMap.setGameObjectInstanceAtLocation(exitLocation,exitInstance);

	}
	
	@Test
	public void mapValidatorMethodTest(){
		assertTrue(gameMap.mapValidator());
	}
	

}
