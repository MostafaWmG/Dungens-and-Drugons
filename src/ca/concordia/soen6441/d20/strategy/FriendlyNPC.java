package ca.concordia.soen6441.d20.strategy;

import ca.concordia.soen6441.controller.Game;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;

public class FriendlyNPC extends Strategy{
	

	
	public FriendlyNPC(Fighter fighter) {
		super();
		setFighter(fighter);
	}
	
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

	@Override
	public void move() {
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
