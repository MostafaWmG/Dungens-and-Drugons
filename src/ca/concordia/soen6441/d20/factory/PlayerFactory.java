package ca.concordia.soen6441.d20.factory;

//import ca.concordia.soen6441.d20.character.Player;
import ca.concordia.soen6441.d20.dice.Dice;
import ca.concordia.soen6441.d20.fighter.Fighter;

/**
 * This is a Factory class for creating character and items of the character.
 * @author wmg
 * @author alvaro
 */
public class PlayerFactory implements FighterFactory {
	
	Dice dice ;
	Fighter character;
	
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
	public Fighter create(String tag,String name) {
		if(tag.equals("Enemy")){
			return createCharacter(tag, name);
		}else if(tag.equals("Player")){
			return createCharacter(tag, name);
		}else {
			return null;
		}

	}
	
	/**
	 * Creating a character.
	 * @param tag set the tag for the character.
	 * @param name set the name for the character.
	 * @return character object.
	 */
	private Fighter createCharacter(String tag,String name){
		character = new Fighter(tag,name);
//		character.showAll();
		return character;
	}

	@Override
	public Fighter create(String tag, String name, int x, int y) {
		if(tag.equals("Enemy")){
			return  new Fighter(tag,name,x,y);
		}else if(tag.equals("Player")){
			return new Fighter(tag,name,x,y);
		}else {
			return null;
		}

		
	}
}
