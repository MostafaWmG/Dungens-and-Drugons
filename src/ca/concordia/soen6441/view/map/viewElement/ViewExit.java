package ca.concordia.soen6441.view.map.viewElement;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

public class ViewExit extends ViewObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewExit(String tag) {
		setImageIcon(new ImageIcon("5.png"));
		setIcon(getImageIcon());
		setTag(tag);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
