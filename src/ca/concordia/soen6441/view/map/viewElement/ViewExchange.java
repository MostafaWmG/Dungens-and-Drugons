package ca.concordia.soen6441.view.map.viewElement;

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
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.d20.item.ItemEnum;

public class ViewExchange extends JFrame implements Observer,ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fighter fighter;
	private Fighter exchangeFighter;
	private Constants constants;
	private JTextArea jTextArea ;
	private JTextArea JTextAreaExchange;
	
	@SuppressWarnings("static-access")
	public ViewExchange(Fighter fighter, Fighter exchangeFighter) {
		setFighter(fighter);
		setExchangeFighter(exchangeFighter);
		//enable absolute positioning mode 
		setLayout(null);
		setName(fighter.getName());
		//setting up location
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); 
		setSize(dimension.width,dimension.height);
		setLocation(getWidth() /2  , getHeight() / 2 );
		
		
		jTextArea = new JTextArea();
		initializeText(jTextArea,"JTextArea",-50,120,340,constants.LOCATION_HEIGHT_OFFSET -50,0);
		
		jTextArea.insert(fighter.showInvetory(false),0);
		
		JTextAreaExchange = new JTextArea();
		initializeText(JTextAreaExchange,"JTextAreaExchange",-50,120,-340,constants.LOCATION_HEIGHT_OFFSET- 50,0);
		
		JTextAreaExchange.insert(getExchangeFighter().showInvetory(false),0);
		
		JButton toBackPack= new JButton("Y:FromBackPack");
		initializeButton(toBackPack, "ToBackPack", 0, 0, 210, constants.LOCATION_HEIGHT_OFFSET - 60, 3);
		toBackPack.addActionListener(this);
		
		JButton toCharacter = new JButton("Y:FromCharacter");
		initializeButton(toCharacter, "ToCharacter", 0, 0, 410, constants.LOCATION_HEIGHT_OFFSET - 60, 3);
		toCharacter.addActionListener(this);
		
		JButton switchButton = new JButton("Y:Switch");
		initializeButton(switchButton, "Switch", 0, 0, 610, constants.LOCATION_HEIGHT_OFFSET - 60, 3);
		switchButton.addActionListener(this);
		
		JButton toBackPackEx= new JButton("FromBackPack");
		initializeButton(toBackPackEx, "ToBackPackEx", 0, 0, -210, constants.LOCATION_HEIGHT_OFFSET - 60, 3);
		toBackPackEx.addActionListener(this);
		
		JButton toCharacterEx = new JButton("FromCharacter");
		initializeButton(toCharacterEx, "ToCharacterEx", 0, 0, -410, constants.LOCATION_HEIGHT_OFFSET - 60, 3);
		toCharacterEx.addActionListener(this);
		
		JButton switchButtonEx = new JButton("Switch");
		initializeButton(switchButtonEx, "SwitchEx", 0, 0, -610, constants.LOCATION_HEIGHT_OFFSET - 60, 3);
		switchButtonEx.addActionListener(this);
		
		JButton swapButton = new JButton("Swap");
		initializeButton(swapButton, "Swap", 0, 0, 0, constants.LOCATION_HEIGHT_OFFSET - 60, 3);
		swapButton.addActionListener(this);
		
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
	@Override
	public void update(Observable o, Object arg) {
		jTextArea.setText(null);
		JTextAreaExchange.setText(null);
		JTextAreaExchange.insert(getExchangeFighter().showInvetory(false), 0);
		jTextArea.insert(getFighter().showInvetory(false),0);
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
				
			}if(e.getActionCommand().equals("ToCharacterEx")){
				String itemStr = JOptionPane.showInputDialog(this, "Enter the Item name: ");
				if(itemStr != null)
					getExchangeFighter().putWearItemIntoBackPack(getExchangeFighter().getWearItems().get(ItemEnum.valueOf(itemStr.toUpperCase()).getValue()));
			}else if (e.getActionCommand().equals("ToBackPackEx")){
				String itemStr = JOptionPane.showInputDialog(this, "Enter the number slot: ");
				if(itemStr != null){
					int itemInt = Integer.parseInt(itemStr);
					getExchangeFighter().putBackPackIntoWearList(getExchangeFighter().getBackPack().get(itemInt),itemInt);	
				}
			}else if (e.getActionCommand().equals("SwitchEx")){
				String itemStr = JOptionPane.showInputDialog(this, "Enter the number slot: ");
				if(itemStr != null){
					int itemInt = Integer.parseInt(itemStr);
					itemStr = JOptionPane.showInputDialog(this, "Enter the Item name: ");
					if(itemStr != null)
						getExchangeFighter().switchWearItemWithBackPack(getExchangeFighter().getItem(ItemEnum.valueOf(itemStr.toUpperCase())), getExchangeFighter().getBackPack().get(itemInt),itemInt);	
				}
				
			}else if (e.getActionCommand().equals("Swap")){
				swap();
			}
		}
		
	}
	
	private void swap(){
		String itemStr = JOptionPane.showInputDialog(this, "YOU :Enter the Item name: ");
		if(itemStr != null){
			
			String itemStrEx = JOptionPane.showInputDialog(this, "OTHER :Enter the Item name: ");
			if(itemStrEx != null)
			{
				Item item =getFighter().getItem(ItemEnum.valueOf(itemStr.toUpperCase()));
				getFighter().removeItem(item);
				Item exchangeItem = getExchangeFighter().getItem(ItemEnum.valueOf(itemStrEx.toUpperCase()));
				getExchangeFighter().removeItem(exchangeItem);
				getFighter().putOnItem(exchangeItem);
				getExchangeFighter().putOnItem(item);

			}	
		}
		
	}
	/**
	 * @return the exchangeFighter
	 */
	public Fighter getExchangeFighter() {
		return exchangeFighter;
	}

	/**
	 * @param exchangeFighter the exchangeFighter to set
	 */
	public void setExchangeFighter(Fighter exchangeFighter) {
		this.exchangeFighter = exchangeFighter;
	}

	/**
	 * @return the jTextAreaExchange
	 */
	public JTextArea getJTextAreaExchange() {
		return JTextAreaExchange;
	}

	/**
	 * @param jTextAreaExchange the jTextAreaExchange to set
	 */
	public void setJTextAreaExchange(JTextArea jTextAreaExchange) {
		JTextAreaExchange = jTextAreaExchange;
	}

}
