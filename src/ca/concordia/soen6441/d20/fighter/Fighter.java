package ca.concordia.soen6441.d20.fighter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.concordia.soen6441.d20.ability.Ability;
import ca.concordia.soen6441.d20.ability.AbilityEntity;
import ca.concordia.soen6441.d20.ability.AbilityEnum;
import ca.concordia.soen6441.d20.attribute.ArmorClass;
import ca.concordia.soen6441.d20.attribute.AttackBonus;
import ca.concordia.soen6441.d20.attribute.AttributeEnum;
import ca.concordia.soen6441.d20.attribute.DamageBonus;
import ca.concordia.soen6441.d20.attribute.HitPoint;
import ca.concordia.soen6441.d20.dice.Dice;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEntity;
import ca.concordia.soen6441.d20.item.ItemEnum;

/**
 * This class is used to create a character, a monster or a player.
 * @author alvaro
 * @author mostafa
 */
// ArmorClass,HitPoint,DamageBonus,AttackBonus need to re factor and all of them can have same interface or inherits from attribute
public class Fighter extends GameObject {
	private static final int BACKPACK_SIZE = 10;

	private static final int BACK_PACK_FULL = -1;

	/**
	*  Each character object(character, player or monster) has below characteristics:
	*level : level of the character
	* name : name of the character
	* characterEntity: it is used for saving and loading characters
	* wearItems : items that character already wear
	* armorClass: the armor of the character
	* attackBonus : the attack of the character
	* damageBonus : the damage of the character
	* hitPoint    : the hitPoint of the character
	* dice  : the dice class for rolling a dice
	 */	
	private FighterEntity characterEntity;
	
	protected List<Item> backPack;
	protected List <Ability> abilities;
	protected List <Item> wearItems;
	protected ArmorClass armorClass;
	protected AttackBonus attackBonus;
	protected DamageBonus damageBonus;
	protected HitPoint hitPoint;
	protected Dice dice;
	
	public Fighter(){
		init();
	}
	
	/**
	 * This constructor creates a character object with given tag,name.
	 * @param tag sets tag for the character
	 * @param name sets the name of the character
	 */
	public Fighter(String tag,String name) {
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
		setAttack(new AttackBonus(getAbilities() .get(AbilityEnum.DEXTERITY.getValue()).getModifier(),getLevel())); 
		setHitPoint(new HitPoint(getAbilities().get(AbilityEnum.CONSTITUTION.getValue()).getModifier(),getLevel()));
		setAbilitiesListener();	
		showAbilities();
		showAttributes();
		emptyWearList();
	}
	
	/**
	 * We usually use this constructor to load data
	 * @param entity
	 */
	public Fighter(FighterEntity entity)
	{
		setCharacterEntity(entity);
		backPack = new ArrayList<>();
		wearItems = new ArrayList<>();
		abilities = new ArrayList<>();
		for (ItemEntity item : entity.getWearItems())
			addItem(item.createItemModel(), false);
		for (AbilityEntity ability : entity.getAbilities())
			addAbility(ability.createAbility(), false);
		for (ItemEntity item : entity.getBackpack())
			addBackPack(item.createItemModel(), false);
		setArmor(entity.getArmorClass().createArmorModel());
		setAttack(entity.getAttackBonus().createAttackBonus());
		setDamage(entity.getDamageBonus().createDamageBonus());
		setHitPoint(entity.getHitPoint().createHitPoint());
		setAbilitiesListener();
		showAll();
		showBackPack();
	}
	
	/**
	 * This method sets entity for each object created
	 */
	private void init(){
		
		setCharacterEntity(new FighterEntity());
	}
		
	public void attack(Fighter enemy) {
		
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
			getWearItems().set(item.getItemEnum().getValue(), item);
			getCharacterEntity().getWearItems().set(item.getItemEnum().getValue(), item.getItemEntity());
			wearItem(item, item.getEnchantmentPoint());
//			System.out.println("characted wore the item");
			setChanged();
			notifyObservers(this);
			return false;
		}
	}
	
	// need to  re factor
	//TODO Is there any part of this function related to persistence?
	/**
	 * this method updates the character statistics that character gains by wearing or removing the item
	 * @param item the item that character wears
	 * @param value the item value that affect the character
	 */
	public void wearItem(Item item , int value){
		if (item.getAttributeType() == null ){
			System.out.println("debug1: "+ item.getEnchantmentType() + " value: " + value);
			if(item.getEnchantmentType() != null)
				getAbilities().get(item.getEnchantmentType().getValue()).update(value);
		}else if (item.getEnchantmentType() == null){
			System.out.println("debug1: "+ item.getAttributeType() + " value: " + value);
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
			Item tmp = new Item(getName()+item.getItemEnum().getValue()+"dontduplicate", item.getItemEnum());
			getWearItems().set(item.getItemEnum().getValue(), tmp);		
			System.out.println("item name :" + tmp.getName());
			getCharacterEntity().getWearItems().set(item.getItemEnum().getValue(), tmp.getItemEntity());
			wearItem(item, -1 * item.getEnchantmentPoint());
			System.out.println("item Removed");
			setChanged();
			notifyObservers(this);
		}else {
			 System.out.println("Character dont have this item");
		}
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * randomly generating numbers using 4dice6 and d20 rules
	 */
	public List<Integer> rollDice(){
		int roll = 0;
		int[] rollTemp = new int[2];
		rollTemp[0] = 0 ;
		rollTemp[1] = 0 ;
 		dice  = new Dice();
 		List<Integer> numbers = new ArrayList<>();

		for (int i= 0 ; i < AbilityEnum.values().length ; i++ ){
			roll = 0 ;
			rollTemp[0] = dice.roll6();
			rollTemp[1] = dice.roll6();
			roll = dice.roll6() + dice.roll6() + rollTemp[dice.getRandom().nextInt(2)];
			numbers.add(roll);
		}
 		Collections.sort(numbers);
 		return numbers;
	}
		
	/**
	 * randomly determining the character abilities using dice6 and d20 rules
	 */
	public void setCharacterAbility(){
		
		int roll = 0;
		dice  = new Dice();

		for (int i= 0 ; i < AbilityEnum.values().length ; i++ ){
			
			roll = 0 ;
			roll = dice.roll6() + dice.roll6() + dice.roll6() ;
			
//			System.out.println("character ability : " + AbilityEnum.values()[i] + " ,Score :  " + roll + " ,modifier : " + (int)Math.floor( (roll - 10) /2 ));
			// To determine an ability modifier without consulting the table, subtract 10 from the ability score and then divide the result by 2 (round down).
			addAbility(new Ability(AbilityEnum.values()[i],roll,(int)Math.floor( (roll - 10) /2 )) );
		}
		
		for(int i = 0 ; i < ItemEnum.values().length; i ++){
			addItem(new Item(getName()+i+"dontduplicate",ItemEnum.values()[i]));
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
		getAbilities().get(AbilityEnum.DEXTERITY.getValue()).addListener(getAttack());
	}
	
	/**
	 * this method will level up our character 
	 * @param point the amount of level that our character gain in level up action
	 */
	public void levelUp(int point){
		getCharacterEntity().setLevel(getLevel() + point);
		getAttack().setLevel(getLevel());
		getHitPoint().setLevel(getLevel());
		iterate(getAbilities(),getWearItems(), point);
	}
	
	/**
	 * print all statistics of the characters
	 */
	public String showAbilities(){
		 return showAbilities(true);
	}
	
	/**
	 * print all statistics of the characters
	 */
	public String showAbilities(boolean consolEn){
		if(consolEn);
			System.out.println("<<<ABILITIES>>>");
		String s = "<<<ABILITIES>>>"+ "\n";
		for (int i= 0 ; i < AbilityEnum.values().length ; i++ ){
			if(consolEn)
				System.out.println("character ability : " + AbilityEnum.values()[i] + " ,Score :  " + getAbilities().get(AbilityEnum.values()[i].getValue()).getScore() + " ,modifier : " + getAbilities().get(AbilityEnum.values()[i].getValue()).getModifier() );
			s = s.concat("character ability : " + AbilityEnum.values()[i] + " ,Score :  " + getAbilities().get(AbilityEnum.values()[i].getValue()).getScore() + " ,modifier : " + getAbilities().get(AbilityEnum.values()[i].getValue()).getModifier() +"\n");
		}
		return s;
	}
	
	/**
	 * print all attributes of character
	 */
	public String showAttributes(){
		return showAttributes(true);
	}
	
	/**
	 * print all attributes of character
	 */
	public String showAttributes(boolean consol){
		String s ="<<<ATTRIBUITES>>>" + "\n";
		
		if(consol){
			System.out.println("<<<ATTRIBUITES>>>");
			getArmor().showPoint();
			getDamage().showPoint();
			getAttack().showPoint();
			getHitPoint().showPoint();
		}else{
			s = s.concat(getArmor().showPoint(consol) );
			s = s.concat( getDamage().showPoint(consol));
			s = s.concat(getAttack().showPoint(consol));
			s = s.concat(getHitPoint().showPoint(consol));
		}
		return s;
	}
	
	/**
	 * show both attributes and abilities
	 */
	public String show(boolean consol){
		String s = getName().toUpperCase() + "\n";
		s = s.concat(showAbilities(consol) + "\n");
		s = s.concat(showAttributes(consol) + "\n");
		return s;
	}
	
	/**
	 * show both attributes and abilities
	 */
	public void show(){
		showAbilities(true);
		showAttributes(true);
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
	 * show all items of the character
	 * getWearItems().get(i).show();
	 */
	public String showItems(){
		return showItems(true);
	}
	
	/**
	 * show all items of the character
	 * getWearItems().get(i).show();
	 */
	public String showItems(boolean consol){
		String s = "<<<ITEMS>>>" + "\n";
		if(consol){
			System.out.println("<<<ITEMS>>>");
			for(int i = 0 ; i < getWearItems().size() ; i ++){
				if(getWearItems().get(i).getEnchantmentType() ==null && getWearItems().get(i).getAttributeType() ==null){
					System.out.println(ItemEnum.values()[i] + " empty slot");
				}else {
					getWearItems().get(i).show(consol);
				}
			}
			
		}else{
			for(int i = 0 ; i < getWearItems().size() ; i ++){
				if(getWearItems().get(i).getEnchantmentType() ==null && getWearItems().get(i).getAttributeType() ==null){
					s = s.concat(ItemEnum.values()[i] + " empty slot"+"\n");
				}else {
					s = s.concat(getWearItems().get(i).show(consol));
				}
			}
		}
		return s;
	}
	
	/**
	 * show items in the backpack
	 */
	public String showBackPack(){
		return showBackPack(true);
	}
	
	/**
	 * show items in the backpack
	 */
	public String showBackPack(boolean consol){
		String s = "";
		if(consol){
			System.out.println("<<<BACK PACK>>> ");
			for(int i = 0 ; i < BACKPACK_SIZE; i ++){
				if(getBackPack().get(i).getAttributeType() == null && getBackPack().get(i).getEnchantmentType() == null){
					System.out.println("Slot " + i + " : "+ "Empty");	
				}else{
					System.out.print("Slot " + i+" ");	
					getBackPack().get(i).show(consol);
				}
				
			}	
		}else{
			s = s.concat("<<<BACK PACK>>> "+ "\n");
			for(int i = 0 ; i < BACKPACK_SIZE; i ++){
				if(getBackPack().get(i).getAttributeType() == null && getBackPack().get(i).getEnchantmentType() == null){
					s = s.concat("Slot " + i + " : "+ "Empty" + "\n");
				}else{
					s = s.concat("Slot " + i+" ");
					s = s.concat(getBackPack().get(i).show(consol));
				}
				
			}
		}
		
		return s;
	}
	
	/**
	 * show items in your backpack and show your worn items
	 */
	public String showInvetory(boolean consol){
		String s = getName().toUpperCase() + "\n";
		if(consol){
			System.out.println("<<<INVENTORY>>>");
			showItems(consol);
			showBackPack(consol);
				
		}else{
			s = s.concat("<<<INVENTORY>>>"+"\n");
			s = s.concat(showItems(consol));
			s = s.concat(showBackPack(consol));
		}
		
		return s;
	}
	
	/**
	 * show items in your backpack and show your worn items
	 */
	public String showInvetory(){
		return showInvetory(true);
	}
	
	/**
	 * add ability to the character
	 * @param ability the ability we are adding .
	 */
	public void addAbility(Ability ability) {
		addAbility(ability, true);

	}
	/**
	 * 
	 * @param ability to add to the character
	 * @param saveEntity true if we want to save the character object to database
	 */
	public void addAbility(Ability ability, boolean saveEntity)
	{
		abilities.add(ability.getAbilityEnum().getValue(), ability);
		if (saveEntity)
			getCharacterEntity().getAbilities().add(ability.getAbilityEnum().getValue(), ability.getAbilityEntity());		
	}
	
	/**
	 * replaceAbility 
	 * @param ability the ability we are adding .
	 */
	public void replaceAbility(Ability ability) {
		replaceAbility(ability, true);

	}
	
	/**
	 * 
	 * @param ability to be replaced 
	 * @param saveEntity true if we want to save the character object to database
	 */
	public void replaceAbility(Ability ability, boolean saveEntity)
	{
		abilities.set(ability.getAbilityEnum().getValue(), ability);
		if (saveEntity)
			getCharacterEntity().getAbilities().set(ability.getAbilityEnum().getValue(), ability.getAbilityEntity());		
	}
	/**
	 * 
	 * @param abilityEnum 
	 * @return
	 */
	public Ability getAbility(AbilityEnum abilityEnum){
		return (Ability) abilities.get(abilityEnum.getValue());
	}
	
	/**
	 * @return a list of abilities that the character has
	 */
	public List<Ability> getAbilities(){
		return abilities;
	}
	
	/**
	 * 
	 * @param abilities sets a list of abilities for the character
	 */
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
		
		addItem(item, true);
	}
	
	/**
	 * 
	 * @param item which is going to be wear.
	 * @param saveEntity true if we want to save this items for character on database
	 */
	private void addItem(Item item, boolean saveEntity)
	{
		if(item.getEnchantmentType() == null && item.getAttributeType() == null){
			Item newItem = new Item(item.getName()+getWearItems().size(),item.getItemEnum());
			getWearItems().add(newItem);
			if (saveEntity)
				getCharacterEntity().getWearItems().add(newItem.getItemEntity());
		}else{
			getWearItems().add(item.getItemEnum().getValue(),item);
			if (saveEntity)
				getCharacterEntity().getWearItems().add(item.getItemEnum().getValue(),item.getItemEntity());
		}
		setChanged();
		notifyObservers(this);
	}
	/**
	 * 
	 * @param itemEnum that is available in ItemEnum 
	 * @return the item matching the itemEnum from character's items.
	 */
	public Item getItem(ItemEnum itemEnum){
		return (Item)getWearItems().get(itemEnum.getValue());
	}
	
	/**
	 * 
	 * @return a list of Items that the character is wearing.
	 */
	public List<Item> getWearItems(){
		return wearItems;
	}
	
	/**
	 * 
	 * @param wearItems sets a lists of items for the character to wear.
	 */
	public void setWearItems(ArrayList<Item> wearItems){
		this.wearItems = wearItems;
		getCharacterEntity().getWearItems().clear();
		for (Item item : wearItems)
			getCharacterEntity().getWearItems().add(item.getItemEntity());
	}
	
	/**
	 * 
	 * @param backPack sets a list of Items for character to put them in his backpack. 
	 */
	public void setBackPack(ArrayList<Item> backPack){
		this.backPack = backPack;
		getCharacterEntity().getBackpack().clear();
		for (Item item : backPack)
			getCharacterEntity().getBackpack().add(item.getItemEntity());
	}
	
	/**
	 * 
	 * @return Items that are in the backpack.
	 */
	public List<Item> getBackPack(){
		return backPack;
	}
	
	/**
	 * this method initialize backpack
	 */
	public void initializeBackPack(){
		for(int i = 0; i < BACKPACK_SIZE ; i ++){
			addBackPack(new Item(getName()+i+7+"dontduplicate", ItemEnum.HELMET));
		}
	}
	
	/**
	 * find first empty slot in backPack <increasingly>
	 * @return empty slot
	 */
	public int findEmptyPositionInBackPack(){
		System.out.println("index: " +getBackPack().size());
		for (int i = 0 ; i < BACKPACK_SIZE; i ++ ){
			if(getBackPack().get(i).getAttributeType() == null && getBackPack().get(i).getEnchantmentType() == null){
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
		 return addBackPack(item, true);
	}
	
	/**
	 * 
	 * @param item to add to backpack
	 * @param saveEntity true if we want the item to be saved for character on database
	 * @return true if the backpack has empty space
	 */
	private boolean addBackPack(Item item, boolean saveEntity)
	{
		if(getBackPack().size() >= BACKPACK_SIZE){
			
			int index = findEmptyPositionInBackPack();
			
			if(index == -1)
				return false;
			
			getBackPack().set(index, item);
			if (saveEntity)
				getCharacterEntity().getBackpack().set(index, item.getItemEntity());
		}else{
			if(item.getEnchantmentType() == null && item.getAttributeType() == null){
				Item newItem = new Item(item.getName(),item.getItemEnum());
				getBackPack().add(newItem);
				if (saveEntity)
					getCharacterEntity().getBackpack().add(item.getItemEntity());
			}else{
				getBackPack().add(item);
				if (saveEntity)
					getCharacterEntity().getBackpack().add(item.getItemEntity());
			}
		}
		setChanged();
		notifyObservers(this);
		return true;
	}
	
	/**
	 * remove item from backPack at index
	 * @param index selected item at the index to be removed.
	 */
	public void removeBackPack(int index){
		getBackPack().add(index,new Item(index+7+"", ItemEnum.HELMET));
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * remove one item from wearList and put it into backpack if it is not full
	 * @param itemWear the item selected from wearList
	 */
	public boolean putWearItemIntoBackPack(Item itemWear){
		
		if(itemWear.getAttributeType() == null && itemWear.getEnchantmentType() == null){
			System.out.println(itemWear.getItemEnum() + "is Empty");
			return false; 
		}
		
		if(findEmptyPositionInBackPack() == -1){
			System.out.println("Back Pack Is Full");
			return false;
		}
		
		addBackPack(itemWear); 
		removeItem(itemWear);
		return true;
	}
	
	/**
	 * switch one item from WearList to BackPack if character doesn't wore the same model of item already
	 * @param itemWear the item selected from wearList
	 * @param itemBp the item selected from backPack
	 */
	public boolean switchWearItemWithBackPack(Item itemWear,Item itemBp,int slot){
		if(itemBp.getAttributeType() == null && itemBp.getEnchantmentType() == null){
			System.out.println("Slot is Empty");
			return false;
		}
		
		if(itemWear.getItemEnum() != itemBp.getItemEnum()){
			if(hasItem(itemBp.getItemEnum())){
				System.out.println("Character can't wore the same model of item");
				return false;
			}
		}

		removeItem(itemWear);
		putOnItem(itemBp);
		getBackPack().remove(itemBp);
		// messy code need to be reFactored!!!
	    getCharacterEntity().getBackpack().set(slot, new Item(itemBp.getName()+slot+7+"dontduplicate", ItemEnum.HELMET).getItemEntity());
		ArrayList<Item> backPack = (ArrayList<Item>) getBackPack();
		backPack.trimToSize();
		addBackPack(itemWear);
		return true;

	}
	
	/**
	 * move item from backPack into wearList if character does not have the same model already.
	 * @param itemBp selected item from backPack
	 */
	public boolean putBackPackIntoWearList(Item itemBp, int slot){
		if(itemBp.getAttributeType() == null && itemBp.getEnchantmentType() == null){
			System.out.println("Slot is Empty");
			return false;
		}
		
		if(hasItem(itemBp.getItemEnum())){
			System.out.println("Character can't wore the same model of item");
			return false;
		}
		putOnItem(itemBp);
		getBackPack().remove(itemBp);
		// messy code need to be reFactored!!!
	    getCharacterEntity().getBackpack().set(slot, new Item(itemBp.getName()+slot+7+"dontduplicate", ItemEnum.HELMET).getItemEntity());
		ArrayList<Item> backPack = (ArrayList<Item>) getBackPack();
		backPack.trimToSize();
		addBackPack(new Item(itemBp.getName()+9+7+"dontduplicate", ItemEnum.HELMET));
		return true;
	}

	/**
	 * 
	 * @return armor class of the character.
	 */
	public ArmorClass getArmor(){
		return armorClass;
	}
	
	/**
	 * 
	 * @param armorClass set the character's armor class.
	 */
	public void setArmor(ArmorClass armorClass){
		this.armorClass = armorClass;
		getCharacterEntity().setArmorClass(armorClass.getArmorClassEntity());
	}
	
	/**
	 * 
	 * @return attackBunos of the character.
	 */
	public AttackBonus getAttack(){
		return attackBonus;
	}
	
	/**
	 * 
	 * @param attackBonus set the attack bonus for the character.
	 */
	public void setAttack(AttackBonus attackBonus){
		this.attackBonus = attackBonus;
		getCharacterEntity().setAttackBonus(attackBonus.getAttackBonusEntity());
	}
	
	/**
	 * 
	 * @return hit point of the character.
	 */
	public HitPoint getHitPoint(){
		return hitPoint;
	}
	
	/**
	 * 
	 * @param hitPoint sets hit point for the character.
	 */
	public void setHitPoint(HitPoint hitPoint){
		this.hitPoint = hitPoint;
		getCharacterEntity().setHitPoint(hitPoint.getHitPointEntity());
	}
	
	/**
	 * 
	 * @return damage bonus for the character.
	 */
	public DamageBonus getDamage(){
		return damageBonus;
	}
	
	/**
	 * 
	 * @param damageBonus set the damage bonus for the character.
	 */
	public void setDamage(DamageBonus damageBonus){
		this.damageBonus = damageBonus;
		getCharacterEntity().setDamageBonus(damageBonus.getDamageBonusEntity());
	}
	
	/**
	 * 
	 * @return character's level.
	 */
	public int getLevel(){
		return getCharacterEntity().getLevel();
		
	}
	
	/**
	 * 
	 * @param level set this level for the character.
	 */
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
				list2.get(i).update(getLevel());
		}		
		
		for(int i =0 ; i < list.size() ; i ++){
			list.get(i).update(value);
		}		
	}
	
	/**
	 * empty the wearItems list
	 */
	public void emptyWearList(){
		for(int i = 0; i < getWearItems().size() ; i ++){
			getWearItems().set(i, new Item(getName()+i+"",ItemEnum.values()[i]));
		}
	}
		
	/**
	 * @return GameObjectEntity for loading character from database
	 */
	@Override
	public GameObjectEntity getEntity() {
		return characterEntity;
	}
	
	/**
	 * @return the character entity 
	 */
	public FighterEntity getCharacterEntity(){
		return characterEntity;
	}
	
	/**
	 * @param characterEntity to set
	 */
	public void setCharacterEntity(FighterEntity characterEntity){
		this.characterEntity = characterEntity;
	}
	
	/**
	 * @param tag to set for character
	 */
	@Override
	public void setTag(String tag)
	{
		getCharacterEntity().setFighterType(tag);
	}
	
	/**
	 * @return tag of the character
	 */
	@Override
	public String getTag()
	{
		return getCharacterEntity().getFighterType();
	}
}
