package ca.concordia.soen6441.d20.gamemap.validator;

import ca.concordia.soen6441.d20.gamemap.GameMap;
/**
 * validate a map so it cannot be smaller than 2x2
 *
 */
public class SizeValidator {
	private static final int MIN_HIGHT= 2;
	private static final int MIN_WIDTH= 2;
private GameMap gameMap;
	
	public SizeValidator(GameMap gameMap) {
		this.gameMap = gameMap;
	}
	
	/**
	 * validate the size of the map. It needs to be bigger that 2x2
	 * @param gameMap the game map
	 * @return true if the validation successes, false otherwise
	 */
	public boolean validate() {
		return this.gameMap.getHeight()>MIN_HIGHT && this.gameMap.getWidth()>MIN_WIDTH;
	}

}
