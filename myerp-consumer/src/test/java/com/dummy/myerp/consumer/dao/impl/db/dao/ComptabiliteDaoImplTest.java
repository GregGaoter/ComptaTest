package com.dummy.myerp.consumer.dao.impl.db.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.CompteComptableRM;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.JournalComptableRM;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.SequenceEcritureComptableRM;
import com.dummy.myerp.consumer.db.DataSourcesEnum;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;

@ExtendWith(MockitoExtension.class)
public class ComptabiliteDaoImplTest {

	@InjectMocks
	@Spy
	private ComptabiliteDaoImpl comptabiliteDaoImpl;
	@Mock
	private JdbcTemplate jdbcTemplate;
	@Mock
	private CompteComptableRM compteComptableRM;
	@Mock
	private JournalComptableRM journalComptableRM;
	@Mock
	private SequenceEcritureComptableRM sequenceEcritureComptableRM;

	// === getListCompteComptable() ===

	@Test
	public void getListCompteComptable_returnsListCompteComptable() {
		// GIVEN
		List<CompteComptable> expectedListCompteComptable = new ArrayList<>();
		ReflectionTestUtils.setField(ComptabiliteDaoImpl.class, "SQLgetListCompteComptable", "");
		doNothing().when(comptabiliteDaoImpl).initJdbcTemplate(any(DataSourcesEnum.class));
		doNothing().when(comptabiliteDaoImpl).initCompteComptableRM();
		when(jdbcTemplate.query(anyString(), any(CompteComptableRM.class))).thenReturn(expectedListCompteComptable);

		// WHEN
		List<CompteComptable> actualListCompteComptable = comptabiliteDaoImpl.getListCompteComptable();

		// THEN
		assertThat(actualListCompteComptable).isEqualTo(expectedListCompteComptable);
	}

	// === getListJournalComptable() ===

	@Test
	public void getListJournalComptable_returnsListJournalComptable() {
		// GIVEN
		List<JournalComptable> expectedListJournalComptable = new ArrayList<>();
		ReflectionTestUtils.setField(ComptabiliteDaoImpl.class, "SQLgetListJournalComptable", "");
		doNothing().when(comptabiliteDaoImpl).initJdbcTemplate(any(DataSourcesEnum.class));
		doNothing().when(comptabiliteDaoImpl).initJournalComptableRM();
		when(jdbcTemplate.query(anyString(), any(JournalComptableRM.class))).thenReturn(expectedListJournalComptable);

		// WHEN
		List<JournalComptable> actualListJournalComptable = comptabiliteDaoImpl.getListJournalComptable();

		// THEN
		assertThat(actualListJournalComptable).isEqualTo(expectedListJournalComptable);
	}

	// === getListSequenceEcritureComptable() ===

	@Test
	public void getListSequenceEcritureComptable_returnsListSequenceEcritureComptable() {
		// GIVEN
		List<SequenceEcritureComptable> expectedListSequenceEcritureComptable = new ArrayList<>();
		ReflectionTestUtils.setField(ComptabiliteDaoImpl.class, "SQLgetListSequenceEcritureComptable", "");
		doNothing().when(comptabiliteDaoImpl).initJdbcTemplate(any(DataSourcesEnum.class));
		doNothing().when(comptabiliteDaoImpl).initSequenceEcritureComptableRM();
		when(jdbcTemplate.query(anyString(), any(SequenceEcritureComptableRM.class)))
				.thenReturn(expectedListSequenceEcritureComptable);

		// WHEN
		List<SequenceEcritureComptable> actualListSequenceEcritureComptable = comptabiliteDaoImpl
				.getListSequenceEcritureComptable();

		// THEN
		assertThat(actualListSequenceEcritureComptable).isEqualTo(expectedListSequenceEcritureComptable);
	}

}
