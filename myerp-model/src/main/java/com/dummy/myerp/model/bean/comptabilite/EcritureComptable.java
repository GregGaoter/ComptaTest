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

/**
 * Bean représentant une Écriture Comptable
 */
public class EcritureComptable {

	// ==================== Attributs ====================
	/** L'id de l'écriture comptable */
	private Integer id;
	/** Journal comptable */
	@NotNull
	private JournalComptable journal;
	/** La référence de l'écriture comptable */
	@Pattern(regexp = "\\d{1,5}-\\d{4}/\\d{5}")
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
	 * Calcul et renvoie le total des montants au débit des lignes d'écriture
	 *
	 * @return {@link BigDecimal}, {@link BigDecimal#ZERO} si aucun montant au débit
	 */
	// TODO à tester
	public BigDecimal getTotalDebit() {
		BigDecimal vRetour = BigDecimal.ZERO;
		for (LigneEcritureComptable vLigneEcritureComptable : listLigneEcriture) {
			if (vLigneEcritureComptable.getDebit() != null) {
				vRetour = vRetour.add(vLigneEcritureComptable.getDebit());
			}
		}
		return vRetour;
	}

	/**
	 * Calcul et renvoie le total des montants au crédit des lignes d'écriture
	 *
	 * @return {@link BigDecimal}, {@link BigDecimal#ZERO} si aucun montant au
	 *         crédit
	 */
	public BigDecimal getTotalCredit() {
		BigDecimal vRetour = BigDecimal.ZERO;
		for (LigneEcritureComptable vLigneEcritureComptable : listLigneEcriture) {
			if (vLigneEcritureComptable.getDebit() != null) {
				vRetour = vRetour.add(vLigneEcritureComptable.getDebit());
			}
		}
		return vRetour;
	}

	/**
	 * Renvoie si l'écriture est équilibrée (TotalDebit = TotalCrédit)
	 * 
	 * @return boolean
	 */
	public boolean isEquilibree() {
		boolean vRetour = this.getTotalDebit().equals(getTotalCredit());
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
