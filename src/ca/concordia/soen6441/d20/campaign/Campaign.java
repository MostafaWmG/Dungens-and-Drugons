package ca.concordia.soen6441.d20.campaign;

import java.util.LinkedList;
import java.util.List;

import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.GameMapEntity;

public class Campaign {
	
	CampaignEntity campaignEntity;	
	private List<GameMap> campaign;
	
	public Campaign(String name) {
		initEmptyEntity();
		setName(name);
		setCampaign(new LinkedList<GameMap>());
	}
	
	public Campaign(CampaignEntity e)
	{
		setCampaignEntity(e);
		for (GameMapEntity gameMapEntity : e.getCampaign())
			addMap(gameMapEntity.createModel(), false);
	}
			
	private void initEmptyEntity()
	{
		setCampaignEntity(new CampaignEntity());
	}
	
	public void addMap(GameMap map)
	{
		addMap(map, true);
	}
	
	private void addMap(GameMap map, boolean saveEntity)
	{
		getCampaign().add(map);
		if (saveEntity)
			getCampaignEntity().getCampaign().add(map.getEntity());
	}
	
	public void show(){
		for(int i = 0 ; i < getCampaign().size(); i ++){
			System.out.println("Map" + i+" : "+ getCampaign().get(i).getMapName());
		}
	}
	/**
	 * @return the campaign
	 */
	public List<GameMap> getCampaign() {
		return campaign;
	}


	/**
	 * @param campaign the campaign to set
	 */
	public void setCampaign(LinkedList<GameMap> campaign) {
		this.campaign = campaign;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return getCampaignEntity().getName();
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		getCampaignEntity().setName(name);
	}

	/**
	 * @return the campaignEntity
	 */
	public CampaignEntity getCampaignEntity() {
		return campaignEntity;
	}

	/**
	 * @param campaignEntity the campaignEntity to set
	 */
	public void setCampaignEntity(CampaignEntity campaignEntity) {
		this.campaignEntity = campaignEntity;
	}

	/**
	 * @param campaign the campaign to set
	 */
	public void setCampaign(List<GameMap> campaign) {
		this.campaign = campaign;
	}		
}
