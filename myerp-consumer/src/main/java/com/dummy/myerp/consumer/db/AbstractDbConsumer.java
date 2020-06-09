package com.dummy.myerp.consumer.db;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.technical.log.message.DebugMessage;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * <p>
 * Classe mère des classes de Consumer DB
 * </p>
 */
public abstract class AbstractDbConsumer {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(AbstractDbConsumer.class);

	// ==================== Attributs ====================

	private JdbcTemplate jdbcTemplate;

// ==================== Attributs Static ====================

	/** Map des DataSources */
	private static Map<DataSourcesEnum, DataSource> mapDataSource;

	// ==================== Constructeurs ====================

	/**
	 * Constructeur.
	 */
	protected AbstractDbConsumer() {
		super();
	}

	// ==================== Getters/Setters ====================
	/**
	 * Renvoie une {@link DaoProxy}
	 *
	 * @return {@link DaoProxy}
	 */
	protected static DaoProxy getDaoProxy() {
		return ConsumerHelper.getDaoProxy();
	}

	// ==================== Méthodes ====================

	protected void createJdbcTemplate(DataSourcesEnum dataSourcesEnum) {
		jdbcTemplate = new JdbcTemplate(getDataSource(dataSourcesEnum));
	}

	/**
	 * Renvoie le {@link DataSource} associé demandée
	 *
	 * @param pDataSourceId - DataSource à renvoyer
	 * @return {@link DataSource}
	 */
	protected DataSource getDataSource(DataSourcesEnum pDataSourceId) {
		LOGGER.trace(new EntreeMessage());
		if (pDataSourceId == null) {
			return null;
		}
		DataSource vRetour = AbstractDbConsumer.mapDataSource.get(pDataSourceId);
		if (vRetour == null) {
			throw new UnsatisfiedLinkError("La DataSource suivante n'a pas été initialisée : " + pDataSourceId);
		}
		LOGGER.trace(new SortieMessage());
		return vRetour;
	}

	/**
	 * Renvoie la dernière valeur utilisé d'une séquence
	 *
	 * <p>
	 * <i><b>Attention : </b>Méthode spécifique au SGBD PostgreSQL</i>
	 * </p>
	 *
	 * @param <T>            : La classe de la valeur de la séquence.
	 * @param pDataSourcesId : L'identifiant de la {@link DataSource} à utiliser
	 * @param pSeqName       : Le nom de la séquence dont on veut récupérer la
	 *                       valeur
	 * @param pSeqValueClass : Classe de la valeur de la séquence
	 * @return La dernière valeur de la séquence
	 */
	protected <T> T queryGetSequenceValuePostgreSQL(DataSourcesEnum pDataSourcesId, String pSeqName,
			Class<T> pSeqValueClass) {
		LOGGER.trace(new EntreeMessage());
		if (pDataSourcesId == null || pSeqName == null || pSeqName.isBlank() || pSeqName == "\n" || pSeqName == "\t"
				|| pSeqValueClass == null) {
			return null;
		}
		createJdbcTemplate(pDataSourcesId);
		String vSeqSQL = "SELECT last_value FROM " + pSeqName;
		LOGGER.debug(new DebugMessage("vSeqSQL", vSeqSQL));
		T vSeqValue = jdbcTemplate.queryForObject(vSeqSQL, pSeqValueClass);
		LOGGER.debug(new DebugMessage("vSeqValue", vSeqValue));
		LOGGER.trace(new SortieMessage());
		return vSeqValue;
	}

	/**
	 * Renvoie la dernière valeur utilisé d'une séquence de type Integer
	 * 
	 * @param pDataSourcesId L'identifiant de la {@link DataSource} à utiliser
	 * @param pSeqName       Le nom de la séquence dont on veut récupérer la valeur
	 * @return La dernière valeur de la séquence
	 */
	public Integer getSequenceValue(DataSourcesEnum pDataSourcesId, String pSeqName) {
		return queryGetSequenceValuePostgreSQL(pDataSourcesId, pSeqName, Integer.class);
	}

	// ==================== Méthodes Static ====================
	/**
	 * Méthode de configuration de la classe
	 *
	 * @param pMapDataSource - Map des DataSource à configurer
	 */
	public static void configure(Map<DataSourcesEnum, DataSource> pMapDataSource) {
		LOGGER.trace(new EntreeMessage());
		// On pilote l'ajout avec l'Enum et on ne rajoute pas tout à l'aveuglette...
		// ( pas de AbstractDbDao.mapDataSource.putAll(...) )
		Map<DataSourcesEnum, DataSource> vMapDataSource = new HashMap<>(DataSourcesEnum.values().length);
		DataSourcesEnum[] vDataSourceIds = DataSourcesEnum.values();
		for (DataSourcesEnum vDataSourceId : vDataSourceIds) {
			DataSource vDataSource = pMapDataSource.get(vDataSourceId);
			// On test si la DataSource est configurée
			// (NB : elle est considérée comme configurée si elle est dans pMapDataSource
			// mais à null)
			if (vDataSource == null) {
				if (!pMapDataSource.containsKey(vDataSourceId)) {
					LOGGER.error("La DataSource " + vDataSourceId + " n'a pas été initialisée !");
				}
			} else {
				vMapDataSource.put(vDataSourceId, vDataSource);
			}
		}
		mapDataSource = vMapDataSource;
		LOGGER.trace(new SortieMessage());
	}
}
