package ca.concordia.soen6441.view.map;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JFrame;

import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.GameMapEntity;
import ca.concordia.soen6441.persistence.dao.DaoFactory;
import ca.concordia.soen6441.view.map.viewElement.ViewEnter;
import ca.concordia.soen6441.view.map.viewElement.ViewExit;
import ca.concordia.soen6441.view.map.viewElement.ViewFighterEnemy;
import ca.concordia.soen6441.view.map.viewElement.ViewGround;
import ca.concordia.soen6441.view.map.viewElement.ViewObject;
import ca.concordia.soen6441.view.map.viewElement.ViewWall;


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
	private Dimension dimension;
	private GameMap map;

	public GameView(int row , int column) {
		//initializing
		this.row = row;
		this.column = column;
		viewElements = new ViewObject[row][column];
		//enable absolute positioning mode 
		setLayout(null);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//setting up location
		dimension = Toolkit.getDefaultToolkit().getScreenSize(); 
		setSize(dimension.width,dimension.height);
		setLocation((dimension.width - row ) / 2  , (dimension.height - column ) / 2 );
				
		initializeDimension();
		setVisible(true);
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
		
		if(viewElements !=null)
			removeGrid();	
		
		List<GameMapEntity> list = DaoFactory.getGameMapDao().findByName(fileName);
		if (list.isEmpty())
		{
			//TODO use appropriate procedure
			System.out.println("Invalid map name");
			return;
		}
		map = list.get(0).createModel();
		
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
					viewElements[i][j]= new ViewFighterEnemy("Enemy",(Fighter)map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject());
					setButton(i, j);
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Enter")){
					viewElements[i][j]= new ViewEnter("Enter");
					setButton(i, j);
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Exit")){
					viewElements[i][j]= new ViewExit("Exit");
					setButton(i, j);
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Player")){
					viewElements[i][j]= new ViewFighterEnemy("Player",(Fighter)map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject());
					setButton(i, j);					
				}else if (map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject().getTag().equals("Item")){
					viewElements[i][j].setTag("Item");
					// TODO change int to chest 
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
		// TODO Auto-generated method stub
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
}
