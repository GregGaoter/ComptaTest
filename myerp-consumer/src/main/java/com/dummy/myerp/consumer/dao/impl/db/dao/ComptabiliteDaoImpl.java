package com.dummy.myerp.consumer.dao.impl.db.dao;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.CompteComptableRM;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.EcritureComptableRM;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.JournalComptableRM;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.LigneEcritureComptableRM;
import com.dummy.myerp.consumer.db.AbstractDbConsumer;
import com.dummy.myerp.consumer.db.DataSourcesEnum;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.technical.log.message.DebugMessage;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.ErrorMessage;
import com.dummy.myerp.technical.log.message.ParamMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * Extension de la classe abstraite {@link AbstractDbConsumer} et implémentation
 * de l'interface {@link ComptabiliteDao}
 */
public class ComptabiliteDaoImpl extends AbstractDbConsumer implements ComptabiliteDao {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(ComptabiliteDaoImpl.class);

	// ==================== Constructeurs ====================
	/** Instance unique de la classe (design pattern Singleton) */
	private static final ComptabiliteDaoImpl INSTANCE = new ComptabiliteDaoImpl();

	/**
	 * Renvoie l'instance unique de la classe (design pattern Singleton).
	 *
	 * @return {@link ComptabiliteDaoImpl}
	 */
	public static ComptabiliteDaoImpl getInstance() {
		return ComptabiliteDaoImpl.INSTANCE;
	}

	/**
	 * Constructeur.
	 */
	protected ComptabiliteDaoImpl() {
		super();
	}

	// ==================== Méthodes ====================
	/** Requête SQL pour avoir la liste des comptes comptables */
	private static String SQLgetListCompteComptable;

	/** Initialise la requête SQL pour avoir la liste des comptes comptables */
	public void setSQLgetListCompteComptable(String pSQLgetListCompteComptable) {
		SQLgetListCompteComptable = pSQLgetListCompteComptable;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see JdbcTemplate
	 * @see CompteComptableRM
	 */
	@Override
	public List<CompteComptable> getListCompteComptable() {
		LOGGER.trace(new EntreeMessage());
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.getDataSource(DataSourcesEnum.MYERP));
		CompteComptableRM vRM = new CompteComptableRM();
		LOGGER.debug(new DebugMessage("SQLgetListCompteComptable", SQLgetListCompteComptable));
		List<CompteComptable> vList = vJdbcTemplate.query(SQLgetListCompteComptable, vRM);
		LOGGER.trace(new SortieMessage());
		return vList;
	}

	/** Requête SQL pour avoir la liste des journaux comptables */
	private static String SQLgetListJournalComptable;

	/** Initialise la requête SQL pour avoir la liste des journaux comptables */
	public void setSQLgetListJournalComptable(String pSQLgetListJournalComptable) {
		SQLgetListJournalComptable = pSQLgetListJournalComptable;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see JdbcTemplate
	 */
	@Override
	public List<JournalComptable> getListJournalComptable() {
		LOGGER.trace(new EntreeMessage());
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.getDataSource(DataSourcesEnum.MYERP));
		JournalComptableRM vRM = new JournalComptableRM();
		LOGGER.debug(new DebugMessage("SQLgetListJournalComptable", SQLgetListJournalComptable));
		List<JournalComptable> vList = vJdbcTemplate.query(SQLgetListJournalComptable, vRM);
		LOGGER.trace(new SortieMessage());
		return vList;
	}

	// ==================== EcritureComptable - GET ====================

	/** Requête SQL pour avoir la liste des écritures comptables */
	private static String SQLgetListEcritureComptable;

	/** Initialise la requête SQL pour avoir la liste des écritures comptables */
	public void setSQLgetListEcritureComptable(String pSQLgetListEcritureComptable) {
		SQLgetListEcritureComptable = pSQLgetListEcritureComptable;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see JdbcTemplate
	 */
	@Override
	public List<EcritureComptable> getListEcritureComptable() {
		LOGGER.trace(new EntreeMessage());
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.getDataSource(DataSourcesEnum.MYERP));
		EcritureComptableRM vRM = new EcritureComptableRM();
		LOGGER.debug(new DebugMessage("SQLgetListEcritureComptable", SQLgetListEcritureComptable));
		List<EcritureComptable> vList = vJdbcTemplate.query(SQLgetListEcritureComptable, vRM);
		LOGGER.trace(new SortieMessage());
		return vList;
	}

	/** Requête SQL pour avoir une écriture comptable par id */
	private static String SQLgetEcritureComptable;

	/** Initialise la requête SQL pour avoir une écriture comptable par id */
	public void setSQLgetEcritureComptable(String pSQLgetEcritureComptable) {
		SQLgetEcritureComptable = pSQLgetEcritureComptable;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see NamedParameterJdbcTemplate
	 * @see MapSqlParameterSource
	 * @see EcritureComptableRM
	 * @see EmptyResultDataAccessException
	 */
	@Override
	public EcritureComptable getEcritureComptable(Integer pId) throws NotFoundException {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new ParamMessage(Map.of("Integer pId", pId)));
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		vSqlParams.addValue("id", pId);
		EcritureComptableRM vRM = new EcritureComptableRM();
		EcritureComptable vBean;
		LOGGER.debug(new DebugMessage("SQLgetEcritureComptable", SQLgetEcritureComptable));
		try {
			vBean = vJdbcTemplate.queryForObject(SQLgetEcritureComptable, vSqlParams, vRM);
		} catch (EmptyResultDataAccessException vEx) {
			LOGGER.error(new ErrorMessage(vEx));
			NotFoundException notFoundException = new NotFoundException("EcritureComptable non trouvée : id=" + pId);
			LOGGER.error(new ErrorMessage(notFoundException));
			throw notFoundException;
		}
		LOGGER.trace(new SortieMessage());
		return vBean;
	}

	/** Requête SQL pour avoir une écriture comptable par référence */
	private static String SQLgetEcritureComptableByRef;

	/** Initialise la requête SQL pour avoir une écriture comptable par référence */
	public void setSQLgetEcritureComptableByRef(String pSQLgetEcritureComptableByRef) {
		SQLgetEcritureComptableByRef = pSQLgetEcritureComptableByRef;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see NamedParameterJdbcTemplate
	 * @see MapSqlParameterSource
	 * @see EcritureComptableRM
	 * @see EmptyResultDataAccessException
	 */
	@Override
	public EcritureComptable getEcritureComptableByRef(String pReference) throws NotFoundException {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new ParamMessage(Map.of("String pReference", pReference)));
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		vSqlParams.addValue("reference", pReference);
		EcritureComptableRM vRM = new EcritureComptableRM();
		EcritureComptable vBean;
		LOGGER.debug(new DebugMessage("SQLgetEcritureComptableByRef", SQLgetEcritureComptableByRef));
		try {
			vBean = vJdbcTemplate.queryForObject(SQLgetEcritureComptableByRef, vSqlParams, vRM);
		} catch (EmptyResultDataAccessException vEx) {
			LOGGER.error(new ErrorMessage(vEx));
			NotFoundException notFoundException = new NotFoundException(
					"EcritureComptable non trouvée : reference=" + pReference);
			LOGGER.error(new ErrorMessage(notFoundException));
			throw notFoundException;
		}
		LOGGER.trace(new SortieMessage());
		return vBean;
	}

	/**
	 * Requête SQL pour charger la liste des lignes d'écritures d'une écriture
	 * comptable
	 */
	private static String SQLloadListLigneEcriture;

	/**
	 * Initialise la requête SQL pour charger la liste des lignes d'écritures d'une
	 * écriture comptable
	 */
	public void setSQLloadListLigneEcriture(String pSQLloadListLigneEcriture) {
		SQLloadListLigneEcriture = pSQLloadListLigneEcriture;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see NamedParameterJdbcTemplate
	 * @see MapSqlParameterSource
	 * @see LigneEcritureComptableRM
	 */
	@Override
	public void loadListLigneEcriture(EcritureComptable pEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new ParamMessage(Map.of("EcritureComptable pEcritureComptable", pEcritureComptable)));
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		LOGGER.debug(new DebugMessage("pEcritureComptable.getId()", pEcritureComptable.getId()));
		vSqlParams.addValue("ecriture_id", pEcritureComptable.getId());
		LigneEcritureComptableRM vRM = new LigneEcritureComptableRM();
		LOGGER.debug(new DebugMessage("SQLloadListLigneEcriture", SQLloadListLigneEcriture));
		List<LigneEcritureComptable> vList = vJdbcTemplate.query(SQLloadListLigneEcriture, vSqlParams, vRM);
		pEcritureComptable.getListLigneEcriture().clear();
		pEcritureComptable.getListLigneEcriture().addAll(vList);
		LOGGER.trace(new SortieMessage());
	}

	// ==================== EcritureComptable - INSERT ====================

	/** Requête SQL pour insérer une écriture comptable */
	private static String SQLinsertEcritureComptable;

	/** Initialise la requête SQL pour insérer une écriture comptable */
	public void setSQLinsertEcritureComptable(String pSQLinsertEcritureComptable) {
		SQLinsertEcritureComptable = pSQLinsertEcritureComptable;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see NamedParameterJdbcTemplate
	 * @see MapSqlParameterSource
	 */
	@Override
	public void insertEcritureComptable(EcritureComptable pEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new ParamMessage(Map.of("EcritureComptable pEcritureComptable", pEcritureComptable)));
		// ===== Ecriture Comptable
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		LOGGER.debug(new DebugMessage("pEcritureComptable.getJournal().getCode()",
				pEcritureComptable.getJournal().getCode()));
		vSqlParams.addValue("journal_code", pEcritureComptable.getJournal().getCode());
		LOGGER.debug(new DebugMessage("pEcritureComptable.getReference()", pEcritureComptable.getReference()));
		vSqlParams.addValue("reference", pEcritureComptable.getReference());
		LOGGER.debug(new DebugMessage("pEcritureComptable.getDate()", pEcritureComptable.getDate()));
		vSqlParams.addValue("date", pEcritureComptable.getDate(), Types.DATE);
		LOGGER.debug(new DebugMessage("pEcritureComptable.getLibelle()", pEcritureComptable.getLibelle()));
		vSqlParams.addValue("libelle", pEcritureComptable.getLibelle());

		LOGGER.debug(new DebugMessage("SQLinsertEcritureComptable", SQLinsertEcritureComptable));
		vJdbcTemplate.update(SQLinsertEcritureComptable, vSqlParams);

		// ----- Récupération de l'id
		Integer vId = this.queryGetSequenceValuePostgreSQL(DataSourcesEnum.MYERP, "myerp.ecriture_comptable_id_seq",
				Integer.class);
		LOGGER.debug(new DebugMessage("vId", vId));
		pEcritureComptable.setId(vId);

		// ===== Liste des lignes d'écriture
		this.insertListLigneEcritureComptable(pEcritureComptable);
		LOGGER.trace(new SortieMessage());
	}

	/**
	 * Requête SQL pour insérer des lignes d'écriture dans une écriture comptable
	 */
	private static String SQLinsertListLigneEcritureComptable;

	/**
	 * Initialise la requête SQL pour insérer des lignes d'écriture dans une
	 * écriture comptable
	 */
	public void setSQLinsertListLigneEcritureComptable(String pSQLinsertListLigneEcritureComptable) {
		SQLinsertListLigneEcritureComptable = pSQLinsertListLigneEcritureComptable;
	}

	/**
	 * Insert les lignes d'écriture de l'écriture comptable
	 * 
	 * @param pEcritureComptable l'écriture comptable dans laquelle insérer des
	 *                           lignes d'écriture
	 * 
	 * @see NamedParameterJdbcTemplate
	 * @see MapSqlParameterSource
	 */
	protected void insertListLigneEcritureComptable(EcritureComptable pEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new ParamMessage(Map.of("EcritureComptable pEcritureComptable", pEcritureComptable)));
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		LOGGER.debug(new DebugMessage("pEcritureComptable.getId()", pEcritureComptable.getId()));
		vSqlParams.addValue("ecriture_id", pEcritureComptable.getId());

		LOGGER.debug(new DebugMessage("SQLinsertListLigneEcritureComptable", SQLinsertListLigneEcritureComptable));
		int vLigneId = 0;
		for (LigneEcritureComptable vLigne : pEcritureComptable.getListLigneEcriture()) {
			vLigneId++;
			vSqlParams.addValue("ligne_id", vLigneId);
			vSqlParams.addValue("compte_comptable_numero", vLigne.getCompteComptable().getNumero());
			vSqlParams.addValue("libelle", vLigne.getLibelle());
			vSqlParams.addValue("debit", vLigne.getDebit());

			vSqlParams.addValue("credit", vLigne.getCredit());

			vJdbcTemplate.update(SQLinsertListLigneEcritureComptable, vSqlParams);
		}
		LOGGER.debug(new DebugMessage("vLigneId", vLigneId));
		LOGGER.trace(new SortieMessage());
	}

	// ==================== EcritureComptable - UPDATE ====================

	/** Requête SQL pour mettre à jour une écriture comptable */
	private static String SQLupdateEcritureComptable;

	/** Initialise la requête SQL pour mettre à jour une écriture comptable */
	public void setSQLupdateEcritureComptable(String pSQLupdateEcritureComptable) {
		SQLupdateEcritureComptable = pSQLupdateEcritureComptable;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see NamedParameterJdbcTemplate
	 * @see MapSqlParameterSource
	 */
	@Override
	public void updateEcritureComptable(EcritureComptable pEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new ParamMessage(Map.of("EcritureComptable pEcritureComptable", pEcritureComptable)));
		// ===== Ecriture Comptable
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		LOGGER.debug(new DebugMessage("pEcritureComptable.getId()", pEcritureComptable.getId()));
		vSqlParams.addValue("id", pEcritureComptable.getId());
		LOGGER.debug(new DebugMessage("pEcritureComptable.getJournal().getCode()",
				pEcritureComptable.getJournal().getCode()));
		vSqlParams.addValue("journal_code", pEcritureComptable.getJournal().getCode());
		LOGGER.debug(new DebugMessage("pEcritureComptable.getReference()", pEcritureComptable.getReference()));
		vSqlParams.addValue("reference", pEcritureComptable.getReference());
		LOGGER.debug(new DebugMessage("pEcritureComptable.getDate()", pEcritureComptable.getDate()));
		vSqlParams.addValue("date", pEcritureComptable.getDate(), Types.DATE);
		LOGGER.debug(new DebugMessage("pEcritureComptable.getLibelle()", pEcritureComptable.getLibelle()));
		vSqlParams.addValue("libelle", pEcritureComptable.getLibelle());

		LOGGER.debug(new DebugMessage("SQLupdateEcritureComptable", SQLupdateEcritureComptable));
		vJdbcTemplate.update(SQLupdateEcritureComptable, vSqlParams);

		// ===== Liste des lignes d'écriture
		this.deleteListLigneEcritureComptable(pEcritureComptable.getId());
		this.insertListLigneEcritureComptable(pEcritureComptable);
		LOGGER.trace(new SortieMessage());
	}

	// ==================== EcritureComptable - DELETE ====================

	/** Requête SQL pour supprimer une écriture comptable */
	private static String SQLdeleteEcritureComptable;

	/** Initialise la requête SQL pour supprimer une écriture comptable */
	public void setSQLdeleteEcritureComptable(String pSQLdeleteEcritureComptable) {
		SQLdeleteEcritureComptable = pSQLdeleteEcritureComptable;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see NamedParameterJdbcTemplate
	 * @see MapSqlParameterSource
	 */
	@Override
	public void deleteEcritureComptable(Integer pId) {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new ParamMessage(Map.of("Integer pId", pId)));
		// ===== Suppression des lignes d'écriture
		this.deleteListLigneEcritureComptable(pId);

		// ===== Suppression de l'écriture
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		vSqlParams.addValue("id", pId);
		LOGGER.debug(new DebugMessage("SQLdeleteEcritureComptable", SQLdeleteEcritureComptable));
		vJdbcTemplate.update(SQLdeleteEcritureComptable, vSqlParams);
		LOGGER.trace(new SortieMessage());
	}

	/** Requête SQL pour supprimer des lignes d'écriture comptable */
	private static String SQLdeleteListLigneEcritureComptable;

	/** Initialise la requête SQL pour supprimer des lignes d'écriture comptable */
	public void setSQLdeleteListLigneEcritureComptable(String pSQLdeleteListLigneEcritureComptable) {
		SQLdeleteListLigneEcritureComptable = pSQLdeleteListLigneEcritureComptable;
	}

	/**
	 * Supprime les lignes d'écriture de l'écriture comptable d'id
	 * {@code pEcritureId}
	 * 
	 * @param pEcritureId id de l'écriture comptable
	 * 
	 * @see NamedParameterJdbcTemplate
	 * @see MapSqlParameterSource
	 */
	protected void deleteListLigneEcritureComptable(Integer pEcritureId) {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new ParamMessage(Map.of("Integer pEcritureId", pEcritureId)));
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		vSqlParams.addValue("ecriture_id", pEcritureId);
		LOGGER.debug(new DebugMessage("SQLdeleteListLigneEcritureComptable", SQLdeleteListLigneEcritureComptable));
		vJdbcTemplate.update(SQLdeleteListLigneEcritureComptable, vSqlParams);
		LOGGER.trace(new SortieMessage());
	}
}
