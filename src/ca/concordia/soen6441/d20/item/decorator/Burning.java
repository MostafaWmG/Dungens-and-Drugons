package ca.concordia.soen6441.d20.item.decorator;

import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;

public class Burning extends ItemDecorator {

	public Burning(Item decoratedItem) {
		super(decoratedItem);
	}

	@Override
	public Fighter specialEffect(Fighter attacker, Fighter target) {
		Item item = attacker.getItem(ItemEnum.WEAPON);
		
		if(item.getEnchantmentType() == null && item.getAttributeType() == null){
			System.out.println("CHARACTER HAS NO WEAPON.");
			return decoratedItem.specialEffect(attacker,target);
		}
		
		int damage = item.getEnchantmentPoint() * 5;
		System.out.println("Burning Damage: " + damage);
		
		target.getStrategy().activeBuringEffect(damage);
		return decoratedItem.specialEffect(attacker,target);
	}
}
