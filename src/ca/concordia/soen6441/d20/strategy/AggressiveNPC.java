package ca.concordia.soen6441.d20.strategy;

import java.util.Random;

import ca.concordia.soen6441.controller.Game;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;

public class AggressiveNPC extends Strategy{
	
	
	public AggressiveNPC(Fighter fighter) {
		super();
		setFighter(fighter);
		System.out.println("AGGRESIVE PLAYER CREATED: ");
	}
	@Override
	public void turn(Game game) {
		setCount(6);
		setMoveCounter(0);
		setCanInteract(true);
		setGame(game);

		
		while(count > 0){
			System.out.println("ENTER AGGRESIVE PLAYER LOOP: ");
			setOrigin(game.getLocations().get(fighter)); 
			setDestinationUp(new Location(origin.getX(),origin.getY()-1));
			setDestinationDown(new Location(origin.getX(),origin.getY()+1));
			setDestinationLeft(new Location(origin.getX()-1,origin.getY()));
			setDestinationRight(new Location(origin.getX()+1,origin.getY()));
			System.out.println("Current location of NPC : " + getOrigin().getX() + " : " + getOrigin().getY());
			setCount(getCount() -1);			
			attack();

		}
		
	}
		
	

	@Override
	public void move() {
		setMoveCounter(getMoveCounter() + 1); 
		Location direction;
		direction =new Location(game.getCurrentLocation().getX() - getOrigin().getX(),game.getCurrentLocation().getY() - getOrigin().getY());
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
			game.moveDirection(getOrigin(), direction);
		}else if (direction.getX() == 0 && direction.getY() == 0){
			System.out.println("BOTH ZERO");
			int rand = new Random().nextInt(4);
			System.out.println("RANDOM DIR FOR AI: "+ rand);
			if(rand ==0){
				game.moveUP(getOrigin());
			}else if (rand == 1){
				game.moveDown(getOrigin());
			}else if (rand == 2){
				game.moveLeft(getOrigin());
			}else if (rand == 3){
				game.moveRight(getOrigin());
			}
		}else{
			System.out.println("NORMAL MOVE OF AI");
			game.moveDirection(getOrigin(), direction);
		}
		threadSleep();
	}

	@Override
	public void attack() {
		if(checkDestination(game, getDestinationUp()).equals("Player")){
			game.moveUP(getOrigin());
			threadSleep();
			setCanInteract(false);
			setCount(0);
		}else if (checkDestination(game, getDestinationDown()).equalsIgnoreCase("Player")){
			game.moveDown(getOrigin());
			threadSleep();
			setCanInteract(false);
			setCount(0);
		}else if (checkDestination(game, getDestinationLeft()).equalsIgnoreCase("Player")) {
			game.moveLeft(getOrigin());
			threadSleep();
			setCanInteract(false);
			setCount(0);
		}else if (checkDestination(game, getDestinationRight()).equalsIgnoreCase("Player")) {
			game.moveRight(getOrigin());
			threadSleep();
			setCanInteract(false);
			setCount(0);
		}else if (isCanInteract()){
			interact();
		}else if (getMoveCounter() != 3){
			move();
		}else{
			System.out.println("AI Enter ALERT MODE: ");
		}
	}

	@Override
	public void interact() {
		if(checkDestination(game, getDestinationUp()).equals("Chest") ||checkDestination(game, getDestinationUp()).equals("Enemy") ){
			game.moveUP(getOrigin());
			threadSleep();
			canInteract = false;
		}else if (checkDestination(game, getDestinationDown()).equalsIgnoreCase("Chest") || checkDestination(game, getDestinationDown()).equals("Enemy")){
			game.moveDown(getOrigin());
			threadSleep();
			canInteract = false;
		}else if (checkDestination(game, getDestinationLeft()).equalsIgnoreCase("Chest") || checkDestination(game, getDestinationLeft()).equals("Enemy")) {
			game.moveLeft(getOrigin());
			threadSleep();
			canInteract = false;
		}else if (checkDestination(game, getDestinationRight()).equalsIgnoreCase("Chest") || checkDestination(game, getDestinationRight()).equals("Enemy")) {
			game.moveRight(getOrigin());
			threadSleep();
			canInteract = false;
		}else if (getMoveCounter() != 3){
			move();
		}else{
			System.out.println("AI IS IN ALERT MODE");
		}
		
	}
}
