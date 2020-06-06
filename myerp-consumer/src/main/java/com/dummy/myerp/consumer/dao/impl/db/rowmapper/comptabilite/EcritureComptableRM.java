package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.impl.cache.JournalComptableDaoCache;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.technical.log.message.DebugMessage;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * {@link RowMapper} de {@link EcritureComptable}
 */
public class EcritureComptableRM implements RowMapper<EcritureComptable> {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(EcritureComptableRM.class);

	/** JournalComptableDaoCache */
	private final JournalComptableDaoCache journalComptableDaoCache = new JournalComptableDaoCache();
	/**
	 * {@link CompteComptable}
	 */
	private EcritureComptable ecritureComptable;

	/**
	 * Constructeur. Instancie {@link EcritureComptable}.
	 */
	public EcritureComptableRM() {
		ecritureComptable = new EcritureComptable();
	}

	@Override
	public EcritureComptable mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.trace(new EntreeMessage());
		if (pRS == null) {
			ecritureComptable = null;
		} else {
			LOGGER.debug(new DebugMessage("int pRowNum", pRowNum));
			LOGGER.debug(new DebugMessage("(ResultSet) pRS.getInt(\"id\")", pRS.getInt("id")));
			ecritureComptable.setId(pRS.getInt("id"));
			LOGGER.debug(
					new DebugMessage("(ResultSet) pRS.getString(\"journal_code\")", pRS.getString("journal_code")));
			ecritureComptable.setJournal(journalComptableDaoCache.getByCode(pRS.getString("journal_code")));
			LOGGER.debug(new DebugMessage("(ResultSet) pRS.getString(\"reference\")", pRS.getString("reference")));
			ecritureComptable.setReference(pRS.getString("reference"));
			LOGGER.debug(new DebugMessage("(ResultSet) pRS.getDate(\"date\")", pRS.getDate("date")));
			ecritureComptable.setDate(pRS.getDate("date"));
			LOGGER.debug(new DebugMessage("(ResultSet) pRS.getString(\"libelle\")", pRS.getString("libelle")));
			ecritureComptable.setLibelle(pRS.getString("libelle"));
			// Chargement des lignes d'écriture
			ConsumerHelper.getDaoProxy().getComptabiliteDao().loadListLigneEcriture(ecritureComptable);
		}
		LOGGER.trace(new SortieMessage());
		return ecritureComptable;
	}
}
