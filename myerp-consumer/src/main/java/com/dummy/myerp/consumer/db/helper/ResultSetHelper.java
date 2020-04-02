package com.dummy.myerp.consumer.db.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * Classe utilitaire travaillant sur les ResultSet
 */
public abstract class ResultSetHelper {

	// TODO Reprendre les logs DEBUG ici.

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(ResultSetHelper.class);

	// ==================== Constructeurs ====================
	/**
	 * Constructeur.
	 */
	protected ResultSetHelper() {
		super();
	}

	// ==================== Méthodes ====================
	/**
	 * Renvoie la valeur de la colonne pColName dans un <code>Integer</code>. Si la
	 * colonne vaut <code>null</code>, la méthode renvoie <code>null</code>
	 *
	 * @param pRS      : Le ResultSet à interroger
	 * @param pColName : Le nom de la colonne dans le retour de la requête SQL
	 * @return <code>Integer</code> ou <code>null</code>
	 * @throws SQLException sur erreur SQL
	 */
	public static Integer getInteger(ResultSet pRS, String pColName) throws SQLException {
		LOGGER.trace(new EntreeMessage());
		Integer vRetour = null;
		int vInt = pRS.getInt(pColName);
		if (!pRS.wasNull()) {
			// vRetour = new Integer(vInt);
			vRetour = Integer.valueOf(vInt);
		}
		LOGGER.trace(new SortieMessage());
		return vRetour;
	}

	/**
	 * Renvoie la valeur de la colonne pColName dans un <code>Long</code>. Si la
	 * colonne vaut <code>null</code>, la méthode renvoie <code>null</code>
	 *
	 * @param pRS      : Le ResultSet à interroger
	 * @param pColName : Le nom de la colonne dans le retour de la requête SQL
	 * @return <code>Long</code> ou <code>null</code>
	 * @throws SQLException sur erreur SQL
	 */
	public static Long getLong(ResultSet pRS, String pColName) throws SQLException {
		LOGGER.trace(new EntreeMessage());
		Long vRetour = null;
		Long vLong = pRS.getLong(pColName);
		if (!pRS.wasNull()) {
			// vRetour = new Long(vLong);
			vRetour = vLong;
		}
		LOGGER.trace(new SortieMessage());
		return vRetour;
	}

	/**
	 * Renvoie la valeur de la colonne pColName dans un {@link Date} en faisant un
	 * truncate de l'heure. Si la colonne vaut <code>null</code>, la méthode renvoie
	 * <code>null</code>.
	 *
	 * @param pRS      : Le ResultSet à interroger
	 * @param pColName : Le nom de la colonne dans le retour de la requête SQL
	 * @return {@link Date} ou <code>null</code>
	 * @throws SQLException sur erreur SQL
	 */
	public static Date getDate(ResultSet pRS, String pColName) throws SQLException {
		LOGGER.trace(new EntreeMessage());
		Date vDate = pRS.getDate(pColName);
		if (vDate != null) {
			vDate = DateUtils.truncate(vDate, Calendar.DATE);
		}
		LOGGER.trace(new SortieMessage());
		return vDate;
	}
}
