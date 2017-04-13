package ca.concordia.soen6441.d20.campaign;

import java.util.LinkedList;
import java.util.List;

import ca.concordia.soen6441.d20.gamemap.GameMap;

/**
 * This class impelements game campaign which is a graph of maps that connect the together.
 * @author wmg
 *
 */
public class Campaign {
	
	CampaignEntity campaignEntity;	
	private List<GameMap> campaign;
	/**
	 * Creating a campaign object with the name specified
	 * @param name of the campaign
	 */
	public Campaign(String name) {
		initEmptyEntity();
		setName(name);
		setCampaign(new LinkedList<GameMap>());
	}
	/**
	 * This constructor is used to save/load the campaign
	 * @param e a CampaingEntity object to help saving or loading the campaign object
	 */
	public Campaign(CampaignEntity e)
	{
		setCampaignEntity(e);
		campaign = new LinkedList<GameMap>();

		for(int i = 0 ; i < e.getCampaign().size(); i++){
			addMap(i, e.getCampaign().get(i).createModel(),false);
		}
	}
	
	/**
	 * Initialize a model entity
	 */
	private void initEmptyEntity()
	{
		setCampaignEntity(new CampaignEntity());
	}
	
	/**
	 * add map to Campaign
	 * @param map selected map to be added
	 */
	public void addMap(int index,GameMap map)
	{
		addMap(index,map, true);
	}
	
	/**
	 * add map to Campaign
	 * @param map selected map to be added
	 * @param saveEntity if we are loading or saving
	 */
	private void addMap(int index,GameMap map, boolean saveEntity)
	{
		getMaps().add(index,map);
		if (saveEntity)
			getCampaignEntity().getCampaign().add(index,map.getEntity());
	}
	/**
	 * This method handles removing a game map
	 * @param index of the map that is going to be removed
	 */
	public void removeMap(int index)
	{
		if (index < 0 || index >= getMaps().size())
			throw new IllegalArgumentException("0 <= index < " + getMaps().size());
		getMaps().remove(index);
		getCampaignEntity().getCampaign().remove(index);
	}
	
	/**
	 * show maps inside Campaign
	 */
	public void show(){
		
		System.out.println("<<Campaign>>");
		for(int i = 0 ; i < getMaps().size(); i ++){
			System.out.println("Map" + i+" : "+ getMaps().get(i).getMapName());
		}
	}
	/**
	 * @return the campaign
	 */
	public List<GameMap> getMaps() {
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
