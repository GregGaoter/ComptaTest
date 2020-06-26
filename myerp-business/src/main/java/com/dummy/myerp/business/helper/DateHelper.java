package com.dummy.myerp.business.helper;

import java.util.Calendar;
import java.util.Date;

public abstract class DateHelper {

	/**
	 * Renvoie une {@link Date} à 00:00:00.0.
	 * 
	 * @param jour
	 * @param mois
	 * @param annee
	 * @return Une {@link Date}.
	 */
	public static Date getDate(int jour, int mois, int annee) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, jour);
		calendar.set(Calendar.MONTH, mois - 1);
		calendar.set(Calendar.YEAR, annee);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Renvoie le numéro du jour du mois d'une {@link Date}.
	 * 
	 * @param date Date
	 * @return Le numéro du jour du mois d'une {@link Date}.
	 */
	public static int getJour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * Renvoie le numéro du mois d'une {@link Date}.
	 * 
	 * @param date Date
	 * @return Le numéro du mois d'une {@link Date}.
	 */
	public static int getMois(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * Renvoie l'année d'une {@link Date}.
	 * 
	 * @param date Date
	 * @return L'année de la {@link Date}.
	 */
	public static int getAnnee(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

}
