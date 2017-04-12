package ca.concordia.soen6441.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import ca.concordia.soen6441.d20.campaign.Campaign;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.dice.Dice;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.element.Entery;
import ca.concordia.soen6441.d20.gamemap.element.Exit;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.Wall;
import ca.concordia.soen6441.d20.item.Chest;
import ca.concordia.soen6441.d20.strategy.AggressiveNPC;
import ca.concordia.soen6441.d20.strategy.FriendlyNPC;
import ca.concordia.soen6441.view.map.Observers.GameView;
import ca.concordia.soen6441.view.map.viewElement.ViewExchange;

/**
 * this class implements all the game logic.
 * This is the main class where the player can run the game and play
 *
 */
public class Game implements Runnable {
	
	/**
	 * the campaign for this game
	 */
	private Campaign campaign;
	
	private List<Fighter> fighters;
	/**
	 * player playing the game
	 */
	private Fighter fighter;
	/**
	 * enemies in the game
	 */
	private List<Fighter> enemies;
	/**
	 * frien characters in the map
	 */
	private List<Fighter> friends;
	/**
	 * chest in the map
	 */
	private List<Chest> chests;
	/**
	 * wall in the map
	 */
	private List<Wall> walls;
	/**
	 * exit and enter door
	 */
	private Exit exit;
	private Entery entery;
	private Location currentLocation;
	private Dice dice ;


	/**
	 * game map
	 */
	private GameMap map;
	private GameView gameView;
	private int mapNumber;
	private List<Fighter> tempSort;
	private List<Fighter> turnOrder;
	private boolean ordered;
	/**
	 * check if the game is running, true, if it is not, or false if it is stopped 
	 */
	private volatile boolean runner;

	/**
	 * Constructs a game given the campaing and player
	 * @param campaign campaign the fighter wants to get in
	 * @param fighter the player
	 */
	public Game(Campaign campaign, Fighter fighter) {
		setRunner(true);
		setCampaign(campaign);
		setFighter(fighter);
		setEnemies(new ArrayList<>());
		setFriends(new ArrayList<>());
		setChests(new ArrayList<>());
		setWalls(new ArrayList<>());
		setMapNumber(0);
		setDice(new Dice());
		setFighters(new ArrayList<Fighter>());
		setTempSort(new ArrayList<Fighter>());
		setTurnOrder(new ArrayList<Fighter>());
		setOrdered(false);
		initializeAndShow();
	}
	
	/**
	 * create a game
	 * @param campaign campaing the player wants to play
	 * @param fighter the player
	 * @param test if this is a test this parameter is true, false otherwise
	 */
	public Game(Campaign campaign, Fighter fighter,boolean test) {
		setRunner(true);
		setCampaign(campaign);
		setFighter(fighter);
		setEnemies(new ArrayList<>());
		setFriends(new ArrayList<>());
		setChests(new ArrayList<>());
		setWalls(new ArrayList<>());
		setMapNumber(0);
		setDice(new Dice());
		setFighters(new ArrayList<Fighter>());
		setTempSort(new ArrayList<Fighter>());
		setTurnOrder(new ArrayList<Fighter>());
		setOrdered(false);
		initializeAndShow(test);
	}
	
	public void initializeAndShow(){
		initializeAndShow(true);
	}
	
	/**
	 * initialize the map with the object saved
	 * @param test parameter to know if this is a test or an actual game
	 */
	public void initializeAndShow(boolean test){
		if(getMapNumber() < getCampaign().getMaps().size()){
			setMap(getCampaign().getMaps().get(getMapNumber()));
		}
		else{ 
			finishGame();
			return;
		}
		
		System.out.println("   ");
		
		for (int i = 0 ; i < map.getHeight() ; i++){
			for ( int j = 0 ; j < map.getWidth() ; j++){
				GameObject reference = map.getGameObjectInstanceAtLocation(new Location(j,i)).getGameObject();
				
				if (reference.getTag().equals("Ground") ){
					System.out.print("G");//ground
				}else if (reference.getTag().equals("Wall") ){
					System.out.print("W");//wall
					getWalls().add((Wall)reference);
				}else if (reference.getTag().equals("Enemy") ){
					System.out.print("H");//hostile
					Fighter tempFighter = (Fighter)reference;
					tempFighter.setStrategy(new AggressiveNPC(tempFighter));
					getEnemies().add(tempFighter);
					getFighters().add(tempFighter);
				}else if (reference.getTag().equals("Player") ){
					System.out.print("F");//friendly
					Fighter tempFighter = (Fighter)reference;
					tempFighter.setStrategy(new FriendlyNPC(tempFighter));
					getFriends().add(tempFighter);
					getFighters().add(tempFighter);
				}else if (reference.getTag().equals("Chest") ){
					System.out.print("C");//chest
					getChests().add((Chest)reference);
				}else if (reference.getTag().equals("Exit") ){
					System.out.print("Q");//exit
					setExit((Exit)reference);
				}else if (reference.getTag().equals("Enter") ){
					System.out.print("E");//enter
					setEntery((Entery)reference);
					setCurrentLocation(new Location(j,i));
				}
				
				System.out.print(" ");
				if(j == getMap().getWidth() - 1 ){
					System.out.println("");
				}
			}
		}
		
		if(!isOrdered())
			orderFighters();

		if(test){
			if(getGameView() !=null){
				getGameView().removeGrid();
			}else{
				setGameView(new GameView(getMap().getHeight(),getMap().getWidth(),this));
			}

			adaptToCharacter();
			getMap().addObserver(getGameView());
			getGameView().load(getMap(),getFighter());
		}
	}
	
	/**
	 * adapt the map and elements in it to the player level
	 */
	public void adaptToCharacter(){
		int level = getFighter().getLevel();
		
		for(int i = 0 ; i < getEnemies().size() ; i ++){
			getEnemies().get(i).levelUp(level - getEnemies().get(i).getLevel());
		}
		
		for(int i = 0 ; i < getFriends().size() ; i ++){
			getFriends().get(i).levelUp(level - getFriends().get(i).getLevel());
		}
		
		for(int i = 0 ; i < getChests().size() ; i ++){
			getChests().get(i).update(level);
		}
	}
	
	
	private void orderFighters(){
		setOrdered(true);
		for (int i = 0; i < 21; i++) {
			getTempSort().add(null);
		}
		
		getFighters().add(getFighter());
		
		for (int i = 0; i < getFighters().size(); i++) {
			int roll = getDice().roll20();
//				System.out.println(getFighters().get(i)+ ":number:  "+ roll);
				if(getTempSort().get(i) != null){
					getTempSort().add(getFighters().get(i));
				}else {
					getTempSort().set(roll, getFighters().get(i));
				}
				
		}
		
		for (int i = getTempSort().size()-1 ; i >= 0; i--) {
			if(getTempSort().get(i) != null){
				getTurnOrder().add(getTempSort().get(i));
			}
		}
		
		for (int i = 0; i < getTurnOrder().size(); i++) {
//			System.out.println("sorted: " + getTurnOrder().get(i));
		}
	}
	/**
	 * create the view exchange
	 * @param exchangeFighter
	 * @param fighter
	 */
	public void createViewExchange(Fighter exchangeFighter,Fighter fighter){
		ViewExchange viewExchange = new ViewExchange(fighter,exchangeFighter);
		exchangeFighter.addObserver(viewExchange);
		fighter.addObserver(viewExchange);
	}
	
	public void reset(){
		setMapNumber(getMapNumber()+1);
		getFighter().levelUp(1,true);
		initializeAndShow();
	}
	
	/**
	 * terminate the run method
	 */
	public void terminate(){
		setRunner(false);
	}
	
	/**
	 * finishes the game
	 */
	private void finishGame(){
		System.out.print("Game Is finished");
		getGameView().setVisible(false);
		getGameView().dispose();
		terminate();
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
	 * @return the exit
	 */
	public Exit getExit() {
		return exit;
	}

	/**
	 * @param exit the exit to set
	 */
	public void setExit(Exit exit) {
		this.exit = exit;
	}

	/**
	 * @return the entry
	 */
	public Entery getEntery() {
		return entery;
	}

	/**
	 * @param entery the entry to set
	 */
	public void setEntery(Entery entery) {
		this.entery = entery;
	}

	/**
	 * @return the enemies
	 */
	public List<Fighter> getEnemies() {
		return enemies.stream().filter(en->!en.isDead()).collect(Collectors.toList());
	}

	/**
	 * @param enemies the enemies to set
	 */
	public void setEnemies(List<Fighter> enemies) {
		this.enemies = enemies;
	}

	/**
	 * @return the walls
	 */
	public List<Wall> getWalls() {
		return walls;
	}

	/**
	 * @param walls the walls to set
	 */
	public void setWalls(List<Wall> walls) {
		this.walls = walls;
	}

	/**
	 * @return the chests
	 */
	public List<Chest> getChests() {
		return chests;
	}

	/**
	 * @param chests the chests to set
	 */
	public void setChests(List<Chest> chests) {
		this.chests = chests;
	}

	/**
	 * @return the friends
	 */
	public List<Fighter> getFriends() {
		return friends;
	}

	/**
	 * @param friends the friends to set
	 */
	public void setFriends(List<Fighter> friends) {
		this.friends = friends;
	}

	/**
	 * @return the campaign
	 */
	public Campaign getCampaign() {
		return campaign;
	}

	/**
	 * @param campaign the campaign to set
	 */
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	/**
	 * @return the currentLocation
	 */
	public Location getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * @param currentLocation the currentLocation to set
	 */
	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	@Override
	public void run() {

//		Strategy strategy;
//		scanner  = new Scanner(System.in);
//		String keyStr;
//		while(runner){
//			System.out.println("Please move the character: ");
//			keyStr = scanner.nextLine();
//			
//			try {
//				Thread.sleep(200);
//			} catch (Exception e) {
//				break;			}
//			
//			if(keyStr.equalsIgnoreCase("w") ){
//				moveUP();
//
//			}else if (keyStr.equalsIgnoreCase("a")){
//				moveLeft();
//			}else if (keyStr.equalsIgnoreCase("s")){
//				moveDown();
//			}else if (keyStr.equalsIgnoreCase("d")){
//				moveRight();
//			}
//	
//		}
		while(runner){

			if(getTurnOrder().isEmpty())
				finishGame();
			
			for (Iterator<Fighter> iterator = getTurnOrder().iterator() ; iterator.hasNext();) {
				Fighter fighter = iterator.next();
				fighter.getStrategy().turn(this);

				System.out.println("Turn Finished:");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Next Turn: ");
		}


	}
	
	public void moveUP(){
		System.out.println("w pressed ");
		if(getMap().move(currentLocation.getX(), currentLocation.getY(),currentLocation.getX() ,currentLocation.getY() - 1 ,this)){
		}else{
			System.out.println("Cant move UP");
		}
	}
	
	public void moveDown(){
		System.out.println("s pressed ");
		if(getMap().move(currentLocation.getX(), currentLocation.getY(),currentLocation.getX() ,currentLocation.getY() + 1 ,this)){
		}else{
			System.out.println("Cant move Down");
		}		
	}
	
	public void moveLeft(){
		System.out.println("a pressed ");
		if(getMap().move(currentLocation.getX(), currentLocation.getY(),currentLocation.getX() - 1,currentLocation.getY() ,this)){
		}else{
			System.out.println("Cant move Left");
		}
	}
	
	public void moveRight(){
		System.out.println("d pressed ");
		if(getMap().move(currentLocation.getX(), currentLocation.getY(),currentLocation.getX() + 1 ,currentLocation.getY()  ,this)){
		}else{
			System.out.println("Cant move Right");
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
	 * @return the gameView
	 */
	public GameView getGameView() {
		return gameView;
	}

	/**
	 * @param gameView the gameView to set
	 */
	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	/**
	 * @return the mapNumber
	 */
	public int getMapNumber() {
		return mapNumber;
	}

	/**
	 * @param mapNumber the mapNumber to set
	 */
	public void setMapNumber(int mapNumber) {
		this.mapNumber = mapNumber;
	}

	/**
	 * @return the runner
	 */
	public boolean isRunner() {
		return runner;
	}

	/**
	 * @param runner the runner to set
	 */
	public void setRunner(boolean runner) {
		this.runner = runner;
	}

	/**
	 * @return the fighters
	 */
	public List<Fighter> getFighters() {
		return fighters;
	}

	/**
	 * @param fighters the fighters to set
	 */
	public void setFighters(List<Fighter> fighters) {
		this.fighters = fighters;
	}

	/**
	 * @return the dice
	 */
	public Dice getDice() {
		return dice;
	}

	/**
	 * @param dice the dice to set
	 */
	public void setDice(Dice dice) {
		this.dice = dice;
	}

	/**
	 * @return the tempSort
	 */
	public List<Fighter> getTempSort() {
		return tempSort;
	}

	/**
	 * @param tempSort the tempSort to set
	 */
	public void setTempSort(List<Fighter> tempSort) {
		this.tempSort = tempSort;
	}

	/**
	 * @return the turnOrder
	 */
	public List<Fighter> getTurnOrder() {
		return turnOrder;
	}

	/**
	 * @param turnOrder the turnOrder to set
	 */
	public void setTurnOrder(List<Fighter> turnOrder) {
		this.turnOrder = turnOrder;
	}

	/**
	 * @return the ordered
	 */
	public boolean isOrdered() {
		return ordered;
	}

	/**
	 * @param ordered the ordered to set
	 */
	public void setOrdered(boolean ordered) {
		this.ordered = ordered;
	}
	
}
