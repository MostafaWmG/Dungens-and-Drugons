package ca.concordia.soen6441.d20.item;

import ca.concordia.soen6441.d20.gamemap.element.GameObject;
import ca.concordia.soen6441.d20.gamemap.element.GameObjectEntity;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ArmorClassEntity
 * @author Saman Saadi
 */
@Entity

public class ArmorClassEntity extends GameObjectEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private int base;
	private int modfier;

	public ArmorClassEntity() {
		super();
	}

	@Override
	public GameObject createModel() {
		return new ArmorClass(this);
	}

	/**
	 * @return the base
	 */
	public int getBase() {
		return base;
	}

	/**
	 * @param base the base to set
	 */
	public void setBase(int base) {
		this.base = base;
	}

	/**
	 * @return the modfier
	 */
	public int getModfier() {
		return modfier;
	}

	/**
	 * @param modfier the modfier to set
	 */
	public void setModfier(int modfier) {
		this.modfier = modfier;
	}
	
}
