package ca.concordia.soen6441.d20.item.decorator;

import ca.concordia.soen6441.d20.fighter.Fighter;

public interface IItem {
	public Fighter specialEffect(Fighter attacker,Fighter target);
}
