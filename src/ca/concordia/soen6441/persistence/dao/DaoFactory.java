package ca.concordia.soen6441.persistence.dao;

import ca.concordia.soen6441.d20.gamemap.GameMapEntity;
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
	}
	
	public static INamedEntityDao<GameMapEntity, Long> getGameMapDao()
	{
		return DaoHolder.jpaGameMapDao;
	}

}
