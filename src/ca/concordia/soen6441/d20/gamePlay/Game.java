package ca.concordia.soen6441.d20.gamePlay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ca.concordia.soen6441.d20.campaign.Campaign;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.element.Entery;
import ca.concordia.soen6441.d20.gamemap.element.Exit;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.Wall;
import ca.concordia.soen6441.d20.item.Chest;
import ca.concordia.soen6441.view.map.GameView;
import ca.concordia.soen6441.view.map.viewElement.ViewExchange;

public class Game implements Runnable {
	private Campaign campaign;
	private Fighter fighter;
	private List<Fighter> enemies;
	private List<Fighter> friends;
	private List<Chest> chests;
	private List<Wall> walls;
	private Exit exit;
	private Entery entery;
	private Location currentLocation;
	private Scanner scanner;
	private GameMap map;
	
	public Game(Campaign campaign, Fighter fighter) {
		setCampaign(campaign);
		setFighter(fighter);
		setEnemies(new ArrayList<>());
		setFriends(new ArrayList<>());
		setChests(new ArrayList<>());
		setWalls(new ArrayList<>());
		initializeAndShow();
	}
	
	public void initializeAndShow(){
		setMap(getCampaign().getMaps().get(0));
		
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
					getEnemies().add((Fighter)reference);
				}else if (reference.getTag().equals("Player") ){
					System.out.print("F");//friendly
					getFriends().add((Fighter)reference);
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
		
		GameView gameView =new GameView(getMap().getHeight(),getMap().getWidth(),this);
		adaptToCharacter();
		getMap().addObserver(gameView);
		gameView.load(getMap(),getFighter());
	}
	
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
	
	public void createViewExchange(Fighter exchangeFighter,Fighter fighter){
		ViewExchange viewExchange = new ViewExchange(fighter,exchangeFighter);
		exchangeFighter.addObserver(viewExchange);
		fighter.addObserver(viewExchange);
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
		return enemies;
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
		
		scanner  = new Scanner(System.in);
		String keyStr;
		while(true){
			System.out.println("Please move the character: ");
			keyStr = scanner.nextLine();
			
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				break;			}
			
			if(keyStr.equalsIgnoreCase("w") ){
				System.out.println("w pressed ");
				System.out.println("X:" + currentLocation.getX() + "Y:" + currentLocation.getY());
				if(getMap().move(currentLocation.getX(), currentLocation.getY(),currentLocation.getX() ,currentLocation.getY() - 1 ,this)){
					System.out.println("new X:" + currentLocation.getX() + "new Y:" + currentLocation.getY());
				}else{
					System.out.println("Cant move UP");
				}

			}else if (keyStr.equalsIgnoreCase("a")){
				System.out.println("a pressed ");
				if(getMap().move(currentLocation.getX(), currentLocation.getY(),currentLocation.getX() - 1,currentLocation.getY() ,this)){
					System.out.println("new X:" + currentLocation.getX() + "new Y:" + currentLocation.getY());
				}else{
					System.out.println("Cant move Left");
				}
				
			}else if (keyStr.equalsIgnoreCase("s")){
				System.out.println("s pressed ");
				if(getMap().move(currentLocation.getX(), currentLocation.getY(),currentLocation.getX() ,currentLocation.getY() + 1 ,this)){
					System.out.println("new X:" + currentLocation.getX() + "new Y:" + currentLocation.getY());
				}else{
					System.out.println("Cant move Down");
				}
			}else if (keyStr.equalsIgnoreCase("d")){
				System.out.println("d pressed ");
				if(getMap().move(currentLocation.getX(), currentLocation.getY(),currentLocation.getX() + 1 ,currentLocation.getY()  ,this)){
					System.out.println("new X:" + currentLocation.getX() + "new Y:" + currentLocation.getY());
				}else{
					System.out.println("Cant move Right");
				}
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
	
}
