package ca.concordia.soen6441.d20.character.factory;

import ca.concordia.soen6441.d20.character.Character;
import ca.concordia.soen6441.d20.character.Player;
import ca.concordia.soen6441.d20.dice.Dice;

/**
 * this a Factory class for creating character and items of the character.
 * @author wmg
 * @author alvaro
 */
public class PlayerFactory implements CharacterFactory {
	
	Dice dice ;
	Character character;
	
	/**
	 * constructor
	 * create a new dice 
	 */
	public PlayerFactory(){
		dice = new Dice();
	}
	
	/**
	 * create a character 
	 * @param name specify the name of the character
	 * @param tag  specify the type of the character
	 */
	@Override
	public Character create(String tag,String name) {
		character = new Player(tag,name);
		character.showAbilities();
		character.getArmor().showPoint();
		character.getAttack().showPoint();
		character.getDamage().showPoint();
		return character;
	}	
}
