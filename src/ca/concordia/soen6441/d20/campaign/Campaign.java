package ca.concordia.soen6441.d20.campaign;

import java.util.LinkedList;
import java.util.List;

import ca.concordia.soen6441.d20.gamemap.GameMap;

public class Campaign {

	private List<GameMap> campaign;
	private String name;
	
	public Campaign(String name) {
		setName(name);
		setCampaign(new LinkedList<GameMap>());
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
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
