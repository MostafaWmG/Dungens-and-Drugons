package ca.concordia.soen6441.d20.campaign;

import java.util.LinkedList;
import java.util.List;

import ca.concordia.soen6441.d20.gamemap.GameMap;
import ca.concordia.soen6441.d20.gamemap.GameMapEntity;

/**
 * graph of maps that connect the together.
 * @author wmg
 *
 */
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
		campaign = new LinkedList<GameMap>();
		for (GameMapEntity gameMapEntity : e.getCampaign())
			addMap(gameMapEntity.createModel(), false);
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
	public void addMap(GameMap map)
	{
		addMap(map, true);
	}
	
	/**
	 * add map to Campaign
	 * @param map selected map to be added
	 * @param saveEntity if we are loading or saving
	 */
	private void addMap(GameMap map, boolean saveEntity)
	{
		getMaps().add(map);
		if (saveEntity)
			getCampaignEntity().getCampaign().add(map.getEntity());
	}
	
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
