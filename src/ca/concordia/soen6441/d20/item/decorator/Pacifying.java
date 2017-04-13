package ca.concordia.soen6441.d20.item.decorator;

import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.strategy.FriendlyNPC;
/**
 * This class is subclass of ItemDecorator. It is used to implement Decorator pattern.
 * target adopts the “Friendly NPC” character strategy.  
 * 
 */

public class Pacifying extends ItemDecorator {

	public Pacifying(Item decoratedItem) {
		super(decoratedItem);
	}

	@Override
	public void specialEffect(Fighter attacker, Fighter target) {
		
				target.setStrategy(new FriendlyNPC(target));
			
			decoratedItem.specialEffect(attacker,target);
	}
}
