package ca.concordia.soen6441.d20.strategy;

import ca.concordia.soen6441.controller.Game;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;

public abstract class Strategy {
	
	
	protected Fighter fighter;
	protected Game game;
	protected Location destinationUp;
	protected Location destinationDown;
	protected Location destinationLeft;
	protected Location destinationRight;
	protected Location origin;
	protected int moveCounter ;
	protected boolean canInteract ;
	protected boolean canAttack ;
	protected int count;
	protected boolean isAlive;
	protected int burnTimes;
	protected int burnDamage;
	protected int freezeTimes;
	protected boolean freeze;
	
	public Strategy() {
		setAlive(true);
		setFreeze(false);
	}
	
	public abstract void turn(Game game);
	
	public abstract void move();
	
	public abstract void attack();
	
	public abstract void interact();
	
	public String checkDestination(Game game,Location destination){
		if(! game.getMap().moveCanBeDone(game.getCurrentLocation().getX(), game.getCurrentLocation().getY(), destination.getX(), destination.getY())) {
			System.out.println("out of map");
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
	
	protected void applyEffects(){
		if(getBurnTimes() > 0){
			setBurnTimes(getBurnTimes() - 1);
			setAlive(!getFighter().takeDamge(getBurnDamage()));
		}
		
		if(getFreezeTimes() > 0 ){
			setFreezeTimes(getFreezeTimes() - 1 );
		}else{
			setFreeze(false);
		}
	}
	
	public void activeBuringEffect(int burnDamge){
		setBurnTimes(3);
		setBurnDamage(burnDamge);
	}
	
	public void activeSlayingEffect()
	{
		setAlive(false);
	}
	
	public void activeFreezeEffect(int freezeTimes){
		setFreezeTimes(freezeTimes);
		setFreeze(true);
	}
	
	public void setSlayingEffect(){
		
	}
	
	public void threadSleep()
	{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	/**
	 * @return the destinationUp
	 */
	public Location getDestinationUp() {
		return destinationUp;
	}
	/**
	 * @param destinationUp the destinationUp to set
	 */
	public void setDestinationUp(Location destinationUp) {
		this.destinationUp = destinationUp;
	}
	/**
	 * @return the destinationDown
	 */
	public Location getDestinationDown() {
		return destinationDown;
	}
	/**
	 * @param destinationDown the destinationDown to set
	 */
	public void setDestinationDown(Location destinationDown) {
		this.destinationDown = destinationDown;
	}
	/**
	 * @return the destinationLeft
	 */
	public Location getDestinationLeft() {
		return destinationLeft;
	}
	/**
	 * @param destinationLeft the destinationLeft to set
	 */
	public void setDestinationLeft(Location destinationLeft) {
		this.destinationLeft = destinationLeft;
	}
	/**
	 * @return the destinationRight
	 */
	public Location getDestinationRight() {
		return destinationRight;
	}
	/**
	 * @param destinationRight the destinationRight to set
	 */
	public void setDestinationRight(Location destinationRight) {
		this.destinationRight = destinationRight;
	}
	/**
	 * @return the origin
	 */
	public Location getOrigin() {
		return origin;
	}
	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(Location origin) {
		this.origin = origin;
	}
	/**
	 * @return the moveCounter
	 */
	public int getMoveCounter() {
		return moveCounter;
	}
	/**
	 * @param moveCounter the moveCounter to set
	 */
	public void setMoveCounter(int moveCounter) {
		this.moveCounter = moveCounter;
	}
	/**
	 * @return the canInteract
	 */
	public boolean isCanInteract() {
		return canInteract;
	}
	/**
	 * @param canInteract the canInteract to set
	 */
	public void setCanInteract(boolean canInteract) {
		this.canInteract = canInteract;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the isAlive
	 */
	public boolean isAlive() {
		return isAlive;
	}
	/**
	 * @param isAlive the isAlive to set
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	/**
	 * @return the canAttack
	 */
	public boolean isCanAttack() {
		return canAttack;
	}
	/**
	 * @param canAttack the canAttack to set
	 */
	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}

	/**
	 * @return the burnTimes
	 */
	public int getBurnTimes() {
		return burnTimes;
	}

	/**
	 * @param burnTimes the burnTimes to set
	 */
	public void setBurnTimes(int burnTimes) {
		this.burnTimes = burnTimes;
	}

	/**
	 * @return the burnDamage
	 */
	public int getBurnDamage() {
		return burnDamage;
	}

	/**
	 * @param burnDamage the burnDamage to set
	 */
	public void setBurnDamage(int burnDamage) {
		this.burnDamage = burnDamage;
	}

	/**
	 * @return the freezeTimes
	 */
	public int getFreezeTimes() {
		return freezeTimes;
	}

	/**
	 * @param freezeTimes the freezeTimes to set
	 */
	public void setFreezeTimes(int freezeTimes) {
		this.freezeTimes = freezeTimes;
	}

	/**
	 * @return the freeze
	 */
	public boolean isFreeze() {
		return freeze;
	}

	/**
	 * @param freeze the freeze to set
	 */
	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}
}
