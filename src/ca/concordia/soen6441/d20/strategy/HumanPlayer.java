package ca.concordia.soen6441.d20.strategy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import ca.concordia.soen6441.controller.Game;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectInstance;
import ca.concordia.soen6441.d20.item.Chest;
/**
 * One subclass of Strategy class in strategy pattern.
 * This strategy is for a player character controlled by the user. It requires user 
 * interaction for determining where the player moves, what NPC it attacks, and 
 * what other interactions it will do during a turn. 
 */
public class HumanPlayer extends Strategy {


	private Scanner scanner;
	
	public HumanPlayer(Fighter fighter) {
		super();
		setFighter(fighter);
	}
	/**
	 * each character by calling this method gets a turn to play the game
	 */
	@Override
	public void turn(Game game) {
		setCount(6);
		setCanAttack(true);
		setCanInteract(true);
		scanner  = new Scanner(System.in);
		setGame(game);
		
		while(getCount() > 0){
			System.out.println("ENTER HUMAN PLAYER LOOP: ");
			setOrigin(game.getCurrentLocation()); 
			setDestinationUp(new Location(getOrigin().getX(),getOrigin().getY()-1));
			setDestinationDown(new Location(getOrigin().getX(),getOrigin().getY()+1));
			setDestinationLeft(new Location(getOrigin().getX()-1,getOrigin().getY()));
			setDestinationRight(new Location(getOrigin().getX()+1,getOrigin().getY()));
			System.out.println("Current location : " + getOrigin().getX() + " : " + getOrigin().getY());
			setCount(getCount()-1); 
			System.out.println("counter : " + getCount());
			applyEffects();
			if(isAlive() && !isFreeze()){
				if(getCount() <= 2 ){

					if(isCanAttack()){
						attack();
					}else if(isCanInteract()){
						interact();
					}else{
						System.out.println("cant attack or move <2");
					}
				}else{

					move();
				}
				
				threadSleep();
			}else{
				if(!isAlive()){
					DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
					Date dateobj = new Date();
					Chest chest = new Chest(fighter.getName() +df.format(dateobj));
					getGame().getMap().putFromFighterToChest(getFighter(),chest);
					getGame().getMap().setGameObjectInstanceAtLocation(getOrigin(),new GameObjectInstance(chest, getGame().getMap()));
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
	 * Check to see what is in front of the character a chest or an enemy
	 * @param location in around character
	 * @return
	 */
	private boolean check(Location location){
		if(checkDestination(getGame(), location).equals("Enemy")){
			if(isCanAttack()){
				setCanAttack(false);	
			}else{
				return false;
			}
			
		}else if (checkDestination(getGame(), location).equals("Chest") || checkDestination(getGame(), location).equals("Player")){
			if(isCanInteract()){
				setCanInteract(false);
			}else {
				return false;
			}
			
		}
		return true;
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
			direction =new Location(getGame().getCurrentLocation().getX() - getFearTarget().getX(),getGame().getCurrentLocation().getY() - getFearTarget().getY());
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
			String keyStr;
			System.out.println("Please move the character: ");
			keyStr = scanner.nextLine();

			if(keyStr.equalsIgnoreCase("w") ){
				if(check(getDestinationUp()))
					getGame().moveUP();
				else {
					System.out.println("Cant attack or interact.");
				}
			}else if (keyStr.equalsIgnoreCase("a")){
				if (check(getDestinationLeft()))
					getGame().moveLeft();
				else {
					System.out.println("Cant attack or interact.");
				}
			}else if (keyStr.equalsIgnoreCase("s")){
				if (check(getDestinationDown()))
					getGame().moveDown();
				else {
					System.out.println("Cant attack or interact.");
				}
			}else if (keyStr.equalsIgnoreCase("d")){
				if(check(getDestinationRight()))
					getGame().moveRight();
				else {
					System.out.println("Cant attack or interact.");
				}
			}
		}

	}
	/**
	 * By calling this method a character attacks another character
	 */
	@Override
	public void attack() {
		String temp;
		System.out.println("attack up");
		temp = checkDestination(getGame(),getDestinationUp());
		if(temp.equals("Enemy")){
			getGame().moveUP();
			setCanAttack(false);
		}else{
			System.out.println("attack down");
			temp = checkDestination(getGame(),getDestinationDown());
			if(temp.equals("Enemy")){
				getGame().moveDown();
				setCanAttack(false);
			}else{
				System.out.println("attack left");
				temp = checkDestination(getGame(),getDestinationLeft());
				if(temp.equals("Enemy")){
					getGame().moveLeft();
					setCanAttack(false);
				}else{
					System.out.println("attack right");
					temp = checkDestination(getGame(),getDestinationRight());
					if(temp.equals("Enemy")){
						getGame().moveRight();
						setCanAttack(false);
					}else{
						System.out.println("interact");
						if(isCanInteract()){
							interact();
						}else{
							System.out.println("no interact");
						}
					}
				}
			}
		}

	}

	@Override
	public void interact() {
		String temp;
		System.out.println("interact entered");
		temp = checkDestination(getGame(),getDestinationUp());
		if(temp.equals("Player") || temp.equals("Chest")||temp.equals("Exit")){
			setCanInteract(false);
			getGame().moveUP();
		}else{
			System.out.println("null exception: "+ getDestinationDown() + ":" + temp);
			temp = checkDestination(getGame(),getDestinationDown());
			if(temp.equals("Player") || temp.equals("Chest") ||temp.equals("Exit")){
				setCanInteract(false);
				getGame().moveDown();
			}else{
				temp = checkDestination(getGame(),getDestinationLeft());
				if(temp.equals("Player") || temp.equals("Chest")||temp.equals("Exit")){
					setCanInteract(false);
					getGame().moveLeft();
				}else{
					temp = checkDestination(getGame(),getDestinationRight());
					if(temp.equals("Player") || temp.equals("Chest") || temp.equals("Exit")){
						setCanInteract(false);
						getGame().moveRight();
					}else{
						System.out.println("there is nothing  to interact with");
					}
				}
			}
		}

	}
 
}
