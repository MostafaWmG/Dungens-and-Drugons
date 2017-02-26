package ca.concordia.soen6441.d20.character;
import java.util.ArrayList;
import java.util.List;

import ca.concordia.soen6441.d20.dice.Dice;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;
import ca.concordia.soen6441.d20.gamemap.element.GroundEntity;
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
	 * characterEntity: used for saving and loading characters
	 * wearItems : items that character already wear
	 * armorClass: the armor of the character
	 * attackBonus : the attack of the character
	 * damageBonus : the damage of the character
	 * acBonus : check if character have any Armor bonus from items or not 
	 */
	protected int level;
	protected String name;
	
	private CharacterEntity characterEntity;
	
	protected List <Item> wearItems;
	protected List <Ability> abilities;
	protected ArmorClass armorClass;
	protected AttackBonus attackBonus;
	protected DamageBonus damageBonus;
	protected HitPoint hitPoint;
	protected Dice dice;
	protected boolean acBonus;
	
	public Character(int initialPosistionX, int initialPositionY) {
		
		//I think level should be deleted now, since it is defined in its entity class
		level = 1;
		wearItems = new ArrayList<Item>();
		abilities = new ArrayList<Ability>();
		setCharacterAbility();
		armorClass = new ArmorClass(abilities.get(AbilityEnum.DEXTERITY.getValue()).getModifier());
		damageBonus = new DamageBonus(abilities.get(AbilityEnum.STRENGTH.getValue()).getModifier());
		attackBonus = new AttackBonus(level);
		hitPoint = new HitPoint(abilities.get(AbilityEnum.CONSTITUTION.getValue()).getModifier(),level);
		setAbilitiesListener();
		acBonus = false;
		
		init();
		
	}
		
	public Character() {
		
		//I think level should be deleted now, since it is defined in its entity class
		level = 1;
		wearItems = new ArrayList<Item>();
		abilities = new ArrayList<Ability>();
		setCharacterAbility();
		armorClass = new ArmorClass(abilities.get(AbilityEnum.DEXTERITY.getValue()).getModifier());
		damageBonus = new DamageBonus(abilities.get(AbilityEnum.STRENGTH.getValue()).getModifier());
		attackBonus = new AttackBonus(level);
		hitPoint = new HitPoint(abilities.get(AbilityEnum.CONSTITUTION.getValue()).getModifier(),level);
		setAbilitiesListener();
		acBonus = false;
		
		init();
	}
	/*
	 * This method sets entity for each object created
	 */
	private void init(){
		
		setCharacterEntity(new CharacterEntity());
	}
	
	/**
	 * move the character around 
	 * @param dx x delta of the movement, relative to the character current position
	 * @param dy y delta of the movement, relative to the character current position
	 */
	public void move(int dx, int dy) {
		try {
			//changing keyword "this" to characterEntity
			//adding characterEntity to the other lines
			characterEntity.getField().move(getLocation().getX(), getLocation().getY(), getLocation().getX()+dx, getLocation().getY()+dy);
			characterEntity.getLocation().setX(getLocation().getX()+dx);
			characterEntity.getLocation().setY(getLocation().getY()+dy);
		
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
		abilities.get(AbilityEnum.DEXTERITY.getValue()).addListener(armorClass);
		abilities.get(AbilityEnum.CONSTITUTION.getValue()).addListener(hitPoint);
		abilities.get(AbilityEnum.STRENGTH.getValue()).addListener(damageBonus);
	}
	
	public void levelUp(int point){
		characterEntity.setLevel(characterEntity.getLevel() + 1);
		attackBonus.update(characterEntity.getLevel());
		hitPoint.setLevel(characterEntity.getLevel());
		iterate(abilities, point);
	}

	/**
	 * check if character have wore this item or not
	 * @param itemEnum type of item
	 * @return if true : has this item 
	 */
	public boolean hasItem(ItemEnum itemEnum){
		if(characterEntity.wearItems.get(itemEnum.getValue()) != null)
			return true;
		else
			return false;
	}
	
	/**
	 * print all statistics of the characters
	 */
	public void showAbilities(){
		
		for (int i= 0 ; i < AbilityEnum.values().length ; i++ ){			
			System.out.println("character ability : " + AbilityEnum.values()[i] + " ,Score :  " + abilities.get(AbilityEnum.values()[i].getValue()).getScore() + " ,modifier : " + abilities.get(AbilityEnum.values()[i].getValue()).getModifier() );
		}
	}
	
	/**
	 * add ability to the character
	 * @param ability the ability we are adding .
	 */
	public void addAbility(Ability ability) {
		abilities.add(ability.getAbilityEnum().getValue(), ability);
	}
	
	public Ability getAbility(AbilityEnum abilityEnum){
		return (Ability) abilities.get(abilityEnum.getValue());
	}
	
	/**
	 * character will wear this item.
	 * @param item which is going to be wear.
	 */
	public void addItem(Item item) {
		characterEntity.wearItems.add(item.getItemEnum().getValue(),item);
	}
	
	public Item getItem(ItemEnum itemEnum){
		return (Item)characterEntity.wearItems.get(itemEnum.getValue());
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

	private void iterate(List<Ability> list , int value) {
		
		for(int i =0 ; i < list.size() ; i ++){
			list.get(i).update(value);
		}		
	}

	@Override
	public GameObjectEntity getEntity() {
		// TODO Auto-generated method stub
		return characterEntity;
	}
	
	/*
	 * @return the character entity 
	 */
	public CharacterEntity getCharacterEntity(){
		return characterEntity;
	}
	
	/*
	 * @param characterEntity to set
	 */
	public void setCharacterEntity(CharacterEntity characterEntity){
		this.characterEntity = characterEntity;
	}
}
