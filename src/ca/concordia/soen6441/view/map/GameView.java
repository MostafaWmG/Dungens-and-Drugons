package ca.concordia.soen6441.view.map;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import ca.concordia.soen6441.constants.Constants;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.GameMapEntity;
import ca.concordia.soen6441.d20.item.Item;
import ca.concordia.soen6441.persistence.dao.DaoFactory;
import ca.concordia.soen6441.view.map.viewElement.ViewGround;
import ca.concordia.soen6441.view.map.viewElement.ViewObject;


public class GameView  extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int row;
	private int column;
	private ViewObject[][] viewElements;
	private int elementSizeX = 29;
	private int elementSizeY = 29;
	private GameMap map;
	private Constants constants;
	private Dimension dimension;
	private ImageIcon currentPointer;
	private String tag;
	private Fighter character;
	private Item item;
	
	public ImageIcon[] images;
	public JButton[] iconButtons;
	
	public GameView(int row , int column) {
		//initializing
		this.row = row;
		this.column = column;
		viewElements = new ViewObject[row][column];
		iconButtons = new JButton[8];
//		setCurrentPointer(new ImageIcon());
		images = new ImageIcon[8];
		//enable absolute positioning mode 
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setSize(row,column);
		
		//setting up location
		dimension = Toolkit.getDefaultToolkit().getScreenSize(); 
		setSize(dimension.width,dimension.height);
		setLocation((dimension.width - row ) / 2  , (dimension.height - column ) / 2 );
		
		initializeGrid();
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
			//TODO use appropriate procedure
			System.out.println("Invalid map name");
			return;
		}
		GameMap map = list.get(0).createModel();
		
		column = map.getWidth();
		row = map.getHeight();
		viewElements = new ViewGround[row][column];
		initializeGrid();

		for(int i = 0; i < row ; i++){
			for(int j = 0; j < column ; j++){
//				System.out.println(map.getGameObjectAtLocation(new Location(j,i)).getTag());
				System.out.println("Show:" + map.getGameObjectInstanceAtLocation(new Location(j,i)));
				if(map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Ground")){
					viewElements[i][j].setTag("Ground");
				}else if(map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Wall")){
					viewElements[i][j].setTag("Wall");
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Enemy")){
					viewElements[i][j].setTag("Enemy");
//					viewElements[i][j].setCharacter((Fighter) (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject()));
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Enter")){
					viewElements[i][j].setTag("Enter");
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Exit")){
					viewElements[i][j].setTag("Exit");
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Player")){
					viewElements[i][j].setTag("Player");
//					viewElements[i][j].setCharacter((Fighter) (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject()));
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Item")){
					viewElements[i][j].setTag("Item");
//					viewElements[i][j].setItem((Item)(map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject()));
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
				viewElements[i][j]=new ViewGround("Ground");
				viewElements[i][j].setSize(elementSizeX, elementSizeY);
				viewElements[i][j].setLocation( (dimension.width - column * (elementSizeX +1 ) ) / 2 + j * (elementSizeX +1 ), 30 + i * (elementSizeY + 1));

				viewElements[i][j].addActionListener( viewElements[i][j]);
				getContentPane().add(viewElements[i][j]);
			}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
