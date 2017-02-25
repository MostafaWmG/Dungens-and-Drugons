package ca.concordia.soen6441.d20.item;

public enum ItemEnum {
	HELMET(0) ,ARMOR(1),SHIELD(2),RING(3),BELT(4),BOOTS(5),WEAPON(6);
	
    private final int value;
    
    private ItemEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
