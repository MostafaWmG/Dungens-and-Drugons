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

import test.ca.concordia.soen6441.model.persistence.sample_entities.FighterEntity;
import test.ca.concordia.soen6441.model.persistence.sample_entities.GameMapEntity;
import test.ca.concordia.soen6441.model.persistence.sample_entities.GameObjectEntity;
import test.ca.concordia.soen6441.model.persistence.sample_entities.ItemEntity;

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
		GameMapEntity gameMap = em.find(GameMapEntity.class, 1);
		tx.commit();
		if (gameMap != null)
		{
			System.out.println("Game Map is in DB");
			System.out.println(gameMap);
			assertTrue(true);
			return;
		}
		
		ItemEntity item1 = new ItemEntity(1, "Sword1");
		ItemEntity item2 = new ItemEntity(2, "Sword2");
		
		ArrayList<GameObjectEntity> objects = new ArrayList<GameObjectEntity>();
		
		FighterEntity fighter1 = new FighterEntity(1,  "Fighter1");
		fighter1.getItems().add(item1);
		objects.add(fighter1);
		
		FighterEntity fighter2 = new FighterEntity(2, "Fighter2");
		fighter2.getItems().add(item2);
//		objects.add(fighter2);
		
		gameMap = new GameMapEntity(1, "GameMap1", objects);
		System.out.println(gameMap);
		
		tx.begin();
		em.persist(gameMap);
		tx.commit();
		System.out.println("Inserting a new game map to DB");
		System.out.println(gameMap);
		assertTrue(true);
	}

//	@Test
//	public void testGetObjects() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetObjects() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetName() {
//		fail("Not yet implemented");
//	}

}
