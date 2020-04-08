package com.dummy.myerp.consumer.dao.impl.cache;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.technical.log.message.DebugMessage;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * Cache DAO de {@link JournalComptable}
 */
public class JournalComptableDaoCache {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(JournalComptableDaoCache.class);

	// ==================== Attributs ====================
	/** La liste des journaux comptables. */
	private List<JournalComptable> listJournalComptable;

	// ==================== Constructeurs ====================
	/**
	 * Constructeur.
	 */
	public JournalComptableDaoCache() {
	}

	// ==================== Méthodes ====================
	/**
	 * Renvoie le journal comptable de code {@code pCode}.
	 *
	 * @param pCode le code du {@link JournalComptable}
	 * @return {@link JournalComptable} ou {@code null}
	 */
	public JournalComptable getByCode(String pCode) {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new DebugMessage("String pCode", pCode));
		if (listJournalComptable == null) {
			listJournalComptable = ConsumerHelper.getDaoProxy().getComptabiliteDao().getListJournalComptable();
		}
		JournalComptable vRetour = JournalComptable.getByCode(listJournalComptable, pCode);
		LOGGER.trace(new SortieMessage());
		return vRetour;
	}
}
