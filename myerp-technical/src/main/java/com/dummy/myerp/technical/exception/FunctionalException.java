package com.dummy.myerp.technical.exception;

/**
 * Classe des Exceptions Fonctionnelles
 */
public class FunctionalException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	// ==================== Constructeurs ====================
	/**
	 * Constructeur.
	 *
	 * @param pMessage - Message de l'exception.
	 */
	public FunctionalException(String pMessage) {
		super(pMessage);
	}

	/**
	 * Constructeur.
	 *
	 * @param pCause - Cause de l'exception.
	 */
	public FunctionalException(Throwable pCause) {
		super(pCause);
	}

	/**
	 * Constructeur.
	 *
	 * @param pMessage - Message de l'exception.
	 * @param pCause   - Cause de l'exception.
	 */
	public FunctionalException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
}
