package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.technical.log.message.DebugMessage;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * {@link RowMapper} de {@link JournalComptable}
 */
public class JournalComptableRM implements RowMapper<JournalComptable> {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(JournalComptableRM.class);

	/**
	 * {@link JournalComptable}.
	 */
	private JournalComptable journalComptable;

	private void initJournalComptable() {
		journalComptable = new JournalComptable();
	}

	@Override
	public JournalComptable mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.trace(new EntreeMessage());
		initJournalComptable();
		if (pRS == null) {
			journalComptable = null;
		} else {
			LOGGER.debug(new DebugMessage("int pRowNum", pRowNum));
			LOGGER.debug(new DebugMessage("(ResultSet) pRS.getString(\"code\")", pRS.getString("code")));
			journalComptable.setCode(pRS.getString("code"));
			LOGGER.debug(new DebugMessage("(ResultSet) pRS.getString(\"libelle\")", pRS.getString("libelle")));
			journalComptable.setLibelle(pRS.getString("libelle"));
		}
		LOGGER.trace(new SortieMessage());
		return journalComptable;
	}
}
