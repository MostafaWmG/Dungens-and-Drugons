package ca.concordia.soen6441.d20.character;

import java.io.Serializable;
import java.lang.ref.WeakReference;
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
@Entity
public class CharacterEntity extends GameObjectEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//TODO Fighters, armors and some other game objects have level. So I don't think we should put level here
	//Maybe we need to put it in GameObject
	private int level;
	private List <AbilityEntity> abilities;
	private List <ItemEntity> wearItems;
	private ArmorClassEntity armorClass;
	private AttackBonusEntity attackBonus;
	private DamageBonusEntity damageBonus;
	private HitPointEntity hitPoint;	

	public CharacterEntity() {
		super();
		level = 1;		
		abilities = new ArrayList<AbilityEntity>();
		wearItems = new ArrayList<ItemEntity>();
		armorClass = new ArmorClassEntity();
		attackBonus = new AttackBonusEntity();
		hitPoint = new HitPointEntity();
	}
   public GameObject createModel(){
	   //TODO
	   return null;
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
}
