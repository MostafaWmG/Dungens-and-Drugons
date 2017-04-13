package ca.concordia.soen6441.d20.item.decorator;

import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;

public class Freezing extends ItemDecorator {

	public Freezing(Item decoratedItem) {
		super(decoratedItem);
	}

	@Override
	public void specialEffect(Fighter attacker, Fighter target) {
		Item item = attacker.getItem(ItemEnum.WEAPON);
		
		if(item.getEnchantmentType() == null && item.getAttributeType() == null){
			System.out.println("CHARACTER HAS NO WEAPON.");
		}else{
			int freezeTimes = item.getEnchantmentPoint();
			System.out.println("Freeze Times: " + freezeTimes);
			
			target.getStrategy().activeFreezeEffect(freezeTimes);
			decoratedItem.specialEffect(attacker,target);
		}

	}
}
