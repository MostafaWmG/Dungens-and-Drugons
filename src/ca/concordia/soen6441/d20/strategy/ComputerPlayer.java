package ca.concordia.soen6441.d20.strategy;

import ca.concordia.soen6441.controller.Game;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;

public class ComputerPlayer implements Strategy{
	
	private Fighter fighter;
	private Game game;
	
	public ComputerPlayer(Fighter fighter) {
		setFighter(fighter);
	}
	
	@Override
	public void turn(Game game) {
		int count = 3;
		setGame(game);
		Location origin = getGame().getCurrentLocation();
		Location destinationUp = new Location(origin.getX(),origin.getY()-1);
		Location destinationDown = new Location(origin.getX(),origin.getY()+1);
		Location destinationLeft = new Location(origin.getX()-1,origin.getY());
		Location destinationRight = new Location(origin.getX()+1,origin.getY());
		
		while(count > 0){
			count --;
			if(checkDestination(game, destinationUp).equals("Enemy")){
				attack(destinationUp);
				threadSleep();
			}else if(checkDestination(game, destinationUp).equals("Ground")){
				
			}
		}
		
	}

	@Override
	public void move(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interact(Location location) {
		// TODO Auto-generated method stub
		
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
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

}
