package ca.concordia.soen6441.d20.gamePlay;

import java.util.ArrayList;
import java.util.List;

import ca.concordia.soen6441.d20.campaign.Campaign;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.element.Entery;
import ca.concordia.soen6441.d20.gamemap.element.Exit;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.Wall;
import ca.concordia.soen6441.d20.item.Chest;
import ca.concordia.soen6441.d20.item.IRoot;

public class Game {
	private Campaign campaign;
	private Fighter fighter;
	private List<Fighter> enemies;
	private List<Fighter> friends;
	private List<Chest> chests;
	private List<Wall> walls;
	private Exit exit;
	private Entery entery;
	
	public Game(Campaign campaign, Fighter fighter) {
		setCampaign(campaign);
		setFighter(fighter);
		setEnemies(new ArrayList<>());
		setFriends(new ArrayList<>());
		setChests(new ArrayList<>());
		setWalls(new ArrayList<>());
		initializeAndShow();
	}
	
	public void gameLoop(){

		while(true){
			
		}
	}
	
	public void initializeAndShow(){
		GameMap map = getCampaign().getMaps().get(0);
		
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
				}
				
				System.out.print(" ");
				if(j == map.getWidth() - 1 ){
					System.out.println("");
				}
			}
		}
	}
	
	public void adaptToCharacter(){
		int level = fighter.getLevel();
		
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
	
	public void showCharacteristics(Fighter fighter){
		
	}
	
	public void root(IRoot root){
		
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
	 * @return the entery
	 */
	public Entery getEntery() {
		return entery;
	}

	/**
	 * @param entery the entery to set
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
}
