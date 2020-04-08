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

	@Override
	public JournalComptable mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new DebugMessage("ResultSet pRS", pRS));
		LOGGER.debug(new DebugMessage("int pRowNum", pRowNum));
		JournalComptable vBean = new JournalComptable();
		LOGGER.debug(new DebugMessage("(ResultSet) pRS.getString(\"code\")", pRS.getString("code")));
		vBean.setCode(pRS.getString("code"));
		LOGGER.debug(new DebugMessage("(ResultSet) pRS.getString(\"libelle\")", pRS.getString("libelle")));
		vBean.setLibelle(pRS.getString("libelle"));
		LOGGER.trace(new SortieMessage());
		return vBean;
	}
}
