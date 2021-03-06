package com.dummy.myerp.model.bean.comptabilite;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dummy.myerp.technical.log.message.DebugMessage;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * Bean représentant un Compte Comptable
 */
public class CompteComptable implements Bean<CompteComptable> {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(CompteComptable.class);

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
	@Override
	public CompteComptable deepCopy() {
		return new CompteComptable(numero, libelle);
	}

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
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new DebugMessage("List<? extends CompteComptable> pList", pList));
		LOGGER.debug(new DebugMessage("Integer pNumero", pNumero));
		CompteComptable vRetour = null;
		if (pList != null) {
			for (CompteComptable vBean : pList) {
				if (vBean != null && Objects.equals(vBean.getNumero(), pNumero)) {
					vRetour = vBean;
					break;
				}
			}
		}
		LOGGER.debug(new DebugMessage("CompteComptable vRetour", vRetour));
		LOGGER.trace(new SortieMessage());
		return vRetour;
	}
}
