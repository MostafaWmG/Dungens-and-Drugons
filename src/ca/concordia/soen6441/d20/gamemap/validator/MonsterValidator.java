package ca.concordia.soen6441.d20.gamemap.validator;

import ca.concordia.soen6441.d20.character.Player;
import ca.concordia.soen6441.d20.character.Character;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;

/**
 * class that models a validator searching for a monster on the map
 * @author alvaro
 *
 */
public class MonsterValidator {
	private GameMap gameMap;
	
	public MonsterValidator(GameMap gameMap) {
		this.gameMap = gameMap;
	}
	
	/**
	 * validate if the map has at least a monster in it
	 * @param gameMap the game map
	 * @return true if the validation successes, false otherwise
	 */
	public boolean validate() {
		boolean valid = false;
		int width = gameMap.getWidth();
		int height = gameMap.getHeight();
		
		for(int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				GameObject object = gameMap.getGameObjectAtLocation(new Location(x, y));
				if (object instanceof Character && !(object instanceof Player)) valid = true;
			}
		}
		return valid;		
	}

}
