package ca.concordia.soen6441.d20.factory;

import ca.concordia.soen6441.d20.fighter.Fighter;

/**
 * this class is in charge of creating the fighter using the fighter builder
 *
 */
public class FighterDirector {
	private FighterBuilder fighterBuilder;

	/**
	 * @return the Fighter
	 */
	public Fighter getFighterBuilder() {
		return fighterBuilder.getFighter();
	}

	/**
	 * @param fighterBuilder the fighterBuilder to set
	 */
	public void setFighterBuilder(FighterBuilder fighterBuilder) {
		this.fighterBuilder = fighterBuilder;
	}
	
	public void constructFighter(String tag,String name){
		fighterBuilder.createNewFighter();
		fighterBuilder.create(tag, name);
		fighterBuilder.buildInitialize();
		fighterBuilder.setCharacterAbility();
		fighterBuilder.setCharacterAttribute();
		fighterBuilder.show();
	}
}
