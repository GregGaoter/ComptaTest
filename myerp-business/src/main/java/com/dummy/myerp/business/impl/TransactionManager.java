package com.dummy.myerp.business.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.ParamMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * <p>
 * Classe de gestion des Transactions de persistance
 * </p>
 */
public class TransactionManager {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(TransactionManager.class);

	// ==================== Attributs Static ====================
	/** {@link PlatformTransactionManager} pour le DataSource MyERP */
	private static PlatformTransactionManager ptmMyERP;

	// ==================== Constructeurs ====================
	/**
	 * Instance unique de la classe (design pattern Singleton).<br>
	 * Le manager de comptabilité est instancié lors de l'initialisation de
	 * l'attribut.
	 */
	private static final TransactionManager INSTANCE = new TransactionManager();

	/**
	 * Renvoie l'instance unique de la classe (design pattern Singleton).
	 *
	 * @return {@link TransactionManager}
	 */
	public static TransactionManager getInstance() {
		return TransactionManager.INSTANCE;
	}

	/**
	 * Renvoie l'instance unique de la classe (design pattern Singleton).
	 *
	 * @param pPtmMyERP - {@link PlatformTransactionManager} pour le DataSource
	 *                  MyERP
	 * @return {@link TransactionManager}
	 */
	public static TransactionManager getInstance(PlatformTransactionManager pPtmMyERP) {
		ptmMyERP = pPtmMyERP;
		return TransactionManager.INSTANCE;
	}

	/**
	 * Constructeur.
	 */
	protected TransactionManager() {
		super();
	}

	// ==================== Méthodes ====================
	/**
	 * Démarre une transaction sur le DataSource MyERP
	 *
	 * @return TransactionStatus à passer aux méthodes :
	 *         <ul>
	 *         <li>{@link #commitMyERP(TransactionStatus)}</li>
	 *         <li>{@link #rollbackMyERP(TransactionStatus)}</li>
	 *         </ul>
	 * @see DefaultTransactionDefinition
	 */
	public TransactionStatus beginTransactionMyERP() {
		LOGGER.trace(new EntreeMessage());
		DefaultTransactionDefinition vTDef = new DefaultTransactionDefinition();
		vTDef.setName("Transaction_txManagerMyERP");
		vTDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		LOGGER.trace(new SortieMessage());
		return ptmMyERP.getTransaction(vTDef);
	}

	/**
	 * Commit la transaction sur le DataSource MyERP
	 *
	 * @param pTStatus retounré par la méthode {@link #beginTransactionMyERP()}
	 */
	public void commitMyERP(TransactionStatus pTStatus) {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new ParamMessage(Map.of("TransactionStatus pTStatus", pTStatus)));
		if (pTStatus != null) {
			ptmMyERP.commit(pTStatus);
		}
		LOGGER.trace(new SortieMessage());
	}

	/**
	 * Rollback la transaction sur le DataSource MyERP
	 *
	 * @param pTStatus retounré par la méthode {@link #beginTransactionMyERP()}
	 */
	public void rollbackMyERP(TransactionStatus pTStatus) {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new ParamMessage(Map.of("TransactionStatus pTStatus", pTStatus)));
		if (pTStatus != null) {
			ptmMyERP.rollback(pTStatus);
		}
		LOGGER.trace(new SortieMessage());
	}
}
