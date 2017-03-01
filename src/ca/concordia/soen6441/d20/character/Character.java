package ca.concordia.soen6441.d20.character;
import java.util.ArrayList;
import java.util.List;

import ca.concordia.soen6441.d20.common.Location;
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
import ca.concordia.soen6441.d20.item.ItemEntity;
import ca.concordia.soen6441.d20.item.ItemEnum;

/**
 * this class represents a character, a monster or a player
 * @author alvaro
 * @author mostafa
 */
// ArmorClass,HitPoint,DamageBonus,AttackBonus need to re factor and all of them can have same interface or inherits from attribute
public class Character extends GameObject {
	private static final int BACKPACK_SIZE = 10;

	private static final int BACK_PACK_FULL = -1;

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
	
	protected List<Item> backPack;
	protected List <Ability> abilities;
	protected List <Item> wearItems;
	protected ArmorClass armorClass;
	protected AttackBonus attackBonus;
	protected DamageBonus damageBonus;
	protected HitPoint hitPoint;
	protected Dice dice;
	
	public Character(String tag,String name,int initialPosistionX, int initialPositionY) {
		init();
		setName(name);
		setTag(tag);
		setLocation(new Location(initialPosistionX,initialPositionY));
		setBackPack(new ArrayList<Item>());
		initializeBackPack();
		setWearItems(new ArrayList<Item>());
		setAbilities(new ArrayList<Ability>());
		setCharacterAbility();
		setArmor(new ArmorClass(getAbilities() .get(AbilityEnum.DEXTERITY.getValue()).getModifier()));
		setDamage(new DamageBonus(getAbilities().get(AbilityEnum.STRENGTH.getValue()).getModifier()));
		setAttack(new AttackBonus(getLevel())); 
		setHitPoint(new HitPoint(getAbilities().get(AbilityEnum.CONSTITUTION.getValue()).getModifier(),getLevel()));
		setAbilitiesListener();
		showAttributes();
//		emptyWearList();
		testWearItems();

	}
		
	public Character(String tag,String name) {
		init();
		setName(name);
		setTag(tag);
		setBackPack(new ArrayList<Item>());
		initializeBackPack();
		setWearItems(new ArrayList<Item>());
		setAbilities(new ArrayList<Ability>());
		setCharacterAbility();
		setArmor(new ArmorClass(getAbilities() .get(AbilityEnum.DEXTERITY.getValue()).getModifier()));
		setDamage(new DamageBonus(getAbilities().get(AbilityEnum.STRENGTH.getValue()).getModifier()));
		setAttack(new AttackBonus(getLevel())); 
		setHitPoint(new HitPoint(getAbilities().get(AbilityEnum.CONSTITUTION.getValue()).getModifier(),getLevel()));
		setAbilitiesListener();	
		showAttributes();
//		emptyWearList();
		testWearItems();
	}
	
	/**
	 * We usually use this constructor to load data
	 * @param entity
	 */
	public Character(CharacterEntity entity)
	{
		setCharacterEntity(entity);
		//TODO support backpack loading
		for (ItemEntity item : entity.getWearItems())
			addItem(item.createItemModel());
		//TODO
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
			getField().move(getLocation().getX(), getLocation().getY(), getLocation().getX()+dx, getLocation().getY()+dy);
			getCharacterEntity().getLocation().setX(getLocation().getX()+dx);
			getCharacterEntity().getLocation().setY(getLocation().getY()+dy);
		
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
		if(getItem(itemEnum).getAttributeType() == null && getItem(itemEnum).getEnchantmentType() == null  )
			return false;
		else
			return true;
	}
	
	/**
	 * order to character to wear this item.
	 * @param item the item that is going to be wear.
	 * @return shows if character already filed this slot with any item or not.
	 */
	public boolean putOnItem(Item item){
		
		if(hasItem(item.getItemEnum())){
			 System.out.println("Character already has this item");
			 return true;
		}else {
			//TODO
			getWearItems().set(item.getItemEnum().getValue(), item);
			wearItem(item, item.getEnchantmentPoint());
//			System.out.println("characted wore the item");
			return false;
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
//			System.out.println("debug1: "+ item.getEnchantmentType() + " value: " + value);
			getAbilities().get(item.getEnchantmentType().getValue()).update(value);
		}else if (item.getEnchantmentType() == null){
//			System.out.println("debug1: "+ item.getAttributeType() + " value: " + value);
			if(item.getAttributeType() == AttributeEnum.ARMORCLASS){
				getArmor().setBase(getArmor().getBase() + value);
			}else if (item.getAttributeType() == AttributeEnum.ATTACKBONUS){
				getAttack().setBase(getAttack().getBase() + value);
			}else if (item.getAttributeType() == AttributeEnum.DAMAGEBONUS){
				getDamage().setBase(getDamage().getBase() + value);
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
			//TODO the line of code is not easy to persist
			getWearItems().set(item.getItemEnum().getValue(),new Item(item.getItemEnum().getValue()+"", item.getItemEnum()));
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
			
			System.out.println("character ability : " + AbilityEnum.values()[i] + " ,Score :  " + roll + " ,modifier : " + (int)Math.floor( (roll - 10) /2 ));
			// To determine an ability modifier without consulting the table, subtract 10 from the ability score and then divide the result by 2 (round down).
			addAbility(new Ability(AbilityEnum.values()[i],roll,(int)Math.floor( (roll - 10) /2 )) );
		}
		
		for(int i = 0 ; i < ItemEnum.values().length; i ++){
			addItem(new Item(i+"",ItemEnum.values()[i]));
		}
		
	}
	
	/**
	 * this method sets the listeners of abilities.
	 * so when we modify any of these abilities it will automatically changes other statistics related to it. 
	 */
	public void setAbilitiesListener(){
		getAbilities().get(AbilityEnum.DEXTERITY.getValue()).addListener(getArmor());
		getAbilities().get(AbilityEnum.CONSTITUTION.getValue()).addListener(getHitPoint());
		getAbilities().get(AbilityEnum.STRENGTH.getValue()).addListener(getDamage());
	}
	
	/**
	 * this method will level up our character 
	 * @param point the amount of level that our character gain in level up action
	 */
	public void levelUp(int point){
		getCharacterEntity().setLevel(getCharacterEntity().getLevel() + 1);
		getAttack().update(getCharacterEntity().getLevel());
		getHitPoint().setLevel(getCharacterEntity().getLevel());
		iterate(getAbilities(),getWearItems(), point);
	}
	
	/**
	 * print all statistics of the characters
	 */
	public void showAbilities(){
		
		for (int i= 0 ; i < AbilityEnum.values().length ; i++ ){			
			System.out.println("character ability : " + AbilityEnum.values()[i] + " ,Score :  " + getAbilities().get(AbilityEnum.values()[i].getValue()).getScore() + " ,modifier : " + getAbilities().get(AbilityEnum.values()[i].getValue()).getModifier() );
		}
	}
	
	/**
	 * print all attributes of character
	 */
	public void showAttributes(){
		getArmor().showPoint();
		getDamage().showPoint();
		getAttack().showPoint();
		getHitPoint().showPoint();
	}
	
	/**
	 * show both attributes and abilities
	 */
	public void show(){
		showAbilities();
		showAttributes();
	}
	
	/**
	 * show all :attributes and abilities and items
	 */
	public void showAll(){
		showAbilities();
		showAttributes();
		showItems();
	}
	
	/**
	 * getWearItems().get(i).show();
	 * show all items of the character
	 */
	public void showItems(){
		for(int i = 0 ; i < getWearItems().size() ; i ++){
			if(getWearItems().get(i).getEnchantmentType() ==null && getWearItems().get(i).getAttributeType() ==null){
				System.out.println(ItemEnum.values()[i] + " empty slot");
			}else {
				getWearItems().get(i).show();
			}
		}
	}
	
	/**
	 * show items in the backpack
	 */
	public void showBackPack(){
		for(int i = 0 ; i < BACKPACK_SIZE; i ++){
			if(getBackPack().get(i).getAttributeType() == null && getBackPack().get(i).getEnchantmentType() == null){
				System.out.println("Slot " + i + " : "+ "Empty");	
			}else{
				System.out.print("Slot " + i+" ");	
				getBackPack().get(i).show();
			}
			
		}
	}
	
	/**
	 * show items in your backpack and show your worn items
	 */
	public void showInvetory(){
		showItems();
		showBackPack();
	}
	
	/**
	 * add ability to the character
	 * @param ability the ability we are adding .
	 */
	public void addAbility(Ability ability) {
		abilities.add(ability.getAbilityEnum().getValue(), ability);
		getCharacterEntity().getAbilities().add(ability.getAbilityEnum().getValue(), ability.getAbilityEntity());
	}
	
	public Ability getAbility(AbilityEnum abilityEnum){
		return (Ability) abilities.get(abilityEnum.getValue());
	}
	
	public List<Ability> getAbilities(){
		return abilities;
	}
	
	public void setAbilities(ArrayList<Ability> abilities){
		this.abilities = abilities;
		getCharacterEntity().getAbilities().clear();
		for (Ability ability : abilities)
			getCharacterEntity().getAbilities().add(ability.getAbilityEntity());
	}
	/**
	 * character will wear this item.
	 * @param item which is going to be wear.
	 */
	public void addItem(Item item) {
		if(item.getEnchantmentType() == null && item.getAttributeType() == null){
			Item newItem = new Item(item.getName(),item.getItemEnum());
			getWearItems().add(newItem);
			getCharacterEntity().getWearItems().add(newItem.getItemEntity());
		}else{
			getWearItems().add(item.getItemEnum().getValue(),item);
			getCharacterEntity().getWearItems().add(item.getItemEnum().getValue(),item.getItemEntity());
		}

	}
	
	public Item getItem(ItemEnum itemEnum){
		return (Item)getWearItems().get(itemEnum.getValue());
	}
	
	public List<Item> getWearItems(){
		return wearItems;
	}
	
	public void setWearItems(ArrayList<Item> wearItems){
		this.wearItems = wearItems;
		getCharacterEntity().getWearItems().clear();
		for (Item item : wearItems)
			getCharacterEntity().getWearItems().add(item.getItemEntity());
	}
	
	public void setBackPack(ArrayList<Item> backPack){
		this.backPack = backPack;
	}
	
	public List<Item> getBackPack(){
		return backPack;
	}
	
	/**
	 * this method initialize backpack
	 */
	public void initializeBackPack(){
		for(int i = 0; i < BACKPACK_SIZE ; i ++){
			getBackPack().add(new Item(i+7+"", ItemEnum.HELMET));
		}
	}
	
	/**
	 * find first empty slot in backPack <increasingly>
	 * @return empty slot
	 */
	public int findEmptyPositionInBackPack(){
		for (int i = 0 ; i < BACKPACK_SIZE; i ++ ){
			if(getBackPack().get(i).getAttributeType() == null && getBackPack().get(i).getEnchantmentType() == null){
			}else{
				return i ;
			}
		}
		// back pack full
		return BACK_PACK_FULL;
	}
	
	/**
	 * add item to backpack
	 * @param item item 
	 * @return if backpack is full or not
	 */
	public boolean addBackPack(Item item){
		if(findEmptyPositionInBackPack() == -1){
			System.out.println("Back Pack Is Full");
			return false;
		}else {
			int index = findEmptyPositionInBackPack();
			getBackPack().add(index, item);
			return true;
		}
	}
	
	/**
	 * remove item from backPack at index
	 * @param index selected item at the index to be removed.
	 */
	public void removeBackPack(int index){
		getBackPack().add(index,new Item(index+7+"", ItemEnum.HELMET));
	}
	
	/**
	 * remove one item from wearList and put it into backpack if it is not full
	 * @param itemWear the item selected from wearList
	 */
	public void putWearItemIntoBackPack(Item itemWear){
		if(findEmptyPositionInBackPack() == -1){
			System.out.println("Back Pack Is Full");
			return;
		}

		Item newItem = new Item(itemWear); 
		removeItem(itemWear);
		addBackPack(newItem);
	}
	
	/**
	 * switch one item from WearList to BackPack if character doesn't wore the same model of item already
	 * @param itemWear the item selected from wearList
	 * @param itemBp the item selected from backPack
	 */
	public void switchWearItemWithBackPack(Item itemWear,Item itemBp){
		if(itemWear.getItemEnum() != itemBp.getItemEnum()){
			if(hasItem(itemBp.getItemEnum())){
				System.out.println("Character can't wore the same model of item");
				return;
			}
		}
		Item newItemWear = new Item(itemWear);
		removeItem(itemWear);
		putOnItem(itemBp);
		getBackPack().remove(itemBp);
		ArrayList<Item> backPack = (ArrayList<Item>) getBackPack();
		backPack.trimToSize();
		getBackPack().add(newItemWear);
	}
	
	/**
	 * move item from backPack into wearList if character does not have the same model already.
	 * @param itemBp selected item from backPack
	 */
	public void putBackPackIntoWearList(Item itemBp){
		if(hasItem(itemBp.getItemEnum())){
			System.out.println("Character can't wore the same model of item");
			return;
		}
		putOnItem(itemBp);
	}

	public ArmorClass getArmor(){
		return armorClass;
	}
	
	public void setArmor(ArmorClass armorClass){
		this.armorClass = armorClass;
		getCharacterEntity().setArmorClass(armorClass.getArmorClassEntity());
	}
	
	public AttackBonus getAttack(){
		return attackBonus;
	}
	
	public void setAttack(AttackBonus attackBonus){
		this.attackBonus = attackBonus;
		getCharacterEntity().setAttackBonus(attackBonus.getAttackBonusEntity());
	}
	
	public HitPoint getHitPoint(){
		return hitPoint;
	}
	
	public void setHitPoint(HitPoint hitPoint){
		this.hitPoint = hitPoint;
		getCharacterEntity().setHitPoint(hitPoint.getHitPointEntity());
	}
	
	public DamageBonus getDamage(){
		return damageBonus;
	}
	
	public void setDamage(DamageBonus damageBonus){
		this.damageBonus = damageBonus;
		getCharacterEntity().setDamageBonus(damageBonus.getDamageBonusEntity());
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
	
	/**
	 * empty the wearItems list
	 */
	private void emptyWearList(){
		for(int i = 0; i < getWearItems().size() ; i ++){
			getWearItems().set(i, new Item(i+"",ItemEnum.values()[i]));
		}
	}
	
	private void testWearItems(){
		//for test only
		for(int i = 0 ; i < ItemEnum.values().length; i ++){
			if(i == ItemEnum.values().length -1){
			putOnItem(new Item("test"+i,ItemEnum.values()[i], AttributeEnum.ARMORCLASS, i));
//			getWearItems().set(i,new Item(ItemEnum.values()[i], AttributeEnum.ARMORCLASS, i));
			}else {
				putOnItem(new Item("test"+i,ItemEnum.values()[i], AbilityEnum.values()[i], i));
//				getWearItems().set(i,new Item(ItemEnum.values()[i],AbilityEnum.values()[i], i));				
			}
		}
		showAbilities();
	}
	
	@Override
	public GameObjectEntity getEntity() {
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
