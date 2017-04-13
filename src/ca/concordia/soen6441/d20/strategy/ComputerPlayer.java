package ca.concordia.soen6441.d20.strategy;

import java.util.Random;

import ca.concordia.soen6441.controller.Game;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;
/**
 * 
 * One subclass of Strategy class in strategy pattern.
 * This strategy is for a player character controlled by the computer. A computer  
 * player character’s objective is to go to the next map, i.e. fulfilling any objective 
 * that you have defined to finish a map. 
 
 */
public class ComputerPlayer extends Strategy{
		
	public ComputerPlayer(Fighter fighter) {
		super();
		setFighter(fighter);
	}
	/**
	 * each character by calling this method gets a turn to play the game
	 */
	@Override
	public void turn(Game game) {
		setCount(6);
		setMoveCounter(0);
		setCanAttack(true);
		setCanInteract(true);
		setGame(game);
		
		while(count > 0){
			System.out.println("ENTER COMPUTER PLAYER LOOP: ");
			setOrigin(game.getCurrentLocation());
			System.out.println("Current location of NPC : " + getOrigin().getX() + " : " + getOrigin().getY());
			setCount(getCount() - 1);
			applyEffects();
			
			if(isAlive() && !isFreeze()){
				if(getMoveCounter() !=3)
					move();
				else {
					System.out.println("COMPUTER Waiting: ");
				}
			}else{
				if(isAlive()){
					game.getGameView().removeDeadFighter(getOrigin());
					System.out.println("Fighter: "+ getFighter().getName() + " Dies because of weapon effects" );
				}
				
				if(isFreeze()){
					System.out.println("FREEZED");
				}
				
				break;
			}
		}
		
	}
	/**
	 * This is a method used to implement character's movement
	 */
	@Override
	public void move() {
		if(isFear()){
			System.out.println("FEARED: ");
			setMoveCounter(getMoveCounter() + 1);
			Location direction;
			direction =new Location(getOrigin().getX() - getFearTarget().getX(),getOrigin().getY() - getFearTarget().getY());
			double magnetude  = Math.sqrt( Math.pow(direction.getX(),2) + Math.pow(direction.getY(), 2) );
			direction.setX((int) (direction.getX() / magnetude));
			direction.setY((int) (direction.getY()/magnetude));
			
			System.out.println("direction:" + direction.getX() +" : "+ direction.getY());
			if(direction.getX() != 0 && direction.getY() != 0){
				System.out.println("BOTH NOT ZERO");
				boolean rand = new Random().nextBoolean();
				if(rand){
					System.out.println("X DIR");
					direction.setX(0);
				}else {
					System.out.println("Y DIR");
					direction.setY(0);
				}
	       			getGame().moveDirection(getOrigin(), direction,false);
			}else if (direction.getX() == 0 && direction.getY() == 0){
				System.out.println("BOTH ZERO");
				int rand = new Random().nextInt(4);
				System.out.println("RANDOM DIR FOR AI: "+ rand);
				if(rand ==0){
					getGame().moveUP();
				}else if (rand == 1){
					getGame().moveDown();
				}else if (rand == 2){
					getGame().moveLeft();
				}else if (rand == 3){
					getGame().moveRight();
				}
			}else{
				System.out.println("NORMAL MOVE OF AI");
					getGame().moveDirection(getOrigin(), direction,false);
			}
			threadSleep();
		}else{
			setMoveCounter(getMoveCounter() + 1);
			Location direction;
			direction =new Location(getGame().getExit().getX() - getOrigin().getX(),getGame().getExit().getY() - getOrigin().getY());
			double magnetude  = Math.sqrt( Math.pow(direction.getX(),2) + Math.pow(direction.getY(), 2) );
			direction.setX((int) (direction.getX() / magnetude));
			direction.setY((int) (direction.getY()/magnetude));
			
			System.out.println("direction:" + direction.getX() +" : "+ direction.getY());
			if(direction.getX() != 0 && direction.getY() != 0){
				System.out.println("BOTH NOT ZERO");
				boolean rand = new Random().nextBoolean();
				if(rand){
					System.out.println("X DIR");
					direction.setX(0);
				}else {
					System.out.println("Y DIR");
					direction.setY(0);
				}
	       			getGame().moveDirection(getOrigin(), direction,false);
			}else if (direction.getX() == 0 && direction.getY() == 0){
				System.out.println("BOTH ZERO");
				int rand = new Random().nextInt(4);
				System.out.println("RANDOM DIR FOR AI: "+ rand);
				if(rand ==0){
					getGame().moveUP();
				}else if (rand == 1){
					getGame().moveDown();
				}else if (rand == 2){
					getGame().moveLeft();
				}else if (rand == 3){
					getGame().moveRight();
				}
			}else{
				System.out.println("NORMAL MOVE OF AI");
					getGame().moveDirection(getOrigin(), direction,false);
			}
			threadSleep();
		}
	
		
	}
	
//	private void check(Location direction){
//		if(checkDestination(getGame(), new Location(getOrigin().getX()+direction.getX(),getOrigin().getY()+direction.getY())).equals("Enemy")){
//			setCanAttack(false);
//		}
//		
//		if(checkDestination(getGame(), new Location(getOrigin().getX()+direction.getX(),getOrigin().getY()+direction.getY())).equals("Chest") ||checkDestination(getGame(), new Location(getOrigin().getX()+direction.getX(),getOrigin().getY()+direction.getY())).equals("Player") ){
//			setCanInteract(false);
//		}
//	}
	/**
	 * By calling this method a character attacks another character
	 */
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interact() {
		// TODO Auto-generated method stub
		
	}
}
