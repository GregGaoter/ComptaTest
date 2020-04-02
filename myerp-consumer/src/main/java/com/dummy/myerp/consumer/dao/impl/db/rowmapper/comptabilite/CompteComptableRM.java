package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.technical.log.message.DebugMessage;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * {@link RowMapper} de {@link CompteComptable}
 */
public class CompteComptableRM implements RowMapper<CompteComptable> {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(CompteComptableRM.class);

	@Override
	public CompteComptable mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.trace(new EntreeMessage());
		CompteComptable vBean = new CompteComptable();
		LOGGER.debug(new DebugMessage("(ResultSet) pRS.getInt(\"numero\")", pRS.getInt("numero")));
		vBean.setNumero(pRS.getInt("numero"));
		LOGGER.debug(new DebugMessage("(ResultSet) pRS.getString(\"libelle\")", pRS.getString("libelle")));
		vBean.setLibelle(pRS.getString("libelle"));
		LOGGER.trace(new SortieMessage());
		return vBean;
	}
}
