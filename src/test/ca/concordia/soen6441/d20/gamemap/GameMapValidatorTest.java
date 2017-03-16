//package test.ca.concordia.soen6441.d20.gamemap;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import ca.concordia.soen6441.d20.common.Location;
//import ca.concordia.soen6441.d20.gamemap.GameMap;
//import ca.concordia.soen6441.d20.gamemap.element.Entery;
//import ca.concordia.soen6441.d20.gamemap.element.Exit;
//import ca.concordia.soen6441.d20.gamemap.element.Ground;
//import ca.concordia.soen6441.d20.gamemap.element.Wall;
//
//public class GameMapValidatorTest {
//	private GameMap map;
//	
//	@Before
//	public void beforMethod(){
//		String name = "TestCase1";
//		int column = 4;
//		int row = 5;
//		map = new GameMap(name, column, row);
//
//		
//		map.setGameObjectAtLocation(new Location(0, 0), new Ground(name, 0, 0));
//		map.setGameObjectAtLocation(new Location(0, 1), new Ground(name, 0, 1));
//		map.setGameObjectAtLocation(new Location(0, 2), new Ground(name, 0, 2));
//		map.setGameObjectAtLocation(new Location(0, 3), new Entery(name, 0, 3));
//		map.setGameObjectAtLocation(new Location(1, 0), new Ground(name, 1, 0));
//		map.setGameObjectAtLocation(new Location(1, 1), new Ground(name, 1, 1));
//		map.setGameObjectAtLocation(new Location(1, 2), new Wall(name, 1, 2));
//		map.setGameObjectAtLocation(new Location(1, 3), new Ground(name, 1, 3));
//		map.setGameObjectAtLocation(new Location(2, 0), new Wall(name, 2, 0));
//		map.setGameObjectAtLocation(new Location(2, 1), new Wall(name, 2, 1));
//		map.setGameObjectAtLocation(new Location(2, 2), new Wall(name, 2, 2));
//		map.setGameObjectAtLocation(new Location(2, 3), new Ground(name, 2, 3));
//		map.setGameObjectAtLocation(new Location(3, 0), new Ground(name, 3, 0));
//		map.setGameObjectAtLocation(new Location(3, 1), new Ground(name, 3, 1));
//		map.setGameObjectAtLocation(new Location(3, 2), new Ground(name, 3, 2));
//		map.setGameObjectAtLocation(new Location(3, 3), new Ground(name, 3, 3));
//		map.setGameObjectAtLocation(new Location(4, 0), new Exit(name, 4, 0));
//		map.setGameObjectAtLocation(new Location(4, 1), new Ground(name, 4, 1));
//		map.setGameObjectAtLocation(new Location(4, 2), new Wall(name, 4, 2));
//		map.setGameObjectAtLocation(new Location(4, 3), new Ground(name, 4, 3));
//
//		for(int i=0 ; i < row ; i ++){
//		for (int j = 0 ; j < column ; j++){
//			System.out.println("show test case : "+" i: "+i+" X: "+ map.getGameObjectAtLocation(new Location(i,j)).getLocation().getX() +" j: "+j+" y:"+ map.getGameObjectAtLocation(new Location(i,j)).getLocation().getY() );
//
//		}
//	}
//
//	}
//	
//	@Test
//	public void assertionTest1(){
//		assertTrue(map.mapValidator());
//		
//	}
//	@Test
//	public void assertTest2(){
////		assertNotSame(map.getElements(), map.mapCopy);
//	}
//}
