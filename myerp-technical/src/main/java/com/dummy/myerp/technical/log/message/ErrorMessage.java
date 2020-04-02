package com.dummy.myerp.technical.log.message;

import java.util.Objects;

/**
 * Classe héritant de {@link DefaultMessage} et implémentant les messages des
 * logs de niveau {@code ERROR} lorsqu'une exception est levée.
 */
public class ErrorMessage extends DefaultMessage {

	/**
	 * {@code serialVersionUID}
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * L'exception levée.
	 */
	private final Exception exception;

	/**
	 * Constructeur.
	 * 
	 * @param <E>       La classe de l'exception levée.
	 * @param exception L'exception levée.
	 * 
	 * @throws NullPointerException si le paramètre est {@code null}
	 */
	public <E extends Exception> ErrorMessage(E exception) {
		this.exception = Objects.requireNonNull(exception);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return The message string.
	 */
	@Override
	public String getFormattedMessage() {
		StringBuilder stringBuilder = new StringBuilder("Exception levée !");
		String retourligne = System.getProperty("line.separator") + "\t";
		String separateur2Pts = " : ";
		String separateurTiret = " - ";
		if (exception != null) {
			String exceptionName = exception.getClass().getSimpleName();
			String exceptionMessage = exception.getMessage();
			stringBuilder.append(retourligne).append("Exception").append(separateurTiret).append(exceptionName)
					.append(separateur2Pts).append(exceptionMessage);
			Throwable cause = exception.getCause();
			if (cause != null) {
				String causeName = cause.getClass().getSimpleName();
				String causeMessage = cause.getMessage();
				stringBuilder.append(retourligne).append("Cause").append(separateurTiret).append(causeName)
						.append(separateur2Pts).append(causeMessage);
			}
		}
		return stringBuilder.toString();
	}

}
