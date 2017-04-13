package ca.concordia.soen6441.d20.strategy;

import java.util.Random;

import ca.concordia.soen6441.controller.Game;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;
/**
 * One subclass of Strategy class in strategy pattern.
 * This strategy is for friendly NPCs. A friendly NPC will wander around the map 
 * randomly. If it comes near a chest while moving, it will loot it. If a character using 
 * the friendly strategy is attacked, it will change its strategy and become 
 * aggressive.  
 *
 */
public class FriendlyNPC extends Strategy{
	

	
	public FriendlyNPC(Fighter fighter) {
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
			System.out.println("ENTER Friendly PLAYER LOOP: ");
			setOrigin(game.getLocations().get(getFighter()));
			setDestinationUp(new Location(origin.getX(),origin.getY()-1));
			setDestinationDown(new Location(origin.getX(),origin.getY()+1));
			setDestinationLeft(new Location(origin.getX()-1,origin.getY()));
			setDestinationRight(new Location(origin.getX()+1,origin.getY()));
			System.out.println("Current location of FNPC : " + getOrigin().getX() + " : " + getOrigin().getY());
			setCount(getCount() - 1);
			
			applyEffects();
			
			if(isAlive() && !isFreeze()){
				if(isCanInteract()){
					interact();
				}else if (isCanAttack()){
					attack();
				}else if(getMoveCounter() !=3){
					move();
				}else {
					System.out.println("FNPC Waiting: ");
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
	       			getGame().moveDirection(getOrigin(), direction,true);
			}else if (direction.getX() == 0 && direction.getY() == 0){
				System.out.println("BOTH ZERO");
				int rand = new Random().nextInt(4);
				System.out.println("RANDOM DIR FOR AI: "+ rand);
				if(rand ==0){
					getGame().moveUP(getOrigin());
				}else if (rand == 1){
					getGame().moveDown(getOrigin());
				}else if (rand == 2){
					getGame().moveLeft(getOrigin());
				}else if (rand == 3){
					getGame().moveRight(getOrigin());
				}
			}else{
				System.out.println("NORMAL MOVE OF AI");
					getGame().moveDirection(getOrigin(), direction,true);
			}
			threadSleep();
			
		}else {
			setMoveCounter(getMoveCounter() + 1);
			
			if(checkDestination(getGame(), destinationUp).equals("Ground")){
				getGame().moveUP(getOrigin());
			}else if (checkDestination(getGame(), destinationDown).equals("Ground")){
				getGame().moveDown(getOrigin());
			}else if(checkDestination(getGame(), destinationLeft).equals("Ground")){
				getGame().moveLeft(getOrigin());
			}else if (checkDestination(getGame(), destinationRight).equals("Ground")){
				getGame().moveRight(getOrigin());
			}else {
				System.out.println("Friendly out move and waiting");
			}
		}

	}
	/**
	 * By calling this method a character attacks another character
	 */
	@Override
	public void attack() {
		if(checkDestination(game, getDestinationUp()).equalsIgnoreCase("Enemy")){
			getGame().moveUP(getOrigin());
			setCanAttack(false);
			threadSleep();
		}else if (checkDestination(game, getDestinationDown()).equalsIgnoreCase("Enemy")){
			getGame().moveDown(getOrigin());
			setCanAttack(false);
			threadSleep();
		}else if (checkDestination(game, getDestinationLeft()).equalsIgnoreCase("Enemy")){
			getGame().moveLeft(getOrigin());
			setCanAttack(false);
			threadSleep();
		}else if (checkDestination(game, getDestinationRight()).equalsIgnoreCase("Enemy")){
			getGame().moveRight(getOrigin());
			setCanAttack(false);
			threadSleep();
		}else if(getMoveCounter() !=3){
			move();
		}else {
			System.out.println("Friendly out of move");
		}
		
	}

	@Override
	public void interact() {
		if(checkDestination(game, getDestinationUp()).equalsIgnoreCase("Chest")){
			getGame().moveUP(getOrigin());
			setCanInteract(false);
			threadSleep();
		}else if (checkDestination(game, getDestinationDown()).equalsIgnoreCase("Chest")){
			getGame().moveDown(getOrigin());
			setCanInteract(false);
			threadSleep();
		}else if (checkDestination(game, getDestinationLeft()).equalsIgnoreCase("Chest")){
			getGame().moveLeft(getOrigin());
			setCanInteract(false);
			threadSleep();
		}else if (checkDestination(game, getDestinationRight()).equalsIgnoreCase("Chest")){
			getGame().moveRight(getOrigin());
			setCanInteract(false);
			threadSleep();
		}else if (isCanAttack()){
			attack();
		}else if (getMoveCounter() !=3){
			move();
		}
		
	}
}
