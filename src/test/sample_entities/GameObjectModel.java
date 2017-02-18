/**
 * 
 */
package test.sample_entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

/**
 * @author Saman Saadi
 *
 */

@Entity
@Inheritance
@DiscriminatorColumn(name="GAMEOBJ_TYPE")
@Table(name="GAME_OBJECT")
public abstract class GameObjectModel {
	@Id
	private int id;
	private int row;
	private int col;

}
