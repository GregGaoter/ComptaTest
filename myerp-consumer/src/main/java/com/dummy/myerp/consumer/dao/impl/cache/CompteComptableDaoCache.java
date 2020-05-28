package com.dummy.myerp.consumer.dao.impl.cache;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.technical.log.message.DebugMessage;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * Cache DAO de {@link CompteComptable}
 */
public class CompteComptableDaoCache {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(CompteComptableDaoCache.class);

	// ==================== Attributs ====================
	/** La liste des comptes comptables. */
	private List<CompteComptable> listCompteComptable;

	// ==================== Constructeurs ====================
	/**
	 * Constructeur.
	 */
	public CompteComptableDaoCache() {
	}

	// ==================== Méthodes ====================
	/**
	 * Renvoie le compte comptable de numéro {@code pNumero}.
	 *
	 * @param pNumero le numéro du {@link CompteComptable}
	 * @return {@link CompteComptable} ou {@code null}
	 */
	public CompteComptable getByNumero(Integer pNumero) {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new DebugMessage("Integer pNumero", pNumero));
		if (listCompteComptable == null) {
			listCompteComptable = ConsumerHelper.getDaoProxy().getComptabiliteDao().getListCompteComptable();
		}
		CompteComptable vRetour = pNumero == null || listCompteComptable.isEmpty() ? null
				: CompteComptable.getByNumero(listCompteComptable, pNumero);
		LOGGER.trace(new SortieMessage());
		return vRetour;
	}
}
