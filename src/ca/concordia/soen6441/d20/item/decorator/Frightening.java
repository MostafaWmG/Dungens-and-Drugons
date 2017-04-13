package ca.concordia.soen6441.d20.item.decorator;

import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;
/**
 * 
 * This class is subclass of ItemDecorator. It is used to implement Decorator pattern.
 * Target in frightening mode runs away from character for a number of turns equal to the enchantment bonus of the weapon.
 */
public class Frightening extends ItemDecorator {

	public Frightening(Item decoratedItem) {
		super(decoratedItem);
	}

	@Override
	public void specialEffect(Fighter attacker, Fighter target) {
		Item item = attacker.getItem(ItemEnum.WEAPON);
		
		if(item.getEnchantmentType() == null && item.getAttributeType() == null){
			System.out.println("CHARACTER HAS NO WEAPON.");
		}else{
			int fearTimes = item.getEnchantmentPoint();
			System.out.println("Freeze Times: " + fearTimes);
			
			target.getStrategy().activeFearEffect(fearTimes,attacker);
			decoratedItem.specialEffect(attacker,target);
		}

	}
}
