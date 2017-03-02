package ca.concordia.soen6441.view.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.character.Character;

/**
 * this a Grid class for elements in mapView
 * @author wmg
 *
 */
@SuppressWarnings("serial")
public class Grid extends JButton implements ActionListener{
	
	private ImageIcon icon;
	private MapEditor mapEditor;
	private String tag;
	private Character character;
	private Item item;
	
	public Grid(MapEditor mapEditor,String tag)
	{
		this.mapEditor = mapEditor;
		this.setTag(tag);
		setCharacter(null);
		setItem(null);
		iconHandler();
	}
	
	public Grid(Character character,MapEditor mapEditor,String tag) {
		this.mapEditor = mapEditor;
		this.setTag(tag);
		setCharacter(character);
		this.setItem(null);
		iconHandler();
	}
	
	public Grid(Item character,MapEditor mapEditor,String tag) {
		this.mapEditor = mapEditor;
		this.setTag(tag);
		setItem(item);
		setCharacter(null);
		iconHandler();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getID() == 1001){

			setIcon(mapEditor.getCurrentPointer());
			this.setTag(mapEditor.getTag());
			if(mapEditor.getTag().equals("Player")){
				setCharacter(mapEditor.getCharacter());
				mapEditor.iconButtons[6].setVisible(false);
				mapEditor.setCurrentPointer(mapEditor.images[2]);
				mapEditor.setTag("Ground");
			}
			if(mapEditor.getTag().equals("Item")){
				setItem(mapEditor.getItem());
				mapEditor.iconButtons[5].setVisible(false);
				mapEditor.setCurrentPointer(mapEditor.images[2]);
				mapEditor.setTag("Ground");
			}
		}
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public void setIcon(ImageIcon icon){
		this.icon = icon;
	}
	
	public ImageIcon getIcon(){
		return icon;
	}
	
	/**
	 * this a method for showing icons by tag
	 */
	public void iconHandler(){
		if(tag == "Ground"){
			icon = new ImageIcon("3.png");
			setIcon(icon);
		}else if (tag == "Wall"){
			icon = new ImageIcon("1.png");
			setIcon(icon);
		}else if (tag == "Enemy"){
			icon = new ImageIcon("2.png");
			setIcon(icon);
		}else if (tag == "Enter"){
			icon = new ImageIcon("4.png");
			setIcon(icon);
		}else if (tag == "Exit"){
			icon = new ImageIcon("5.png");
			setIcon(icon);
		}else if (tag == "Item"){
			icon = new ImageIcon("6.png");
			setIcon(icon);
		}else if (tag == "Player"){
			icon = new ImageIcon("7.png");
			setIcon(icon);
		}
	}

	/**
	 * @return the character
	 */
	public Character getCharacter() {
		return character;
	}

	/**
	 * @param character the character to set
	 */
	public void setCharacter(Character character) {
		this.character = character;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}
}
