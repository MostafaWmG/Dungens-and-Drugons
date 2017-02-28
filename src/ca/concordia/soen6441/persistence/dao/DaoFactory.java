package ca.concordia.soen6441.persistence.dao;

import ca.concordia.soen6441.d20.gamemap.GameMapEntity;

/**
 * 
 * @author Saman Saadi
 *
 */
public class DaoFactory {
	private static class DaoHolder
	{
		static final JpaDao<GameMapEntity, Long> jpaGameMapDao = new JpaDao<>(GameMapEntity.class); 
	}
	
	public static IGenericDao<GameMapEntity, Long> getGameMapDao()
	{
		return DaoHolder.jpaGameMapDao;
	}

}
