package com.dummy.myerp.model.bean.comptabilite;

/**
 * Bean représentant une séquence pour les références d'écriture comptable
 */
public class SequenceEcritureComptable {

	// ==================== Attributs ====================
	/** Le code du journal comptable */
	private String journal_code;
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

	/**
	 * Constructeur
	 *
	 * @param pJournalCode    - Le code du journal comptable
	 * @param pAnnee          - L'année de l'écriture comptable
	 * @param pDerniereValeur - La dernière valeur utilisée pour les références
	 *                        d'écriture comptable
	 */
	public SequenceEcritureComptable(String pJournalCode, Integer pAnnee, Integer pDerniereValeur) {
		journal_code = pJournalCode;
		annee = pAnnee;
		derniereValeur = pDerniereValeur;
	}

	// ==================== Getters/Setters ====================
	/**
	 * Renvoie le code du journal dans lequel figure l'écriture comptable
	 * 
	 * @return Le code du journal dans lequel figure l'écriture comptable
	 */
	public String getJournalCode() {
		return journal_code;
	}

	/**
	 * Initialise le code du journal dans lequel figure l'écriture comptable
	 * 
	 * @param pAnnee - Le code du journal dans lequel figure l'écriture comptable
	 */
	public void setJournalCode(String journalCode) {
		this.journal_code = journalCode;
	}

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
		final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
		final String vSEP = ", ";
		vStB.append("{").append("annee=").append(annee).append(vSEP).append("derniereValeur=").append(derniereValeur)
				.append("}");
		return vStB.toString();
	}
}
