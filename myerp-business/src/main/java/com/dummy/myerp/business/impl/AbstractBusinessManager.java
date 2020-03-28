package com.dummy.myerp.business.impl;

import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.dummy.myerp.business.contrat.BusinessProxy;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;

/**
 * <p>
 * Classe mère des Managers
 * </p>
 */
public abstract class AbstractBusinessManager {

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
		businessProxy = pBusinessProxy;
		daoProxy = pDaoProxy;
		transactionManager = pTransactionManager;
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
		Configuration<?> vConfiguration = Validation.byDefaultProvider().configure();
		ValidatorFactory vFactory = vConfiguration.buildValidatorFactory();
		Validator vValidator = vFactory.getValidator();
		return vValidator;
	}
}
