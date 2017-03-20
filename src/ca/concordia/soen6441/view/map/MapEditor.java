package ca.concordia.soen6441.view.map;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ca.concordia.soen6441.constants.Constants;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.fighter.FighterEntity;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.GameMapEntity;
import ca.concordia.soen6441.d20.gamemap.element.Entery;
import ca.concordia.soen6441.d20.gamemap.element.Exit;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectInstance;
import ca.concordia.soen6441.d20.gamemap.element.Ground;
import ca.concordia.soen6441.d20.gamemap.element.Wall;
import ca.concordia.soen6441.d20.item.Chest;
import ca.concordia.soen6441.d20.item.ChestEntity;
import ca.concordia.soen6441.persistence.dao.DaoFactory;

/**
 * this class implements is mapEditor View of the architectural design. 
 * @author wmg
 *
 */

@SuppressWarnings("serial")
public class MapEditor  extends JFrame implements ActionListener{

	/**
	 * row: number of rows of the map
	 * column: number of columns of the game map.
	 * viewElements: elements that are on the map
	 * elementSizeX: 
	 * elementSizeY:
	 * map: map of the game
	 * constants: is used to constant properties of the game elements
	 * dimension: 
	 * currentPointer:
	 * tag: to set the tag of elements on the map
	 * playerFactory: to 
	 * character: to place a character on the map
	 * item: items of the character's on the map
	 * images: to show images on the map
	 * iconButton: to display button on the map
	 */
	private int row;
	private int column;
	private Grid[][] viewElements;
	private int elementSizeX = 29;
	private int elementSizeY = 29;
	private GameMap map;
	private Constants constants;
	private Dimension dimension;
	private ImageIcon currentPointer;
	private String tag;
	private Fighter character;
	private Chest chest;
	
	public ImageIcon[] images;
	public JButton[] iconButtons;

	/**
	 * constructor
	 * @param row row of the mapView
	 * @param column column of the mapView
	 */
	public MapEditor(int row, int column) {
		
		//initializing
		this.row = row;
		this.column = column;
		viewElements = new Grid[row][column];
		iconButtons = new JButton[8];
		setCurrentPointer(new ImageIcon());
		images = new ImageIcon[8];
		//enable absolute positioning mode 
		setLayout(null);
		
		//setting up location
		dimension = Toolkit.getDefaultToolkit().getScreenSize(); 
		setSize(dimension.width,dimension.height);
		setLocation((dimension.width - row ) / 2  , (dimension.height - column ) / 2 );
		
		initializeGrid();
		
		initializeIcon();
		
		JButton saveButton = new JButton("Save");
		initializeButton(saveButton, "Save",0,0,-100,0,0,0 );
		
		JButton loadButton = new JButton("Load");
		initializeButton(loadButton, "Load",0,0,-100,0,0 ,2);
		
		JButton loadCharacterButton = new JButton("LoadCharacter");
		initializeButton(loadCharacterButton, "LoadCharacter",10,0,-100,0,0 ,4);
		
		JButton loadChestButton = new JButton("LoadChest");
		initializeButton(loadChestButton, "LoadChest",0,0,-100,0,0 ,-2);
		//show the JFrame
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
		 * initializeGird
		 */
		public void initializeGrid(){
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
			
			for(int i = 0; i < row ; i++)
				for(int j = 0; j < column ; j++){
					viewElements[i][j]=new Grid(this,"Ground");
					viewElements[i][j].setSize(elementSizeX, elementSizeY);
					viewElements[i][j].setLocation( (dimension.width - column * (elementSizeX +1 ) ) / 2 + j * (elementSizeX +1 ), 30 + i * (elementSizeY + 1));

					viewElements[i][j].addActionListener( viewElements[i][j]);
					getContentPane().add(viewElements[i][j]);
				}
		}
		
		/**
		 * initializeIcons which are used to modify map with
		 */
		public void initializeIcon(){
			
			for(int i = 0 ; i < iconButtons.length; i ++){
				images[i] = new ImageIcon((i+1)+".png");
				
				iconButtons[i] = new JButton(images[i]);
				iconButtons[i].setSize(30,30);
				iconButtons[i].setLocation(10,40 + (30 + 1) * i);
				iconButtons[i].setActionCommand("image"+i);
				getContentPane().add(iconButtons[i]);
				iconButtons[i].addActionListener(this);
			}
			iconButtons[1].setVisible(false);
			iconButtons[5].setVisible(false);
			iconButtons[6].setVisible(false);
			iconButtons[7].setVisible(false);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getID() == 1001){
			
			if(e.getActionCommand().equals ("image0")){
				setCurrentPointer((ImageIcon) iconButtons[0].getIcon());
				setTag("Wall");
			}
			
			if(e.getActionCommand().equals ("image1")){
				setCurrentPointer((ImageIcon) iconButtons[1].getIcon());
				setTag("Enemy");
			}

			if(e.getActionCommand().equals ("image2")){
				setCurrentPointer((ImageIcon) iconButtons[2].getIcon());
				setTag("Ground");
			}
			
			if(e.getActionCommand().equals ("image3")){
				setCurrentPointer((ImageIcon) iconButtons[3].getIcon());
				setTag("Enter");
			}
			
			if(e.getActionCommand().equals ("image4")){
				setCurrentPointer((ImageIcon) iconButtons[4].getIcon());
				setTag("Exit");
			}
			
			if(e.getActionCommand().equals ("image5")){
				setCurrentPointer((ImageIcon) iconButtons[5].getIcon());
				setTag("Chest");
			}
			
			if(e.getActionCommand().equals ("image6")){
				setCurrentPointer((ImageIcon) iconButtons[6].getIcon());
				setTag(getCharacter().getTag());
			}
			
			if(e.getActionCommand().equals ("image7")){
				setCurrentPointer((ImageIcon) iconButtons[7].getIcon());
				setTag("Enemy");
			}
			
			if(e.getActionCommand().equals("Save"))
			{
				
				save(JOptionPane.showInputDialog(this, "Save"));
			}
			
			if(e.getActionCommand().equals("Load"))
			{
				 load(JOptionPane.showInputDialog(this, "Load"));
			}
			
			if(e.getActionCommand().equals("LoadCharacter"))
			{
				 loadCharacter(JOptionPane.showInputDialog(this, "LoadCharacter"));
			}
			
			
			if(e.getActionCommand().equals("LoadChest"))
			{
				 LoadChest(JOptionPane.showInputDialog(this, "LoadChest"));
			}
		}
	}
	/**
	 * 
	 * @param name is used to specify which character to load from data base.
	 */
	public void loadCharacter(String name){
		List<FighterEntity> list = DaoFactory.getFighterDao().findByName(name);
		if (list.isEmpty())
		{
			System.out.println("Invalid Fighter name");
			return;
		}
		Fighter character = (Fighter) list.get(0).createModel();
		setCharacter(character);

		if(character.getTag().equals("Player")){
			iconButtons[6].setVisible(true);
		}else{
			iconButtons[1].setVisible(true);
		}
	}
	/**
	 * 
	 * @param name is used to load items related to that name
	 */
	public void LoadChest(String name){
		List<ChestEntity> list = DaoFactory.getChestDao().findByName(name);
		if (list.isEmpty())
		{
			System.out.println("Invalid Item name");
			return;
		}
		Chest Chest = (Chest) list.get(0).createModel();
		setChest(Chest);
		iconButtons[5].setVisible(true);
	}
	/**
	 * This the method used for saving a map
	 * @param mapName primary key
	 */
	public void save(String mapName){

		map = new GameMap(mapName,column, row);

		for(int i = 0; i < row ; i++){
			for(int j = 0; j < column ; j++){	
				if(viewElements[i][j].getTag().equals("Ground")){
					Location location = new Location(j, i);
					map.setGameObjectInstanceAtLocation(location, new GameObjectInstance(new Ground(mapName+i+j), map));
				}else if(viewElements[i][j].getTag().equals("Wall")){
					Location location = new Location(j, i);
					map.setGameObjectInstanceAtLocation(location, new GameObjectInstance(new Wall(mapName+i+j), map));
				}else if (viewElements[i][j].getTag().equals("Enemy")){
					Location location = new Location(j, i);
					map.setGameObjectInstanceAtLocation(location, new GameObjectInstance(viewElements[i][j].getCharacter(), map));
				}else if (viewElements[i][j].getTag().equals("Enter")){
					Location location = new Location(j, i);
					map.setGameObjectInstanceAtLocation(location, new GameObjectInstance(new Entery(mapName+i+j), map));
				}else if (viewElements[i][j].getTag().equals("Exit")){
					Location location = new Location(j, i);
					map.setGameObjectInstanceAtLocation(location, new GameObjectInstance(new Exit(mapName+i+j), map));
				}else if (viewElements[i][j].getTag().equals("Player")){
					Location location = new Location(j, i);
					map.setGameObjectInstanceAtLocation(location, new GameObjectInstance(viewElements[i][j].getCharacter(), map));
				}else if (viewElements[i][j].getTag().equals("Chest")){
					Location location = new Location(j, i);
					map.setGameObjectInstanceAtLocation(location, new GameObjectInstance(viewElements[i][j].getChest(), map));
				}

			}
		}


		if(map.mapValidator()){
			System.out.println("map Created");
			DaoFactory.getGameMapDao().create(map.getEntity());
		}else {
			System.out.println("Map is not Valid !!!!");
		}
		
	}
	
	/**
	 * This the method that is used for loading map
	 * @param fileName primary key
	 */
	public void load(String fileName){
		
		if(fileName == null || (fileName != null && ("".equals(fileName))))   
		{
		    return;
		}
		removeGrid();		
		List<GameMapEntity> list = DaoFactory.getGameMapDao().findByName(fileName);
		if (list.isEmpty())
		{
			System.out.println("Invalid map name");
			return;
		}
		GameMap map = list.get(0).createModel();
		
		column = map.getWidth();
		row = map.getHeight();
		viewElements = new Grid[row][column];
		initializeGrid();

		for(int i = 0; i < row ; i++){
			for(int j = 0; j < column ; j++){
				if(map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Ground")){
					viewElements[i][j].setTag("Ground");
					viewElements[i][j].iconHandler();
				}else if(map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Wall")){
					viewElements[i][j].setTag("Wall");
					viewElements[i][j].iconHandler();
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Enemy")){
					viewElements[i][j].setTag("Enemy");
					viewElements[i][j].setCharacter((Fighter) (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject()));
					viewElements[i][j].iconHandler();
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Enter")){
					viewElements[i][j].setTag("Enter");
					viewElements[i][j].iconHandler();
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Exit")){
					viewElements[i][j].setTag("Exit");
					viewElements[i][j].iconHandler();
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Player")){
					viewElements[i][j].setTag("Player");
					viewElements[i][j].setCharacter((Fighter) (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject()));
					viewElements[i][j].iconHandler();
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Chest")){
					viewElements[i][j].setTag("Chest");
					viewElements[i][j].setChest((Chest)(map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject()));
					viewElements[i][j].iconHandler();
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
				getContentPane().remove(viewElements[i][j]);
				getContentPane().revalidate();
				getContentPane().repaint();
				}
			}

			viewElements = null;
	}
	/**
	 * 
	 * @return 
	 */
	public ImageIcon getCurrentPointer() {
		return currentPointer;
	}

	public void setCurrentPointer(ImageIcon currentPointer) {
		this.currentPointer = currentPointer;
	}

	/**
	 * 
	 * @return the game map
	 */
	public GameMap getMap() {
		return map;
	}
	/**
	 * 
	 * @param map to set as the game map.
	 */
	public void setMap(GameMap map) {
		this.map = map;
	}

	/**
	 * 
	 * @return tag of element on the map
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * 
	 * @param tag to set for elements on the map
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @return the character that is on the map
	 */
	public Fighter getCharacter() {
		return character;
	}

	/**
	 * @param character to set on the map
	 */
	public void setCharacter(Fighter character) {
		this.character = character;
	}

	/**
	 * @return the chest
	 */
	public Chest getChest() {
		return chest;
	}

	/**
	 * @param chest the chest to set
	 */
	public void setChest(Chest chest) {
		this.chest = chest;
	}
}
