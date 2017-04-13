package ca.concordia.soen6441.d20.item.decorator;

import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.Item;

public class Pacifying extends ItemDecorator {

	public Pacifying(Item decoratedItem) {
		super(decoratedItem);
	}

	@Override
	public void specialEffect(Fighter attacker, Fighter target) {
		
			if(target.getTag().equals(attacker.getTag()))
				target.setStrategy(attacker.getStrategy());
			
			decoratedItem.specialEffect(attacker,target);
	}
}
