package test.ca.concordia.soen6441.d20.character;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.ability.AbilityEnum;
import ca.concordia.soen6441.d20.factory.BullyFighterBuilder;
import ca.concordia.soen6441.d20.factory.FighterDirector;
import ca.concordia.soen6441.d20.factory.NimbleFighterBuilder;
import ca.concordia.soen6441.d20.factory.TankFighterBuilder;
import ca.concordia.soen6441.d20.fighter.Fighter;
/**
 * This test class contains test cases that test getScore methods of the
 * different players that is created using the builder pattern
 * @author negar
 *
 */
public class CharacterBuilderTest {
	FighterDirector director;
	/**
	 * This method initializes the director object of the fighter builder to
	 * be used in each test case
	 */
	@Before
	public void beforeMethod(){
		director = new FighterDirector();
	}
	/**
	 * This test case is used to check that getScore method returns the expected value
	 * for each ability of the player "testBully" that is build by the builder pattern
	 */
	@Test
	public void testAssertionBully(){
		BullyFighterBuilder bullyFighterBuilder = new BullyFighterBuilder();
		director.setFighterBuilder(bullyFighterBuilder);
		director.constructFighter("Player",	"testBully");
		
		Fighter fighter  = director.getFighterBuilder();
		assertTrue( fighter.getAbility(AbilityEnum.STRENGTH).getScore() >= fighter.getAbility(AbilityEnum.CONSTITUTION).getScore());
		assertTrue( fighter.getAbility(AbilityEnum.CONSTITUTION).getScore() >= fighter.getAbility(AbilityEnum.DEXTERITY).getScore());
		assertTrue( fighter.getAbility(AbilityEnum.DEXTERITY).getScore() >= fighter.getAbility(AbilityEnum.INTELLIGENCE).getScore());
		assertTrue( fighter.getAbility(AbilityEnum.INTELLIGENCE).getScore() >= fighter.getAbility(AbilityEnum.CHARISMA).getScore());
		assertTrue( fighter.getAbility(AbilityEnum.CHARISMA).getScore() >= fighter.getAbility(AbilityEnum.WISDOM).getScore());
	}
	/**
	 * This test case checks that getScore method returns the expected scores of the abilities 
	 * for the "testNimble" character
	 */
	@Test
	public void testAssertionNimble(){
		NimbleFighterBuilder nimbleFighterBuilder = new NimbleFighterBuilder();
		director.setFighterBuilder(nimbleFighterBuilder);
		director.constructFighter("Player",	"testNimble");
		
		Fighter fighter  = director.getFighterBuilder();
		assertTrue( fighter.getAbility(AbilityEnum.DEXTERITY).getScore() >= fighter.getAbility(AbilityEnum.CONSTITUTION).getScore());
		assertTrue( fighter.getAbility(AbilityEnum.CONSTITUTION).getScore() >= fighter.getAbility(AbilityEnum.STRENGTH).getScore());
		assertTrue( fighter.getAbility(AbilityEnum.STRENGTH).getScore() >= fighter.getAbility(AbilityEnum.INTELLIGENCE).getScore());
		assertTrue( fighter.getAbility(AbilityEnum.INTELLIGENCE).getScore() >= fighter.getAbility(AbilityEnum.CHARISMA).getScore());
		assertTrue( fighter.getAbility(AbilityEnum.CHARISMA).getScore() >= fighter.getAbility(AbilityEnum.WISDOM).getScore());
	}
	/**
	 * This test case checks that getScore method returns the expected scores of the abilities 
	 * for the "testTank" character
	 */
	@Test
	public void testAssertionTank(){
		TankFighterBuilder tankFighterBuilder = new TankFighterBuilder();
		director.setFighterBuilder(tankFighterBuilder);
		director.constructFighter("Player",	"testTank");
		
		Fighter fighter  = director.getFighterBuilder();
		assertTrue( fighter.getAbility(AbilityEnum.CONSTITUTION).getScore() >= fighter.getAbility(AbilityEnum.DEXTERITY).getScore());
		assertTrue( fighter.getAbility(AbilityEnum.DEXTERITY).getScore() >= fighter.getAbility(AbilityEnum.STRENGTH).getScore());
		assertTrue( fighter.getAbility(AbilityEnum.STRENGTH).getScore() >= fighter.getAbility(AbilityEnum.INTELLIGENCE).getScore());
		assertTrue( fighter.getAbility(AbilityEnum.INTELLIGENCE).getScore() >= fighter.getAbility(AbilityEnum.CHARISMA).getScore());
		assertTrue( fighter.getAbility(AbilityEnum.CHARISMA).getScore() >= fighter.getAbility(AbilityEnum.WISDOM).getScore());
	}
	
}
