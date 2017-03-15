package ca.concordia.soen6441.d20.factory;

import ca.concordia.soen6441.d20.fighter.Fighter;

/**
 * this an interface for creating character and items.
 * @author mostafa
 * @author alvaro
 */
public interface FighterFactory {
	
	/**
	 * this method could use by a user to create an item.
	 * @param tag player or monster
	 * @return
	 */
	public Fighter create(String tag,String name);
	public Fighter create(String tag,String name,int x ,int y);
}
