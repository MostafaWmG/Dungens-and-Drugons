package ca.concordia.soen6441.d20.item;

/**
 * 
 * This class represents all kinds of items that each character can wear or have in its backpack.
 *
 */

public enum ItemEnum {
	HELMET(0) ,ARMOR(1),SHIELD(2),RING(3),BELT(4),BOOTS(5),WEAPON(6);
	
    private final int value;
    
    /**
     * 
     * @param value to set for the Item
     */
    private ItemEnum(int value) {
        this.value = value;
    }

    /**
     * 
     * @return item value
     */
    public int getValue() {
        return value;
    }
}
