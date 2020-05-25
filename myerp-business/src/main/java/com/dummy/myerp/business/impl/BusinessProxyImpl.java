package com.dummy.myerp.business.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dummy.myerp.business.contrat.BusinessProxy;
import com.dummy.myerp.business.contrat.manager.ComptabiliteManager;
import com.dummy.myerp.business.impl.manager.ComptabiliteManagerImpl;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.technical.exception.TechnicalException;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * <p>
 * Implémentation du Proxy d'accès à la couche Business.
 * </p>
 */
public class BusinessProxyImpl implements BusinessProxy {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(BusinessProxyImpl.class);

	// ==================== Attributs Static ====================
	/** Le Proxy d'accès à la couche Consumer-DAO */
	private static DaoProxy daoProxy;

	// ==================== Attributs ====================
	/**
	 * Le manager de comptabilité.<br>
	 * Le manager de comptabilité est instancié lors de l'initialisation de
	 * l'attribut.
	 */
	private ComptabiliteManager comptabiliteManager = new ComptabiliteManagerImpl();

	// ==================== Constructeurs ====================
	/**
	 * Instance unique de la classe (design pattern Singleton).<br>
	 * L'instance unique de {@link BusinessProxyImpl} est instanciée lors de
	 * l'initialisation de l'attribut.
	 */
	private static final BusinessProxyImpl INSTANCE = new BusinessProxyImpl();

	/**
	 * Renvoie l'instance unique de la classe (design pattern Singleton).<br>
	 * Construit une erreur {@link UnsatisfiedLinkError} si la classe
	 * {@link BusinessProxyImpl} n'a pas été initialisée.
	 *
	 * @return {@link BusinessProxyImpl}
	 * @throws TechnicalException
	 */
	protected static BusinessProxyImpl getInstance() {
		LOGGER.trace(new EntreeMessage());
		if (daoProxy == null) {
			throw new UnsatisfiedLinkError("La classe BusinessProxyImpl n'a pas été initialisée.");
		}
		LOGGER.trace(new SortieMessage());
		return BusinessProxyImpl.INSTANCE;
	}

	/**
	 * Renvoie l'instance unique de la classe (design pattern Singleton).
	 *
	 * @param pDaoProxy           - Proxy d'accès à la couche Consumer-DAO
	 * @param pTransactionManager - Gestionnaire de transaction
	 * @return {@link BusinessProxyImpl}
	 */
	public static BusinessProxyImpl getInstance(DaoProxy pDaoProxy, TransactionManager pTransactionManager) {
		LOGGER.trace(new EntreeMessage());
		daoProxy = pDaoProxy;
		AbstractBusinessManager.configure(BusinessProxyImpl.INSTANCE, pDaoProxy, pTransactionManager);
		LOGGER.trace(new SortieMessage());
		return BusinessProxyImpl.INSTANCE;
	}

	/**
	 * Constructeur par defaut.
	 */
	protected BusinessProxyImpl() {
		super();
	}

	// ==================== Getters/Setters ====================
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComptabiliteManager getComptabiliteManager() {
		return comptabiliteManager;
	}
}
