package ca.concordia.soen6441.d20.strategy;

import ca.concordia.soen6441.controller.Game;
import ca.concordia.soen6441.d20.common.Location;

public interface Strategy {
	
	public void turn(Game game);
	
	public void move(Location location);
	
	public void attack(Location location);
	
	public void interact(Location location);
	
	public default String checkDestination(Game game,Location destination){
		if(! game.getMap().moveCanBeDone(game.getCurrentLocation().getX(), game.getCurrentLocation().getY(), destination.getX(), destination.getY())) {
//			System.out.println("out of map");
			return "null";
		}else{
			System.out.println("we can go");
		}
//		System.out.println("error:"+ game.getMap().getGameObjectInstanceAtLocation(destination).getGameObject());
		if(game.getMap().getGameObjectInstanceAtLocation(destination).getGameObject().getTag().equals("Ground"))
			return "Ground";
		else if(game.getMap().getGameObjectInstanceAtLocation(destination).getGameObject().getTag().equals("Wall"))
			return "Wall";
		else if(game.getMap().getGameObjectInstanceAtLocation(destination).getGameObject().getTag().equals("Exit"))
			return "Exit";
		else if(game.getMap().getGameObjectInstanceAtLocation(destination).getGameObject().getTag().equals("Enemy"))
			return "Enemy";
		else if(game.getMap().getGameObjectInstanceAtLocation(destination).getGameObject().getTag().equals("Player"))
			return "Player";
		else {
			return "Chest";
		}
	}
	
	public default void threadSleep()
	{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
