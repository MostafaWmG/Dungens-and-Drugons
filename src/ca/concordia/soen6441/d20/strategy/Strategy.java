package ca.concordia.soen6441.d20.strategy;

import ca.concordia.soen6441.controller.Game;
import ca.concordia.soen6441.d20.common.Location;
import ca.concordia.soen6441.d20.fighter.Fighter;
/**
 * This abstract class is used to implement Strategy pattern.
 * In this game we have 4 Strategies.
 * Each subclasses of this class implement one strategy.
 * @author negar
 *
 */
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
	protected int fearTiems;
	protected Location fearTarget;
	protected boolean fear;
	
	public Strategy() {
		setAlive(true);
		setFreeze(false);
		setFear(false);
	}
	
	public abstract void turn(Game game);
	
	public abstract void move();
	
	public abstract void attack();
	
	public abstract void interact();
	
	/**
	 * This method validates that the character of the game can move to the desired destination or not
	 * @param game the current game that character is playin
	 * @param destination that character wants to go
	 * @return string that show us the ability to move or not
	 */
	public String checkDestination(Game game,Location destination){
		if(! game.getMap().moveCanBeDone(game.getCurrentLocation().getX(), game.getCurrentLocation().getY(), destination.getX(), destination.getY())) {
//			System.out.println("out of map");
			return "null";
		}else{
//			System.out.println("we can go");
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
	/**
	 * this method applies the effect that is appropriate
	 */
	protected void applyEffects(){
		if(getCount() == 5){
			if(getBurnTimes() > 0){
				setBurnTimes(getBurnTimes() - 1);
				setAlive(!getFighter().takeDamge(getBurnDamage()));
				System.out.println(getBurnDamage() + " Burning damge taken by: " + getFighter().getName()+ " at: " + (3-getBurnTimes()) +" Times");
			}
			
			if(getFreezeTimes() > 0 ){
				setFreezeTimes(getFreezeTimes() - 1 );
			}else{
				setFreeze(false);
			}
			
			if(getFearTiems() > 0){
				setFearTiems(getFearTiems() - 1);
			}else{
				setFear(false);
			}
		}

	}
	/**
	 * apply fear effect
	 * @param fearTimes 
	 * @param attacker use this fighter to get its location
	 */
	public void activeFearEffect(int fearTimes,Fighter attacker){
		setFearTiems(fearTimes);
		setFearTarget(getGame().getLocations().get(attacker));
		setFear(true);
	}
	/**
	 * apply burning effect
	 * @param burnDamge to set
	 */
	public void activeBuringEffect(int burnDamge){
		setBurnTimes(3);
		setBurnDamage(burnDamge);
	}
	/**
	 * apply slaying effect
	 */
	public void activeSlayingEffect()
	{
		setAlive(false);
		System.out.println("slaying effect active: " + getFighter().getName() + " " + isAlive());
	}
	
	public void activeFreezeEffect(int freezeTimes){
		setFreezeTimes(freezeTimes);
		setFreeze(true);
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

	/**
	 * @return the fearTiems
	 */
	public int getFearTiems() {
		return fearTiems;
	}

	/**
	 * @param fearTiems the fearTiems to set
	 */
	public void setFearTiems(int fearTiems) {
		this.fearTiems = fearTiems;
	}

	/**
	 * @return the fearTarget
	 */
	public Location getFearTarget() {
		return fearTarget;
	}

	/**
	 * @param fearTarget the fearTarget to set
	 */
	public void setFearTarget(Location fearTarget) {
		this.fearTarget = fearTarget;
	}

	/**
	 * @return the fear
	 */
	public boolean isFear() {
		return fear;
	}

	/**
	 * @param fear the fear to set
	 */
	public void setFear(boolean fear) {
		this.fear = fear;
	}
}
