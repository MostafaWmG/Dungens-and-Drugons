package ca.concordia.soen6441.view.map.viewElement;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import ca.concordia.soen6441.d20.item.Chest;

public class ViewChest extends ViewObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Chest chest;
	
	public ViewChest(String tag,Chest chest) {
		setImageIcon(new ImageIcon("6.png"));
		setIcon(getImageIcon());
		setChest(chest);
		setTag(tag);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the chest
	 */
	public Chest getChest() {
		return chest;
	}

	/**
	 * @param chest the chest to set
	 */
	public void setChest(Chest chest) {
		this.chest = chest;
	}
}
