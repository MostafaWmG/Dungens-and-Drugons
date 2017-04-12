package test.ca.concordia.soen6441.d20.fighter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.attribute.HitPoint;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;
/**
 * A test class for the Fighter class
 * @author negar
 *
 */
public class FighterTest {
	private Fighter fighter;
	
	/**
	 * setUp is responsible for creating a fighter object before each test case executes
	 */
	@Before
	public void setUp() {
		fighter = new Fighter();
		fighter.setLocation(new Location(0,0));
	}
	/**
	 * In this test case we create an enemy object of class fighter and
	 * we check that hit points of the enemy character
	 * reduces after each successful attack 
	 */
	@Test
	public void reduceHitpointsWhenAttackIsSuccessfullTest(){
		Fighter enemy = new Fighter();
		enemy.setHitPoint(new HitPoint(1,1));
		int initailHitpoints = enemy.getHitPoint().getBase();
		enemy.setLocation(new Location(1,0));
		
		fighter.attack(enemy);
		assertTrue(initailHitpoints >enemy.getHitPoint().getBase());
	}
	/**
	 * In this test case we create an enemy object of class fighter and
	 * we check that after an unsuccessful attack the
	 * hit point of the enemy object remains the same
	 */
	@Test
	public void hitpointsShouldBeTheSameWhenAttackIsUnsuccessfullTest(){
		Fighter enemy = new Fighter();
		enemy.setHitPoint(new HitPoint(1,1));
		int initailHitpoints = enemy.getHitPoint().getBase();
		enemy.setLocation(new Location(1,1));
		
		fighter.attack(enemy);
		assertTrue(initailHitpoints == enemy.getHitPoint().getBase());
	}

}
