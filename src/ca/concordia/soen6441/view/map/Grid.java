package ca.concordia.soen6441.view.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * this a Grid class for elements in mapView
 * @author wmg
 *
 */
@SuppressWarnings("serial")
public class Grid extends JButton implements ActionListener{
	
	private int x;
	private int y;
	private ImageIcon icon;
	private MapEditor mapEditor;
	private String tag;
	
	public Grid(MapEditor mapEditor,int x, int y,String tag)
	{
		this.x = x;
		this.y = y;
		this.mapEditor = mapEditor;
		this.setTag(tag);
		icon = new ImageIcon("3.png");
		setIcon(icon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getID() == 1001){
			setIcon(mapEditor.getCurrentPointer());
			this.setTag(mapEditor.getTag());
		}
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
