package ca.concordia.soen6441.view.map.Observers;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;

import ca.concordia.soen6441.constants.Constants;
import ca.concordia.soen6441.controller.Game;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectInstance;
import ca.concordia.soen6441.d20.item.Chest;
import ca.concordia.soen6441.view.map.viewElement.ViewChest;
import ca.concordia.soen6441.view.map.viewElement.ViewExit;
import ca.concordia.soen6441.view.map.viewElement.ViewFighterEnemy;
import ca.concordia.soen6441.view.map.viewElement.ViewFighterPlayer;
import ca.concordia.soen6441.view.map.viewElement.ViewGround;
import ca.concordia.soen6441.view.map.viewElement.ViewObject;
import ca.concordia.soen6441.view.map.viewElement.ViewWall;

/**
 * game view deriving from JFrame. It is the view in the ModelViewComtroller pattern
 * shows the game itself
 *
 */
public class GameView  extends JFrame implements ActionListener,Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int row;
	private int column;
	private ViewObject[][] viewElements;
	private int elementSizeX = 29;
	private int elementSizeY = 29;
	private Dimension dimension;
	private GameMap map;
	private Constants constants;
	private boolean inventoryEn;
	private Game game;
	private Location mainCharacterLocation;
	
	/**
	 * constructor
	 * @param row number of rows the map has
	 * @param column number of columns the map has
	 * @param game the object game which represents the game itself
	 */
	public GameView(int row , int column,Game game) {
		//initializing
		this.game = game;
		this.row = row;
		this.column = column;
		viewElements = new ViewObject[row][column];
		//enable absolute positioning mode 
		setLayout(null);
		
		//setting up location
		dimension = Toolkit.getDefaultToolkit().getScreenSize(); 
		setSize(dimension.width,dimension.height);
		setLocation((dimension.width - row ) / 2  , (dimension.height - column ) / 2 );
		
		JButton inventory = new JButton("Inventory");
		initializeButton(inventory, "Inventory",0,0,-100,0,0,0 );
		
		
		JButton ability = new JButton("Ability");
		initializeButton(ability, "Ability",0,0,-100,0,0 ,2);
		
		JButton save = new JButton("Save");
		initializeButton(save, "Save",0,0,-100,0,0 ,4);
		
		initializeDimension();
		setVisible(true);
	}
	
	/**
	 *  This method will create a button	
	 * @param button JButton object
	 * @param name name of the button
	 * @param sizeWidthOffset sizeWidthOffset
	 * @param sizeHeightOffset sizeHeightOffset
	 * @param locationWidthOffset locationWidthOffset
	 * @param locationHeightOffset locationHeightOffset
	 * @param heightMultiple  using to locate button in a screen
	 */
		
		@SuppressWarnings("static-access")
		public void initializeButton(JButton button ,String name , int sizeWidthOffset ,int sizeHeightOffset , int locationWidthOffset, int locationHeightOffset ,int heightMultiple, int widthMultiple){
			
			Dimension d = this.getSize();
			button.setSize(d.width/constants.BUTTON_SIZE_SAVE + sizeWidthOffset , d.height/constants.BUTTON_SIZE_SAVE + sizeHeightOffset);
			button.setLocation( (dimension.width- button.getWidth() )/2  +( button.getWidth() * widthMultiple)+ locationWidthOffset,dimension.height - (button.getHeight() * 3)+ locationHeightOffset);
			button.setActionCommand(name);
			getContentPane().add(button);
			button.addActionListener(this);
		}
		
	/**
	 * This the method that is used for loading map
	 * @param fileName primary key
	 */
	public void load(GameMap mapLoaded,Fighter mainCharacter){
		map = mapLoaded;
		
		column = map.getWidth();
		row = map.getHeight();
		viewElements = new ViewObject[row][column];

		for(int i = 0; i < row ; i++){
			for(int j = 0; j < column ; j++){
				if(map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Ground")){
					viewElements[i][j]= new ViewGround("Ground");
					setButton(i, j);
				}else if(map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Wall")){
					viewElements[i][j]= new ViewWall("Wall");
					setButton(i, j);
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Enemy")){
					viewElements[i][j]= new ViewFighterEnemy("Enemy",(Fighter)map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject(),this,false);
					setButton(i, j);
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Enter")){
					if(mainCharacter.getTag().equals("Enemy")){
						viewElements[i][j]= new ViewFighterEnemy("Enemy",mainCharacter,this,true);
					}else{
						viewElements[i][j]= new ViewFighterEnemy("Player",mainCharacter,this,true);
					}
					setMainCharacterLocation(new Location(j,i));
					map.setGameObjectInstanceAtLocation(new Location(j,i),new GameObjectInstance(mainCharacter, map));
					setButton(i, j);
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Exit")){
					viewElements[i][j]= new ViewExit("Exit");
					setButton(i, j);
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Player")){
					viewElements[i][j]= new ViewFighterPlayer("Player",(Fighter)map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject(),this,false);
					setButton(i, j);					
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Chest")){
					viewElements[i][j]= new ViewChest("Chest",(Chest)map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject());
					setButton(i, j);	
				}
			}
		}
	}
	
	/**
	 * This method will remove previous grid
	 */
	public void removeGrid(){
		for(int i = 0; i < row ; i++){
			for(int j = 0; j < column ; j++){
				if(viewElements[i][j] != null){
					getContentPane().remove(viewElements[i][j]);
					getContentPane().revalidate();
					getContentPane().repaint();
				}
			}
		}

		viewElements = null;
	}
	
	/**
	 * This method will remove element
	 */
	public void removeElement(int i , int j){

		getContentPane().remove(viewElements[i][j]);
		getContentPane().revalidate();
		getContentPane().repaint();
	}
	
	/**
	 * initializeDimension
	 */
	public void initializeDimension(){
		//setting up the grid
		int x = (dimension.width )/ column ;
		int y = (dimension.height) / row ;

		if( x <= 44 )
			elementSizeX = x - 5  ;
		if( y <= 30)
			elementSizeY = y - 5  ;
		

		//minimum size of button
		if ( elementSizeX <=3 )
			elementSizeX = 3;
		if ( elementSizeY <=3 )
			elementSizeY = 3;		
	}
	
	public void setButton(int i,int j){
		viewElements[i][j].setSize(elementSizeX, elementSizeY);
		viewElements[i][j].setLocation( (dimension.width - column * (elementSizeX +1 ) ) / 2 + j * (elementSizeX +1 ), 30 + i * (elementSizeY + 1));

		viewElements[i][j].addActionListener( viewElements[i][j]);
		getContentPane().add(viewElements[i][j]);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getID() == 1001){
			if(e.getActionCommand().equals("Inventory")){
				setInventoryEn(true);
			}else if (e.getActionCommand().equals("Ability")){
				setInventoryEn(false);
			}else if (e.getActionCommand().equalsIgnoreCase("Save")){
				game.save();
			}
		}
	}

	/**
	 * @return the map
	 */
	public GameMap getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(GameMap map) {
		this.map = map;
	}

	/**
	 * @return the inventoryEn
	 */
	public boolean isInventoryEn() {
		return inventoryEn;
	}

	/**
	 * @param inventoryEn the inventoryEn to set
	 */
	public void setInventoryEn(boolean inventoryEn) {
		this.inventoryEn = inventoryEn;
	}

	@Override
	public void update(Observable o, Object arg) {
		GameMap gMap = (GameMap)o; 
		
		if(gMap.getGameObjectInstanceAtLocation(game.getCurrentLocation()).getGameObject().getTag().equals("Chest")){
			
			removeElement(game.getCurrentLocation().getY(),game.getCurrentLocation().getX());
			viewElements[game.getCurrentLocation().getY()][game.getCurrentLocation().getX()] = new ViewChest("Chest",(Chest)gMap.getGameObjectInstanceAtLocation(game.getCurrentLocation()).getGameObject());
			setButton(game.getCurrentLocation().getY(), game.getCurrentLocation().getX());
			
			game.setCurrentLocation(new Location(getMainCharacterLocation().getX(),getMainCharacterLocation().getY()));
		}else{
			removeElement(getMainCharacterLocation().getY(),getMainCharacterLocation().getX());
			viewElements[getMainCharacterLocation().getY()][getMainCharacterLocation().getX()] = new ViewGround("Ground");
			setButton(getMainCharacterLocation().getY(), getMainCharacterLocation().getX());
			
			removeElement(game.getCurrentLocation().getY(),game.getCurrentLocation().getX());
			viewElements[game.getCurrentLocation().getY()][game.getCurrentLocation().getX()] = new ViewFighterPlayer(gMap.getGameObjectInstanceAtLocation(game.getCurrentLocation()).getGameObject().getTag(),(Fighter) gMap.getGameObjectInstanceAtLocation(game.getCurrentLocation()).getGameObject(), this, true);
			setButton(game.getCurrentLocation().getY(), game.getCurrentLocation().getX());
			
			setMainCharacterLocation(new Location(game.getCurrentLocation().getX(),game.getCurrentLocation().getY()));
		}

	}
	
	public void updateWithOutObserver(Location location , Location destination){
		updateWithOutObserver(location, destination,false);
	}
	
	public void updateWithOutObserver(Location location,Location destination, boolean isFriendly){
		
		if(map.getGameObjectInstanceAtLocation(destination).getGameObject().getTag().equals("Chest")){
			System.out.println("Enter chest:");
			removeElement(destination.getY(),destination.getX());
			viewElements[destination.getY()][destination.getX()] = new ViewChest("Chest",(Chest)map.getGameObjectInstanceAtLocation(destination).getGameObject());
			setButton(destination.getY(), destination.getX());
			
		}else{
			System.out.println("Skip chest:");
			removeElement(location.getY(),location.getX());
			viewElements[location.getY()][location.getX()] = new ViewGround("Ground");
			setButton(location.getY(), location.getX());
			
			removeElement(destination.getY(),destination.getX());
			if(isFriendly){
				viewElements[destination.getY()][destination.getX()] = new ViewFighterPlayer(map.getGameObjectInstanceAtLocation(destination).getGameObject().getTag(),(Fighter) map.getGameObjectInstanceAtLocation(destination).getGameObject(), this, false);
			}else{
				viewElements[destination.getY()][destination.getX()] = new ViewFighterEnemy(map.getGameObjectInstanceAtLocation(destination).getGameObject().getTag(),(Fighter) map.getGameObjectInstanceAtLocation(destination).getGameObject(), this, false);
			}
			
			setButton(destination.getY(),destination.getX());
		}
	}
	
	public void removeDeadFighter(Location origin){
		removeElement(origin.getY(),origin.getX());
		viewElements[origin.getY()][origin.getX()] = new ViewChest("Chest",(Chest)map.getGameObjectInstanceAtLocation(origin).getGameObject());
		setButton(origin.getY(), origin.getX());
	}
	/**
	 * @return the mainCharacterLocation
	 */
	public Location getMainCharacterLocation() {
		return mainCharacterLocation;
	}

	/**
	 * @param mainCharacterLocation the mainCharacterLocation to set
	 */
	public void setMainCharacterLocation(Location mainCharacterLocation) {
		this.mainCharacterLocation = mainCharacterLocation;
	}


}
