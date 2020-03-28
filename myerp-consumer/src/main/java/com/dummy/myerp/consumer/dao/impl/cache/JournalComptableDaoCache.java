package com.dummy.myerp.consumer.dao.impl.cache;

import java.util.List;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;

/**
 * Cache DAO de {@link JournalComptable}
 */
public class JournalComptableDaoCache {

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
		if (listJournalComptable == null) {
			listJournalComptable = ConsumerHelper.getDaoProxy().getComptabiliteDao().getListJournalComptable();
		}

		JournalComptable vRetour = JournalComptable.getByCode(listJournalComptable, pCode);
		return vRetour;
	}
}
