package ca.concordia.soen6441.persistence.dao;

import ca.concordia.soen6441.d20.campaign.CampaignEntity;
import ca.concordia.soen6441.d20.character.CharacterEntity;
import ca.concordia.soen6441.d20.gamemap.GameMapEntity;
import ca.concordia.soen6441.d20.item.ItemEntity;
import ca.concordia.soen6441.persistence.dao.interfaces.INamedEntityDao;

/**
 * 
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
		static final JpaNamedEntityDao<CharacterEntity, Long> jpaCharacterDao =
				new JpaNamedEntityDao<>(CharacterEntity.class,
										"findCharacterEntityByName",
										"findCharacterEntityAll");
		static final JpaNamedEntityDao<CampaignEntity, Long> jpaCampaignDao =
				new JpaNamedEntityDao<>(CampaignEntity.class,
										"findCampaignEntityByName", 
										"findCampaignEntityAll");
	}
	
	public static INamedEntityDao<GameMapEntity, Long> getGameMapDao()
	{
		return DaoHolder.jpaGameMapDao;
	}
	
	public static INamedEntityDao<ItemEntity, Long> getItemDao()
	{
		return DaoHolder.jpaItemDao;
	}
	
	public static INamedEntityDao<CharacterEntity, Long> getCharacterDao()
	{
		return DaoHolder.jpaCharacterDao;
	}
	
	public INamedEntityDao<CampaignEntity, Long> getCampaignDao()
	{
		return DaoHolder.jpaCampaignDao;
	}

}
