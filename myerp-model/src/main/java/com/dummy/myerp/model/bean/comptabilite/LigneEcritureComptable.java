package com.dummy.myerp.model.bean.comptabilite;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dummy.myerp.model.validation.constraint.MontantComptable;

/**
 * Bean représentant une Ligne d'écriture comptable.
 */
public class LigneEcritureComptable implements Bean<LigneEcritureComptable> {

	// ==================== Attributs ====================
	/** {@link CompteComptable} de la ligne d'écriture comptable */
	@NotNull
	private CompteComptable compteComptable;

	/** Libellé de la ligne d'écriture comptable */
	@Size(max = 200)
	private String libelle;

	/** Débit de la ligne d'écriture comptable */
	@MontantComptable
	private BigDecimal debit;

	/** Crédit de la ligne d'écriture comptable */
	@MontantComptable
	private BigDecimal credit;

	// ==================== Constructeurs ====================
	/**
	 * Constructeur.
	 */
	public LigneEcritureComptable() {
	}

	/**
	 * Constructeur.
	 *
	 * @param pCompteComptable - {@link CompteComptable}
	 * @param pLibelle         - Libellé
	 * @param pDebit           - Débit
	 * @param pCredit          - Crédit
	 */
	public LigneEcritureComptable(CompteComptable pCompteComptable, String pLibelle, BigDecimal pDebit,
			BigDecimal pCredit) {
		compteComptable = pCompteComptable;
		libelle = pLibelle;
		debit = pDebit;
		credit = pCredit;
	}

	// ==================== Getters/Setters ====================
	/**
	 * Renvoie le {@link CompteComptable} de la ligne d'écriture comptable
	 * 
	 * @return Le {@link CompteComptable} de la ligne d'écriture comptable
	 */
	public CompteComptable getCompteComptable() {
		return compteComptable;
	}

	/**
	 * Initialise le {@link CompteComptable} de la ligne d'écriture comptable
	 * 
	 * @param pCompteComptable - de la ligne d'écriture comptable
	 */
	public void setCompteComptable(CompteComptable pCompteComptable) {
		compteComptable = pCompteComptable;
	}

	/**
	 * Renvoie le libellé de la ligne d'écriture comptable
	 * 
	 * @return Le libellé de la ligne d'écriture comptable
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Initialise le libellé de la ligne d'écriture comptable
	 * 
	 * @param pLibelle - Le libellé de la ligne d'écriture comptable
	 */
	public void setLibelle(String pLibelle) {
		libelle = pLibelle;
	}

	/**
	 * Renvoie le débit de la ligne d'écriture comptable
	 * 
	 * @return Le débit de la ligne d'écriture comptable
	 */
	public BigDecimal getDebit() {
		return debit;
	}

	/**
	 * Initialise le débit de la ligne d'écriture comptable
	 * 
	 * @param pDebit - Le débit de la ligne d'écriture comptable
	 */
	public void setDebit(BigDecimal pDebit) {
		debit = pDebit;
	}

	/**
	 * Renvoie le crédit de la ligne d'écriture comptable
	 * 
	 * @return Le crédit de la ligne d'écriture comptable
	 */
	public BigDecimal getCredit() {
		return credit;
	}

	/**
	 * Initialise le crédit de la ligne d'écriture comptable
	 * 
	 * @param pCredit - Le crédit de la ligne d'écriture comptable
	 */
	public void setCredit(BigDecimal pCredit) {
		credit = pCredit;
	}

	// ==================== Méthodes ====================
	/**
	 * {@inheritDoc}
	 * 
	 * @see StringBuilder
	 */
	@Override
	public String toString() {
		final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
		final String vSEP = ", ";
		vStB.append("{").append("compteComptable=").append(compteComptable).append(vSEP).append("libelle='")
				.append(libelle).append('\'').append(vSEP).append("debit=").append(debit).append(vSEP).append("credit=")
				.append(credit).append("}");
		return vStB.toString();
	}

	@Override
	public LigneEcritureComptable deepCopy() {
		CompteComptable compte = this.compteComptable != null ? this.compteComptable.deepCopy() : null;
		BigDecimal debit = this.debit != null
				? BigDecimal.valueOf(this.debit.unscaledValue().longValue(), this.debit.scale())
				: null;
		BigDecimal credit = this.credit != null
				? BigDecimal.valueOf(this.credit.unscaledValue().longValue(), this.credit.scale())
				: null;
		return new LigneEcritureComptable(compte, libelle, debit, credit);
	}
}
