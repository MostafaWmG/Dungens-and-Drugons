package ca.concordia.soen6441.persistence.dao;

import ca.concordia.soen6441.d20.campaign.CampaignEntity;
import ca.concordia.soen6441.d20.fighter.FighterEntity;
import ca.concordia.soen6441.d20.gamemap.GameMapEntity;
import ca.concordia.soen6441.d20.item.ChestEntity;
import ca.concordia.soen6441.d20.item.ItemEntity;
import ca.concordia.soen6441.persistence.dao.interfaces.INamedEntityDao;

/**
 * A factory class which returns DAOs for storing and loading entities
 * @author Saman Saadi
 *
 */
public class DaoFactory {
	private static class DaoHolder
	{
		static final JpaNamedEntityDao<GameMapEntity, Long> jpaGameMapDao = 
				new JpaNamedEntityDao<>(GameMapEntity.class, 
										"findGameMapEntityByName", 
										"findGameMapEntityAll");
		static final JpaNamedEntityDao<ItemEntity, Long> jpaItemDao =
				new JpaNamedEntityDao<>(ItemEntity.class,
										"findItemEntityByName",
										"findItemEntityAll");
		static final JpaNamedEntityDao<FighterEntity, Long> jpaFighterDao =
				new JpaNamedEntityDao<>(FighterEntity.class,
										"findFighterEntityByName",
										"findFighterEntityAll");
		static final JpaNamedEntityDao<CampaignEntity, Long> jpaCampaignDao =
				new JpaNamedEntityDao<>(CampaignEntity.class,
										"findCampaignEntityByName", 
										"findCampaignEntityAll");
		static final JpaNamedEntityDao<ChestEntity, Long> jpaChestDao =
				new JpaNamedEntityDao<>(ChestEntity.class,
						"findChestEntityByName",
						"findChestEntityAll");
	}
	
	/**
	 * 
	 * @return  A DAO for CRUD operation for GameMapEntity
	 */
	public static INamedEntityDao<GameMapEntity, Long> getGameMapDao()
	{
		return DaoHolder.jpaGameMapDao;
	}
	
	/**
	 * 
	 * @return A DAO for CRUD operation for ItemEntity
	 */
	public static INamedEntityDao<ItemEntity, Long> getItemDao()
	{
		return DaoHolder.jpaItemDao;
	}
	
	/**
	 * 
	 * @return A DAO for CRUD operation for CharacterEntity
	 */
	public static INamedEntityDao<FighterEntity, Long> getFighterDao()
	{
		return DaoHolder.jpaFighterDao;
	}
	
	/**
	 * 
	 * @return A DAO for CRUD operation for CampaignEntity
	 */
	public static INamedEntityDao<CampaignEntity, Long> getCampaignDao()
	{
		return DaoHolder.jpaCampaignDao;
	}
	
	public static INamedEntityDao<ChestEntity, Long> getChestDao()
	{
		return DaoHolder.jpaChestDao;
	}
}
