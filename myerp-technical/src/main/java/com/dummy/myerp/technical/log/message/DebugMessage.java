package com.dummy.myerp.technical.log.message;

/**
 * Classe héritant de {@link DefaultMessage} et implémentant les messages des
 * logs de niveau {@code DEBUG} pour afficher des valeurs de données.
 */
public class DebugMessage extends DefaultMessage {

	/**
	 * {@code serialVersionUID}
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Le nom du paramètre.
	 */
	private final String dataName;
	/**
	 * La représentation {@link String} de la valeur du paramètre.
	 */
	private final String value;

	/**
	 * Constructeur.<br>
	 * Le constructeur accepte tout type d'objet comme {@code value}, puis convertit
	 * explicitement {@code value} en {@link String} en appelant sa méthode
	 * {@code toString}.<br>
	 * Cette technique permet d'éviter un
	 * "{@code ERROR Recursive call to appender [appender]}", dû à ce que l'objet
	 * redéfinit sa méthode {@code toString}, et qui appelle elle-même la méthode
	 * {@code toString} redéfinit d'un autre objet qui log un évenement sur le même
	 * appender.
	 * 
	 * @param dataName Le nom du paramètre.
	 * @param value    La valeur du paramètre.
	 */
	public DebugMessage(String dataName, Object value) {
		this.dataName = dataName;
		this.value = value.toString();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return The message string.
	 */
	@Override
	public String getFormattedMessage() {
		return dataName + " = " + value;
	}

}
