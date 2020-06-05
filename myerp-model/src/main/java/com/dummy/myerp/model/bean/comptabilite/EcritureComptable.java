package com.dummy.myerp.model.bean.comptabilite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dummy.myerp.technical.log.message.DebugMessage;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * Bean représentant une Écriture Comptable
 */
public class EcritureComptable {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(EcritureComptable.class);

	// ==================== Attributs ====================
	/** L'id de l'écriture comptable */
	private Integer id;

	/** Journal comptable */
	@NotNull
	private JournalComptable journal;

	/** La référence de l'écriture comptable */
	// @Pattern(regexp = "\\d{1,5}-\\d{4}/\\d{5}")
	@Pattern(regexp = "\\D{1,5}-\\d{4}/\\d{5}")
	private String reference;

	/** La date de l'écriture comptable */
	@NotNull
	private Date date;

	/** Le libellé de l'écriture comptable */
	@NotNull
	@Size(min = 1, max = 200)
	private String libelle;

	/** La liste des lignes d'écriture comptable. */
	@Valid
	@Size(min = 2)
	private final List<LigneEcritureComptable> listLigneEcriture = new ArrayList<>();

	// ==================== Constructeurs ====================
	/**
	 * Constructeur.
	 */
	public EcritureComptable() {
		super();
	}

	/**
	 * Constructeur.
	 */
	public EcritureComptable(String reference) {
		this.reference = reference;
	}

	/**
	 * Constructeur.
	 */
	public EcritureComptable(Integer id) {
		this.id = id;
	}

	/**
	 * Constructeur.
	 */
	public EcritureComptable(Integer id, String reference) {
		this.id = id;
		this.reference = reference;
	}

	/**
	 * Constructeur.
	 */
	public EcritureComptable(Integer id, JournalComptable journal) {
		this.id = id;
		this.journal = journal;
	}

	/**
	 * Constructeur.
	 */
	public EcritureComptable(JournalComptable journal) {
		this.journal = journal;
	}

	/**
	 * Constructeur.
	 */
	public EcritureComptable(List<LigneEcritureComptable> listLigneEcriture) {
		this.listLigneEcriture.clear();
		this.listLigneEcriture.addAll(listLigneEcriture);
	}

	// ==================== Getters/Setters ====================
	/**
	 * Renvoie l'id de l'écriture comptable
	 * 
	 * @return L'id de l'écriture comptable
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Initialise l'id de l'écriture comptable
	 * 
	 * @param pId - L'id de l'écriture comptable
	 */
	public void setId(Integer pId) {
		id = pId;
	}

	/**
	 * Renvoie le journal comptable
	 * 
	 * @return Le journal comptable
	 */
	public JournalComptable getJournal() {
		return journal;
	}

	/**
	 * Initialise le journal comptable
	 * 
	 * @param pJournal - Le journal comptable
	 */
	public void setJournal(JournalComptable pJournal) {
		journal = pJournal;
	}

	/**
	 * Renvoie la référence de l'écriture comptable
	 * 
	 * @return La référence de l'écriture comptable
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Initialise la référence de l'écriture comptable
	 * 
	 * @param pReference - La référence de l'écriture comptable
	 */
	public void setReference(String pReference) {
		reference = pReference;
	}

	/**
	 * Renvoie la date de l'écriture comptable
	 * 
	 * @return La date de l'écriture comptable
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Initialise la date de l'écriture comptable
	 * 
	 * @param pDate - La date de l'écriture comptable
	 */
	public void setDate(Date pDate) {
		date = pDate;
	}

	/**
	 * Renvoie le libellé de l'écriture comptable
	 * 
	 * @return Le libellé de l'écriture comptable
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Initialise le libellé de l'écriture comptable
	 * 
	 * @param pLibelle - Le libellé de l'écriture comptable
	 */
	public void setLibelle(String pLibelle) {
		libelle = pLibelle;
	}

	/**
	 * Renvoie les lignes d'écriture de l'écriture comptable
	 * 
	 * @return Les lignes d'écriture de l'écriture comptable
	 */
	public List<LigneEcritureComptable> getListLigneEcriture() {
		return listLigneEcriture;
	}

	/**
	 * Initialise les lignes d'écriture de l'écriture comptable
	 * 
	 * @param listLigneEcriture - La liste des lignes d'écriture
	 */
	public void setListLigneEcriture(List<LigneEcritureComptable> listLigneEcriture) {
		this.listLigneEcriture.clear();
		this.listLigneEcriture.addAll(listLigneEcriture);
	}

	/**
	 * Calcul et renvoie le total des montants au débit des lignes d'écriture
	 *
	 * @return {@link BigDecimal}, {@link BigDecimal#ZERO} si aucun montant au débit
	 */
	public BigDecimal getTotalDebit() {
		LOGGER.trace(new EntreeMessage());
		BigDecimal vRetour = BigDecimal.ZERO;
		for (LigneEcritureComptable vLigneEcritureComptable : listLigneEcriture) {
			if (vLigneEcritureComptable.getDebit() != null) {
				vRetour = vRetour.add(vLigneEcritureComptable.getDebit());
			}
		}
		LOGGER.debug(new DebugMessage("BigDecimal getTotalDebit()", vRetour));
		LOGGER.trace(new SortieMessage());
		return vRetour;
	}

	/**
	 * Calcul et renvoie le total des montants au crédit des lignes d'écriture
	 *
	 * @return {@link BigDecimal}, {@link BigDecimal#ZERO} si aucun montant au
	 *         crédit
	 */
	public BigDecimal getTotalCredit() {
		LOGGER.trace(new EntreeMessage());
		BigDecimal vRetour = BigDecimal.ZERO;
		for (LigneEcritureComptable vLigneEcritureComptable : listLigneEcriture) {
			if (vLigneEcritureComptable.getCredit() != null) {
				vRetour = vRetour.add(vLigneEcritureComptable.getCredit());
			}
		}
		LOGGER.debug(new DebugMessage("BigDecimal getTotalCredit()", vRetour));
		LOGGER.trace(new SortieMessage());
		return vRetour;
	}

	/**
	 * Renvoie si l'écriture est équilibrée (TotalDebit = TotalCrédit)
	 * 
	 * @return boolean
	 */
	public boolean isEquilibree() {
		LOGGER.trace(new EntreeMessage());
		boolean vRetour = false;
		if (getTotalDebit() != null && getTotalCredit() != null) {
			vRetour = getTotalDebit().equals(getTotalCredit());
		}
		LOGGER.debug(new DebugMessage("getTotalCredit()", getTotalCredit()));
		LOGGER.debug(new DebugMessage("getTotalDebit()", getTotalDebit()));
		LOGGER.debug(new DebugMessage("isEquilibree()", vRetour));
		LOGGER.trace(new SortieMessage());
		return vRetour;
	}

	// ==================== Méthodes ====================
	/**
	 * {@inheritDoc}
	 * 
	 * @see StringBuilder
	 * @see StringUtils
	 */
	@Override
	public String toString() {
		final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
		final String vSEP = ", ";
		vStB.append("{").append("id=").append(id).append(vSEP).append("journal=").append(journal).append(vSEP)
				.append("reference='").append(reference).append('\'').append(vSEP).append("date=").append(date)
				.append(vSEP).append("libelle='").append(libelle).append('\'').append(vSEP).append("totalDebit=")
				.append(this.getTotalDebit().toPlainString()).append(vSEP).append("totalCredit=")
				.append(this.getTotalCredit().toPlainString()).append(vSEP).append("listLigneEcriture=[\n")
				.append(StringUtils.join(listLigneEcriture, "\n")).append("\n]").append("}");
		return vStB.toString();
	}

}
