package com.dummy.myerp.testconsumer.consumer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.dummy.myerp.consumer.dao.impl.db.dao.ComptabiliteDaoImpl;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.CompteComptableRM;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.EcritureComptableRM;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.JournalComptableRM;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.LigneEcritureComptableRM;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.SequenceEcritureComptableRM;
import com.dummy.myerp.consumer.db.DataSourcesEnum;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.NotFoundException;

public class ComptabiliteDaoImplIT extends AbstractConsumerIt {

	private ComptabiliteDaoImpl comptabiliteDaoImpl;

	@BeforeAll
	public static void initDb() {
		initDbExpected();
		Assertions.setAllowExtractingPrivateFields(false);
	}

	@AfterAll
	public static void undefDb() {
		undefDbExpected();
	}

	@BeforeEach
	public void init() {
		comptabiliteDaoImpl = ComptabiliteDaoImpl.getInstance();
	}

	@AfterEach
	public void undef() {
		comptabiliteDaoImpl = null;
	}

	// === initJdbcTemplate(DataSourcesEnum) ===

	@Test
	public void initJdbcTemplate_initJdbcTemplate() {
		// GIVEN

		// WHEN
		comptabiliteDaoImpl.initJdbcTemplate(DataSourcesEnum.MYERP);
		JdbcTemplate jdbcTemplate = (JdbcTemplate) ReflectionTestUtils.getField(comptabiliteDaoImpl, "jdbcTemplate");

		// THEN
		assertThat(jdbcTemplate).isNotNull();
	}

	// === initNamedParameterJdbcTemplate(DataSourcesEnum) ===

	@Test
	public void initNamedParameterJdbcTemplate_initNamedParameterJdbcTemplate() {
		// GIVEN

		// WHEN
		comptabiliteDaoImpl.initNamedParameterJdbcTemplate(DataSourcesEnum.MYERP);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) ReflectionTestUtils
				.getField(comptabiliteDaoImpl, "namedParameterJdbcTemplate");

		// THEN
		assertThat(namedParameterJdbcTemplate).isNotNull();
	}

	// === initMapSqlParameterSource() ===

	@Test
	public void initMapSqlParameterSource_initMapSqlParameterSource() {
		// GIVEN

		// WHEN
		comptabiliteDaoImpl.initMapSqlParameterSource();
		MapSqlParameterSource mapSqlParameterSource = (MapSqlParameterSource) ReflectionTestUtils
				.getField(comptabiliteDaoImpl, "mapSqlParameterSource");

		// THEN
		assertThat(mapSqlParameterSource).isNotNull();
	}

	// === initCompteComptableRM() ===

	@Test
	public void initCompteComptableRM_initCompteComptableRM() {
		// GIVEN

		// WHEN
		comptabiliteDaoImpl.initCompteComptableRM();
		CompteComptableRM compteComptableRM = (CompteComptableRM) ReflectionTestUtils.getField(comptabiliteDaoImpl,
				"compteComptableRM");

		// THEN
		assertThat(compteComptableRM).isNotNull();
	}

	// === initJournalComptableRM() ===

	@Test
	public void initJournalComptableRM_initJournalComptableRM() {
		// GIVEN

		// WHEN
		comptabiliteDaoImpl.initJournalComptableRM();
		JournalComptableRM journalComptableRM = (JournalComptableRM) ReflectionTestUtils.getField(comptabiliteDaoImpl,
				"journalComptableRM");

		// THEN
		assertThat(journalComptableRM).isNotNull();
	}

	// === initSequenceEcritureComptableRM() ===

	@Test
	public void initSequenceEcritureComptableRM_initSequenceEcritureComptableRM() {
		// GIVEN

		// WHEN
		comptabiliteDaoImpl.initSequenceEcritureComptableRM();
		SequenceEcritureComptableRM sequenceEcritureComptableRM = (SequenceEcritureComptableRM) ReflectionTestUtils
				.getField(comptabiliteDaoImpl, "sequenceEcritureComptableRM");

		// THEN
		assertThat(sequenceEcritureComptableRM).isNotNull();
	}

	// === initEcritureComptableRM() ===

	@Test
	public void initEcritureComptableRM_initEcritureComptableRM() {
		// GIVEN

		// WHEN
		comptabiliteDaoImpl.initEcritureComptableRM();
		EcritureComptableRM ecritureComptableRM = (EcritureComptableRM) ReflectionTestUtils
				.getField(comptabiliteDaoImpl, "ecritureComptableRM");

		// THEN
		assertThat(ecritureComptableRM).isNotNull();
	}

	// === initLigneEcritureComptableRM() ===

	@Test
	public void initLigneEcritureComptableRM_initLigneEcritureComptableRM() {
		// GIVEN

		// WHEN
		comptabiliteDaoImpl.initLigneEcritureComptableRM();
		LigneEcritureComptableRM ligneEcritureComptableRM = (LigneEcritureComptableRM) ReflectionTestUtils
				.getField(comptabiliteDaoImpl, "ligneEcritureComptableRM");

		// THEN
		assertThat(ligneEcritureComptableRM).isNotNull();
	}

	// === getListCompteComptable() ===

	@Test
	public void getListCompteComptable_returnsListCompteComptable() {
		dockerEnvironment.start();
		try {
			// GIVEN

			// WHEN
			List<CompteComptable> listeCompteActual = comptabiliteDaoImpl.getListCompteComptable();

			// THEN
			assertThat(listeCompteActual).usingFieldByFieldElementComparator().containsAll(mapCompteExpected.values());
		} finally {
			dockerEnvironment.stop();
		}
	}

	// === getListJournalComptable() ===

	@Test
	public void getListJournalComptable_returnsListJournalComptable() {
		dockerEnvironment.start();
		try {
			// GIVEN

			// WHEN
			List<JournalComptable> listeJournalActual = comptabiliteDaoImpl.getListJournalComptable();

			// THEN
			assertThat(listeJournalActual).usingFieldByFieldElementComparator()
					.containsAll(mapJournalExpected.values());
		} finally {
			dockerEnvironment.stop();
		}
	}

	// === getListSequenceEcritureComptable() ===

	@Test
	public void getListSequenceEcritureComptable_returnsListSequenceEcritureComptable() {
		dockerEnvironment.start();
		try {
			// GIVEN

			// WHEN
			List<SequenceEcritureComptable> listeSequenceEcritureActual = comptabiliteDaoImpl
					.getListSequenceEcritureComptable();

			// THEN
			assertThat(listeSequenceEcritureActual).usingFieldByFieldElementComparator()
					.containsAll(mapSequenceEcritureExpected.values());
		} finally {
			dockerEnvironment.stop();
		}
	}

	// === insertSequenceEcritureComptable(SequenceEcritureComptable) ===

	@Test
	public void insertSequenceEcritureComptable_insertsSequenceEcritureComptable() {
		dockerEnvironment.start();
		try {
			// GIVEN
			SequenceEcritureComptable sequenceEcriture = new SequenceEcritureComptable("BQ", 2020, 1);

			// WHEN
			comptabiliteDaoImpl.insertSequenceEcritureComptable(sequenceEcriture);
			List<SequenceEcritureComptable> listeSequenceEcritureActual = comptabiliteDaoImpl
					.getListSequenceEcritureComptable();

			// THEN
			assertThat(listeSequenceEcritureActual).usingFieldByFieldElementComparator().contains(sequenceEcriture);
		} finally {
			dockerEnvironment.stop();
		}
	}

	// === updateSequenceEcritureComptable(SequenceEcritureComptable) ===

	@Test
	public void updateSequenceEcritureComptable_updatesSequenceEcritureComptable() {
		dockerEnvironment.start();
		try {
			// GIVEN
			SequenceEcritureComptable sequenceEcriture = new SequenceEcritureComptable("BQ", 2016, 52);

			// WHEN
			comptabiliteDaoImpl.updateSequenceEcritureComptable(sequenceEcriture);
			List<SequenceEcritureComptable> listeSequenceEcritureActual = comptabiliteDaoImpl
					.getListSequenceEcritureComptable();

			// THEN
			assertThat(listeSequenceEcritureActual).usingFieldByFieldElementComparator().contains(sequenceEcriture);
		} finally {
			dockerEnvironment.stop();
		}
	}

	// === getListEcritureComptable() ===

	@Test
	public void getListEcritureComptable_returnsListEcritureComptable() {
		dockerEnvironment.start();
		try {
			// GIVEN

			// WHEN
			List<EcritureComptable> listeEcritureActual = comptabiliteDaoImpl.getListEcritureComptable();

			// THEN
			assertThat(listeEcritureActual).usingElementComparator(comparatorEcritureComptable)
					.containsAll(mapEcritureExpected.values());
		} finally {
			dockerEnvironment.stop();
		}
	}

	// === getEcritureComptable(Integer) ===

	@Test
	public void getEcritureComptable_withIdValid_returnsEcritureComptable() throws NotFoundException {
		dockerEnvironment.start();
		try {
			// GIVEN
			EcritureComptable ecritureExpected = mapEcritureExpected.get(-3);

			// WHEN
			EcritureComptable ecritureActual = comptabiliteDaoImpl.getEcritureComptable(-3);

			// THEN
			assertThat(ecritureActual).usingComparator(comparatorEcritureComptable).isEqualTo(ecritureExpected);
		} finally {
			dockerEnvironment.stop();
		}
	}

}
