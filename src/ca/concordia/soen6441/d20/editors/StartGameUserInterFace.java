package ca.concordia.soen6441.d20.editors;

import java.util.List;
import java.util.Scanner;

import ca.concordia.soen6441.controller.Game;
import ca.concordia.soen6441.d20.campaign.Campaign;
import ca.concordia.soen6441.d20.campaign.CampaignEntity;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.fighter.FighterEntity;
import ca.concordia.soen6441.d20.strategy.ComputerPlayer;
import ca.concordia.soen6441.d20.strategy.HumanPlayer;
import ca.concordia.soen6441.persistence.dao.DaoFactory;

public class StartGameUserInterFace {
	private Scanner scanner;
	private Campaign campaign;
	private Fighter fighter;
	
	/**
	 * by choosing the campaing and the character, it loads both from the file
	 */
	public StartGameUserInterFace(){

		System.out.println("Start Game: ");
		System.out.println("Choose Your character : ");
		scanner = new Scanner(System.in);
		String charName = scanner.nextLine();

		fighter = loadCharacter(charName);
		
		System.out.println("Choose Your character Strategy : ");
		String charStrategy = scanner.nextLine();
		
		if(charStrategy.equalsIgnoreCase("HumanPlayer")){
			fighter.setStrategy(new HumanPlayer(fighter));
		}else if (charStrategy.equalsIgnoreCase("ComputerPlayer")) {
			fighter.setStrategy(new ComputerPlayer(fighter));
		}else{
			System.out.print("Wrong input");
			return;
		}
		
		System.out.println("Choose Your Campaign : ");
		String campName = scanner.nextLine();
		
		campaign = loadCampaign(campName);
		
		if(fighter !=null && campaign!= null){
			Thread t = new Thread(new Game(campaign,fighter));
			t.start();
		}else {
			System.out.println("ERROR NULL");
		}
		
	}
	
	/**
	 * load a character
	 * @param characterLoaded name of the character to be loaded
	 * @return
	 */
	private Fighter loadCharacter(String characterLoaded){
		List<FighterEntity> list = DaoFactory.getFighterDao().findByName(characterLoaded);
		if (list.isEmpty())
		{
			System.out.println("Invalid Fighter name");
			return null;
		}
		return (Fighter) list.get(0).createModel();	
	}
	
	/**
	 * load a  campaing
	 * @param CampaignLoaded campaing's name to be loaded
	 * @return
	 */
	private Campaign loadCampaign(String CampaignLoaded){
		List<CampaignEntity> list = DaoFactory.getCampaignDao().findByName(CampaignLoaded);
		if (list.isEmpty())
		{
			//TODO use appropriate procedure
			System.out.println("Invalid map name");
			return null;
		}
		 return list.get(0).createModel();
	} 

}
