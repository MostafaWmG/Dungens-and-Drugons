package ca.concordia.soen6441.d20.item;

public enum AbilityEnum {
	INTELLIGENCE(0),STRENGTH(1),CONSTITUTION(2),WISDOM(3),CHARISMA(4),DEXTERITY(5);
	
    private final int value;
    
    private AbilityEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
