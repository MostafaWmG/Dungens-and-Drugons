package ca.concordia.soen6441.view.map.viewElement;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.view.map.Observers.GameView;
import ca.concordia.soen6441.view.map.Observers.ViewCharacteristics;
import ca.concordia.soen6441.view.map.Observers.ViewInventory;

/**
 * used to generate the enemy view (an icon to put in the map)
 *
 */
public class ViewFighterEnemy extends ViewObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fighter fighter;
	//need re factor
	private GameView gameView;
	private boolean mainCharacter;
	// re factor needed
	public ViewFighterEnemy(String tag,Fighter fighter,GameView gameView,boolean mainCharacter) {
		setMainCharacter(mainCharacter);
		setFighter(fighter);
		setGameView(gameView);
		setTag(tag);
		if(mainCharacter){
			setImageIcon(new ImageIcon("10.png"));
			setIcon(getImageIcon());
		}else{
			setImageIcon(new ImageIcon("2.png"));
			setIcon(getImageIcon());
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getID() == 1001){
			if(gameView.isInventoryEn()){
				getFighter().addObserver(new ViewInventory(getFighter()));
			}else{
				getFighter().addObserver(new ViewCharacteristics(getFighter()));
			}

		}
		
	}
	/**
	 * @return the fighter
	 */
	public Fighter getFighter() {
		return fighter;
	}
	/**
	 * @param fighter the fighter to set
	 */
	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}
	/**
	 * @return the gameView
	 */
	public GameView getGameView() {
		return gameView;
	}
	/**
	 * @param gameView the gameView to set
	 */
	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}
	/**
	 * @return the mainCharacter
	 */
	public boolean isMainCharacter() {
		return mainCharacter;
	}
	/**
	 * @param mainCharacter the mainCharacter to set
	 */
	public void setMainCharacter(boolean mainCharacter) {
		this.mainCharacter = mainCharacter;
	}
}
