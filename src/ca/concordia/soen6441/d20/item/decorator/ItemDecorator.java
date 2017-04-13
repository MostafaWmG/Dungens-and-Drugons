package ca.concordia.soen6441.d20.item.decorator;

import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.Item;
/**
 * The abstract ItemDecorator class defines the functionality of 
 * any IItem implemented by subclasses of IItem.
 *
 */
public abstract class ItemDecorator implements IItem {
	protected final IItem decoratedItem;
	
	public ItemDecorator(Item decoratedItem) {
		this.decoratedItem = decoratedItem;
	}

	@Override
	public void specialEffect(Fighter attacker,Fighter target) {
		decoratedItem.specialEffect(attacker,target);
	}
}
