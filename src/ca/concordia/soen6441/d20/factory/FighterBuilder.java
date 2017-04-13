package ca.concordia.soen6441.d20.factory;

import java.util.ArrayList;

import ca.concordia.soen6441.d20.ability.Ability;
import ca.concordia.soen6441.d20.ability.AbilityEnum;
import ca.concordia.soen6441.d20.attribute.ArmorClass;
import ca.concordia.soen6441.d20.attribute.AttackBonus;
import ca.concordia.soen6441.d20.attribute.DamageBonus;
import ca.concordia.soen6441.d20.attribute.HitPoint;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.Item;

/**
 * this an abstract class for creating character and items.
 * It implements builder pattern
 * @author mostafa
 */
public abstract class FighterBuilder {
	
	protected Fighter fighter;
	
	/**
	 * @return the fighter
	 */
	public Fighter getFighter() {
		return fighter;
	}
	
	/**
	 * createNewFighter
	 */
	public void createNewFighter() {
		fighter = new Fighter();
	}
	
	/**
	 * this method could use by a user to create an item.
	 * @param tag player or monster
	 * @return
	 */
	public abstract void create(String tag,String name);
	
	public void buildInitialize(){
		fighter.setBackPack(new ArrayList<Item>());
		fighter.initializeBackPack();
		fighter.setWearItems(new ArrayList<Item>());
		fighter.setAbilities(new ArrayList<Ability>());
	}
	
	public abstract void setCharacterAbility();
	/**
	 * sets attributes for character
	 */
	public void setCharacterAttribute(){
		fighter.setArmor(new ArmorClass(fighter.getAbilities() .get(AbilityEnum.DEXTERITY.getValue()).getModifier()));
		fighter.setDamage(new DamageBonus(fighter.getAbilities().get(AbilityEnum.STRENGTH.getValue()).getModifier()));
		fighter.setAttack(new AttackBonus(fighter.getAbilities() .get(AbilityEnum.DEXTERITY.getValue()).getModifier(),fighter.getLevel())); 
		fighter.setHitPoint(new HitPoint(fighter.getAbilities().get(AbilityEnum.CONSTITUTION.getValue()).getModifier(),fighter.getLevel()));
		fighter.setAbilitiesListener();	
	}
	/**
	 * Shows abilities, attributes and items wear by character
	 */
	public void show(){
		fighter.showAbilities();
		fighter.showAttributes();
		fighter.emptyWearList();
	}

}
