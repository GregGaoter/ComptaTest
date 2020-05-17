package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.log.message.DebugMessage;
import com.dummy.myerp.technical.log.message.EntreeMessage;

/**
 * {@link RowMapper} de {@link SequenceEcritureComptable}
 */
public class SequenceEcritureComptableRM implements RowMapper<SequenceEcritureComptable> {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(SequenceEcritureComptableRM.class);

	@Override
	public SequenceEcritureComptable mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new DebugMessage("ResultSet pRS", pRS));
		LOGGER.debug(new DebugMessage("int pRowNum", pRowNum));
		SequenceEcritureComptable vBean = new SequenceEcritureComptable();
		LOGGER.debug(new DebugMessage("(ResultSet) pRS.getString(\"journal_code\")", pRS.getString("journal_code")));
		vBean.setJournalCode(pRS.getString("journal_code"));
		LOGGER.debug(new DebugMessage("(ResultSet) pRS.getInt(\"annee\")", pRS.getInt("annee")));
		vBean.setAnnee(pRS.getInt("annee"));
		LOGGER.debug(new DebugMessage("(ResultSet) pRS.getInt(\"derniere_valeur\")", pRS.getInt("derniere_valeur")));
		vBean.setDerniereValeur(pRS.getInt("derniere_valeur"));
		return vBean;
	}

}
