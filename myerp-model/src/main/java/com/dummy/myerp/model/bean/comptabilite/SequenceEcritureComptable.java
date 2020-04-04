package com.dummy.myerp.model.bean.comptabilite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dummy.myerp.technical.log.message.DebugMessage;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * Bean représentant une séquence pour les références d'écriture comptable
 */
public class SequenceEcritureComptable {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(SequenceEcritureComptable.class);

	// ==================== Attributs ====================
	/** L'année de l'écriture comptable */
	private Integer annee;
	/** La dernière valeur utilisée pour les références d'écriture comptable */
	private Integer derniereValeur;

	// ==================== Constructeurs ====================
	/**
	 * Constructeur
	 */
	public SequenceEcritureComptable() {
	}

	/**
	 * Constructeur
	 *
	 * @param pAnnee          - L'année de l'écriture comptable
	 * @param pDerniereValeur - La dernière valeur utilisée pour les références
	 *                        d'écriture comptable
	 */
	public SequenceEcritureComptable(Integer pAnnee, Integer pDerniereValeur) {
		annee = pAnnee;
		derniereValeur = pDerniereValeur;
	}

	// ==================== Getters/Setters ====================
	/**
	 * Renvoie l'année de l'écriture comptable
	 * 
	 * @return L'année de l'écriture comptable
	 */
	public Integer getAnnee() {
		return annee;
	}

	/**
	 * Initialise l'année de l'écriture comptable
	 * 
	 * @param pAnnee - L'année de l'écriture comptable
	 */
	public void setAnnee(Integer pAnnee) {
		annee = pAnnee;
	}

	/**
	 * Renvoie la dernière valeur utilisée pour les références d'écriture comptable
	 * 
	 * @return La dernière valeur utilisée pour les références d'écriture comptable
	 */
	public Integer getDerniereValeur() {
		return derniereValeur;
	}

	/**
	 * Initialise la dernière valeur utilisée pour les références d'écriture
	 * comptable
	 * 
	 * @param pDerniereValeur - La dernière valeur utilisée pour les références
	 *                        d'écriture comptable
	 */
	public void setDerniereValeur(Integer pDerniereValeur) {
		derniereValeur = pDerniereValeur;
	}

	// ==================== Méthodes ====================
	/**
	 * {@inheritDoc}
	 * 
	 * @see StringBuilder
	 */
	@Override
	public String toString() {
		LOGGER.trace(new EntreeMessage());
		final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
		final String vSEP = ", ";
		vStB.append("{").append("annee=").append(annee).append(vSEP).append("derniereValeur=").append(derniereValeur)
				.append("}");
		LOGGER.debug(new DebugMessage("StringBuilder vStB.toString()", vStB.toString()));
		LOGGER.trace(new SortieMessage());
		return vStB.toString();
	}
}
