package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import com.dummy.myerp.business.contrat.manager.ComptabiliteManager;
import com.dummy.myerp.business.impl.AbstractBusinessManager;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.technical.log.message.DebugMessage;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.ErrorMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * Comptabilite manager implementation.<br>
 * Cette classe hérite de {@link AbstractBusinessManager} et implémente
 * {@link ComptabiliteManager}.
 */
@Service
public class ComptabiliteManagerImpl extends AbstractBusinessManager implements ComptabiliteManager {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(ComptabiliteManagerImpl.class);

	// ==================== Attributs ====================

	// ==================== Constructeurs ====================
	/**
	 * Constructeur.<br>
	 * Instantiates a new Comptabilite manager.
	 */
	public ComptabiliteManagerImpl() {
	}

	// ==================== Getters/Setters ====================
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CompteComptable> getListCompteComptable() {
		return getDaoProxy().getComptabiliteDao().getListCompteComptable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<JournalComptable> getListJournalComptable() {
		return getDaoProxy().getComptabiliteDao().getListJournalComptable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EcritureComptable> getListEcritureComptable() {
		return getDaoProxy().getComptabiliteDao().getListEcritureComptable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SequenceEcritureComptable> getListSequenceEcritureComptable() {
		return getDaoProxy().getComptabiliteDao().getListSequenceEcritureComptable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void addReference(EcritureComptable pEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		// Bien se réferer à la JavaDoc de cette méthode !
		/*
		 * Le principe : 1. Remonter depuis la persitance la dernière valeur de la
		 * séquence du journal pour l'année de l'écriture (table
		 * sequence_ecriture_comptable) 2. * S'il n'y a aucun enregistrement pour le
		 * journal pour l'année concernée : 1. Utiliser le numéro 1. Sinon : 1. Utiliser
		 * la dernière valeur + 1 3. Mettre à jour la référence de l'écriture avec la
		 * référence calculée (RG_Compta_5) 4. Enregistrer (insert/update) la valeur de
		 * la séquence en persitance (table sequence_ecriture_comptable)
		 */

		if (pEcritureComptable != null) {
			// Paramètres
			JournalComptable journal = pEcritureComptable.getJournal();
			String codeJournal = journal.getCode();
			Date dateEcriture = pEcritureComptable.getDate();
			Integer anneeEcriture = Integer.valueOf(getYear(dateEcriture));
			SequenceEcritureComptable sequence = new SequenceEcritureComptable();

			// Dernière valeur de la séquence
			String prochaineValeurSequence = "00001";
			for (SequenceEcritureComptable seq : getListSequenceEcritureComptable()) {
				if (seq.getJournalCode().equals(codeJournal) && seq.getAnnee().equals(anneeEcriture)) {
					prochaineValeurSequence = String.format("%05d", seq.getDerniereValeur() + 1);
					sequence.setAnnee(seq.getAnnee());
					sequence.setDerniereValeur(seq.getDerniereValeur() + 1);
					sequence.setJournalCode(seq.getJournalCode());
					break;
				}
			}

			// Référence
			String reference = codeJournal + "-" + anneeEcriture + "/" + prochaineValeurSequence;
			pEcritureComptable.setReference(reference);

			// Persistance de la valeur de la séquence
			if (prochaineValeurSequence.equals("00001")) {
				sequence.setAnnee(anneeEcriture);
				sequence.setDerniereValeur(1);
				sequence.setJournalCode(codeJournal);
				insertSequenceEcritureComptable(sequence);
			} else {
				updateSequenceEcritureComptable(sequence);
			}
		}

		LOGGER.trace(new SortieMessage());
	}

	/**
	 * Renvoie l'année d'une {@link Date}.
	 * 
	 * @param date Date
	 * @return L'année de la {@link Date}.
	 */
	private int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void checkEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException {
		LOGGER.trace(new EntreeMessage());
		this.checkEcritureComptableUnit(pEcritureComptable);
		this.checkEcritureComptableContext(pEcritureComptable);
		LOGGER.trace(new SortieMessage());
	}

	/**
	 * Vérifie que l'écriture comptable respecte les règles de gestion unitaires,
	 * c'est à dire indépendemment du contexte (unicité de la référence, exercie
	 * comptable non cloturé...)
	 *
	 * @param pEcritureComptable - L'écriture comptable sur laquelle vérifier les
	 *                           règles de gestion unitaires.
	 * @throws FunctionalException Si l'écriture comptable ne respecte pas les
	 *                             règles de gestion unitaires
	 */
	// TODO tests à compléter
	protected void checkEcritureComptableUnit(EcritureComptable pEcritureComptable) throws FunctionalException {
		LOGGER.trace(new EntreeMessage());
		checkEcritureComptableUnitViolation(pEcritureComptable);
		checkEcritureComptableUnitRG2(pEcritureComptable);
		checkEcritureComptableUnitRG3(pEcritureComptable);
		checkEcritureComptableUnitReference(pEcritureComptable);
		LOGGER.trace(new SortieMessage());
	}

	/**
	 * Vérifie les contraintes unitaires sur les attributs de l'écriture.
	 * 
	 * @param pEcritureComptable L'écriture comptable.
	 */
	protected void checkEcritureComptableUnitViolation(EcritureComptable pEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		Set<ConstraintViolation<EcritureComptable>> vViolations = getConstraintValidator().validate(pEcritureComptable);
		if (!vViolations.isEmpty()) {
			try {
				throw new FunctionalException("L'écriture comptable ne respecte pas les règles de gestion.",
						new ConstraintViolationException(
								"L'écriture comptable ne respecte pas les contraintes de validation", vViolations));
			} catch (FunctionalException e) {
				LOGGER.error(new ErrorMessage(e));
			}
		}
		LOGGER.trace(new SortieMessage());
	}

	/**
	 * Vérifie que l'écriture comptable est équilibrée.
	 * 
	 * @param pEcritureComptable L'écriture comptable
	 */
	protected void checkEcritureComptableUnitRG2(EcritureComptable pEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		if (!pEcritureComptable.isEquilibree()) {
			try {
				throw new FunctionalException("L'écriture comptable n'est pas équilibrée.");
			} catch (FunctionalException e) {
				LOGGER.error(new ErrorMessage(e));
			}
		}
		LOGGER.trace(new SortieMessage());
	}

	/**
	 * Vérifie que l'écriture comptable a au moins 2 lignes d'écriture (1 au débit,
	 * 1 au crédit)
	 * 
	 * @param pEcritureComptable L'écriture comptable
	 */
	protected void checkEcritureComptableUnitRG3(EcritureComptable pEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		int vNbrCredit = 0;
		int vNbrDebit = 0;
		for (LigneEcritureComptable vLigneEcritureComptable : pEcritureComptable.getListLigneEcriture()) {
			if (BigDecimal.ZERO
					.compareTo(ObjectUtils.defaultIfNull(vLigneEcritureComptable.getCredit(), BigDecimal.ZERO)) != 0) {
				vNbrCredit++;
			}
			if (BigDecimal.ZERO
					.compareTo(ObjectUtils.defaultIfNull(vLigneEcritureComptable.getDebit(), BigDecimal.ZERO)) != 0) {
				vNbrDebit++;
			}
		}
		// On test le nombre de lignes car si l'écriture à une seule ligne
		// avec un montant au débit et un montant au crédit ce n'est pas valable
		if (pEcritureComptable.getListLigneEcriture().size() < 2 || vNbrCredit < 1 || vNbrDebit < 1) {
			try {
				throw new FunctionalException(
						"L'écriture comptable doit avoir au moins deux lignes : une ligne au débit et une ligne au crédit.");
			} catch (FunctionalException e) {
				LOGGER.error(new ErrorMessage(e));
			}
		}
		LOGGER.trace(new SortieMessage());
	}

	/**
	 * Vérifie le format de la référence, que l'année dans la référence correspond
	 * bien à la date de l'écriture, idem pour le code journal...
	 * 
	 * @param pEcritureComptable L'écriture comptable
	 */
	protected void checkEcritureComptableUnitReference(EcritureComptable pEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		String reference = pEcritureComptable.getReference();

		// Format
		if (!isEcritureComptableReferenceFormatValid(pEcritureComptable)) {
			try {
				throw new FunctionalException("Le format de la référence est invalide.");
			} catch (FunctionalException e) {
				LOGGER.error(new ErrorMessage(e));
			}
		} else {
			String[] referenceArray = reference.split("-|/");

			// Année
			if (!isEcritureComptableReferenceAnneeValid(pEcritureComptable)) {
				try {
					throw new FunctionalException("L'année de la référence ne correspond pas à la date de l'écriture.");
				} catch (FunctionalException e) {
					LOGGER.error(new ErrorMessage(e));
				}
			}

			// Code journal
			if (!isEcritureComptableReferenceCodeJournalValid(pEcritureComptable)) {
				try {
					throw new FunctionalException(
							"Le code journal de la référence ne correspond pas au code journal de l'écriture.");
				} catch (FunctionalException e) {
					LOGGER.error(new ErrorMessage(e));
				}
			}

			// Numéro de séquence
			Integer valeurSequence = getDerniereValeurNumeroSequence(pEcritureComptable);
			if (valeurSequence == null || Integer.valueOf(referenceArray[2]).compareTo(valeurSequence) > 0) {
				try {
					throw new FunctionalException("Le numéro de séquence de la référence n'est pas valide.");
				} catch (FunctionalException e) {
					LOGGER.error(new ErrorMessage(e));
				}
			}
		}
		LOGGER.trace(new SortieMessage());
	}

	/**
	 * Renvoie la dernière valeur du numéro de séquence correspondant au journal et
	 * à l'année de l'écriture comptable.
	 * 
	 * @param pEcritureComptable L'écriture comptable
	 * @return La dernière valeur du numéro de séquence ou null si aucune séquence
	 *         ne correspond au journal et à l'année de l'écriture.
	 */
	protected Integer getDerniereValeurNumeroSequence(EcritureComptable pEcritureComptable) {
		Integer valeurSequence = null;
		for (SequenceEcritureComptable seq : getListSequenceEcritureComptable()) {
			if (seq.getJournalCode().equals(pEcritureComptable.getJournal().getCode())
					&& seq.getAnnee().equals(Integer.valueOf(getYear(pEcritureComptable.getDate())))) {
				valeurSequence = seq.getDerniereValeur();
				break;
			}
		}
		return valeurSequence;
	}

	/**
	 * Vérifie si le format de la référence d'une écriture comptable est valide.
	 * 
	 * @param pEcritureComptable L'écriture comptable
	 * @return true si le format est valide, false autrement.
	 */
	protected boolean isEcritureComptableReferenceFormatValid(EcritureComptable pEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		LOGGER.trace(new SortieMessage());
		return pEcritureComptable.getReference().matches("\\d{1,5}-\\d{4}/\\d{5}");
	}

	/**
	 * Vérifie si l'année de la référence d'une écriture comptable est valide.
	 * 
	 * @param pEcritureComptable L'écriture comptable
	 * @return true si l'année est valide, false si le format ou l'année ne sont pas
	 *         valides.
	 */
	protected boolean isEcritureComptableReferenceAnneeValid(EcritureComptable pEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		if (isEcritureComptableReferenceFormatValid(pEcritureComptable)) {
			String reference = pEcritureComptable.getReference();
			String annee = reference.split("-|/")[0];
			return annee.equals(String.valueOf(getYear(pEcritureComptable.getDate())));
		}
		LOGGER.trace(new SortieMessage());
		return false;
	}

	/**
	 * Vérifie si le code journal de la référence d'une écriture comptable est
	 * valide.
	 * 
	 * @param pEcritureComptable L'écriture comptable
	 * @return true si le code journal est valide, false si le format ou le code
	 *         journal ne sont pas valides.
	 */
	protected boolean isEcritureComptableReferenceCodeJournalValid(EcritureComptable pEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		if (isEcritureComptableReferenceFormatValid(pEcritureComptable)) {
			String reference = pEcritureComptable.getReference();
			String codeJournal = reference.split("-|/")[1];
			return codeJournal.equals(pEcritureComptable.getJournal().getCode());
		}
		LOGGER.trace(new SortieMessage());
		return false;
	}

	/**
	 * Vérifie que l'écriture comptable respecte les règles de gestion liées au
	 * contexte (unicité de la référence, année comptable non cloturé...)
	 *
	 * @param pEcritureComptable - L'écriture comptable sur laquelle vérifier les
	 *                           règles de gestion liées au contexte.
	 * @throws FunctionalException Si l'écriture comptable ne respecte pas les
	 *                             règles de gestion liées au contexte
	 */
	protected void checkEcritureComptableContext(EcritureComptable pEcritureComptable) throws FunctionalException {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new DebugMessage("EcritureComptable pEcritureComptable", pEcritureComptable));
		// ===== RG_Compta_6 : La référence d'une écriture comptable doit être unique
		LOGGER.debug(new DebugMessage("pEcritureComptable.getReference()", pEcritureComptable.getReference()));
		if (StringUtils.isNoneEmpty(pEcritureComptable.getReference())) {
			try {
				// Recherche d'une écriture ayant la même référence
				EcritureComptable vECRef = getDaoProxy().getComptabiliteDao()
						.getEcritureComptableByRef(pEcritureComptable.getReference());

				// Si l'écriture à vérifier est une nouvelle écriture (id == null),
				// ou si elle ne correspond pas à l'écriture trouvée (id != idECRef),
				// c'est qu'il y a déjà une autre écriture avec la même référence
				LOGGER.debug(new DebugMessage("pEcritureComptable.getId()", pEcritureComptable.getId()));
				LOGGER.debug(new DebugMessage("vECRef.getId()", vECRef.getId()));
				if (pEcritureComptable.getId() == null || !pEcritureComptable.getId().equals(vECRef.getId())) {
					try {
						throw new FunctionalException(
								"Une autre écriture comptable existe déjà avec la même référence.");
					} catch (FunctionalException e) {
						LOGGER.error(new ErrorMessage(e));
					}
				}
			} catch (NotFoundException vEx) {
				// Dans ce cas, c'est bon, ça veut dire qu'on n'a aucune autre écriture avec la
				// même référence.
			}
		}
		LOGGER.trace(new SortieMessage());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new DebugMessage("EcritureComptable pEcritureComptable", pEcritureComptable));
		this.checkEcritureComptable(pEcritureComptable);
		TransactionStatus vTS = getTransactionManager().beginTransactionMyERP();
		try {
			getDaoProxy().getComptabiliteDao().insertEcritureComptable(pEcritureComptable);
			getTransactionManager().commitMyERP(vTS);
			vTS = null;
		} finally {
			getTransactionManager().rollbackMyERP(vTS);
		}
		LOGGER.trace(new SortieMessage());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new DebugMessage("EcritureComptable pEcritureComptable", pEcritureComptable));
		TransactionStatus vTS = getTransactionManager().beginTransactionMyERP();
		try {
			getDaoProxy().getComptabiliteDao().updateEcritureComptable(pEcritureComptable);
			getTransactionManager().commitMyERP(vTS);
			vTS = null;
		} finally {
			getTransactionManager().rollbackMyERP(vTS);
		}
		LOGGER.trace(new SortieMessage());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteEcritureComptable(Integer pId) {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new DebugMessage("Integer pId", pId));
		TransactionStatus vTS = getTransactionManager().beginTransactionMyERP();
		try {
			getDaoProxy().getComptabiliteDao().deleteEcritureComptable(pId);
			getTransactionManager().commitMyERP(vTS);
			vTS = null;
		} finally {
			getTransactionManager().rollbackMyERP(vTS);
		}
		LOGGER.trace(new SortieMessage());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertSequenceEcritureComptable(SequenceEcritureComptable pSequenceEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		TransactionStatus vTS = getTransactionManager().beginTransactionMyERP();
		try {
			getDaoProxy().getComptabiliteDao().insertSequenceEcritureComptable(pSequenceEcritureComptable);
			getTransactionManager().commitMyERP(vTS);
			vTS = null;
		} finally {
			getTransactionManager().rollbackMyERP(vTS);
		}
		LOGGER.trace(new SortieMessage());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateSequenceEcritureComptable(SequenceEcritureComptable pSequenceEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		TransactionStatus vTS = getTransactionManager().beginTransactionMyERP();
		try {
			getDaoProxy().getComptabiliteDao().updateSequenceEcritureComptable(pSequenceEcritureComptable);
			getTransactionManager().commitMyERP(vTS);
			vTS = null;
		} finally {
			getTransactionManager().rollbackMyERP(vTS);
		}
		LOGGER.trace(new SortieMessage());
	}

}
