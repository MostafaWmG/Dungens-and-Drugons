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
		int count = 6;
		canAttack = true;
		canInteract = true;
		scanner  = new Scanner(System.in);
		setGame(game);
		
		while(count > 0){
			origin = getGame().getCurrentLocation();
			destinationUp = new Location(origin.getX(),origin.getY()-1);
			destinationDown = new Location(origin.getX(),origin.getY()+1);
			destinationLeft = new Location(origin.getX()-1,origin.getY());
			destinationRight = new Location(origin.getX()+1,origin.getY());
			System.out.println("Current location : " + origin.getX() + " : " + origin.getY());
			count --;
			System.out.println("counter : " + count);
			if(count <= 2 ){
				
				if(canAttack){
					attack();
				}else if(canInteract){
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
		if(checkDestination(game, location).equals("Enemy")){
			if(canAttack){
				canAttack = false;	
			}else{
				return false;
			}
			
		}else if (checkDestination(game, location).equals("Chest") || checkDestination(game, location).equals("Player")){
			if(canInteract){
				canInteract = false;	
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
			if(check(destinationUp))
				getGame().moveUP();
			else {
				System.out.println("Cant attack or interact.");
			}
		}else if (keyStr.equalsIgnoreCase("a")){
			if (check(destinationLeft))
				getGame().moveLeft();
			else {
				System.out.println("Cant attack or interact.");
			}
		}else if (keyStr.equalsIgnoreCase("s")){
			if (check(destinationDown))
				getGame().moveDown();
			else {
				System.out.println("Cant attack or interact.");
			}
		}else if (keyStr.equalsIgnoreCase("d")){
			if(check(destinationRight))
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
		temp = checkDestination(game,destinationUp);
		if(temp.equals("Enemy")){
			getGame().moveUP();
		}else{
			System.out.println("attack down");
			temp = checkDestination(game,destinationDown);
			if(temp.equals("Enemy")){
				getGame().moveDown();
			}else{
				System.out.println("attack left");
				temp = checkDestination(game,destinationLeft);
				if(temp.equals("Enemy")){
					getGame().moveLeft();
				}else{
					System.out.println("attack right");
					temp = checkDestination(game,destinationRight);
					if(temp.equals("Enemy")){
						getGame().moveRight();
					}else{
						System.out.println("interact");
						if(canInteract){
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
		temp = checkDestination(game,destinationUp);
		if(temp.equals("Player") || temp.equals("Chest")||temp.equals("Exit")){
			canInteract = false;
			getGame().moveUP();
		}else{
			System.out.println("null exception: "+ destinationDown + ":" + temp);
			temp = checkDestination(game,destinationDown);
			if(temp.equals("Player") || temp.equals("Chest") ||temp.equals("Exit")){
				canInteract = false;
				getGame().moveDown();
			}else{
				temp = checkDestination(game,destinationLeft);
				if(temp.equals("Player") || temp.equals("Chest")||temp.equals("Exit")){
					canInteract = false;
					getGame().moveLeft();
				}else{
					temp = checkDestination(game,destinationRight);
					if(temp.equals("Player") || temp.equals("Chest") || temp.equals("Exit")){
						canInteract = false;
						getGame().moveRight();
					}else{
						System.out.println("there is nothing  to interact with");
					}
				}
			}
		}

	}
 
}
