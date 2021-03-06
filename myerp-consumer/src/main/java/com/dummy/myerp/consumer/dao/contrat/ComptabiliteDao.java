package com.dummy.myerp.consumer.dao.contrat;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.SequenceEcritureComptableRM;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.NotFoundException;

/**
 * Interface de DAO des objets du package Comptabilite
 */
public interface ComptabiliteDao {

	/**
	 * Renvoie la liste des Comptes Comptables
	 * 
	 * @return {@link List}
	 */
	List<CompteComptable> getListCompteComptable();

	/**
	 * Renvoie la liste des Journaux Comptables
	 * 
	 * @return {@link List}
	 */
	List<JournalComptable> getListJournalComptable();

	// ==================== EcritureComptable ====================

	/**
	 * Renvoie la liste des Écritures Comptables
	 * 
	 * @return {@link List}
	 */
	List<EcritureComptable> getListEcritureComptable();

	/**
	 * Renvoie l'Écriture Comptable d'id {@code pId}.
	 *
	 * @param pId - l'id de l'écriture comptable
	 * @return {@link EcritureComptable}
	 * @throws NotFoundException Si l'écriture comptable n'est pas trouvée
	 */
	EcritureComptable getEcritureComptable(Integer pId) throws NotFoundException;

	/**
	 * Renvoie l'Écriture Comptable de référence {@code pRef}.
	 *
	 * @param pReference - la référence de l'écriture comptable
	 * @return {@link EcritureComptable}
	 * @throws NotFoundException Si l'écriture comptable n'est pas trouvée
	 */
	EcritureComptable getEcritureComptableByRef(String pReference) throws NotFoundException;

	/**
	 * Charge la liste des lignes d'écriture de l'écriture comptable
	 * {@code pEcritureComptable}
	 *
	 * @param pEcritureComptable - l'écriture comptable à partir de laquelle charger
	 *                           les lignes d'écriture
	 */
	void loadListLigneEcriture(EcritureComptable pEcritureComptable);

	/**
	 * Insert une nouvelle écriture comptable.
	 *
	 * @param pEcritureComptable - l'écriture comptable à insérer
	 */
	void insertEcritureComptable(EcritureComptable pEcritureComptable);

	/**
	 * Met à jour l'écriture comptable.
	 *
	 * @param pEcritureComptable - l'écriture comptable à mettre à jour
	 */
	void updateEcritureComptable(EcritureComptable pEcritureComptable);

	/**
	 * Supprime l'écriture comptable d'id {@code pId}.
	 *
	 * @param pId l'id de l'écriture comptable à supprimer
	 */
	void deleteEcritureComptable(Integer pId);

	/**
	 * {@inheritDoc}
	 * 
	 * @see JdbcTemplate
	 * @see SequenceEcritureComptableRM
	 */
	List<SequenceEcritureComptable> getListSequenceEcritureComptable();

	/**
	 * Insert une nouvelle séquence d'écriture comptable.
	 *
	 * @param pSequenceEcritureComptable - la séquence d'écriture comptable à
	 *                                   insérer
	 */
	void insertSequenceEcritureComptable(SequenceEcritureComptable pSequenceEcritureComptable);

	/**
	 * Met à jour la séquence d'écriture comptable.
	 *
	 * @param pSequenceEcritureComptable - la séquence d'écriture comptable à mettre
	 *                                   à jour
	 */
	void updateSequenceEcritureComptable(SequenceEcritureComptable pSequenceEcritureComptable);

	/**
	 * {@inheritDoc}
	 * 
	 * @see JdbcTemplate
	 * @see LigneEcritureComptableRM
	 */
	List<LigneEcritureComptable> getListLigneEcritureComptable();
}
