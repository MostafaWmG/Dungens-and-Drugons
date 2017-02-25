package ca.concordia.soen6441.d20.character;
import java.util.HashMap;
import java.util.Iterator;
//import java.util.Iterator;
import java.util.Map;
//import java.util.Map.Entry;



import java.util.Map.Entry;

import ca.concordia.soen6441.d20.dice.Dice;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;
import ca.concordia.soen6441.d20.gamemap.exceptions.MoveNotValidException;
import ca.concordia.soen6441.d20.item.Ability;
import ca.concordia.soen6441.d20.item.AbilityEnum;
import ca.concordia.soen6441.d20.item.ArmorClass;
import ca.concordia.soen6441.d20.item.AttackBonus;
import ca.concordia.soen6441.d20.item.DamageBonus;
import ca.concordia.soen6441.d20.item.HitPoint;
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
	protected HitPoint hitPoint;
	protected Dice dice;
	protected boolean acBonus;
	
	public Character(int initialPosistionX, int initialPositionY) {
		level = 1;
		wearItems = new HashMap<ItemEnum,Item>();
		abilities = new HashMap<AbilityEnum,Ability>();
		setCharacterAbility();
		armorClass = new ArmorClass(abilities.get(AbilityEnum.DEXTERITY).getModifier());
		damageBonus = new DamageBonus(abilities.get(AbilityEnum.STRENGTH).getModifier());
		attackBonus = new AttackBonus(level);
		hitPoint = new HitPoint(abilities.get(AbilityEnum.CONSTITUTION).getModifier(),level);
		setAbilitiesListener();
		acBonus = false;
	}
		
	public Character() {
		level = 1;
		wearItems = new HashMap<ItemEnum,Item>();
		abilities = new HashMap<AbilityEnum,Ability>();
		setCharacterAbility();
		armorClass = new ArmorClass(abilities.get(AbilityEnum.DEXTERITY).getModifier());
		damageBonus = new DamageBonus(abilities.get(AbilityEnum.STRENGTH).getModifier());
		attackBonus = new AttackBonus(1);
		hitPoint = new HitPoint(abilities.get(AbilityEnum.CONSTITUTION).getModifier(),level);
		setAbilitiesListener();
		acBonus = false;
	}
	
	/**
	 * move the character around 
	 * @param dx x delta of the movement, relative to the character current position
	 * @param dy y delta of the movement, relative to the character current position
	 */
	public void move(int dx, int dy) {
		try {
			this.getField().move(getLocation().getX(), getLocation().getY(), getLocation().getX()+dx, getLocation().getY()+dy);
			getLocation().setX(getLocation().getX()+dx);
			getLocation().setY(getLocation().getY()+dy);
		
		} catch(MoveNotValidException e) {
			e.printStackTrace();
		}
	}
	
	public void attack(Character enemy) {
		
	}
		
	/**
	 * randomly determining the character abilites using dice6 and d20 rules
	 */
	private void setCharacterAbility(){
		
		int roll = 0;
		dice  = new Dice();
		for (int i= 0 ; i < AbilityEnum.values().length ; i++ ){
			
			roll = 0 ;
			roll = dice.roll6() + dice.roll6() + dice.roll6() ;
			
			System.out.println("DEBUGLOG!! " + " character ability : " + AbilityEnum.values()[i] + " ,Score :  " + roll + " ,modifier : " + (int)Math.floor( (roll - 10) /2 ));
			// To determine an ability modifier without consulting the table, subtract 10 from the ability score and then divide the result by 2 (round down).
		    addAbility(new Ability(AbilityEnum.values()[i],roll,(int)Math.floor( (roll - 10) /2 )) );
		}
		
	}
	
	public void setAbilitiesListener(){
		abilities.get(AbilityEnum.DEXTERITY).addListener(armorClass);
		abilities.get(AbilityEnum.CONSTITUTION).addListener(hitPoint);
		abilities.get(AbilityEnum.STRENGTH).addListener(damageBonus);
	}
	
	public void levelUp(int point){
		level ++ ;
		attackBonus.update(level);
		hitPoint.setLevel(level);
		iterate(abilities, point);
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
		
		System.out.println("Name:" + name + " ,CharacterType: " + getTag());
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

	private void iterate(Map<AbilityEnum,Ability> map , int value) {
		Iterator<Entry<AbilityEnum, Ability>> iterator = map.entrySet().iterator();
		
		while(iterator.hasNext()){
			map.entrySet().iterator().next().getValue().update(value);
		}
		
	}

	@Override
	public GameObjectEntity getEntity() {
		// TODO Auto-generated method stub
		return null;
	}
}
