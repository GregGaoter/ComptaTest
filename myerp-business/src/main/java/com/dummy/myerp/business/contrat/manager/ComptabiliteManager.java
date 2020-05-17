package com.dummy.myerp.business.contrat.manager;

import java.util.List;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;

/**
 * Interface du manager du package comptabilite.
 */
public interface ComptabiliteManager {

	/**
	 * Renvoie la liste des comptes comptables.
	 *
	 * @return {@link List}
	 */
	List<CompteComptable> getListCompteComptable();

	/**
	 * Renvoie la liste des journaux comptables.
	 *
	 * @return {@link List}
	 */
	List<JournalComptable> getListJournalComptable();

	/**
	 * Renvoie la liste des écritures comptables.
	 *
	 * @return {@link List}
	 */
	List<EcritureComptable> getListEcritureComptable();

	/**
	 * Renvoie la liste des séquences des écritures comptables.
	 *
	 * @return {@link List}
	 */
	List<SequenceEcritureComptable> getListSequenceEcritureComptable();

	/**
	 * Ajoute une référence à l'écriture comptable.<br>
	 *
	 * <strong>RG_Compta_5 : </strong> La référence d'une écriture comptable est
	 * composée du code du journal dans lequel figure l'écriture suivi de l'année et
	 * d'un numéro de séquence (propre à chaque journal) sur 5 chiffres incrémenté
	 * automatiquement à chaque écriture. Le formatage de la référence est :
	 * XX-AAAA/#####. <br>
	 * Ex : Journal de banque (BQ), écriture au 31/12/2016
	 * 
	 * <pre>
	 * BQ - 2016 / 00001
	 * </pre>
	 *
	 * <p>
	 * <strong>Attention :</strong> l'écriture n'est pas enregistrée en persistance
	 * </p>
	 * 
	 * @param pEcritureComptable - L'écriture comptable sur laquelle ajouter une
	 *                           référence.
	 */
	void addReference(EcritureComptable pEcritureComptable);

	/**
	 * Vérifie que l'écriture comptable respecte les règles de gestion suivantes :
	 * <ul>
	 * <li><strong>RG_Compta_2 : </strong>pour qu'une écriture comptable soit
	 * valide, elle doit être équilibrée : la somme des montants au crédit des
	 * lignes d'écriture doit être égale à la somme des montants au débit.</li>
	 * <li><strong>RG_Compta_3 : </strong>une écriture comptable doit contenir au
	 * moins deux lignes d'écriture : une au débit et une au crédit.</li>
	 * </ul>
	 *
	 * @param pEcritureComptable - L'écriture comptable sur laquelle vérifier les
	 *                           règles de gestion.
	 * @throws FunctionalException Si l'écriture comptable ne respecte pas les
	 *                             règles de gestion
	 */
	void checkEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException;

	/**
	 * Insert une nouvelle écriture comptable à la liste des écritures comptables.
	 *
	 * @param pEcritureComptable - L'écriture comptable à insérer.
	 * @throws FunctionalException Si l'ecriture comptable ne respecte pas les
	 *                             règles de gestion
	 * @see ComptabiliteManager#checkEcritureComptable
	 */
	void insertEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException;

	/**
	 * Met à jour l'écriture comptable.
	 *
	 * @param pEcritureComptable - L'écriture comptable à mettre à jour.
	 * @throws FunctionalException Si l'écriture comptable ne respecte pas les
	 *                             règles de gestion
	 * @see ComptabiliteManager#checkEcritureComptable
	 */
	void updateEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException;

	/**
	 * Supprime l'écriture comptable d'id {@code pId}.
	 *
	 * @param pId - L'id de l'écriture comptable.
	 */
	void deleteEcritureComptable(Integer pId);

	/**
	 * Insert une nouvelle séquence d'écriture comptable à la liste des séquences
	 * d'écritures comptables.
	 *
	 * @param pSequenceEcritureComptable - La séquence d'écriture comptable à
	 *                                   insérer.
	 */
	void insertSequenceEcritureComptable(SequenceEcritureComptable pSequenceEcritureComptable);

	/**
	 * Met à jour la séquence d'écriture comptable.
	 *
	 * @param pSequenceEcritureComptable - La séquence d'écriture comptable à mettre
	 *                                   à jour.
	 */
	void updateSequenceEcritureComptable(SequenceEcritureComptable pSequenceEcritureComptable);
}
