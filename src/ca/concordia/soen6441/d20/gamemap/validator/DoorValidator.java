package ca.concordia.soen6441.d20.gamemap.validator;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.element.Door;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;

public class DoorValidator {
	private GameMap gameMap;
	
	public DoorValidator(GameMap gameMap) {
		this.gameMap = gameMap;
	}
	
	public boolean validate() {
		boolean valid = false;
		int width = gameMap.getWidth();
		int height = gameMap.getHeight();
		
		for(int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				GameObject object = gameMap.getGameObjectAtLocation(new Location(x, y));
				if (object instanceof Door) valid = true;
			}
		}
		return valid;		
	}

}
