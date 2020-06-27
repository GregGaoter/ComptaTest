package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.dummy.myerp.consumer.dao.impl.cache.CompteComptableDaoCache;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.log.message.DebugMessage;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * {@link RowMapper} de {@link LigneEcritureComptable}
 */
public class LigneEcritureComptableRM implements RowMapper<LigneEcritureComptable> {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(LigneEcritureComptableRM.class);

	/** CompteComptableDaoCache */
	private final CompteComptableDaoCache compteComptableDaoCache = new CompteComptableDaoCache();

	/**
	 * {@link LigneEcritureComptable}
	 */
	private LigneEcritureComptable ligneEcritureComptable;

	/**
	 * Initialise une {@link LigneEcritureComptable}.
	 */
	private void initLigneEcritureComptable() {
		ligneEcritureComptable = new LigneEcritureComptable();
	}

	@Override
	public LigneEcritureComptable mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.trace(new EntreeMessage());
		initLigneEcritureComptable();
		if (pRS == null) {
			ligneEcritureComptable = null;
		} else {
			LOGGER.debug(new DebugMessage("int pRowNum", pRowNum));
			LOGGER.debug(new DebugMessage("(ResultSet) pRS.getObject(\"compte_comptable_numero\", Integer.class)",
					pRS.getObject("compte_comptable_numero", Integer.class)));
			ligneEcritureComptable.setCompteComptable(
					compteComptableDaoCache.getByNumero(pRS.getObject("compte_comptable_numero", Integer.class)));
			LOGGER.debug(new DebugMessage("(ResultSet) pRS.getBigDecimal(\"credit\")", pRS.getBigDecimal("credit")));
			ligneEcritureComptable.setCredit(pRS.getBigDecimal("credit"));
			LOGGER.debug(new DebugMessage("(ResultSet) pRS.getBigDecimal(\"debit\")", pRS.getBigDecimal("debit")));
			ligneEcritureComptable.setDebit(pRS.getBigDecimal("debit"));
			LOGGER.debug(new DebugMessage("(ResultSet) pRS.getString(\"libelle\")", pRS.getString("libelle")));
			ligneEcritureComptable.setLibelle(pRS.getString("libelle"));
		}
		LOGGER.trace(new SortieMessage());
		return ligneEcritureComptable;
	}
}
