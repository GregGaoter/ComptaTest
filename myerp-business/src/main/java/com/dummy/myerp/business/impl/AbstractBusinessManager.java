package com.dummy.myerp.business.impl;

import java.util.Map;

import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dummy.myerp.business.contrat.BusinessProxy;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.ParamMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * <p>
 * Classe mère des Managers
 * </p>
 */
public abstract class AbstractBusinessManager {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(AbstractBusinessManager.class);

	/** Le proxy d'accès à la couche Business */
	private static BusinessProxy businessProxy;
	/** Le proxy d'accès à la couche Consumer-DAO */
	private static DaoProxy daoProxy;
	/** Le gestionnaire de transaction */
	private static TransactionManager transactionManager;

	// ==================== Constructeurs ====================

	/**
	 * Méthode de configuration de la classe
	 *
	 * @param pBusinessProxy      - Proxy d'accès à la couche Business
	 * @param pDaoProxy           - Proxy d'accès à la couche Consumer-DAO
	 * @param pTransactionManager - Gestionnaire de transaction
	 * 
	 * @see BusinessProxy
	 * @see DaoProxy
	 * @see TransactionManager
	 */
	public static void configure(BusinessProxy pBusinessProxy, DaoProxy pDaoProxy,
			TransactionManager pTransactionManager) {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new ParamMessage(Map.of("BusinessProxy pBusinessProxy", pBusinessProxy, "DaoProxy pDaoProxy",
				pDaoProxy, "TransactionManager pTransactionManager", pTransactionManager)));
		businessProxy = pBusinessProxy;
		daoProxy = pDaoProxy;
		transactionManager = pTransactionManager;
		LOGGER.trace(new SortieMessage());
	}

	// ==================== Getters/Setters ====================

	/**
	 * Renvoie le Proxy d'accès à la couche Business
	 *
	 * @return {@link BusinessProxy}
	 */
	protected BusinessProxy getBusinessProxy() {
		return businessProxy;
	}

	/**
	 * Renvoie le Proxy d'accès à la couche Consumer-DAO
	 *
	 * @return {@link DaoProxy}
	 */
	protected DaoProxy getDaoProxy() {
		return daoProxy;
	}

	/**
	 * Renvoie le gestionnaire de Transaction
	 *
	 * @return {@link TransactionManager}
	 */
	protected TransactionManager getTransactionManager() {
		return transactionManager;
	}

	/**
	 * Renvoie un {@link Validator} de contraintes
	 *
	 * @return {@link Validator}
	 * 
	 * @see Configuration
	 * @see ValidatorFactory
	 */
	protected Validator getConstraintValidator() {
		LOGGER.trace(new EntreeMessage());
		Configuration<?> vConfiguration = Validation.byDefaultProvider().configure();
		ValidatorFactory vFactory = vConfiguration.buildValidatorFactory();
		Validator vValidator = vFactory.getValidator();
		LOGGER.trace(new SortieMessage());
		return vValidator;
	}
}
