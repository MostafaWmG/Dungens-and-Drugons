package ca.concordia.soen6441.d20.character;
import java.util.ArrayList;
import java.util.List;

import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.exceptions.MoveNotValidException;
import ca.concordia.soen6441.d20.item.Ability;
import ca.concordia.soen6441.d20.item.Item;

/**
 * this class represents a character, a monster or a player
 * @author alvaro
 *
 */
public class Character extends GameObject {
	protected int level;
	protected String name;
	
	protected List<Ability> abilities;
	protected List<Item> wearItems;
	
	public Character(int initialPosistionX, int initialPositionY) {
		super(initialPosistionX, initialPositionY);
		abilities = new ArrayList<>();
		wearItems = new ArrayList<>();
	}
		
	public Character() {
		this(0, 0);
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
	
	public void addAbility(Ability ability) {
		abilities.add(ability);
	}
}
