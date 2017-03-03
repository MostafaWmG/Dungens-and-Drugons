package ca.concordia.soen6441.d20.editors;

import java.util.List;
import java.util.Scanner;

import ca.concordia.soen6441.d20.campaign.Campaign;
import ca.concordia.soen6441.d20.campaign.CampaignEntity;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.GameMapEntity;
import ca.concordia.soen6441.persistence.dao.DaoFactory;
/**
 * CampaignEditor class
 * @author wmg
 *
 */
public class CampaignEditor {
	private Scanner scanner;
	
	public CampaignEditor() {
		System.out.println("<<<Campaign Edtior Section>>>");
		System.out.println("Choose Section : ");
		System.out.println("EditCampaign:(Type e) , CreateCampaign : (Type C) " );
		scanner = new Scanner(System.in);
		String hitButton = scanner.nextLine();

		if(hitButton.equals("c")){
			createCampaign();
		}else if (hitButton.equals("e")){
			editCampaign();
		}else {
			System.out.println("Error");
		}
	}
	
	/**
	 * userInterFace editCampaign
	 */
	private void editCampaign(){
		System.out.println("Load the Campaign:");
		System.out.println("Name:");
		String CampaignLoaded = scanner.nextLine();
		// here we need to call the load function to load character from file 
		// Campaign Campaign = load(CampaignLoaded);
		
		List<CampaignEntity> list = DaoFactory.getCampaignDao().findByName(CampaignLoaded);
		if (list.isEmpty())
		{
			//TODO use appropriate procedure
			System.out.println("Invalid map name");
			return;
		}
		Campaign campaign = list.get(0).createModel();

		
		editCampaign(campaign);
	}
	
	/**
	 * edit campaign
	 * @param campaign selected campaign
	 */
	private void editCampaign(Campaign campaign){
		campaign.show();
		System.out.println("Add maps :(Type a) ");
		String hitButton = scanner.nextLine();
	
		// we need to create all this characters throw character factory;
		if(hitButton.equals("a")){
			addMap(campaign);
		}else{
			System.out.println("Error");
		}
	}
	
//	private void removeMap(Campaign campaign){
//		campaign.show();
//		System.out.println("Enter your map index to remove from Campaign: ");
//		String mapName = scanner.nextLine();
//		
//		try {
//			int index = Integer.parseInt(mapName);
//			campaign.getCampaign().remove(index);
//			campaign.show();
//			saveCampaignChanges(campaign, "edit");
//		} catch (Exception e) {
//			System.out.println("Wrong Input");
//		}
//
//
//	}
	
	/**
	 * method for createCampaign
	 */
	private void createCampaign(){
		System.out.println("Please Enter Your Campaign Name : ");
		String charName = scanner.nextLine();
		
		Campaign campaign = new Campaign(charName);
		
		addMap(campaign);
		
		
	}
	
	/**
	 * add map to campaign
	 * @param campaign selected campaign
	 */
	public void addMap(Campaign campaign){
		System.out.println("Enter your map Name to add into Campaign: ");
		String mapName = scanner.nextLine();
		
		List<GameMapEntity> list = DaoFactory.getGameMapDao().findByName(mapName);
		if (list.isEmpty())
		{
			//TODO use appropriate procedure
			System.out.println("Invalid map name");
			return;
		}
		
		GameMap map = list.get(0).createModel();
		
		campaign.addMap(map);
		campaign.show();
		saveCampaignChanges(campaign,"add");
	}
	
	/**
	 * this is a method that saves the campaign changes
	 * @param campaign campaign
	 * @param section which part of campaign is changing
	 */
	private void saveCampaignChanges(Campaign campaign , String section){
		if (section.equals("add")){
			System.out.println("Do you want to add another map to campaign :(yes or no)");
		}else if (section.equals("edit")){
			System.out.println("Do you want to edit another campaign  :(yes or no)");
		}

		String answer = scanner.nextLine();
		
		if(answer.equals("yes")){
			if(section.equals("add")){
				addMap(campaign);
			}else if(section.equals("edit")){
				editCampaign(campaign);
			}
		}else if (answer.equals("no")){
			System.out.println("Do you want to save Campaign changes:(yes or no)");
			answer = scanner.nextLine();
			if(answer.equals("yes")){
				DaoFactory.getCampaignDao().create(campaign.getCampaignEntity());
			}else {
				
			}
		}else {
			System.out.println("Error IO");
		}
	}
}
