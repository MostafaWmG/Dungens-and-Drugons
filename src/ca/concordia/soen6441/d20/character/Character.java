package ca.concordia.soen6441.d20.character;
import java.util.ArrayList;
import java.util.List;

import ca.concordia.soen6441.d20.dice.Dice;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;
import ca.concordia.soen6441.d20.gamemap.exceptions.MoveNotValidException;
import ca.concordia.soen6441.d20.item.Ability;
import ca.concordia.soen6441.d20.item.AbilityEnum;
import ca.concordia.soen6441.d20.item.ArmorClass;
import ca.concordia.soen6441.d20.item.AttackBonus;
import ca.concordia.soen6441.d20.item.AttributeEnum;
import ca.concordia.soen6441.d20.item.DamageBonus;
import ca.concordia.soen6441.d20.item.HitPoint;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;

/**
 * this class represents a character, a monster or a player
 * @author alvaro
 * @author mostafa
 */
// ArmorClass,HitPoint,DamageBonus,AttackBonus need to re factor and all of them can have same interface or inherits from attribute
public class Character extends GameObject {
	/**
	 * level : level
	 * name : name 
	 * characterEntity: used for saving and loading characters
	 * wearItems : items that character already wear
	 * armorClass: the armor of the character
	 * attackBonus : the attack of the character
	 * damageBonus : the damage of the character
	 * hitPoint    : the hitPoint of the character
	 * dice        : the dice class for rolling a dice
	 */	
	private CharacterEntity characterEntity;
	
	protected List <Item> wearItems;
	protected List <Ability> abilities;
	protected ArmorClass armorClass;
	protected AttackBonus attackBonus;
	protected DamageBonus damageBonus;
	protected HitPoint hitPoint;
	protected Dice dice;
	
	public Character(int initialPosistionX, int initialPositionY) {
		init();
		wearItems = new ArrayList<Item>();
		abilities = new ArrayList<Ability>();
		setCharacterAbility();
		armorClass = new ArmorClass(abilities .get(AbilityEnum.DEXTERITY.getValue()).getModifier());
		damageBonus = new DamageBonus(abilities.get(AbilityEnum.STRENGTH.getValue()).getModifier());
		attackBonus = new AttackBonus(getLevel());
		hitPoint = new HitPoint(abilities.get(AbilityEnum.CONSTITUTION.getValue()).getModifier(),getLevel());
		setAbilitiesListener();		

		
	}
		
	public Character() {
		init();
		wearItems = new ArrayList<Item>();
		abilities = new ArrayList<Ability>();
		setCharacterAbility();
		armorClass = new ArmorClass(abilities.get(AbilityEnum.DEXTERITY.getValue()).getModifier());
		damageBonus = new DamageBonus(abilities.get(AbilityEnum.STRENGTH.getValue()).getModifier());
		attackBonus = new AttackBonus(getLevel());
		hitPoint = new HitPoint(abilities.get(AbilityEnum.CONSTITUTION.getValue()).getModifier(),getLevel());
		setAbilitiesListener();		

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
			getCharacterEntity().getField().move(getLocation().getX(), getLocation().getY(), getLocation().getX()+dx, getLocation().getY()+dy);
			getCharacterEntity().getLocation().setX(getLocation().getX()+dx);
			getCharacterEntity().getLocation().setY(getLocation().getY()+dy);
		
		} catch(MoveNotValidException e) {
			e.printStackTrace();
		}
	}
	
	public void attack(Character enemy) {
		
	}
	
	/**
	 * order to character to wear this item
	 * @param item the item that is going to be weared.
	 */
	public void putOnItem(Item item){
		
		if(hasItem(item.getItemEnum())){
			 System.out.println("Character already has item");
		}else {
			wearItems.add(item.getItemEnum().getValue(), item);
			wearItem(item, item.getEnchantmentPoint());
			System.out.println("characted wore the item");
		}
	}
	
	// need to  re factor
	/**
	 * this method updates the character statistics that character gains by wearing or removing the item
	 * @param item the item that character wears
	 * @param value the item value that affect the character
	 */
	public void wearItem(Item item , int value){
		if (item.getAttributeType() == null ){
			abilities.get(item.getEnchantmentType().getValue()).update(value);
		}else if (item.getEnchantmentType() == null){
			
			if(item.getAttributeType() == AttributeEnum.ARMORCLASS){
				armorClass.setBase(armorClass.getBase() + value);
			}else if (item.getAttributeType() == AttributeEnum.ATTACKBONUS){
				attackBonus.setBase(attackBonus.getBase() + value);
			}else if (item.getAttributeType() == AttributeEnum.DAMAGEBONUS){
				damageBonus.setBase(damageBonus.getBase() + value);
			}
		}else {
			System.out.println("Error: wrong info");
		}
	}
	
	/**
	 * this method will remove an item form character .
	 * @param item the item that is going to be removed.
	 */
	public void removeItem(Item item){
		
		if(hasItem(item.getItemEnum())){
			wearItems.remove(item.getItemEnum().getValue());
			wearItem(item, -1 * item.getEnchantmentPoint());
			System.out.println("item Removed");
		}else {
			 System.out.println("Character dont have this item");
		}
	}
	
	/**
	 * randomly determining the character abilities using dice6 and d20 rules
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
	
	/**
	 * this method sets the listeners of abilities.
	 * so when we modify any of these abilities it will automatically changes other statistics related to it. 
	 */
	public void setAbilitiesListener(){
		abilities.get(AbilityEnum.DEXTERITY.getValue()).addListener(armorClass);
		abilities.get(AbilityEnum.CONSTITUTION.getValue()).addListener(hitPoint);
		abilities.get(AbilityEnum.STRENGTH.getValue()).addListener(damageBonus);
	}
	
	/**
	 * this method will level up our character 
	 * @param point the amount of level that our character gain in level up action
	 */
	public void levelUp(int point){
		getCharacterEntity().setLevel(getCharacterEntity().getLevel() + 1);
		attackBonus.update(getCharacterEntity().getLevel());
		hitPoint.setLevel(getCharacterEntity().getLevel());
		iterate(abilities,wearItems, point);
	}

	/**
	 * check if character have wore this item or not
	 * @param itemEnum type of item
	 * @return if true : has this item 
	 */
	public boolean hasItem(ItemEnum itemEnum){
		if(getCharacterEntity().wearItems.get(itemEnum.getValue()) != null)
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
		getCharacterEntity().wearItems.add(item.getItemEnum().getValue(),item);
	}
	
	public Item getItem(ItemEnum itemEnum){
		return (Item)getCharacterEntity().wearItems.get(itemEnum.getValue());
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
		return getCharacterEntity().getLevel();
		
	}
	
	public void setLevel(int level){
		getCharacterEntity().setLevel(level);
	}

	/**
	 * it will iterate the list 
	 * @param list the list need to be iterated.
	 * @param value the value need to be updated.
	 */
	private  void iterate(List<Ability> list,List<Item> list2 , int value ) {
		
		for(int i =0 ; i < list2.size() ; i ++){
			if(list2.get(i) != null)
				list2.get(i).update(value);
		}		
		
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
