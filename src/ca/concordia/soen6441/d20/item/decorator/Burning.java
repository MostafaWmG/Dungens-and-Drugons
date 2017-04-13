package ca.concordia.soen6441.d20.item.decorator;

import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;
/**
 * This class is a subclass of ItemDecorator that is used to implement Decorator pattern. 
 * @author Mostafa
 *
 */
public class Burning extends ItemDecorator {

	public Burning(Item decoratedItem) {
		super(decoratedItem);
	}

	@Override
	public void specialEffect(Fighter attacker, Fighter target) {
		Item item = attacker.getItem(ItemEnum.WEAPON);
		
		if(item.getEnchantmentType() == null && item.getAttributeType() == null){
			System.out.println("CHARACTER HAS NO WEAPON.");
		}else{
			int damage = item.getEnchantmentPoint() * 5;
			System.out.println("Burning Damage: " + damage);
			
			target.getStrategy().activeBuringEffect(damage);
			decoratedItem.specialEffect(attacker,target);
		}

	}
}
