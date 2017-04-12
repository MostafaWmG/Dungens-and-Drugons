package ca.concordia.soen6441.d20.item.decorator;

import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.Item;

public abstract class ItemDecorator implements IItem {
	protected final IItem decoratedItem;
	
	public ItemDecorator(Item decoratedItem) {
		this.decoratedItem = decoratedItem;
	}

	@Override
	public Fighter specialEffect(Fighter attacker,Fighter target) {
		return decoratedItem.specialEffect(attacker,target);
	}
}
