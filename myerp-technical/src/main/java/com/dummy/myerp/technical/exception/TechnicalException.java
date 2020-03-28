package com.dummy.myerp.technical.exception;

/**
 * Classe des Exceptions Techniques
 */
public class TechnicalException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	// ==================== Constructeurs ====================
	/**
	 * Constructeur.
	 *
	 * @param pMessage - Message de l'exception.
	 */
	public TechnicalException(String pMessage) {
		super(pMessage);
	}

	/**
	 * Constructeur.
	 *
	 * @param pCause - Cause de l'exception.
	 */
	public TechnicalException(Throwable pCause) {
		super(pCause);
	}

	/**
	 * Constructeur.
	 *
	 * @param pMessage - Message de l'exception.
	 * @param pCause   - Cause de l'exception.
	 */
	public TechnicalException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
}
