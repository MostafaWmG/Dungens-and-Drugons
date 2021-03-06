package ca.concordia.soen6441.d20.item;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;

/**
 * this class implements the chest which is a container for items that character will wear or collects.
 * @author wmg
 *
 */
public class Chest extends GameObject implements ILevelUp,IRoot{
	
	private static final int CHEST_ITEM_MAX_SIZE = 10;

	private static final int CHEST_IS_FULL = -1;

	private ChestEntity chestEntity;
	
	protected List<Item> chestItems;
	
	public Chest(String name) {
		init();
		setName(name);
		setChestItems(new ArrayList<Item>());
		initializeChest();
	}
	
	public void init(){
		setChestEntity(new ChestEntity());
		setTag("Chest");
	}
	
	/**
	 * We usually use this constructor to load data
	 * @param chestEntity entity we are using to load data
	 */
	public Chest(ChestEntity chestEntity){
		setChestEntity(chestEntity);
		setTag("Chest");
		chestItems = new ArrayList<Item>();
		for (ItemEntity item : chestEntity.getChestItems())
			addItem(item.createItemModel(), false);
	}
	
	@Override
	public GameObjectEntity getEntity() {
		return chestEntity;
	}
	/**
	 * @return the chestItems
	 */
	public List<Item> getChestItems() {
		return chestItems;
	}
	/**
	 * @param chestItems the chestItems to set
	 */
	public void setChestItems(List<Item> chestItems) {
		this.chestItems = chestItems;
		getChestEntity().getChestItems().clear();
		for (Item item : chestItems)
			getChestEntity().getChestItems().add(item.getItemEntity());
	}

	/**
	 * @return the chestEntity
	 */
	public ChestEntity getChestEntity() {
		return chestEntity;
	}

	/**
	 * @param chestEntity the chestEntity to set
	 */
	public void setChestEntity(ChestEntity chestEntity) {
		this.chestEntity = chestEntity;
	}

	@Override
	public void update(int modifier) {
		for(int i =0 ; i < CHEST_ITEM_MAX_SIZE ; i++){
			getChestItems().get(0).update(modifier);
		}
		
	}
	

	public void removeFromChest(Item item,int index){
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		Item itemTmp = new Item(item.getName()+df.format(dateobj)+dateobj+17+"Chest", ItemEnum.HELMET);
		getChestEntity().getChestItems().set(index,itemTmp.getItemEntity());
		getChestItems().set(index,itemTmp);
	    
	}
	
	/**
	 * chest will add this item.
	 * @param item which is going to be wear.
	 */
	public boolean addItem(Item item) {
		
		return addItem(item, true);
	}
	
	/**
	 * 
	 * @param item which is going to be add.
	 * @param saveEntity true if we want to save this items for chest on database
	 */
	private boolean addItem(Item item, boolean saveEntity)
	{
		if(getChestItems().size() >= CHEST_ITEM_MAX_SIZE){
			
			int index = findEmptyPositionInChest();
			
			if(index == -1)
				return false;
			
			getChestItems().set(index, item);
			if (saveEntity)
				getChestEntity().getChestItems().set(index, item.getItemEntity());
		}else{
			if(item.getEnchantmentType() == null && item.getAttributeType() == null){
				Item newItem = new Item(item.getName(),item.getItemEnum());
				getChestItems().add(newItem);
				if (saveEntity)
					getChestEntity().getChestItems().add(item.getItemEntity());
			}else{
				getChestItems().add(item);
				if (saveEntity)
					getChestEntity().getChestItems().add(item.getItemEntity());
			}
		}
		return true;
	}
	
	/**
	 * find first empty slot in chest <increasingly>
	 * @return empty slot
	 */
	public int findEmptyPositionInChest(){
//		System.out.println("indexChest: " +getChestItems().size());
		for (int i = 0 ; i < CHEST_ITEM_MAX_SIZE; i ++ ){
			if(getChestItems().get(i).getAttributeType() == null && getChestItems().get(i).getEnchantmentType() == null){
				return i ;
			}
		}
		// back pack full
		return CHEST_IS_FULL;
	}
	
	/**
	 * this method initialize backpack
	 */
	public void initializeChest(){
		for(int i = 0; i < CHEST_ITEM_MAX_SIZE ; i ++){
			addItem(new Item(getName()+i+17+"Chest", ItemEnum.HELMET));
		}
	}
	
	/**
	 * show items in the Chest
	 */
	public void show(){
		System.out.println("<<<Chest View>>> ");
		for(int i = 0 ; i < CHEST_ITEM_MAX_SIZE; i ++){
			if(getChestItems().get(i).getAttributeType() == null && getChestItems().get(i).getEnchantmentType() == null){
				System.out.println("Slot " + i + " : "+ "Empty");	
			}else{
				System.out.print("Slot " + i+" ");	
				getChestItems().get(i).show();
			}
			
		}
	}

	@Override
	public void putRootedIntoBackPack(Fighter fighter){
		for(int i = 0 ; i < CHEST_ITEM_MAX_SIZE ; i ++){
			Item refItem = getChestItems().get(i);
			if(!isNullItem(refItem)){
				if(fighter.addBackPack(refItem)){
					removeFromChest(getChestItems().get(i),i);
				}
			}	
		}
	}
	/**
	 * This method checks that the specified item exist in chest or not.
	 * @param item item to check
	 * @return true if the item is not in the chest
	 */
	public boolean isNullItem(Item item){
		if(item.getAttributeType() == null && item.getEnchantmentType() == null){
			return true;
		}else{
		return false;
		}
	}
}
