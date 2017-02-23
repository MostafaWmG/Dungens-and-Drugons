package ca.concordia.soen6441.d20.character;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.Map;
//import java.util.Map.Entry;

import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.exceptions.MoveNotValidException;
import ca.concordia.soen6441.d20.item.Ability;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.AbilityEnum;
import ca.concordia.soen6441.d20.item.ItemEnum;

/**
 * this class represents a character, a monster or a player
 * @author alvaro
 * @author mostafa
 */
public class Character extends GameObject {
	protected int level;
	protected String name;
	
	protected Map<ItemEnum, Item> wearItems;
	protected Map<AbilityEnum, Ability> abilities;
	
	public Character(int initialPosistionX, int initialPositionY) {
		super(initialPosistionX, initialPositionY);
		wearItems = new HashMap<ItemEnum,Item>();
		abilities = new HashMap<AbilityEnum,Ability>();
	}
		
	public Character() {
		this(0, 0);
		wearItems = new HashMap<ItemEnum,Item>();
		abilities = new HashMap<AbilityEnum,Ability>();
	}

	/**
	 * move the character around 
	 * @param dx x delta of the movement, relative to the character current position
	 * @param dy y delta of the movement, relative to the character current position
	 */
	public void move(int dx, int dy) {
		try {
			this.field.move(location.getX(), location.getY(), location.getX()+dx, location.getY()+dy);
			location.setX(location.getX()+dx);
			location.setY(location.getY()+dy);
		
		} catch(MoveNotValidException e) {
			e.printStackTrace();
		}
	}
	
	public void attack(Character enemy) {
		
	}
	
	/**
	 * check if character have wore this item or not
	 * @param itemEnum type of item
	 * @return if true : has this item 
	 */
	public boolean hasItem(ItemEnum itemEnum){
		if(wearItems.containsKey(itemEnum))
			return true;
		else
			return false;
	}
	
	public void addAbility(Ability ability) {
		abilities.put(ability.getAbilityEnum(), ability);
	}
	
	public Ability getAbility(AbilityEnum abilityEnum){
		return (Ability) abilities.get(abilityEnum);
	}
	
	/**
	 * character will wear this item.
	 * @param item which is going to be weared.
	 */
	public void addItem(Item item) {
		wearItems.put(item.getItemEnum(), item);
	}
	
	public Item getItem(ItemEnum itemEnum){
		return (Item) wearItems.get(itemEnum);
	}
	
	
//	private void emptyMap(Map map) {
//		Iterator iterator = map.entrySet().iterator();
//		
//		while(iterator.hasNext()){
//			Map.Entry pair = (Map.Entry) iterator.next();
//			pair.setValue(null);
//		}
//	}
}
