package ca.concordia.soen6441.d20.item;

import ca.concordia.soen6441.d20.ability.AbilityEnum;
import ca.concordia.soen6441.d20.attribute.AttributeEnum;
import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ItemEntity
 * @author Saman Saadi
 *
 */

@NamedQueries({
	@NamedQuery(
			name="findItemEntityByName",
			query="SELECT item FROM ItemEntity item WHERE item.name = :name"
			),
	@NamedQuery(
			name="findItemEntityAll",
			query="SELECT item FROM ItemEntity item")
})

@Entity
public class ItemEntity extends GameObjectEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean mele;
	/**
	 * the attribute that represent the enchantment point.
	 */
	private int point;
	private String itemTag;
	/**
	 * the attribute that that represent item's name. If you change enum values, you need to update database
	 */
	@Enumerated(EnumType.STRING)
	private ItemEnum itemEnum;
	/**
	 * the attribute that represent the enchantmentType. If you change enum values, you need to update database
	 */
	@Enumerated(EnumType.STRING)
	private AbilityEnum enchantmentType;
	/**
	 * If you change enum values, you need to update database
	 */
	@Enumerated(EnumType.STRING)
	private AttributeEnum attributeType;

	public ItemEntity() {
		super();
	}
	/**
	 * 
	 * @return Item object that will the value retrieved from the database will be used by it.
	 */
	public Item createItemModel()
	{
		return new Item(this);
	}
	
	@Override
	public GameObject createModel() {
		return createItemModel();
	}
	/**
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}
	/**
	 * @param point the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}
	/**
	 * @return the itemEnum
	 */
	public ItemEnum getItemEnum() {
		return itemEnum;
	}
	/**
	 * @param itemEnum the itemEnum to set
	 */
	public void setItemEnum(ItemEnum itemEnum) {
		this.itemEnum = itemEnum;
	}
	/**
	 * @return the enchantmentType
	 */
	public AbilityEnum getEnchantmentType() {
		return enchantmentType;
	}
	/**
	 * @param enchantmentType the enchantmentType to set
	 */
	public void setEnchantmentType(AbilityEnum enchantmentType) {
		this.enchantmentType = enchantmentType;
	}
	/**
	 * @return the attributeType
	 */
	public AttributeEnum getAttributeType() {
		return attributeType;
	}
	/**
	 * @param attributeType the attributeType to set
	 */
	public void setAttributeType(AttributeEnum attributeType) {
		this.attributeType = attributeType;
	}

	/**
	 * @return the itemTag
	 */
	public String getItemTag() {
		return itemTag;
	}

	/**
	 * @param itemTag the itemTag to set
	 */
	public void setItemTag(String itemTag) {
		this.itemTag = itemTag;
	}
	/**
	 * @return the mele
	 */
	public boolean isMele() {
		return mele;
	}
	/**
	 * @param mele the mele to set
	 */
	public void setMele(boolean mele) {
		this.mele = mele;
	}	   
}
