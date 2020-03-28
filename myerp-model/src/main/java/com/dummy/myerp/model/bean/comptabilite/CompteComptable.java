package com.dummy.myerp.model.bean.comptabilite;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Bean représentant un Compte Comptable
 */
public class CompteComptable {
	// ==================== Attributs ====================
	/** Numéro du compte comptable */
	@NotNull
	private Integer numero;

	/** Libellé du compte comptable */
	@NotNull
	@Size(min = 1, max = 150)
	private String libelle;

	// ==================== Constructeurs ====================
	/**
	 * Constructeur
	 */
	public CompteComptable() {
	}

	/**
	 * Constructeur
	 *
	 * @param pNumero - Numéro du compte comptable
	 */
	public CompteComptable(Integer pNumero) {
		numero = pNumero;
	}

	/**
	 * Constructeur
	 *
	 * @param pNumero  - Numéro du compte comptable
	 * @param pLibelle - Libellé du compte comptable
	 */
	public CompteComptable(Integer pNumero, String pLibelle) {
		numero = pNumero;
		libelle = pLibelle;
	}

	// ==================== Getters/Setters ====================
	/**
	 * Renvoie le numéro du compte comptable
	 *
	 * @return {@link Integer}
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * Initialise le numéro du compte comptable
	 * 
	 * @param pNumero - Numéro du compte comptable
	 */
	public void setNumero(Integer pNumero) {
		numero = pNumero;
	}

	/**
	 * Renvoie le libellé du compte comptable
	 *
	 * @return {@link String}
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Initialise le libellé du compte comptable
	 * 
	 * @param pLibelle - Libellé du compte comptable
	 */
	public void setLibelle(String pLibelle) {
		libelle = pLibelle;
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
		vStB.append("{").append("numero=").append(numero).append(vSEP).append("libelle='").append(libelle).append('\'')
				.append("}");
		return vStB.toString();
	}

	// ==================== Méthodes STATIC ====================
	/**
	 * Renvoie le {@link CompteComptable} de numéro {@code pNumero} s'il est présent
	 * dans la liste
	 *
	 * @param pList   - la liste où chercher le {@link CompteComptable}
	 * @param pNumero - le numero du {@link CompteComptable} à chercher
	 * @return {@link CompteComptable} ou {@code null}
	 */
	public static CompteComptable getByNumero(List<? extends CompteComptable> pList, Integer pNumero) {
		CompteComptable vRetour = null;
		for (CompteComptable vBean : pList) {
			if (vBean != null && Objects.equals(vBean.getNumero(), pNumero)) {
				vRetour = vBean;
				break;
			}
		}
		return vRetour;
	}
}
