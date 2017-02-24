package ca.concordia.soen6441.d20.gamemap;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: GameMapEntity
 * @author Saman Saadi
 *
 */
@Entity
public class GameMapEntity implements Serializable {

	@Id
	private long id;
	private int width;
	private int height;
	private String name;
	private static final long serialVersionUID = 1L;

	public GameMapEntity() {
		super();
	}   
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}   
	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}   
	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
}
