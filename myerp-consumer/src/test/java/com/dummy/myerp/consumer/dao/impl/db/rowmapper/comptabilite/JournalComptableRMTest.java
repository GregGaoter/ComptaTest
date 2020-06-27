package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.dummy.myerp.model.bean.comptabilite.JournalComptable;

public class JournalComptableRMTest {

	private JournalComptableRM journalComptableRM;

	@BeforeEach
	private void init() {
		journalComptableRM = new JournalComptableRM();
	}

	@AfterEach
	private void undef() {
		journalComptableRM = null;
	}

	// === initJournalComptable() ===

	@Test
	public void initJournalComptable_createsJournalComptable() {
		ReflectionTestUtils.invokeMethod(journalComptableRM, "initJournalComptable");
		JournalComptable journalComptable = (JournalComptable) ReflectionTestUtils.getField(journalComptableRM,
				"journalComptable");
		assertThat(journalComptable).isNotNull();
	}

	// === mapRow(ResultSet, int) ===

	private static Stream<Arguments> getArgumentsPourMapRowReturnsJournalComptable() {
		ResultSet resultSet = Mockito.mock(ResultSet.class);
		return Stream.of(Arguments.of(null, Integer.MIN_VALUE), Arguments.of(resultSet, Integer.MAX_VALUE),
				Arguments.of(resultSet, -2085791583), Arguments.of(resultSet, 0), Arguments.of(null, 725075609));
	}

	@ParameterizedTest
	@MethodSource("getArgumentsPourMapRowReturnsJournalComptable")
	public void mapRow_returnsJournalComptable(ResultSet resultSet, int rowNum) throws SQLException {
		// GIVEN
		if (resultSet != null) {
			when(resultSet.getString(anyString())).thenReturn("string");
		}

		// WHEN
		journalComptableRM.mapRow(resultSet, rowNum);
		JournalComptable journalComptable = (JournalComptable) ReflectionTestUtils.getField(journalComptableRM,
				"journalComptable");

		// THEN
		if (resultSet != null) {
			assertThat(journalComptable.getCode()).isEqualTo("string");
			assertThat(journalComptable.getLibelle()).isEqualTo("string");
		} else {
			assertThat(journalComptable).isNull();
		}
	}

}
