package ca.concordia.soen6441.view.map;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.derby.tools.sysinfo;

import ca.concordia.soen6441.constants.Constants;
import ca.concordia.soen6441.d20.character.factory.PlayerFactory;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.element.Entery;
import ca.concordia.soen6441.d20.gamemap.element.Exit;
import ca.concordia.soen6441.d20.gamemap.element.Ground;
import ca.concordia.soen6441.d20.gamemap.element.Wall;
import ca.concordia.soen6441.persistence.dao.DaoFactory;
/**
 * this is mapEditor View 
 * @author wmg
 *
 */

@SuppressWarnings("serial")
public class MapEditor  extends JFrame implements ActionListener{

	private int row;
	private int column;
	private Grid[][] viewElements;
	private int elementSizeX = 29;
	private int elementSizeY = 29;
	private GameMap map;
	private Constants constants;
	private Dimension dimension;
	private JButton[] iconButtons;
	private ImageIcon[] images;
	private ImageIcon currentPointer;
	private String tag;
	
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
		iconButtons = new JButton[5];
		setCurrentPointer(new ImageIcon());
		images = new ImageIcon[5];
		//enable absolute positioning mode 
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setSize(row,column);
		
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
		//show the JFrame
		setVisible(true);
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
		public void initializeButton(JButton button ,String name , int sizeWidthOffset ,int sizeHeightOffset , int locationWidthOffset, int locationHeightOffset ,int heightMultiple, int widthMultiple){
			
			Dimension d = this.getSize();
			button.setSize(d.width/constants.BUTTON_SIZE_SAVE + sizeWidthOffset , d.height/constants.BUTTON_SIZE_SAVE + sizeHeightOffset);
//			System.out.println(button.getHeight() + ":" + button.HEIGHT + ":" + dimension.getWidth() + ":" + dimension.width);
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
//			System.out.println(x+ ":"+ y);
			if( x <= 44 )
				elementSizeX = x - 5  ;
			if( y <= 30)
				elementSizeY = y - 5  ;
			
//			System.out.println(elementSizeX+ ":"+ elementSizeY);
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
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
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
			
			if(e.getActionCommand().equals("Save"))
			{
				
				save(JOptionPane.showInputDialog(this, "Save"));
			}
			
			if(e.getActionCommand().equals("Load"))
			{
				load(JOptionPane.showInputDialog(this, "Load"));
			}
		}
	}
	
	public void save(String mapName){
		map = new GameMap(mapName,column, row);
		PlayerFactory playerFactory = new PlayerFactory();
		
		for(int i = 0; i < row ; i++){
			for(int j = 0; j < column ; j++){				
				if(viewElements[i][j].getTag() == "Ground"){
					Location location = new Location(j, i);
					map.setGameObjectAtLocation(location,new Ground(mapName+i+j,location));
				}else if(viewElements[i][j].getTag() == "Wall"){
					Location location = new Location(j, i);
					map.setGameObjectAtLocation(location,new Wall(mapName+i+j,location));
				}else if (viewElements[i][j].getTag() == "Enemy"){
					Location location = new Location(j, i);
					map.setGameObjectAtLocation(location,playerFactory.create("Enemy",mapName+i+j));
				}else if (viewElements[i][j].getTag() == "Enter"){
					Location location = new Location(j, i);
					map.setGameObjectAtLocation(location,new Entery(mapName+i+j,location));
				}else if (viewElements[i][j].getTag() == "Exit"){
					Location location = new Location(j, i);
					map.setGameObjectAtLocation(location,new Exit(mapName+i+j,location));
				}
			}
		}
		
		DaoFactory.getGameMapDao().create(map.getEntity());
	}
	
	@SuppressWarnings("null")
	public void load(String fileName){
//		removeGrid();
		GameMap map = DaoFactory.getGameMapDao().read((long)1).createModel();
		//load map from file here and replace null with it @ saman
		
		System.out.println("width : "+ map.getWidth() + "height : " + map.getHeight());
		for(int i = 0 ; i < map.getWidth(); i ++){
			for( int j = 0 ; j < map.getHeight(); j ++){
				System.out.println("elementi: "+i+" elementj: "+j+" GameObject: " + map.getGameObjectAtLocation(new Location(i,j)).getTag());
			}
		}
		row = map.getWidth();
		column = map.getHeight();
		viewElements = new Grid[map.getWidth()][map.getHeight()];
		initializeGrid();


		for(int i = 0; i < row ; i++){
			for(int j = 0; j < column ; j++){
				
				if(map.getGameObjectAtLocation(new Location(i,j)).getTag() == "Ground"){
					viewElements[i][j] = new Grid(this,"Ground");
				}else if(map.getGameObjectAtLocation(new Location(i,j)).getTag() == "Wall"){
					viewElements[i][j] = new Grid(this,"Wall");
				}else if (map.getGameObjectAtLocation(new Location(i,j)).getTag() == "Enemy"){
					viewElements[i][j] = new Grid(this,"Enemy");
				}else if (map.getGameObjectAtLocation(new Location(i,j)).getTag() == "Enter"){
					viewElements[i][j] = new Grid(this,"Enter");
				}else if (map.getGameObjectAtLocation(new Location(i,j)).getTag() == "Exit"){
					viewElements[i][j] = new Grid(this,"Exit");
				}
			}
		}
	}
	
	public void removeGrid(){
		for(int i = 0; i < row ; i++){
			for(int j = 0; j < column ; j++){
//				viewElements[i][j]
			 }
			}
	}
	
	public ImageIcon getCurrentPointer() {
		return currentPointer;
	}

	public void setCurrentPointer(ImageIcon currentPointer) {
		this.currentPointer = currentPointer;
	}

	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
