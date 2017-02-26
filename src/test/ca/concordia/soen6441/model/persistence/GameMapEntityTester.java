package test.ca.concordia.soen6441.model.persistence;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import test.ca.concordia.soen6441.model.persistence.sample_entities.FighterEntitySample;
import test.ca.concordia.soen6441.model.persistence.sample_entities.GameMapEntitySample;
import test.ca.concordia.soen6441.model.persistence.sample_entities.GameObjectEntitySample;
import test.ca.concordia.soen6441.model.persistence.sample_entities.ItemEntitySample;

/**
 * A simple class which shows persistent functionality using Hibernate 4.3.11. At 
 * the moment Derby 10.13.1 (embedded) is the backend.
 * @author Saman Saadi
 *
 */
public class GameMapEntityTester {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;
	
	@BeforeClass
	public static void init()
	{
		emf = Persistence.createEntityManagerFactory("dungeons");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}
	
	@AfterClass
	public static void destructor()
	{
		em.close();                                                                 
		emf.close();
	}
	
	@Test
	public void testGameMapEntity()
	{
		tx.begin();
		GameMapEntitySample gameMap = em.find(GameMapEntitySample.class, 1);
		tx.commit();
		if (gameMap != null)
		{
			System.out.println("Game Map is in DB");
			System.out.println(gameMap);
			assertTrue(true);
			return;
		}
		
		ItemEntitySample item1 = new ItemEntitySample(1, "Sword1");
		ItemEntitySample item2 = new ItemEntitySample(2, "Sword2");
		
		ArrayList<GameObjectEntitySample> objects = new ArrayList<GameObjectEntitySample>();
		
		FighterEntitySample fighter1 = new FighterEntitySample(3,  "Fighter1");
		fighter1.getItems().add(item1);
		objects.add(fighter1);
		
		FighterEntitySample fighter2 = new FighterEntitySample(4, "Fighter2");
		fighter2.getItems().add(item2);
		objects.add(fighter2);
		
		gameMap = new GameMapEntitySample(1, "GameMap1", objects);
		System.out.println(gameMap);
		
		tx.begin();
		em.persist(gameMap);
		tx.commit();
		System.out.println("Inserting a new game map to DB");
		System.out.println(gameMap);
		assertTrue(true);
	}

}
