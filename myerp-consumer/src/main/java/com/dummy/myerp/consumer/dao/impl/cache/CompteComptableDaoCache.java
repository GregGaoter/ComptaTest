package com.dummy.myerp.consumer.dao.impl.cache;

import java.util.List;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;

/**
 * Cache DAO de {@link CompteComptable}
 */
public class CompteComptableDaoCache {

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
		if (listCompteComptable == null) {
			listCompteComptable = ConsumerHelper.getDaoProxy().getComptabiliteDao().getListCompteComptable();
		}

		CompteComptable vRetour = CompteComptable.getByNumero(listCompteComptable, pNumero);
		return vRetour;
	}
}
