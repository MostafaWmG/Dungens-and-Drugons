package test.ca.concordia.soen6441.d20.fighter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.attribute.AttributeEnum;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.element.Entery;
import ca.concordia.soen6441.d20.gamemap.element.Exit;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectInstance;
import ca.concordia.soen6441.d20.gamemap.element.Ground;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;
import ca.concordia.soen6441.d20.item.decorator.Slaying;
import ca.concordia.soen6441.d20.strategy.AggressiveNPC;
import ca.concordia.soen6441.d20.strategy.HumanPlayer;

/**
 * testing attack of two fighter
 * @author wmg
 *
 */
public class FighterAttackTest {
	Fighter attacker;
	Fighter target;
	
	int width;
	int height;
	GameMap map;
	
	GameObjectInstance attackerInstance;
	GameObjectInstance targetInstance;
	
	/**
	 * set up map and 2 fighters to attack each other
	 */
	@Before
	public void beforeTestClass(){
		attacker = new Fighter("Player", "testCase1" );
		target = new Fighter("Enemy" , "testCas2");
		
		Location enteryLocation = new Location(6,6);
		Location exitLocation = new Location(1,1);
		width = 10 ;
		height = 10 ;
		
		map = new GameMap("maptestfighterAttacktest", height, width);
		
		for(int i = 0; i < height ; i++){
			for(int j = 0; j < width ; j++){
					Ground ground = new Ground("testGround" +i+j);
					GameObjectInstance groundInstance = new GameObjectInstance(ground,map);
					map.setGameObjectInstanceAtLocation(new Location(j,i),groundInstance);	
			}
		}
		
		Entery entery = new Entery("entery");
		GameObjectInstance enteryInstance = new GameObjectInstance(entery,map);
		map.setGameObjectInstanceAtLocation(enteryLocation,enteryInstance);
		
		Exit exit = new Exit("exit");
		GameObjectInstance exitInstance = new GameObjectInstance(exit,map);
		map.setGameObjectInstanceAtLocation(exitLocation,exitInstance);
		
	}
	
	/**
	 * out of range test
	 */
	@Test
	public void testAssertion1(){
		attackerInstance = new GameObjectInstance(attacker, map);
		targetInstance = new GameObjectInstance(target, map);
		Location attackerLoc = new Location(2,2);
		Location targetLoc = new Location(5,5);
		
		map.setGameObjectInstanceAtLocation(attackerLoc,attackerInstance);
		map.setGameObjectInstanceAtLocation(targetLoc,targetInstance);
		
		assertFalse(attacker.attack(attacker, target, attackerLoc, targetLoc));
		
	}
	
	/**
	 * attack test
	 */
	@Test
	public void testAssertion2(){
		attackerInstance = new GameObjectInstance(attacker, map);
		targetInstance = new GameObjectInstance(target, map);
		Location attackerLoc = new Location(2,2);
		Location targetLoc = new Location(3,3);
		
		map.setGameObjectInstanceAtLocation(attackerLoc,attackerInstance);
		map.setGameObjectInstanceAtLocation(targetLoc,targetInstance);
		
		assertFalse(attacker.attack(attacker, target, attackerLoc, targetLoc));
		
	}
	
	/**
	 * attack test with slaying sword
	 */
	@Test
	public void testAssertion3(){
		
		attacker.setStrategy(new HumanPlayer(attacker));
		target.setStrategy(new AggressiveNPC(target));
		
		attackerInstance = new GameObjectInstance(attacker, map);
		targetInstance = new GameObjectInstance(target, map);
		Item weaponSlayer = new Item("Slayer Effect test 1", ItemEnum.WEAPON, AttributeEnum.ATTACKBONUS, 5, true);
		weaponSlayer = new Slaying(weaponSlayer);
		
		weaponSlayer.setEnchantmentPoint(10);
		attacker.putOnItem(weaponSlayer);
		
		Location attackerLoc = new Location(2,2);
		Location targetLoc = new Location(2,3);
		
		map.setGameObjectInstanceAtLocation(attackerLoc,attackerInstance);
		map.setGameObjectInstanceAtLocation(targetLoc,targetInstance);
		
		assertTrue(target.attack(attacker, target, attackerLoc, targetLoc));
		
	}
	
}
