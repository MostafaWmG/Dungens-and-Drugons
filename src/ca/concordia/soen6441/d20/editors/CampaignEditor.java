package ca.concordia.soen6441.d20.editors;

import java.util.List;
import java.util.Scanner;

import ca.concordia.soen6441.d20.campaign.Campaign;
import ca.concordia.soen6441.d20.character.Character;
import ca.concordia.soen6441.d20.character.factory.PlayerFactory;
import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.GameMapEntity;
import ca.concordia.soen6441.persistence.dao.DaoFactory;

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
//			editCampaign();
		}else {
			System.out.println("Error");
		}
	}
	
	private void createCampaign(){
		System.out.println("Please Enter Your Campaign Name : ");
		String charName = scanner.nextLine();
		
		Campaign campaign = new Campaign(charName);
		
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
		
		
	}
}
