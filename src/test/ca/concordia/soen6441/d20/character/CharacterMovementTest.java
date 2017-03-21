package test.ca.concordia.soen6441.d20.character;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.campaign.Campaign;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.gamePlay.Game;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.element.Entery;
import ca.concordia.soen6441.d20.gamemap.element.Exit;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectInstance;
import ca.concordia.soen6441.d20.gamemap.element.Ground;

public class CharacterMovementTest {
	Fighter fighter;
	GameMap map;
	Campaign camp;
	Game game;
	
	@Before
	public void beforeMethod(){
		fighter = new Fighter("Player", "testFighterMov");
		map = new GameMap("testMapMov", 5, 5);
		camp = new Campaign("testCampMov");
		
		for(int i = 0 ; i < map.getHeight() ; i++){
			for (int j = 0 ; j < map.getWidth() ; j++){
				map.setGameObjectInstanceAtLocation(new Location(j,i),new GameObjectInstance(new Ground("testGroundMov"+i+j), map) );
			}
		}
		
		map.setGameObjectInstanceAtLocation(new Location(1, 1), new GameObjectInstance(new Entery("EnterTestMOve"), map));
		map.setGameObjectInstanceAtLocation(new Location(4, 4), new GameObjectInstance(new Exit("EnterTestMOve"), map));
		
		camp = new Campaign("testCampaignMov");
		camp.addMap(0, map);
		
		game = new Game(camp, fighter,false);
	}
	
	@Test
	public void testAssertionMoveDown(){
		Location currentLocation = new Location(game.getCurrentLocation().getX(),game.getCurrentLocation().getY());
		currentLocation.setY(currentLocation.getY() + 1);
		
		game.moveDown();
		
		game.terminate();
		assertEquals(currentLocation,game.getCurrentLocation());
		
	}	
	
	@Test
	public void testAssertionMoveUP(){
		Location currentLocation = new Location(game.getCurrentLocation().getX(),game.getCurrentLocation().getY());
		currentLocation.setY(currentLocation.getY() - 1);
		
		game.moveUP();
		
		game.terminate();
		assertEquals(currentLocation,game.getCurrentLocation());
		
	}
	
	@Test
	public void testAssertionMoveLeft(){
		Location currentLocation = new Location(game.getCurrentLocation().getX(),game.getCurrentLocation().getY());
		currentLocation.setX(currentLocation.getX() - 1);
		
		game.moveLeft();
		
		game.terminate();
		assertEquals(currentLocation,game.getCurrentLocation());
		
	}
	
	@Test
	public void testAssertionMoveRight(){
		Location currentLocation = new Location(game.getCurrentLocation().getX(),game.getCurrentLocation().getY());
		currentLocation.setX(currentLocation.getX() + 1);
		
		game.moveRight();
		
		game.terminate();
		assertEquals(currentLocation,game.getCurrentLocation());
		
	}
}
