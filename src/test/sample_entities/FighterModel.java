/**
 * 
 */
package test.sample_entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Saman Saadi
 *
 */

@Entity
@Table(name="FIGHTER")
public class FighterModel extends GameObjectModel {
	private String name;

}
