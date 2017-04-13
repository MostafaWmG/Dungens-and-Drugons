package ca.concordia.soen6441.d20.item.decorator;

import ca.concordia.soen6441.d20.fighter.Fighter;
/**
 * 
 * This interface is used for taking advantage of Decorator pattern. Classes implementing this class
 * add a special effect to the characters of the game
 *
 */
public interface IItem {
	public void specialEffect(Fighter attacker,Fighter target);
}
