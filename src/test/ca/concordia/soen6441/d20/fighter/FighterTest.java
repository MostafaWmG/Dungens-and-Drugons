package test.ca.concordia.soen6441.d20.fighter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.attribute.HitPoint;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;

public class FighterTest {
	private Fighter fighter;
	
	@Before
	public void setUp() {
		fighter = new Fighter();
		fighter.setLocation(new Location(0,0));
	}
	
	@Test
	public void reduceHitpointsWhenAttackIsSuccessfullTest(){
		Fighter enemy = new Fighter();
		enemy.setHitPoint(new HitPoint(1,1));
		int initailHitpoints = enemy.getHitPoint().getBase();
		enemy.setLocation(new Location(1,0));
		
		fighter.attack(enemy);
		assertTrue(initailHitpoints >enemy.getHitPoint().getBase());
	}
	
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
