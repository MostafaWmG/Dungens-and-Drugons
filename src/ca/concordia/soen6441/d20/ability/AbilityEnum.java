package ca.concordia.soen6441.d20.ability;
/**
 * This class represents all the type of abilities that character of the game can gain.
 * @author Mostafa
 *
 */

public enum AbilityEnum {
	INTELLIGENCE(0),STRENGTH(1),CONSTITUTION(2),WISDOM(3),CHARISMA(4),DEXTERITY(5);
	/**
	 * value stored in this data member will be assigned as character's ability.
	 */
    private final int value;
    
    /**
     * 
     * @param value to set as the ability of the character
     */
    private AbilityEnum(int value) {
        this.value = value;
    }

    /**
     * 
     * @return value of the ability
     */
    public int getValue() {
        return value;
    }
}
