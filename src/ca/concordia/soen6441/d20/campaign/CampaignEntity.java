package ca.concordia.soen6441.d20.campaign;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import ca.concordia.soen6441.d20.gamemap.GameMapEntity;

/**
 * Entity implementation class for Entity: CampaignEntity
 * @author Saman Saadi
 *
 */

@NamedQueries({
	@NamedQuery(
			name="findCampaignEntityByName",
			query="SELECT ce FROM CampaignEntity ce WHERE ce.name = :name"
			),
	@NamedQuery(
			name="findCampaignEntityAll",
			query="SELECT ce FROM CampaignEntity ce")
})

@Entity
public class CampaignEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private long id;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name="CAMPAIGN_MAP",
			joinColumns=@JoinColumn(name="CAMPAIGN_ID", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="MAP_ID", referencedColumnName="ID"))
	private List<GameMapEntity> campaign;
	private String name;

	public CampaignEntity() {
		super();
		setCampaign(new ArrayList<>());
	}
	
	public Campaign createModel()
	{
		return new Campaign(this);
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the campaign
	 */
	public List<GameMapEntity> getCampaign() {
		return campaign;
	}
	/**
	 * @param campaign the campaign to set
	 */
	public void setCampaign(List<GameMapEntity> campaign) {
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
