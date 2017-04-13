package ca.concordia.soen6441.d20.item.decorator;

import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.Item;

public class Slaying extends ItemDecorator {

	public Slaying(Item decoratedItem) {
		super(decoratedItem);
	}

	@Override
	public void specialEffect(Fighter attacker, Fighter target) {
			System.out.println("Slaying:");
			target.getStrategy().activeSlayingEffect();
			decoratedItem.specialEffect(attacker,target);
	}
}
