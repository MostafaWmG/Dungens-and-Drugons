package test.ca.concordia.soen6441.d20.item;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.ability.Ability;
import ca.concordia.soen6441.d20.ability.AbilityEnum;
import ca.concordia.soen6441.d20.attribute.ArmorClass;
import ca.concordia.soen6441.d20.attribute.AttackBonus;
import ca.concordia.soen6441.d20.attribute.AttributeEnum;
import ca.concordia.soen6441.d20.attribute.DamageBonus;
import ca.concordia.soen6441.d20.attribute.HitPoint;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;

/**
 * wearing items should correctly influence the character’s abilities
 * @author wmg
 *
 */
public class ItemWearTest {
	Fighter fighter;
	Item item;
	
	@Before
	public void beforeMethod(){
		fighter = new Fighter("Player", "testFighterItemWear");
	}
	
	/**
	 * put helmet on and check the effects on score.
	 */
	@Test
	public void testAssertionHelmetEffectScore(){
		item = new Item("testHelmet", ItemEnum.HELMET, AbilityEnum.INTELLIGENCE, 4,false);
		Ability originalIntelligence = fighter.getAbility(AbilityEnum.INTELLIGENCE);
		int originalSocre = originalIntelligence.getScore();
		
		fighter.putOnItem(item);
		Ability modifiedIntelligence = fighter.getAbility(AbilityEnum.INTELLIGENCE);
		int modifiedScore = modifiedIntelligence.getScore();
		
		int checkScore = modifiedScore - originalSocre ;
		
		assertEquals(4,checkScore);
	}
	
	/**
	 * put helmet on and check the effects on modifier.
	 */
	@Test
	public void testAssertionHelmetEffectModifier(){
		item = new Item("testHelmet", ItemEnum.HELMET, AbilityEnum.INTELLIGENCE, 4,false);
		Ability originalIntelligence = fighter.getAbility(AbilityEnum.INTELLIGENCE);
		int originalModifier = originalIntelligence.getModifier();
		int originalSocre = originalIntelligence.getScore();
		
		fighter.putOnItem(item);
		Ability modifiedIntelligence = fighter.getAbility(AbilityEnum.INTELLIGENCE);
		int modfiedModifier = modifiedIntelligence.getModifier();
		int modifiedScore = modifiedIntelligence.getScore();
		
		int expected = (int)Math.floor((modifiedScore-10)/2) - (int)Math.floor((originalSocre-10)/2);
		int checkModifier = modfiedModifier - originalModifier;
		
		assertEquals(expected, checkModifier);
	}

	/**
	 * put Armor on and check the effects .
	 */
	@Test
	public void testAssertionArmorEffect(){
		item = new Item("test", ItemEnum.ARMOR, AttributeEnum.ARMORCLASS, 4,false);
		ArmorClass originalAttribute = fighter.getArmor();
		int originalPoint = originalAttribute.getPoint();
		
		fighter.putOnItem(item);
		ArmorClass modifiedAttribute = fighter.getArmor();
		int modifiedPoint = modifiedAttribute.getPoint();
		
		int checkPoint = modifiedPoint - originalPoint ;
		
		assertEquals(4,checkPoint);
	}
	
	/**
	 * put shield on and check the effects .
	 */
	@Test
	public void testAssertionShieldEffect(){
		item = new Item("test", ItemEnum.SHIELD, AttributeEnum.ARMORCLASS, 3,false);
		ArmorClass originalAttribute = fighter.getArmor();
		int originalPoint = originalAttribute.getPoint();
		
		fighter.putOnItem(item);
		ArmorClass modifiedAttribute = fighter.getArmor();
		int modifiedPoint = modifiedAttribute.getPoint();
		
		int checkPoint = modifiedPoint - originalPoint ;
		
		assertEquals(3,checkPoint);
	}
	
	/**
	 * put ring on and check the effects on score.
	 * 
	 */
	@Test
	public void testAssertionRingEffectScore(){
		item = new Item("test", ItemEnum.RING, AbilityEnum.STRENGTH, 4,false);
		Ability originalAbility = fighter.getAbility(AbilityEnum.STRENGTH);
		int originalSocre = originalAbility.getScore();
		
		fighter.putOnItem(item);
		Ability modifiedAbility = fighter.getAbility(AbilityEnum.STRENGTH);
		int modifiedScore = modifiedAbility.getScore();
		
		int checkScore = modifiedScore - originalSocre ;
		
		assertEquals(4,checkScore);
	}
	
	/**
	 * put ring on and check the effects on modifier.
	 * 
	 */
	@Test
	public void testAssertionRingEffectModifier(){
		item = new Item("test", ItemEnum.RING, AbilityEnum.STRENGTH, 4,false);
		Ability originalAbility = fighter.getAbility(AbilityEnum.STRENGTH);
		int originalModifier = originalAbility.getModifier();
		int originalSocre = originalAbility.getScore();
		
		fighter.putOnItem(item);
		Ability modifiedAbility = fighter.getAbility(AbilityEnum.STRENGTH);
		int modfiedModifier = modifiedAbility.getModifier();
		int modifiedScore = modifiedAbility.getScore();
		
		int expected = (int)Math.floor((modifiedScore-10)/2) - (int)Math.floor((originalSocre-10)/2);
		int checkModifier =Math.abs(modfiedModifier - originalModifier);
		
		assertEquals(expected, checkModifier);
	}
	
	/**
	 * put ring on and check the effects on damage bonus.
	 * 
	 */
	@Test
	public void testAssertionRingEffectDamageBonus(){
		item = new Item("test", ItemEnum.RING, AbilityEnum.STRENGTH, 4,false);
		Ability originalAbility = fighter.getAbility(AbilityEnum.STRENGTH);
		int originalModifier = originalAbility.getModifier();
		DamageBonus originalDamage = fighter.getDamage();
		int originalDamagePoint = fighter.getDamage().getPoint();
		
		fighter.putOnItem(item);
		DamageBonus modifiedDamage = fighter.getDamage();
		Ability modifiedAbility = fighter.getAbility(AbilityEnum.STRENGTH);
		int modfiedModifier = modifiedAbility.getModifier();
		int modifiedDamagePoint = fighter.getDamage().getPoint();
		
		int expected = modfiedModifier - originalModifier;
		int checkDamageBonus = modifiedDamagePoint -  originalDamagePoint;
		
		assertEquals(expected, checkDamageBonus);
		assertSame(originalDamage, modifiedDamage);
		assertNotSame(originalDamagePoint, modifiedDamagePoint);
	}
	
	/**
	 * put belt on and check the effects on score.
	 */
	@Test
	public void testAssertionBeltEffectScore(){
		item = new Item("test", ItemEnum.BELT, AbilityEnum.CONSTITUTION, 4,false);
		Ability originalAbility = fighter.getAbility(AbilityEnum.CONSTITUTION);
		int originalSocre = originalAbility.getScore();
		
		fighter.putOnItem(item);
		Ability modifiedAbility = fighter.getAbility(AbilityEnum.CONSTITUTION);
		int modifiedScore = modifiedAbility.getScore();
		
		int checkScore = modifiedScore - originalSocre ;
		
		assertEquals(4,checkScore);
	}
	
	/**
	 * put belt on and check the effects on modifier.
	 */
	@Test
	public void testAssertionBeltEffectModifier(){
		item = new Item("test", ItemEnum.BELT, AbilityEnum.CONSTITUTION, 4,false);
		Ability originalAbility = fighter.getAbility(AbilityEnum.CONSTITUTION);
		int originalModifier = originalAbility.getModifier();
		int originalSocre = originalAbility.getScore();
		
		fighter.putOnItem(item);
		Ability modifiedAbility = fighter.getAbility(AbilityEnum.CONSTITUTION);
		int modfiedModifier = modifiedAbility.getModifier();
		int modifiedScore = modifiedAbility.getScore();
		
		int expected = (int)Math.floor((modifiedScore-10)/2) - (int)Math.floor((originalSocre-10)/2);
		int checkModifier =Math.abs(modfiedModifier - originalModifier);
		
		assertEquals(expected, checkModifier);
	}
	
	/**
	 * put belt on and check the effects on hit point.
	 * 
	 */
	@Test
	public void testAssertionBeltEffectHitPoint(){
		item = new Item("test", ItemEnum.BELT, AbilityEnum.CONSTITUTION, 4,false);
		Ability originalAbility = fighter.getAbility(AbilityEnum.CONSTITUTION);
		int originalModifier = originalAbility.getModifier();
		HitPoint originalHitPoint = fighter.getHitPoint();
		int originalHitPointInt = fighter.getHitPoint().getPoint();
		
		fighter.putOnItem(item);
		HitPoint modifiedHitPoint = fighter.getHitPoint();
		Ability modifiedAbility = fighter.getAbility(AbilityEnum.CONSTITUTION);
		int modfiedModifier = modifiedAbility.getModifier();
		int modifiedHitPointInt = fighter.getHitPoint().getPoint();
		
		int expected = modfiedModifier - originalModifier;
		int checkHitPoint = modifiedHitPointInt -  originalHitPointInt;
		
		assertEquals(expected, checkHitPoint);
		assertSame(originalHitPoint, modifiedHitPoint);
		assertNotSame(originalHitPointInt, modifiedHitPointInt);
	}
	
	/**
	 * put boots on and check the effects on score.
	 */
	@Test
	public void testAssertionBootsEffectScore(){
		item = new Item("test", ItemEnum.BELT, AbilityEnum.DEXTERITY, 4,false);
		Ability originalAbility = fighter.getAbility(AbilityEnum.DEXTERITY);
		int originalSocre = originalAbility.getScore();
		
		fighter.putOnItem(item);
		Ability modifiedAbility = fighter.getAbility(AbilityEnum.DEXTERITY);
		int modifiedScore = modifiedAbility.getScore();
		
		int checkScore = modifiedScore - originalSocre ;
		
		assertEquals(4,checkScore);
	}
	
	/**
	 * put boots on and check the effects on modifier.
	 */
	@Test
	public void testAssertionBootsEffectModifier(){
		item = new Item("test", ItemEnum.BELT, AbilityEnum.DEXTERITY, 4,false);
		Ability originalAbility = fighter.getAbility(AbilityEnum.DEXTERITY);
		int originalModifier = originalAbility.getModifier();
		int originalSocre = originalAbility.getScore();
		
		fighter.putOnItem(item);
		Ability modifiedAbility = fighter.getAbility(AbilityEnum.DEXTERITY);
		int modfiedModifier = modifiedAbility.getModifier();
		int modifiedScore = modifiedAbility.getScore();
		
		int expected = (int)Math.floor((modifiedScore-10)/2) - (int)Math.floor((originalSocre-10)/2);
		int checkModifier =Math.abs(modfiedModifier - originalModifier);
		
		assertEquals(expected, checkModifier);
	}
	
	/**
	 * put Boots on and check the effects on attack bonus.
	 * 
	 */
	@Test
	public void testAssertionBootsEffectAttackBonus(){
		item = new Item("test", ItemEnum.BELT, AbilityEnum.DEXTERITY, 4,false);
		Ability originalAbility = fighter.getAbility(AbilityEnum.DEXTERITY);
		int originalModifier = originalAbility.getModifier();
		AttackBonus originalAttack = fighter.getAttack();
		int originalAttackInt = fighter.getAttack().getPoint();
		
		fighter.putOnItem(item);
		AttackBonus modifiedAttack = fighter.getAttack();
		Ability modifiedAbility = fighter.getAbility(AbilityEnum.DEXTERITY);
		int modfiedModifier = modifiedAbility.getModifier();
		int modifiedAttackInt = fighter.getAttack().getPoint();
		
		int expected = modfiedModifier - originalModifier;
		int checkHitPoint = modifiedAttackInt -  originalAttackInt;
		
		assertEquals(expected, checkHitPoint);
		assertSame(originalAttack, modifiedAttack);
		assertNotSame(originalAttackInt, modifiedAttackInt);
	}
	
	/**
	 * put weapon on and check the effects .
	 */
	@Test
	public void testAssertionWeaponEffect(){
		item = new Item("test", ItemEnum.SHIELD, AttributeEnum.ATTACKBONUS, 3,false);
		AttackBonus originalAttribute = fighter.getAttack();
		int originalPoint = originalAttribute.getPoint();
		
		fighter.putOnItem(item);
		AttackBonus modifiedAttribute = fighter.getAttack();
		int modifiedPoint = modifiedAttribute.getPoint();
		
		int checkPoint = modifiedPoint - originalPoint ;
		
		assertEquals(3,checkPoint);
	}
}
