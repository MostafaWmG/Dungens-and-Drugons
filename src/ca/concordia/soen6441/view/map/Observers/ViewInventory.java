package ca.concordia.soen6441.view.map.Observers;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import ca.concordia.soen6441.constants.Constants;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.item.ItemEnum;

/**
 * shows the inventory the character has
 *
 */
public class ViewInventory extends JFrame implements Observer,ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fighter fighter;
	private Constants constants;
	private JTextArea jTextArea ;
	
	/**
	 * inventory view for the fighter
	 * @param fighter object whose inventory will be shown
	 */
	@SuppressWarnings("static-access")
	public ViewInventory(Fighter fighter) {
		setFighter(fighter);
		//enable absolute positioning mode 
		setLayout(null);
		setName(fighter.getName());
		//setting up location
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); 
		setSize(dimension.width/2,dimension.height/2);
		setLocation(getWidth() / 2  , getHeight() / 2 );
		
		
		jTextArea = new JTextArea();
		initializeText(jTextArea,"JTextArea",190,120,0,constants.LOCATION_HEIGHT_OFFSET,0);
		
		jTextArea.insert(fighter.showInvetory(false),0);

		
		JButton toBackPack= new JButton("FromBackPack");
		initializeButton(toBackPack, "ToBackPack", 0, 0, 0, constants.LOCATION_HEIGHT_OFFSET + 10, 3);
		toBackPack.addActionListener(this);
		
		JButton toCharacter = new JButton("FromCharacter");
		initializeButton(toCharacter, "ToCharacter", 0, 0, -100, constants.LOCATION_HEIGHT_OFFSET + 10, 3);
		toCharacter.addActionListener(this);
		
		JButton switchButton = new JButton("Switch");
		initializeButton(switchButton, "Switch", 0, 0, 100, constants.LOCATION_HEIGHT_OFFSET + 10, 3);
		switchButton.addActionListener(this);
		
		setVisible(true);
	}
	
	//re factor need 
	@SuppressWarnings("static-access")
	public void initializeText(JTextArea button ,String name , int sizeWidthOffset ,int sizeHeightOffset , int locationWidthOffset, int locationHeightOffset ,int heightMultiple){

		Dimension d = this.getSize();
		button.setSize(d.width/constants.TEXT_SIZE + sizeWidthOffset , d.height/constants.TEXT_SIZE + sizeHeightOffset);
		button.setLocation((d.width - button.getWidth())/2 + locationWidthOffset,(d.height- button.getHeight())/2 + (button.getHeight() * heightMultiple) + locationHeightOffset);
		getContentPane().add(button);
	}
	
	@SuppressWarnings("static-access")
	public void initializeButton(JButton button ,String name , int sizeWidthOffset ,int sizeHeightOffset , int locationWidthOffset, int locationHeightOffset ,int heightMultiple){

		Dimension d = this.getSize();
		button.setSize(d.width/constants.BUTTON_SIZE + sizeWidthOffset , d.height/constants.BUTTON_SIZE + sizeHeightOffset);
		button.setLocation((d.width - button.getWidth())/2 + locationWidthOffset,(d.height- button.getHeight())/2 + (button.getHeight() * heightMultiple) + locationHeightOffset);
		button.setActionCommand(name);
		getContentPane().add(button);
	}
	
	/**
	 * update all the observers
	 */
	@Override
	public void update(Observable o, Object arg) {
		jTextArea.setText(null);
		Fighter f = (Fighter)o;
		jTextArea.insert(f.showInvetory(false),0);
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
	 * method executed eveytime an action(click) is fired
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getID() == 1001){
			if(e.getActionCommand().equals("ToCharacter")){
				String itemStr = JOptionPane.showInputDialog(this, "Enter the Item name: ");
				if(itemStr != null)
					getFighter().putWearItemIntoBackPack(getFighter().getWearItems().get(ItemEnum.valueOf(itemStr.toUpperCase()).getValue()));
			}else if (e.getActionCommand().equals("ToBackPack")){
				String itemStr = JOptionPane.showInputDialog(this, "Enter the number slot: ");
				if(itemStr != null){
					int itemInt = Integer.parseInt(itemStr);
					getFighter().putBackPackIntoWearList(getFighter().getBackPack().get(itemInt),itemInt);	
				}
			}else if (e.getActionCommand().equals("Switch")){
				String itemStr = JOptionPane.showInputDialog(this, "Enter the number slot: ");
				if(itemStr != null){
					int itemInt = Integer.parseInt(itemStr);
					itemStr = JOptionPane.showInputDialog(this, "Enter the Item name: ");
					if(itemStr != null)
						getFighter().switchWearItemWithBackPack(getFighter().getItem(ItemEnum.valueOf(itemStr.toUpperCase())), getFighter().getBackPack().get(itemInt),itemInt);	
				}
				
			}
		}
		
	}
}
