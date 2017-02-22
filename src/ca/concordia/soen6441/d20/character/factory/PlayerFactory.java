package ca.concordia.soen6441.d20.character.factory;

import ca.concordia.soen6441.d20.character.Character;
import ca.concordia.soen6441.d20.character.Player;

public class PlayerFactory implements CharacterFactory {

	//TODOI: here we ned to implement the 3d20+1 dice to create a player randomly
	@Override
	public Character create(String tag) {
		return new Player(tag);
	}

}
