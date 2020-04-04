package com.dummy.myerp.consumer;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.ParamMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * Classe d'aide pour les classes du module consumer
 */
public abstract class ConsumerHelper {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(ConsumerHelper.class);

	/** Le DaoProxy à utiliser pour accéder aux autres classes de DAO */
	private static DaoProxy daoProxy;

	// ==================== Constructeurs ====================
	/**
	 * Méthode de configuration de la classe
	 *
	 * @param pDaoProxy - {@link DaoProxy} à configurer
	 */
	public static void configure(DaoProxy pDaoProxy) {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new ParamMessage(Map.of("DaoProxy pDaoProxy", pDaoProxy)));
		daoProxy = pDaoProxy;
		LOGGER.trace(new SortieMessage());
	}

	// ==================== Getters/Setters ====================
	public static DaoProxy getDaoProxy() {
		return daoProxy;
	}
}
