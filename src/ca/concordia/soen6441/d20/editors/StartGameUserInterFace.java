package ca.concordia.soen6441.d20.editors;

import java.util.List;
import java.util.Scanner;

import ca.concordia.soen6441.d20.campaign.Campaign;
import ca.concordia.soen6441.d20.campaign.CampaignEntity;
import ca.concordia.soen6441.d20.fighter.Fighter;
import ca.concordia.soen6441.d20.fighter.FighterEntity;
import ca.concordia.soen6441.d20.gamePlay.Game;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.persistence.dao.DaoFactory;

public class StartGameUserInterFace {
	private Scanner scanner;
	private Campaign campaign;
	private Fighter fighter;
	
	public StartGameUserInterFace(){

		System.out.println("Start Game: ");
		System.out.println("Choose Your character : ");
		scanner = new Scanner(System.in);
		String charName = scanner.nextLine();
		
		fighter = loadCharacter(charName);
		
		System.out.println("Choose Your Campaign : ");
		String campName = scanner.nextLine();
		
		campaign = loadCampaign(campName);
		
		if(fighter !=null && campaign!= null){
			new Game(campaign,fighter);
		}else {
			System.out.println("ERROR NULL");
		}
		
	}
	
	private Fighter loadCharacter(String characterLoaded){
		List<FighterEntity> list = DaoFactory.getFighterDao().findByName(characterLoaded);
		if (list.isEmpty())
		{
			//TODO use appropriate procedure
			System.out.println("Invalid character name");
			return null;
		}
		return (Fighter) list.get(0).createModel();	
	}
	
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
