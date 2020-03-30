package com.dummy.myerp.consumer.dao.impl.db.dao;

import java.sql.Types;
import java.util.List;

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
import com.dummy.myerp.technical.log.message.EntreeMessage;
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
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		vSqlParams.addValue("id", pId);
		EcritureComptableRM vRM = new EcritureComptableRM();
		EcritureComptable vBean;
		try {
			vBean = vJdbcTemplate.queryForObject(SQLgetEcritureComptable, vSqlParams, vRM);
		} catch (EmptyResultDataAccessException vEx) {
			throw new NotFoundException("EcritureComptable non trouvée : id=" + pId);
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
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		vSqlParams.addValue("reference", pReference);
		EcritureComptableRM vRM = new EcritureComptableRM();
		EcritureComptable vBean;
		try {
			vBean = vJdbcTemplate.queryForObject(SQLgetEcritureComptableByRef, vSqlParams, vRM);
		} catch (EmptyResultDataAccessException vEx) {
			throw new NotFoundException("EcritureComptable non trouvée : reference=" + pReference);
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
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		vSqlParams.addValue("ecriture_id", pEcritureComptable.getId());
		LigneEcritureComptableRM vRM = new LigneEcritureComptableRM();
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
		// ===== Ecriture Comptable
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		vSqlParams.addValue("journal_code", pEcritureComptable.getJournal().getCode());
		vSqlParams.addValue("reference", pEcritureComptable.getReference());
		vSqlParams.addValue("date", pEcritureComptable.getDate(), Types.DATE);
		vSqlParams.addValue("libelle", pEcritureComptable.getLibelle());

		vJdbcTemplate.update(SQLinsertEcritureComptable, vSqlParams);

		// ----- Récupération de l'id
		Integer vId = this.queryGetSequenceValuePostgreSQL(DataSourcesEnum.MYERP, "myerp.ecriture_comptable_id_seq",
				Integer.class);
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
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		vSqlParams.addValue("ecriture_id", pEcritureComptable.getId());

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
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		vSqlParams.addValue("id", pEcritureComptable.getId());
		vSqlParams.addValue("journal_code", pEcritureComptable.getJournal().getCode());
		vSqlParams.addValue("reference", pEcritureComptable.getReference());
		vSqlParams.addValue("date", pEcritureComptable.getDate(), Types.DATE);
		vSqlParams.addValue("libelle", pEcritureComptable.getLibelle());

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
		// ===== Suppression des lignes d'écriture
		this.deleteListLigneEcritureComptable(pId);

		// ===== Suppression de l'écriture
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		vSqlParams.addValue("id", pId);
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
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
		MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
		vSqlParams.addValue("ecriture_id", pEcritureId);
		vJdbcTemplate.update(SQLdeleteListLigneEcritureComptable, vSqlParams);
		LOGGER.trace(new SortieMessage());
	}
}
