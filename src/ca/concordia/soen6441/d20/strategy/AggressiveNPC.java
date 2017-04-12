package ca.concordia.soen6441.d20.strategy;

import java.util.Random;

import ca.concordia.soen6441.controller.Game;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;

public class AggressiveNPC implements Strategy{
	
	private Fighter fighter;
	private Game game;
	private Location destinationUp;
	private Location destinationDown;
	private Location destinationLeft;
	private Location destinationRight;
	private Location origin;
	private int moveCounter ;
	private boolean canInteract ;
	private int count;
	
	public AggressiveNPC(Fighter fighter) {
		setFighter(fighter);
		System.out.println("AGGRESIVE PLAYER CREATED: ");
	}
	@Override
	public void turn(Game game) {
		count = 6;
		moveCounter = 0;
		canInteract = true;
		setGame(game);

		
		while(count > 0){
			System.out.println("ENTER AGGRESIVE PLAYER LOOP: ");
			origin = game.getLocations().get(fighter);
			destinationUp = new Location(origin.getX(),origin.getY()-1);
			destinationDown = new Location(origin.getX(),origin.getY()+1);
			destinationLeft = new Location(origin.getX()-1,origin.getY());
			destinationRight = new Location(origin.getX()+1,origin.getY());
			System.out.println("Current location of NPC : " + origin.getX() + " : " + origin.getY());
			count --;
			
			attack();

		}
		
	}
		
	

	@Override
	public void move() {
		moveCounter ++;
		Location direction;
		direction =new Location(game.getCurrentLocation().getX() - origin.getX(),game.getCurrentLocation().getY() - origin.getY());
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
			game.moveDirection(origin, direction);
		}else if (direction.getX() == 0 && direction.getY() == 0){
			System.out.println("BOTH ZERO");
			int rand = new Random().nextInt(4);
			System.out.println("RANDOM DIR FOR AI: "+ rand);
			if(rand ==0){
				game.moveUP(origin);
			}else if (rand == 1){
				game.moveDown(origin);
			}else if (rand == 2){
				game.moveLeft(origin);
			}else if (rand == 3){
				game.moveRight(origin);
			}
		}else{
			System.out.println("NORMAL MOVE OF AI");
			game.moveDirection(origin, direction);
		}
		threadSleep();
	}

	@Override
	public void attack() {
		if(checkDestination(game, destinationUp).equals("Player")){
			game.moveUP(origin);
			threadSleep();
			count = 0;
		}else if (checkDestination(game, destinationDown).equalsIgnoreCase("Player")){
			game.moveDown(origin);
			threadSleep();
			count = 0;
		}else if (checkDestination(game, destinationLeft).equalsIgnoreCase("Player")) {
			game.moveLeft(origin);
			threadSleep();
			count = 0;
		}else if (checkDestination(game, destinationRight).equalsIgnoreCase("Player")) {
			game.moveRight(origin);
			threadSleep();
			count = 0;
		}else if (canInteract){
			interact();
		}else if (moveCounter != 3){
			move();
		}else{
			System.out.println("AI Enter ALERT MODE: ");
		}
	}

	@Override
	public void interact() {
		if(checkDestination(game, destinationUp).equals("Chest") ||checkDestination(game, destinationUp).equals("Enemy") ){
			game.moveUP(origin);
			threadSleep();
			canInteract = false;
		}else if (checkDestination(game, destinationDown).equalsIgnoreCase("Chest") || checkDestination(game, destinationDown).equals("Enemy")){
			game.moveDown(origin);
			threadSleep();
			canInteract = false;
		}else if (checkDestination(game, destinationLeft).equalsIgnoreCase("Chest") || checkDestination(game, destinationLeft).equals("Enemy")) {
			game.moveLeft(origin);
			threadSleep();
			canInteract = false;
		}else if (checkDestination(game, destinationRight).equalsIgnoreCase("Chest") || checkDestination(game, destinationRight).equals("Enemy")) {
			game.moveRight(origin);
			threadSleep();
			canInteract = false;
		}else if (moveCounter != 3){
			move();
		}else{
			System.out.println("AI IS IN ALERT MODE");
		}
		
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
