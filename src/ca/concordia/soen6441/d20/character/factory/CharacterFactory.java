package ca.concordia.soen6441.d20.character.factory;

import ca.concordia.soen6441.d20.character.Character;

public interface CharacterFactory {
	public Character create(String tag);
}
