package ca.concordia.soen6441.d20.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;
import ca.concordia.soen6441.d20.item.AbilityEntity;
import ca.concordia.soen6441.d20.item.ArmorClassEntity;
import ca.concordia.soen6441.d20.item.AttackBonusEntity;
import ca.concordia.soen6441.d20.item.DamageBonusEntity;
import ca.concordia.soen6441.d20.item.HitPointEntity;
import ca.concordia.soen6441.d20.item.ItemEntity;


/**
 * Entity implementation class for Entity: CharacterEntity
 * @author negar
 * @author Saman Saadi
 */

@NamedQueries({
	@NamedQuery(
			name="findCharacterEntityByName",
			query="SELECT character FROM CharacterEntity character WHERE character.name = :name"
			),
	@NamedQuery(
			name="findCharacterEntityAll",
			query="SELECT character FROM CharacterEntity character")
})

@Entity
public class CharacterEntity extends GameObjectEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//TODO Fighters, armors and some other game objects have level. So I don't think we should put level here
	//Maybe we need to put it in GameObject
	private int level;
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
			name="FIGHTER_ABILITIES",
			joinColumns=@JoinColumn(name="FIGHTER_ID", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="ABILITY_ID", referencedColumnName="ABILITY_ID", unique=true))
	private List <AbilityEntity> abilities;
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
			name="WEAR_ITEMS",
			joinColumns=@JoinColumn(name="FIGHTER_ID", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="WEAR_ITEM_ID", referencedColumnName="ID", unique=true))
	private List <ItemEntity> wearItems;
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
			name="BACKPACK",
			joinColumns=@JoinColumn(name="FIGHTER_ID", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="BACKPACK_ID", referencedColumnName="ID", unique=true))
	private List<ItemEntity> backpack;
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="ARMOR_ID")
	private ArmorClassEntity armorClass;
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="ATTACK_ID")
	private AttackBonusEntity attackBonus;
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="DAMAGE_ID")
	private DamageBonusEntity damageBonus;
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="HITPOINT_ID")
	private HitPointEntity hitPoint;
	String fighterType;

	public CharacterEntity() {
		super();
		level = 1;		
		abilities = new ArrayList<AbilityEntity>();
		wearItems = new ArrayList<ItemEntity>();
		backpack = new ArrayList<ItemEntity>();
		armorClass = new ArmorClassEntity();
		attackBonus = new AttackBonusEntity();
		hitPoint = new HitPointEntity();
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return the abilities
	 */
	public List<AbilityEntity> getAbilities() {
		return abilities;
	}
	/**
	 * @param abilities the abilities to set
	 */
	public void setAbilities(List<AbilityEntity> abilities) {
		this.abilities = abilities;
	}
	/**
	 * @return the wearItems
	 */
	public List<ItemEntity> getWearItems() {
		return wearItems;
	}
	/**
	 * @param wearItems the wearItems to set
	 */
	public void setWearItems(List<ItemEntity> wearItems) {
		this.wearItems = wearItems;
	}
	/**
	 * @return the armorClass
	 */
	public ArmorClassEntity getArmorClass() {
		return armorClass;
	}
	/**
	 * @param armorClass the armorClass to set
	 */
	public void setArmorClass(ArmorClassEntity armorClass) {
		this.armorClass = armorClass;
	}
	/**
	 * @return the attackBonus
	 */
	public AttackBonusEntity getAttackBonus() {
		return attackBonus;
	}
	/**
	 * @param attackBonus the attackBonus to set
	 */
	public void setAttackBonus(AttackBonusEntity attackBonus) {
		this.attackBonus = attackBonus;
	}
	/**
	 * @return the damageBonus
	 */
	public DamageBonusEntity getDamageBonus() {
		return damageBonus;
	}
	/**
	 * @param damageBonus the damageBonus to set
	 */
	public void setDamageBonus(DamageBonusEntity damageBonus) {
		this.damageBonus = damageBonus;
	}
	/**
	 * @return the hitPoint
	 */
	public HitPointEntity getHitPoint() {
		return hitPoint;
	}
	/**
	 * @param hitPoint the hitPoint to set
	 */
	public void setHitPoint(HitPointEntity hitPoint) {
		this.hitPoint = hitPoint;
	}
	@Override
	public GameObject createModel() {
		return new Character(this);
	}
	/**
	 * @return the backpack
	 */
	public List<ItemEntity> getBackpack() {
		return backpack;
	}
	/**
	 * @param backpack the backpack to set
	 */
	public void setBackpack(List<ItemEntity> backpack) {
		this.backpack = backpack;
	}
	/**
	 * @return the fighterType
	 */
	public String getFighterType() {
		return fighterType;
	}
	/**
	 * @param fighterType the fighterType to set
	 */
	public void setFighterType(String fighterType) {
		this.fighterType = fighterType;
	}
	
}
