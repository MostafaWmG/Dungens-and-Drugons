package ca.concordia.soen6441.view.map.viewElement;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public abstract class ViewObject extends JButton implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tag;
	
	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
}
