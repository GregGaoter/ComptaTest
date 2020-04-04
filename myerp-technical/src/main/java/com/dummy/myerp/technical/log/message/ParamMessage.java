package com.dummy.myerp.technical.log.message;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Classe héritant de {@link DefaultMessage} et implémentant les messages des
 * logs de niveau {@code DEBUG} pour afficher la valeur des paramètres de
 * méthode.
 */
public class ParamMessage extends DefaultMessage {

	/**
	 * {@code serialVersionUID}
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Paramètres de méthode.
	 */
	private Map<String, Object> paramsMap;

	/**
	 * Constructeur.
	 */
	public ParamMessage(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return The message string.
	 */
	@Override
	public String getFormattedMessage() {
		StringBuilder stringBuilder = new StringBuilder("Valeur des paramètres de la méthode :");
		String retourligne = System.getProperty("line.separator") + "\t";
		String params = paramsMap.entrySet().stream()
				.map(entrySet -> String.format("%s = %s", entrySet.getKey(), entrySet.getValue().toString()))
				.collect(Collectors.joining(", "));
		stringBuilder.append(retourligne).append(params);
		return stringBuilder.toString();
	}

}
