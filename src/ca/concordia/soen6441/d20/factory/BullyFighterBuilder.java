package ca.concordia.soen6441.d20.factory;

import java.util.List;

import ca.concordia.soen6441.d20.ability.Ability;
import ca.concordia.soen6441.d20.ability.AbilityEnum;


/**
 * This is a BullyFighterBuilder class for creating BullyFighter
 * @author wmg
 */
public class BullyFighterBuilder extends FighterBuilder {
		
	/**
	 * create a character 
	 * @param name specify the name of the character
	 * @param tag  specify the type of the character
	 */
	@Override
	public void create(String tag,String name) {
		getFighter().setName(name);
		getFighter().setTag(tag);
	}

	@Override
	public void setCharacterAbility() {
		List<Integer> numbers = getFighter().rollDice();
		
		System.out.println("<<<Bully Fighter CREATED RANDOMLY>>> ");
		getFighter().setCharacterAbility();
		getFighter().replaceAbility(new Ability(AbilityEnum.STRENGTH,numbers.get(numbers.size()-1),(int)Math.floor( (numbers.get(numbers.size()-1) - 10) /2 )));
		getFighter().replaceAbility(new Ability(AbilityEnum.CONSTITUTION,numbers.get(numbers.size()-2),(int)Math.floor( (numbers.get(numbers.size()-2) - 10) /2 )));
		getFighter().replaceAbility(new Ability(AbilityEnum.DEXTERITY,numbers.get(numbers.size()-3),(int)Math.floor( (numbers.get(numbers.size()-3) - 10) /2 )));
		getFighter().replaceAbility(new Ability(AbilityEnum.INTELLIGENCE,numbers.get(numbers.size()-4),(int)Math.floor( (numbers.get(numbers.size()-4) - 10) /2 )));
		getFighter().replaceAbility(new Ability(AbilityEnum.CHARISMA,numbers.get(numbers.size()-5),(int)Math.floor( (numbers.get(numbers.size()-5) - 10) /2 )));
		getFighter().replaceAbility(new Ability(AbilityEnum.WISDOM,numbers.get(numbers.size()-6),(int)Math.floor( (numbers.get(numbers.size()-6) - 10) /2 )));
	}
	

}
