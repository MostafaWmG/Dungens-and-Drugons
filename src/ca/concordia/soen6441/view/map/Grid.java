package ca.concordia.soen6441.view.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.Item;

/**
 * This is a Grid class for elements on mapView
 * @author wmg
 *
 */
@SuppressWarnings("serial")
public class Grid extends JButton implements ActionListener{
	/**
	 * icon: to display an icon picture
	 * mapEditor: game map
	 * tag: to set the tag for map
	 * character: character that is placed on the map
	 * item: item that is on the map.
	 */
	private ImageIcon icon;
	private MapEditor mapEditor;
	private String tag;
	private Fighter character;
	private Item item;
	
	
	/**
	 * 
	 * @param mapEditor the map to put elements on it
	 * @param tag to set the tag.
	 */
	public Grid(MapEditor mapEditor,String tag)
	{
		this.mapEditor = mapEditor;
		this.setTag(tag);
		setCharacter(null);
		setItem(null);
		iconHandler();
	}
	/**
	 * Puts given character with the given on the map
	 * @param character to set on map
	 * @param mapEditor the map to put elements on it
	 * @param tag to set the tag.
	 */
	public Grid(Fighter character,MapEditor mapEditor,String tag) {
		this.mapEditor = mapEditor;
		this.setTag(tag);
		setCharacter(character);
		this.setItem(null);
		iconHandler();
	}
	/**
	 * Puts given character with the given on the map
	 * @param character items to set on map
	 * @param mapEditor the map to put elements on it
	 * @param tag to set the tag.
	 */
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
			if(mapEditor.getTag().equals("Enemy")){
				setCharacter(mapEditor.getCharacter());
				mapEditor.iconButtons[1].setVisible(false);
				mapEditor.setCurrentPointer(mapEditor.images[2]);
				mapEditor.setTag("Ground");
			}
			if(mapEditor.getTag().equals("Item")){
				setItem(mapEditor.getItem());
				mapEditor.iconButtons[5].setVisible(false);
				mapEditor.setCurrentPointer(mapEditor.images[2]);
				mapEditor.setTag("Ground");
			}
			
//			if(mapEditor.getTag().equals("Enemy")){
//				mapEditor.iconButtons[7].setVisible(false);
//			}
		}
	}

	/**
	 * 
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * 
	 * @param tag the tag to set.
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	/**
	 * 
	 * @param icon the icon that will be displayed on the map
	 */
	public void setIcon(ImageIcon icon){
		this.icon = icon;
	}
	/**
	 * @return the icon that will displayed on the
	 */
	public ImageIcon getIcon(){
		return icon;
	}
	
	/**
	 * This is a method for showing icons by tag
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
	public Fighter getCharacter() {
		return character;
	}

	/**
	 * @param character to set on mapView
	 */
	public void setCharacter(Fighter character) {
		this.character = character;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set on the mapView
	 */
	public void setItem(Item item) {
		this.item = item;
	}
}
