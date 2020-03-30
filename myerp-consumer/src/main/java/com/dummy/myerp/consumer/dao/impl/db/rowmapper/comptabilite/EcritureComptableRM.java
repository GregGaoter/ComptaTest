package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.impl.cache.JournalComptableDaoCache;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
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

	@Override
	public EcritureComptable mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.trace(new EntreeMessage());
		EcritureComptable vBean = new EcritureComptable();
		vBean.setId(pRS.getInt("id"));
		vBean.setJournal(journalComptableDaoCache.getByCode(pRS.getString("journal_code")));
		vBean.setReference(pRS.getString("reference"));
		vBean.setDate(pRS.getDate("date"));
		vBean.setLibelle(pRS.getString("libelle"));

		// Chargement des lignes d'écriture
		ConsumerHelper.getDaoProxy().getComptabiliteDao().loadListLigneEcriture(vBean);
		LOGGER.trace(new SortieMessage());
		return vBean;
	}
}
