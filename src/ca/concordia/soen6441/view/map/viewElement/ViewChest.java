package ca.concordia.soen6441.view.map.viewElement;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

public class ViewChest extends ViewObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewChest(String tag) {
		setImageIcon(new ImageIcon("6.png"));
		setIcon(getImageIcon());
		setTag(tag);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
