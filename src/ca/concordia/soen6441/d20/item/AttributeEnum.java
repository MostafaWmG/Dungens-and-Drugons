package ca.concordia.soen6441.d20.item;
/**
 * 
 * This class represents three different type of bonuses that character can gain.
 *
 */

public enum AttributeEnum {
	ARMORCLASS(0),ATTACKBONUS(1),DAMAGEBONUS(2);
	
    private final int value;
    
    /**
     * 
     * @param value to set for specified attribute.
     */
    private AttributeEnum(int value) {
        this.value = value;
    }

    /**
     * 
     * @return of the character's bonus
     */
    public int getValue() {
        return value;
    }
}
