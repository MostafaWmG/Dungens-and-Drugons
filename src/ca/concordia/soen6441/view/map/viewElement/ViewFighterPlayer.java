package ca.concordia.soen6441.view.map.viewElement;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import ca.concordia.soen6441.d20.fighter.Fighter;

public class ViewFighterPlayer extends ViewObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fighter fighter;
	
	public ViewFighterPlayer(String tag) {
		setImageIcon(new ImageIcon("7.png"));
		setIcon(getImageIcon());
		setFighter(fighter);
		setTag(tag);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getID() == 1001){
			getFighter().addObserver(new ViewCharacteristics(getFighter()));
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
}
