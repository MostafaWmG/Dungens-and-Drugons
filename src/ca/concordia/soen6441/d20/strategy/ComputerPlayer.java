package ca.concordia.soen6441.d20.strategy;

import java.util.Random;

import ca.concordia.soen6441.controller.Game;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;

public class ComputerPlayer extends Strategy{
		
	public ComputerPlayer(Fighter fighter) {
		super();
		setFighter(fighter);
	}
	
	@Override
	public void turn(Game game) {
		setCount(6);
		setMoveCounter(0);
		setGame(game);
		
		while(count > 0){
			System.out.println("ENTER COMPUTER PLAYER LOOP: ");
			setOrigin(game.getCurrentLocation());
			System.out.println("Current location of NPC : " + getOrigin().getX() + " : " + getOrigin().getY());
			setCount(getCount() - 1);
			
			if(getMoveCounter() !=3)
				move();
			else {
				System.out.println("COMPUTER Waiting: ");
			}

		}
		
	}

	@Override
	public void move() {
		setMoveCounter(getMoveCounter() + 1);
		Location direction;
		direction =new Location(game.getExit().getX() - getOrigin().getX(),game.getExit().getY() - getOrigin().getY());
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
			game.moveDirection(getOrigin(), direction,true);
		}else if (direction.getX() == 0 && direction.getY() == 0){
			System.out.println("BOTH ZERO");
			int rand = new Random().nextInt(4);
			System.out.println("RANDOM DIR FOR AI: "+ rand);
			if(rand ==0){
				game.moveUP();
			}else if (rand == 1){
				game.moveDown();
			}else if (rand == 2){
				game.moveLeft();
			}else if (rand == 3){
				game.moveRight();
			}
		}else{
			System.out.println("NORMAL MOVE OF AI");
			game.moveDirection(getOrigin(), direction,true);
		}
		threadSleep();
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interact() {
		// TODO Auto-generated method stub
		
	}
}
