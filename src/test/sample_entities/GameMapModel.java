/**
 * 
 */
package test.sample_entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Saman Saadi
 *
 */

@Entity
@Table(name="GAME_MAP")
public class GameMapModel {
	@Id
	private long id;
	private ArrayList<GameObjectModel> objects;
}
