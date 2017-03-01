package ca.concordia.soen6441.d20.character.factory;

import ca.concordia.soen6441.d20.character.Character;

/**
 * this an interface for creating character and items.
 * @author mostafa
 * @author alvaro
 */
public interface CharacterFactory {
	
	/**
	 * this method could use by a user to create an item.
	 * @param tag player or monster
	 * @return
	 */
	public Character create(String tag,String name);
}
