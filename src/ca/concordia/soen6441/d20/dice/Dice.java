package ca.concordia.soen6441.d20.dice;

import java.util.Random;
/**
 * This dice is used in the game for producing numbers randomly. These numbers then define how to play the game.
 * @author wmg
 *
 */
public class Dice {
	
	private Random random;
	
	/**
	 * Constructor:
	 * constructor tries to reset Random seed every time we create a dice,
	 * so the numbers would be different every time we open the application.
	 */
	public Dice(){
		random = new Random();
	}
	
	/**
	 * Equal to d6 dice
	 * @return number between 1 to 6 
	 */
	public int roll6(){
		return (random.nextInt(6) + 1 );
	}
	
	/**
	 * Equal to d10 dice
	 * @return number between 1 to 10
	 */
	public int roll10(){
		return (random.nextInt(10) + 1 );
	}
	
	/**
	 * Equal to d20 dice
	 * @return number between 1 to 20
	 */
	public int roll20(){
		return (random.nextInt(20) + 1 );
	}
	
	/**
	 * This method returns a Random object
	 * @return random object
	 */
	public Random getRandom(){
		return random;
	}
}
