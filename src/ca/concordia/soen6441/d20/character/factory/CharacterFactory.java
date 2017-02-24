package ca.concordia.soen6441.d20.character.factory;

import ca.concordia.soen6441.d20.character.Character;
import ca.concordia.soen6441.d20.item.AbilityEnum;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;
/*
 * this an interface for creating character and items.
 * @author mostafa
 * @author alvaro
 */
public interface CharacterFactory {
	
	/**
	 * this method could use by a user to create an item.
	 * @param tag player or monster
	 * @return
	 */
	public Character create(String tag,String name);
	
	/**
	 * this method could use by a user to create an item.
	 * @param itemEnum with type of item do you want to create (helmet etc..)
	 * @param abienchantmentType what is your enchantmentType ( intelligence etc..) 
	 * @return the item that we build if it is null we can't build this item because of gameRules.
	 */
	public Item createItem(ItemEnum itemEnum , AbilityEnum enchantmentType);
}
