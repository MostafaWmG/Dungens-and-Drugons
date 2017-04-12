package ca.concordia.soen6441.d20.strategy;

import java.util.Scanner;

import ca.concordia.soen6441.controller.Game;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;

public class HumanPlayer extends Strategy {


	private Scanner scanner;
	
	public HumanPlayer(Fighter fighter) {
		super();
		setFighter(fighter);
	}

	@Override
	public void turn(Game game) {
		setCount(6);
		setCanAttack(true);
		setCanInteract(true);
		scanner  = new Scanner(System.in);
		setGame(game);
		
		while(getCount() > 0){
			setOrigin(game.getCurrentLocation()); 
			setDestinationUp(new Location(getOrigin().getX(),getOrigin().getY()-1));
			setDestinationDown(new Location(getOrigin().getX(),getOrigin().getY()+1));
			setDestinationLeft(new Location(getOrigin().getX()-1,getOrigin().getY()));
			setDestinationRight(new Location(getOrigin().getX()+1,getOrigin().getY()));
			System.out.println("Current location : " + getOrigin().getX() + " : " + getOrigin().getY());
			setCount(getCount()-1); 
			System.out.println("counter : " + getCount());
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

		}
		
	}
	
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
	@Override
	public void move() {
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

	@Override
	public void attack() {
		String temp;
		System.out.println("attack up");
		temp = checkDestination(getGame(),getDestinationUp());
		if(temp.equals("Enemy")){
			getGame().moveUP();
		}else{
			System.out.println("attack down");
			temp = checkDestination(getGame(),getDestinationDown());
			if(temp.equals("Enemy")){
				getGame().moveDown();
			}else{
				System.out.println("attack left");
				temp = checkDestination(getGame(),getDestinationLeft());
				if(temp.equals("Enemy")){
					getGame().moveLeft();
				}else{
					System.out.println("attack right");
					temp = checkDestination(getGame(),getDestinationRight());
					if(temp.equals("Enemy")){
						getGame().moveRight();
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
