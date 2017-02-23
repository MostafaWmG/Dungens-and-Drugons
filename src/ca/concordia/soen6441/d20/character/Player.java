package ca.concordia.soen6441.d20.character;
import java.util.ArrayList;
import java.util.List;

import ca.concordia.soen6441.d20.item.Item;
/**
 * this a Player class .
 * @author alvaro
 */
public class Player extends Character {
	private static final int BACKPACK_MAX_CAPACITY = 5;
	private static final int ELEMENT_NOT_FOUND = -1;
	
	private List<Item> backpack;
	
	public Player(String tag) {
		super();
		this.tag = tag;
		backpack = new ArrayList<>();
	}
	
	public void changeItem(Item item){
		int index = backpack.indexOf(item);
		
		if(index == ELEMENT_NOT_FOUND) return;
		
		backpack.set(index, item);
	}
	
	public void pickUp(Item newItem) {
		if(backpack.size() >= BACKPACK_MAX_CAPACITY) return;
		
		backpack.add(newItem);
	}

}
