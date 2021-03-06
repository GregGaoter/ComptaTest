package com.dummy.myerp.consumer.dao.impl.db.dao;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.CompteComptableRM;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.EcritureComptableRM;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.JournalComptableRM;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.LigneEcritureComptableRM;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.SequenceEcritureComptableRM;
import com.dummy.myerp.consumer.db.AbstractDbConsumer;
import com.dummy.myerp.consumer.db.DataSourcesEnum;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.technical.log.message.DebugMessage;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.ErrorMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * Extension de la classe abstraite {@link AbstractDbConsumer} et implémentation
 * de l'interface {@link ComptabiliteDao}
 */
public class ComptabiliteDaoImpl extends AbstractDbConsumer implements ComptabiliteDao {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(ComptabiliteDaoImpl.class);

	// ==================== Attributs ====================

	/**
	 * {@link JdbcTemplate}
	 */
	private JdbcTemplate jdbcTemplate;
	/**
	 * {@link NamedParameterJdbcTemplate}
	 */
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	/**
	 * {@link MapSqlParameterSource}
	 */
	private MapSqlParameterSource mapSqlParameterSource;
	/**
	 * {@link CompteComptableRM}
	 */
	private CompteComptableRM compteComptableRM;
	/**
	 * {@link JournalComptableRM}
	 */
	private JournalComptableRM journalComptableRM;
	/**
	 * {@link SequenceEcritureComptableRM}
	 */
	private SequenceEcritureComptableRM sequenceEcritureComptableRM;
	/**
	 * {@link EcritureComptableRM}
	 */
	private EcritureComptableRM ecritureComptableRM;
	/**
	 * {@link LigneEcritureComptableRM}
	 */
	private LigneEcritureComptableRM ligneEcritureComptableRM;

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

	/**
	 * Initialise le {@link JdbcTemplate}.
	 * 
	 * @param dataSourcesEnum {@link DataSource} utilisée.
	 */
	public void initJdbcTemplate(DataSourcesEnum dataSourcesEnum) {
		jdbcTemplate = new JdbcTemplate(getDataSource(dataSourcesEnum));
	}

	/**
	 * Initialise le {@link NamedParameterJdbcTemplate}.
	 * 
	 * @param dataSourcesEnum {@link DataSource} utilisée.
	 */
	public void initNamedParameterJdbcTemplate(DataSourcesEnum dataSourcesEnum) {
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(dataSourcesEnum));
	}

	/**
	 * Initialise le {@link MapSqlParameterSource}.
	 */
	public void initMapSqlParameterSource() {
		mapSqlParameterSource = new MapSqlParameterSource();
	}

	/**
	 * Initialise le {@link RowMapper} de {@link CompteComptable}
	 */
	public void initCompteComptableRM() {
		compteComptableRM = new CompteComptableRM();
	}

	/**
	 * Initialise le {@link RowMapper} de {@link JournalComptable}
	 */
	public void initJournalComptableRM() {
		journalComptableRM = new JournalComptableRM();
	}

	/**
	 * Initialise le {@link RowMapper} de {@link SequenceEcritureComptableRM}
	 */
	public void initSequenceEcritureComptableRM() {
		sequenceEcritureComptableRM = new SequenceEcritureComptableRM();
	}

	/**
	 * Initialise le {@link RowMapper} de {@link EcritureComptableRM}
	 */
	public void initEcritureComptableRM() {
		ecritureComptableRM = new EcritureComptableRM();
	}

	/**
	 * Initialise le {@link RowMapper} de {@link LigneEcritureComptableRM}
	 */
	public void initLigneEcritureComptableRM() {
		ligneEcritureComptableRM = new LigneEcritureComptableRM();
	}

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
		initJdbcTemplate(DataSourcesEnum.MYERP);
		initCompteComptableRM();
		LOGGER.debug(new DebugMessage("SQLgetListCompteComptable", SQLgetListCompteComptable));
		List<CompteComptable> vList = jdbcTemplate.query(SQLgetListCompteComptable, compteComptableRM);
		LOGGER.trace(new SortieMessage());
		return vList;
	}

	/** Requête SQL pour avoir la liste des lignes d'écriture comptables */
	private static String SQLgetListLigneEcritureComptable;

	/**
	 * Initialise la requête SQL pour avoir la liste des lignes d'écriture
	 * comptables
	 */
	public void setSQLgetListLigneEcritureComptable(String pSQLgetListLigneEcritureComptable) {
		SQLgetListLigneEcritureComptable = pSQLgetListLigneEcritureComptable;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see JdbcTemplate
	 * @see LigneEcritureComptableRM
	 */
	@Override
	public List<LigneEcritureComptable> getListLigneEcritureComptable() {
		LOGGER.trace(new EntreeMessage());
		initJdbcTemplate(DataSourcesEnum.MYERP);
		initLigneEcritureComptableRM();
		LOGGER.debug(new DebugMessage("SQLgetListLigneEcritureComptable", SQLgetListLigneEcritureComptable));
		List<LigneEcritureComptable> vList = jdbcTemplate.query(SQLgetListLigneEcritureComptable,
				ligneEcritureComptableRM);
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
		initJdbcTemplate(DataSourcesEnum.MYERP);
		initJournalComptableRM();
		LOGGER.debug(new DebugMessage("SQLgetListJournalComptable", SQLgetListJournalComptable));
		List<JournalComptable> vList = jdbcTemplate.query(SQLgetListJournalComptable, journalComptableRM);
		LOGGER.trace(new SortieMessage());
		return vList;
	}

	/** Requête SQL pour avoir la liste des séquences d'écritures comptables */
	private static String SQLgetListSequenceEcritureComptable;

	/**
	 * Initialise la requête SQL pour avoir la liste des séquences d'écritures
	 * comptables
	 */
	public void setSQLgetListSequenceEcritureComptable(String pSQLgetListSequenceEcritureComptable) {
		SQLgetListSequenceEcritureComptable = pSQLgetListSequenceEcritureComptable;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see JdbcTemplate
	 * @see SequenceEcritureComptableRM
	 */
	@Override
	public List<SequenceEcritureComptable> getListSequenceEcritureComptable() {
		LOGGER.trace(new EntreeMessage());
		initJdbcTemplate(DataSourcesEnum.MYERP);
		initSequenceEcritureComptableRM();
		LOGGER.debug(new DebugMessage("SQLgetListSequenceEcritureComptable", SQLgetListSequenceEcritureComptable));
		List<SequenceEcritureComptable> vList = jdbcTemplate.query(SQLgetListSequenceEcritureComptable,
				sequenceEcritureComptableRM);
		LOGGER.trace(new SortieMessage());
		return vList;
	}

	// ==================== SequenceEcritureComptable - INSERT ====================

	/** Requête SQL pour insérer une séquence d'écriture comptable */
	private static String SQLinsertSequenceEcritureComptable;

	/** Initialise la requête SQL pour insérer une séquence d'écriture comptable */
	public void setSQLinsertSequenceEcritureComptable(String pSQLinsertSequenceEcritureComptable) {
		SQLinsertSequenceEcritureComptable = pSQLinsertSequenceEcritureComptable;
	}

	@Override
	public void insertSequenceEcritureComptable(SequenceEcritureComptable pSequenceEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		initNamedParameterJdbcTemplate(DataSourcesEnum.MYERP);
		initMapSqlParameterSource();
		mapSqlParameterSource.addValue("journal_code", pSequenceEcritureComptable.getJournalCode());
		mapSqlParameterSource.addValue("annee", pSequenceEcritureComptable.getAnnee());
		mapSqlParameterSource.addValue("derniere_valeur", pSequenceEcritureComptable.getDerniereValeur());
		LOGGER.debug(new DebugMessage("SQLinsertSequenceEcritureComptable", SQLinsertSequenceEcritureComptable));
		namedParameterJdbcTemplate.update(SQLinsertSequenceEcritureComptable, mapSqlParameterSource);
		LOGGER.trace(new SortieMessage());
	}

	// ==================== SequenceEcritureComptable - UPDATE ====================

	/** Requête SQL pour mettre à jour une séquence d'écriture comptable */
	private static String SQLupdateSequenceEcritureComptable;

	/**
	 * Initialise la requête SQL pour mettre à jour une séquence d'écriture
	 * comptable
	 */
	public void setSQLupdateSequenceEcritureComptable(String pSQLupdateSequenceEcritureComptable) {
		SQLupdateSequenceEcritureComptable = pSQLupdateSequenceEcritureComptable;
	}

	@Override
	public void updateSequenceEcritureComptable(SequenceEcritureComptable pSequenceEcritureComptable) {
		LOGGER.trace(new EntreeMessage());
		initNamedParameterJdbcTemplate(DataSourcesEnum.MYERP);
		initMapSqlParameterSource();
		mapSqlParameterSource.addValue("journal_code", pSequenceEcritureComptable.getJournalCode());
		mapSqlParameterSource.addValue("annee", pSequenceEcritureComptable.getAnnee());
		mapSqlParameterSource.addValue("derniere_valeur", pSequenceEcritureComptable.getDerniereValeur());
		LOGGER.debug(new DebugMessage("SQLupdateSequenceEcritureComptable", SQLupdateSequenceEcritureComptable));
		namedParameterJdbcTemplate.update(SQLupdateSequenceEcritureComptable, mapSqlParameterSource);
		LOGGER.trace(new SortieMessage());
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
		initJdbcTemplate(DataSourcesEnum.MYERP);
		initEcritureComptableRM();
		LOGGER.debug(new DebugMessage("SQLgetListEcritureComptable", SQLgetListEcritureComptable));
		List<EcritureComptable> vList = jdbcTemplate.query(SQLgetListEcritureComptable, ecritureComptableRM);
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
		LOGGER.debug(new DebugMessage("Integer pId", pId));
		initNamedParameterJdbcTemplate(DataSourcesEnum.MYERP);
		initMapSqlParameterSource();
		mapSqlParameterSource.addValue("id", pId);
		initEcritureComptableRM();
		EcritureComptable vBean;
		LOGGER.debug(new DebugMessage("SQLgetEcritureComptable", SQLgetEcritureComptable));
		try {
			vBean = namedParameterJdbcTemplate.queryForObject(SQLgetEcritureComptable, mapSqlParameterSource,
					ecritureComptableRM);
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
		LOGGER.debug(new DebugMessage("String pReference", pReference));
		initNamedParameterJdbcTemplate(DataSourcesEnum.MYERP);
		initMapSqlParameterSource();
		mapSqlParameterSource.addValue("reference", pReference);
		initEcritureComptableRM();
		EcritureComptable vBean;
		LOGGER.debug(new DebugMessage("SQLgetEcritureComptableByRef", SQLgetEcritureComptableByRef));
		try {
			vBean = namedParameterJdbcTemplate.queryForObject(SQLgetEcritureComptableByRef, mapSqlParameterSource,
					ecritureComptableRM);
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
		LOGGER.debug(new DebugMessage("EcritureComptable pEcritureComptable", pEcritureComptable));
		initNamedParameterJdbcTemplate(DataSourcesEnum.MYERP);
		initMapSqlParameterSource();
		LOGGER.debug(new DebugMessage("pEcritureComptable.getId()", pEcritureComptable.getId()));
		mapSqlParameterSource.addValue("ecriture_id", pEcritureComptable.getId());
		initLigneEcritureComptableRM();
		LOGGER.debug(new DebugMessage("SQLloadListLigneEcriture", SQLloadListLigneEcriture));
		List<LigneEcritureComptable> vList = namedParameterJdbcTemplate.query(SQLloadListLigneEcriture,
				mapSqlParameterSource, ligneEcritureComptableRM);
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
		// ===== Ecriture Comptable
		initNamedParameterJdbcTemplate(DataSourcesEnum.MYERP);
		initMapSqlParameterSource();
		LOGGER.debug(new DebugMessage("pEcritureComptable.getJournal().getCode()",
				pEcritureComptable.getJournal().getCode()));
		mapSqlParameterSource.addValue("journal_code", pEcritureComptable.getJournal().getCode());
		LOGGER.debug(new DebugMessage("pEcritureComptable.getReference()", pEcritureComptable.getReference()));
		mapSqlParameterSource.addValue("reference", pEcritureComptable.getReference());
		LOGGER.debug(new DebugMessage("pEcritureComptable.getDate()", pEcritureComptable.getDate()));
		mapSqlParameterSource.addValue("date", pEcritureComptable.getDate(), Types.DATE);
		LOGGER.debug(new DebugMessage("pEcritureComptable.getLibelle()", pEcritureComptable.getLibelle()));
		mapSqlParameterSource.addValue("libelle", pEcritureComptable.getLibelle());

		LOGGER.debug(new DebugMessage("SQLinsertEcritureComptable", SQLinsertEcritureComptable));
		namedParameterJdbcTemplate.update(SQLinsertEcritureComptable, mapSqlParameterSource);

		// ----- Récupération de l'id
		Integer vId = getSequenceValue(DataSourcesEnum.MYERP, "myerp.ecriture_comptable_id_seq");
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
		initNamedParameterJdbcTemplate(DataSourcesEnum.MYERP);
		initMapSqlParameterSource();
		LOGGER.debug(new DebugMessage("pEcritureComptable.getId()", pEcritureComptable.getId()));
		mapSqlParameterSource.addValue("ecriture_id", pEcritureComptable.getId());
		LOGGER.debug(new DebugMessage("SQLinsertListLigneEcritureComptable", SQLinsertListLigneEcritureComptable));
		int vLigneId = 0;
		for (LigneEcritureComptable vLigne : pEcritureComptable.getListLigneEcriture()) {
			vLigneId++;
			mapSqlParameterSource.addValue("ligne_id", vLigneId);
			mapSqlParameterSource.addValue("compte_comptable_numero", vLigne.getCompteComptable().getNumero());
			mapSqlParameterSource.addValue("libelle", vLigne.getLibelle());
			mapSqlParameterSource.addValue("debit", vLigne.getDebit());
			mapSqlParameterSource.addValue("credit", vLigne.getCredit());
			namedParameterJdbcTemplate.update(SQLinsertListLigneEcritureComptable, mapSqlParameterSource);
		}
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

		// ===== Ecriture Comptable
		initNamedParameterJdbcTemplate(DataSourcesEnum.MYERP);
		initMapSqlParameterSource();
		LOGGER.debug(new DebugMessage("pEcritureComptable.getId()", pEcritureComptable.getId()));
		mapSqlParameterSource.addValue("id", pEcritureComptable.getId());
		LOGGER.debug(new DebugMessage("pEcritureComptable.getJournal().getCode()",
				pEcritureComptable.getJournal().getCode()));
		mapSqlParameterSource.addValue("journal_code", pEcritureComptable.getJournal().getCode());
		LOGGER.debug(new DebugMessage("pEcritureComptable.getReference()", pEcritureComptable.getReference()));
		mapSqlParameterSource.addValue("reference", pEcritureComptable.getReference());
		LOGGER.debug(new DebugMessage("pEcritureComptable.getDate()", pEcritureComptable.getDate()));
		mapSqlParameterSource.addValue("date", pEcritureComptable.getDate(), Types.DATE);
		LOGGER.debug(new DebugMessage("pEcritureComptable.getLibelle()", pEcritureComptable.getLibelle()));
		mapSqlParameterSource.addValue("libelle", pEcritureComptable.getLibelle());
		LOGGER.debug(new DebugMessage("SQLupdateEcritureComptable", SQLupdateEcritureComptable));
		namedParameterJdbcTemplate.update(SQLupdateEcritureComptable, mapSqlParameterSource);

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
		LOGGER.debug(new DebugMessage("Integer pId", pId));
		// ===== Suppression des lignes d'écriture
		this.deleteListLigneEcritureComptable(pId);

		// ===== Suppression de l'écriture
		initNamedParameterJdbcTemplate(DataSourcesEnum.MYERP);
		initMapSqlParameterSource();
		mapSqlParameterSource.addValue("id", pId);
		LOGGER.debug(new DebugMessage("SQLdeleteEcritureComptable", SQLdeleteEcritureComptable));
		namedParameterJdbcTemplate.update(SQLdeleteEcritureComptable, mapSqlParameterSource);
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
		LOGGER.debug(new DebugMessage("Integer pEcritureId", pEcritureId));
		initNamedParameterJdbcTemplate(DataSourcesEnum.MYERP);
		initMapSqlParameterSource();
		mapSqlParameterSource.addValue("ecriture_id", pEcritureId);
		LOGGER.debug(new DebugMessage("SQLdeleteListLigneEcritureComptable", SQLdeleteListLigneEcritureComptable));
		namedParameterJdbcTemplate.update(SQLdeleteListLigneEcritureComptable, mapSqlParameterSource);
		LOGGER.trace(new SortieMessage());
	}

}
