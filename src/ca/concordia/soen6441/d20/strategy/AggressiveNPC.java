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
			setOrigin(game.getLocations().get(getFighter())); 
			setDestinationUp(new Location(getOrigin().getX(),getOrigin().getY()-1));
			setDestinationDown(new Location(getOrigin().getX(),getOrigin().getY()+1));
			setDestinationLeft(new Location(getOrigin().getX()-1,getOrigin().getY()));
			setDestinationRight(new Location(getOrigin().getX()+1,getOrigin().getY()));
			System.out.println("Current location of NPC : " + getOrigin().getX() + " : " + getOrigin().getY());
			setCount(getCount() -1);	
			applyEffects();
			
			if(isAlive() && !isFreeze()){
				attack();
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
				getGame().moveDirection(getOrigin(), direction);
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
				getGame().moveDirection(getOrigin(), direction);
			}
			threadSleep();
		}else{
			setMoveCounter(getMoveCounter() + 1); 
			Location direction;
			direction =new Location(getGame().getCurrentLocation().getX() - getOrigin().getX(),getGame().getCurrentLocation().getY() - getOrigin().getY());
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
				getGame().moveDirection(getOrigin(), direction);
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
				getGame().moveDirection(getOrigin(), direction);
			}
			threadSleep();	
		}

	}

	@Override
	public void attack() {
		if(checkDestination(getGame(), getDestinationUp()).equals("Player")){
			getGame().moveUP(getOrigin());
			threadSleep();
			setCanInteract(false);
			setCount(0);
		}else if (checkDestination(getGame(), getDestinationDown()).equalsIgnoreCase("Player")){
			getGame().moveDown(getOrigin());
			threadSleep();
			setCanInteract(false);
			setCount(0);
		}else if (checkDestination(getGame(), getDestinationLeft()).equalsIgnoreCase("Player")) {
			getGame().moveLeft(getOrigin());
			threadSleep();
			setCanInteract(false);
			setCount(0);
		}else if (checkDestination(getGame(), getDestinationRight()).equalsIgnoreCase("Player")) {
			getGame().moveRight(getOrigin());
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
		if(checkDestination(getGame(), getDestinationUp()).equals("Chest") ||checkDestination(getGame(), getDestinationUp()).equals("Enemy") ){
			getGame().moveUP(getOrigin());
			threadSleep();
			setCanInteract(false);
		}else if (checkDestination(getGame(), getDestinationDown()).equalsIgnoreCase("Chest") || checkDestination(game, getDestinationDown()).equals("Enemy")){
			getGame().moveDown(getOrigin());
			threadSleep();
			setCanInteract(false);
		}else if (checkDestination(getGame(), getDestinationLeft()).equalsIgnoreCase("Chest") || checkDestination(getGame(), getDestinationLeft()).equals("Enemy")) {
			getGame().moveLeft(getOrigin());
			threadSleep();
			setCanInteract(false);
		}else if (checkDestination(getGame(), getDestinationRight()).equalsIgnoreCase("Chest") || checkDestination(getGame(), getDestinationRight()).equals("Enemy")) {
			getGame().moveRight(getOrigin());
			threadSleep();
			setCanInteract(false);
		}else if (getMoveCounter() != 3){
			move();
		}else{
			System.out.println("AI IS IN ALERT MODE");
		}
		
	}
}
