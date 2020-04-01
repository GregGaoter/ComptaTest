package com.dummy.myerp.technical.log.message;

import org.apache.logging.log4j.message.Message;

/**
 * Classe qui implémente l'interface {@link Message}.<br>
 * Elle fournit l'implémentation par défaut des méthodes de {@link Message}, qui
 * retournent toutes {@code null}.<br>
 * Ceci permet aux classes implémentant les messages des logs d'en hériter et de
 * redéfinir ainsi uniquement les méthodes dont elles ont besoin.
 */
public class DefaultMessage implements Message {

	/**
	 * {@code serialVersionUID}
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 * 
	 * @return {@code null}
	 */
	@Override
	public String getFormattedMessage() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return {@code null}
	 */
	@Override
	public String getFormat() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return {@code null}
	 */
	@Override
	public Object[] getParameters() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return {@code null}
	 */
	@Override
	public Throwable getThrowable() {
		return null;
	}

}
