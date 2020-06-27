package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.consumer.dao.impl.cache.JournalComptableDaoCache;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;

@ExtendWith(MockitoExtension.class)
public class EcritureComptableRMTest {

	private EcritureComptableRM ecritureComptableRM;

	@Mock
	private DaoProxy daoProxy;
	@Mock
	private ComptabiliteDao comptabiliteDao;
	@Mock
	private JournalComptableDaoCache journalComptableDaoCache;

	@BeforeEach
	private void init() {
		ecritureComptableRM = new EcritureComptableRM();
	}

	@AfterEach
	private void undef() {
		ecritureComptableRM = null;
	}

	// === initEcritureComptable() ===

	@Test
	public void initEcritureComptable_createsEcritureComptable() {
		ReflectionTestUtils.invokeMethod(ecritureComptableRM, "initEcritureComptable");
		EcritureComptable ecritureComptable = (EcritureComptable) ReflectionTestUtils.getField(ecritureComptableRM,
				"ecritureComptable");
		assertThat(ecritureComptable).isNotNull();
	}

	// === mapRow(ResultSet, int) ===

	private static Stream<Arguments> getArgumentsPourMapRowReturnsEcritureComptable() {
		ResultSet resultSet = Mockito.mock(ResultSet.class);
		return Stream.of(Arguments.of(null, 725075609), Arguments.of(null, Integer.MIN_VALUE),
				Arguments.of(resultSet, Integer.MAX_VALUE), Arguments.of(resultSet, 0),
				Arguments.of(resultSet, -2085791583));
	}

	@ParameterizedTest
	@MethodSource("getArgumentsPourMapRowReturnsEcritureComptable")
	public void mapRow_returnsEcritureComptable(ResultSet resultSet, int rowNum) throws SQLException {
		// GIVEN
		if (resultSet != null) {
			when(resultSet.getInt(anyString())).thenReturn(1);
			when(resultSet.getString(anyString())).thenReturn("string");
			when(resultSet.getDate(anyString())).thenReturn(new Date(0));
			when(journalComptableDaoCache.getByCode(anyString())).thenReturn(new JournalComptable("code", "libelle"));
			ReflectionTestUtils.setField(ecritureComptableRM, "journalComptableDaoCache", journalComptableDaoCache);
			ReflectionTestUtils.setField(ConsumerHelper.class, "daoProxy", daoProxy);
			when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
		}

		// WHEN
		ecritureComptableRM.mapRow(resultSet, rowNum);
		EcritureComptable ecritureComptable = (EcritureComptable) ReflectionTestUtils.getField(ecritureComptableRM,
				"ecritureComptable");

		// THEN
		if (resultSet != null) {
			assertThat(ecritureComptable.getId()).isEqualTo(1);
			assertThat(ecritureComptable.getJournal().getCode()).isEqualTo("code");
			assertThat(ecritureComptable.getJournal().getLibelle()).isEqualTo("libelle");
			assertThat(ecritureComptable.getReference()).isEqualTo("string");
			assertThat(ecritureComptable.getDate().getTime()).isEqualTo(0);
			assertThat(ecritureComptable.getLibelle()).isEqualTo("string");
			verify(comptabiliteDao).loadListLigneEcriture(any(EcritureComptable.class));
		} else {
			assertThat(ecritureComptable).isNull();
		}
	}

}
