package ca.concordia.soen6441.d20.item;

public enum AttributeEnum {
	ARMORCLASS(0),ATTACKBONUS(1),DAMAGEBONUS(2);
	
    private final int value;
    
    private AttributeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
