package ca.concordia.soen6441.d20.item;

import java.util.List;

import ca.concordia.soen6441.d20.fighter.Fighter;

public interface IRoot {
		
	public  abstract void putRootedIntoBackPack(List<Item> itemsR,Fighter fighter);

}