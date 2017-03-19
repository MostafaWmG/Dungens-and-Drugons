package test.ca.concordia.soen6441.d20.gamemap;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test.*;
import org.junit.Before.*;

import ca.concordia.soen6441.d20.gamemap.GameMap;
/**
 * This is a test case class for validating map of the game
 * @author negar
 *
 */

public class MapValidationTest {
	private GameMap gameMap;
	
	@Before public void beforTest(){
		int width = 50 ;
		int height = 50 ;
		gameMap = new GameMap("Test",width,height);
	}
	
	@Test
	public void mapValidatorMethodTest(){
		//assertEquals(this.gameMap.mapValidator(),);
	}
	

}
