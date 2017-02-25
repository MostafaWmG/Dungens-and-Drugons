import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ca.concordia.soen6441.constants.Constants;
import ca.concordia.soen6441.d20.character.factory.PlayerFactory;
import ca.concordia.soen6441.view.map.MapEditor;
import ca.concordia.soen6441.d20.character.Character;
/**
 * this a Main class 
 * start point of game
 * @author wmg
 *
 */
@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener{

	private Constants constants;
	private Scanner scanner ;
	@SuppressWarnings("static-access")
	public Main(){
		
		//enable absolute positioning mode 
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//setting up location
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); 
		setSize(dimension.width/2,dimension.height/2);
		setLocation(getWidth() / 2  , getHeight() / 2 );
		
		//setting up mapEditorButton
		JButton mapEditorButton = new JButton("MapEditor");
		initializeButton(mapEditorButton,"MapEditor",0,0,0,constants.LOCATION_HEIGHT_OFFSET,0);

		//setting up characterEditorButton
		JButton characterEditorButton = new JButton("CharacterEditor");
		initializeButton(characterEditorButton,"CharacterEditor",20,0,0,constants.LOCATION_HEIGHT_OFFSET,1);

		//setting up characterEditorButton
		JButton itemCreatorButton = new JButton("ItemBuilder");
		initializeButton(itemCreatorButton,"ItemBuilder",0,0,0,constants.LOCATION_HEIGHT_OFFSET,2);
		
		//setting up playButton
		JButton playButton = new JButton("Play");
		initializeButton(playButton,"Play",0,0,0,constants.LOCATION_HEIGHT_OFFSET,3);

		//show the JFrame
		setVisible(true);
	}
	public static void main(String[] args) {
		
		new Main();
	}
/**
 *  it will create a button	
 * @param button JButton object
 * @param name name of the button
 * @param sizeWidthOffset sizeWidthOffset
 * @param sizeHeightOffset sizeHeightOffset
 * @param locationWidthOffset locationWidthOffset
 * @param locationHeightOffset locationHeightOffset
 * @param heightMultiple  using to locate button in a screen
 */
	
	@SuppressWarnings("static-access")
	public void initializeButton(JButton button ,String name , int sizeWidthOffset ,int sizeHeightOffset , int locationWidthOffset, int locationHeightOffset ,int heightMultiple){
		
		Dimension d = this.getSize();
		button.setSize(d.width/constants.BUTTON_SIZE + sizeWidthOffset , d.height/constants.BUTTON_SIZE + sizeHeightOffset);
		button.setLocation((d.width - button.getWidth())/2 + locationWidthOffset,(d.height- button.getHeight())/2 + (button.getHeight() * heightMultiple) + locationHeightOffset);
		button.setActionCommand(name);
		getContentPane().add(button);
		button.addActionListener(this);
	}
	
	
	private void createCharacter(){
		System.out.println("Please Enter Your Character Name : ");
		String charName = scanner.nextLine();
		System.out.println("Please Enter Your Character Type : ");
		String charType = scanner.nextLine();
		PlayerFactory PlayerFactory = new PlayerFactory();
		Character character = PlayerFactory.create(charType,charName);
		System.out.println("Do you want to save your character (yes or no )?");
		String result = scanner.nextLine();
		
		if(result.equals("yes")){
			// save the character into file  . you need to save character object into file @saman @negar.
			System.out.println("Character saved successfully");
		}else if (result.equals("no")){
			System.out.println("Character removed");
		}else {
			System.out.println("Error");
		}
		
		scanner.close();
	}
	
	private void editCharacter(){
		//edit character here 
		scanner.close();
	}
	
	private void createItem(){
		System.out.println("Please Enter Your Character Name : ");
		String charName = scanner.nextLine();
		System.out.println("Please Enter Your Character Type : ");
		String charType = scanner.nextLine();
		PlayerFactory PlayerFactory = new PlayerFactory();
		Character character = PlayerFactory.create(charType,charName);
		System.out.println("Do you want to save your character (yes or no )?");
		String result = scanner.nextLine();
		
		if(result.equals("yes")){
			// save the character into file  . you need to save character object into file @saman @negar.
			System.out.println("Character saved successfully");
		}else if (result.equals("no")){
			System.out.println("Character removed");
		}else {
			System.out.println("Error");
		}
		
		scanner.close();
	}
	
	private void editItem(){
		scanner.close();
	}
	/**
	 * Action Listener for Main class
	 * @param action event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getID() == 1001 ){

			if(e.getActionCommand().equals("MapEditor")){
				try {

					this.setVisible(false);
					String row = JOptionPane.showInputDialog(getContentPane(), "EnterRow", "Row", HEIGHT);
					String column = JOptionPane.showInputDialog(getContentPane(), "EnterColumn", "column", WIDTH);

					int x = Integer.parseInt(column);
					int y = Integer.parseInt(row);
					
					// Maximum of our grid  other maps is not valid to small for screen
					if(y >=65)
						y= 64;
					if(x >=150)
						x =150;
					new MapEditor(y,x);

				} catch (NumberFormatException error) {
					JOptionPane.showMessageDialog(getContentPane(), "Wrong InPut!!");
					this.setVisible(true);
				} 
			}else if (e.getActionCommand().equals("CharacterEditor") ){
				scanner = new Scanner(System.in);
				System.out.println("Character Edtior Section: ");
				System.out.println("Choose Section : ");
				System.out.println("EditCharacter:(Type e) , CreateCharacter : (Type C) " );
				String hitButton = scanner.nextLine();
				
				if(hitButton.equals("c")){
					createCharacter();
				}else if (hitButton.equals("e")){
					editCharacter();
				}else {
					System.out.println("Error");
				}
				
			}else if (e.getActionCommand().equals("ItemBuilder") ) {
				scanner = new Scanner(System.in);
				System.out.println("Item Builder Section: ");
				System.out.println("Choose Section : ");
				System.out.println("EditItem:(Type e) , CreateItem : (Type C) " );
				String hitButton = scanner.nextLine();
				
				if(hitButton.equals("c")){
					createItem();
				}else if (hitButton.equals("e")){
					editItem();
				}else {
					System.out.println("Error");
				}
			}
			
		}
	}
}
