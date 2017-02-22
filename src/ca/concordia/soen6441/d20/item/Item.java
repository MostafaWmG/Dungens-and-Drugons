package ca.concordia.soen6441.d20.item;

public class Item {
	private int points;
	private ItemEnum item;
	private AbilityEnum ability;
	private int enchantment;

	public Item(ItemEnum item,AbilityEnum ability){
		this.item = item;
		this.ability = ability;
	}
	
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
}
