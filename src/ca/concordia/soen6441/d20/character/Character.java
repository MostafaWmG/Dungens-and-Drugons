package ca.concordia.soen6441.d20.character;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.Map;
//import java.util.Map.Entry;

import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;
import ca.concordia.soen6441.d20.gamemap.exceptions.MoveNotValidException;
import ca.concordia.soen6441.d20.item.Ability;
import ca.concordia.soen6441.d20.item.AbilityEnum;
import ca.concordia.soen6441.d20.item.ArmorClass;
import ca.concordia.soen6441.d20.item.AttackBonus;
import ca.concordia.soen6441.d20.item.DamageBonus;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;

/**
 * this class represents a character, a monster or a player
 * @author alvaro
 * @author mostafa
 */
public class Character extends GameObject {
	/**
	 * level : level
	 * name : name 
	 * wearItems : items that character already wear
	 * armorClass: the armor of the character
	 * attackBonus : the attack of the character
	 * damageBonus : the damage of the character
	 * acBonus : check if character have any Armor bonus from items or not 
	 */
	protected int level;
	protected String name;
	
	protected Map<ItemEnum, Item> wearItems;
	protected Map<AbilityEnum, Ability> abilities;
	protected ArmorClass armorClass;
	protected AttackBonus attackBonus;
	protected DamageBonus damageBonus;
	protected boolean acBonus;
	
	public Character(int initialPosistionX, int initialPositionY) {
		super(initialPosistionX, initialPositionY);
		wearItems = new HashMap<ItemEnum,Item>();
		abilities = new HashMap<AbilityEnum,Ability>();
		armorClass = new ArmorClass();
		damageBonus = new DamageBonus();
		attackBonus = new AttackBonus();
		level = 1;
		acBonus = false;
	}
		
	public Character() {
		this(0, 0);
		wearItems = new HashMap<ItemEnum,Item>();
		abilities = new HashMap<AbilityEnum,Ability>();
		armorClass = new ArmorClass();
		damageBonus = new DamageBonus();
		attackBonus = new AttackBonus();
		level = 1;
		acBonus = false;
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
	 * update armor at the creation phase and levelUp phase
	 */
	public void updateArmor(){
		if(acBonus){
			// calculate using ac bonus
		}else{
			armorClass.setPoint(10 + abilities.get(AbilityEnum.DEXTERITY).getModifier());
		}
	}
	
	/**
	 * updateAttack at the creation phase and levelUp phase
	 */
	public void updateAttack(){
		attackBonus.setPoint(1 * level);
	}
	
	/**
	 * updateAttack at the creation phase and levelUp phase
	 */
	public void updateDamage(){
		damageBonus.setPoint(abilities.get(AbilityEnum.STRENGTH).getModifier());
	}
	
	/**
	 * update all damage , attack , armor
	 */
	public void updateAll(){
		updateDamage();
		updateAttack();
		updateArmor();
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
	
	/**
	 * print all statistics of the characters
	 */
	public void showAbilities(){
		
		System.out.println("Name:" + name + " ,CharacterType: " + tag);
		for (int i= 0 ; i < AbilityEnum.values().length ; i++ ){			
			System.out.println("character ability : " + AbilityEnum.values()[i] + " ,Score :  " + abilities.get(AbilityEnum.values()[i]).getScore() + " ,modifier : " + abilities.get(AbilityEnum.values()[i]).getModifier() );
		}
	}
	
	/**
	 * add ability to the character
	 * @param ability the ability we are adding .
	 */
	public void addAbility(Ability ability) {
		abilities.put(ability.getAbilityEnum(), ability);
	}
	
	public Ability getAbility(AbilityEnum abilityEnum){
		return (Ability) abilities.get(abilityEnum);
	}
	
	/**
	 * character will wear this item.
	 * @param item which is going to be wear.
	 */
	public void addItem(Item item) {
		wearItems.put(item.getItemEnum(), item);
	}
	
	public Item getItem(ItemEnum itemEnum){
		return (Item) wearItems.get(itemEnum);
	}
	
	public ArmorClass getArmor(){
		return armorClass;
	}
	
	public void setArmor(ArmorClass armorClass){
		this.armorClass = armorClass;
	}
	
	public AttackBonus getAttack(){
		return attackBonus;
	}
	
	public void setAttack(AttackBonus attackBonus){
		this.attackBonus = attackBonus;
	}
	
	public DamageBonus getDamage(){
		return damageBonus;
	}
	
	public void setDamage(DamageBonus damageBonus){
		this.damageBonus = damageBonus;
	}
	public int getLevel(){
		return level;
		
	}
	
	public void setLevel(int level){
		this.level = level;
	}

	@Override
	public GameObjectEntity getEntity() {
		// TODO Auto-generated method stub
		return null;
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
