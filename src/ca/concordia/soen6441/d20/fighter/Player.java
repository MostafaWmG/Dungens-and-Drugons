package ca.concordia.soen6441.d20.fighter;
//package ca.concordia.soen6441.d20.character;
//import java.util.ArrayList;
//import java.util.List;
//
//import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;
//import ca.concordia.soen6441.d20.item.Item;
///**
// * this a Player class .
// * @author alvaro
// */
//// this is class commented currently to get things easier no time to handle player and monster.all them now are handled in character using tag.
////TODO Do we really need a Character class?
//public class Player extends Character {
//	private static final int BACKPACK_MAX_CAPACITY = 5;
//	private static final int ELEMENT_NOT_FOUND = -1;
//	
//	private List<Item> backpack;
//	
//	public Player(String tag,String name) {
//		super();
//		// we need this tag . it will show character is a monster or player . name shows the custom name for player or monster.
//		// we can re factor that later
//		setTag(tag);
//		setName(name);
//		backpack = new ArrayList<>();
//	}
//	
//	public void changeItem(Item item){
//		int index = backpack.indexOf(item);
//		
//		if(index == ELEMENT_NOT_FOUND) return;
//		
//		backpack.set(index, item);
//	}
//	
//	@Override
//	public GameObjectEntity getEntity() {
//		// TODO Auto-generated method stub
//		return null;
//	}	
//	
//	public void pickUp(Item newItem) {
//		if(backpack.size() >= BACKPACK_MAX_CAPACITY) return;
//		
//		backpack.add(newItem);
//	}
//
//}