package test.ca.concordia.soen6441.d20.gamemap;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.element.Entery;
import ca.concordia.soen6441.d20.gamemap.element.Exit;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectInstance;
import ca.concordia.soen6441.d20.gamemap.element.Ground;
import ca.concordia.soen6441.d20.gamemap.element.Wall;

public class GameMapValidatorTest {
	private GameMap map;
	
	@Before
	public void beforMethod(){
		String name = "TestCase1";
		int column = 5;
		int row = 4;
		map = new GameMap(name, column, row);

		
		map.setGameObjectInstanceAtLocation(new Location(0, 0), new GameObjectInstance(new Ground(name), map));
		map.setGameObjectInstanceAtLocation(new Location(0, 1), new GameObjectInstance(new Ground(name), map));
		map.setGameObjectInstanceAtLocation(new Location(0, 2), new GameObjectInstance(new Ground(name), map));
		map.setGameObjectInstanceAtLocation(new Location(0, 3), new GameObjectInstance(new Entery(name), map));
		map.setGameObjectInstanceAtLocation(new Location(1, 0), new GameObjectInstance(new Ground(name), map));
		map.setGameObjectInstanceAtLocation(new Location(1, 1), new GameObjectInstance(new Ground(name), map));
		map.setGameObjectInstanceAtLocation(new Location(1, 2), new GameObjectInstance(new Wall(name), map));
		map.setGameObjectInstanceAtLocation(new Location(1, 3), new GameObjectInstance(new Ground(name), map));
		map.setGameObjectInstanceAtLocation(new Location(2, 0), new GameObjectInstance(new Wall(name), map));
		map.setGameObjectInstanceAtLocation(new Location(2, 1), new GameObjectInstance(new Wall(name), map));
		map.setGameObjectInstanceAtLocation(new Location(2, 2), new GameObjectInstance(new Wall(name), map));
		map.setGameObjectInstanceAtLocation(new Location(2, 3), new GameObjectInstance(new Ground(name), map));
		map.setGameObjectInstanceAtLocation(new Location(3, 0), new GameObjectInstance(new Ground(name), map));
		map.setGameObjectInstanceAtLocation(new Location(3, 1), new GameObjectInstance(new Ground(name), map));
		map.setGameObjectInstanceAtLocation(new Location(3, 2), new GameObjectInstance(new Ground(name), map));
		map.setGameObjectInstanceAtLocation(new Location(3, 3), new GameObjectInstance(new Ground(name), map));
		map.setGameObjectInstanceAtLocation(new Location(4, 0), new GameObjectInstance(new Exit(name), map));
		map.setGameObjectInstanceAtLocation(new Location(4, 1), new GameObjectInstance(new Ground(name), map));;
		map.setGameObjectInstanceAtLocation(new Location(4, 2), new GameObjectInstance(new Wall(name), map));
		map.setGameObjectInstanceAtLocation(new Location(4, 3), new GameObjectInstance(new Ground(name), map));

	}
	
	@Test
	public void assertionTest1(){
		assertTrue(map.mapValidator());
		
	}
}
