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
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;

@ExtendWith(MockitoExtension.class)
public class SequenceEcritureComptableRMTest {

	private SequenceEcritureComptableRM sequenceEcritureComptableRM;

	@BeforeEach
	private void init() {
		sequenceEcritureComptableRM = new SequenceEcritureComptableRM();
	}

	@AfterEach
	private void undef() {
		sequenceEcritureComptableRM = null;
	}

	// === initSequenceEcritureComptable() ===

	@Test
	public void initSequenceEcritureComptable_createsSequenceEcritureComptable() {
		ReflectionTestUtils.invokeMethod(sequenceEcritureComptableRM, "initSequenceEcritureComptable");
		SequenceEcritureComptable sequenceEcritureComptable = (SequenceEcritureComptable) ReflectionTestUtils
				.getField(sequenceEcritureComptableRM, "sequenceEcritureComptable");
		assertThat(sequenceEcritureComptable).isNotNull();
	}

	// === mapRow(ResultSet, int) ===

	private static Stream<Arguments> getArgumentsPourMapRowReturnsSequenceEcritureComptable() {
		ResultSet resultSet = Mockito.mock(ResultSet.class);
		return Stream.of(Arguments.of(resultSet, Integer.MIN_VALUE), Arguments.of(null, Integer.MAX_VALUE),
				Arguments.of(null, 0), Arguments.of(resultSet, -356327592), Arguments.of(resultSet, 669260762));
	}

	@ParameterizedTest
	@MethodSource("getArgumentsPourMapRowReturnsSequenceEcritureComptable")
	public void mapRow_returnsCompteComptable(ResultSet resultSet, int rowNum) throws SQLException {
		// GIVEN
		if (resultSet != null) {
			when(resultSet.getInt(anyString())).thenReturn(1);
			when(resultSet.getString(anyString())).thenReturn("string");
		}

		// WHEN
		sequenceEcritureComptableRM.mapRow(resultSet, rowNum);
		SequenceEcritureComptable sequenceEcritureComptable = (SequenceEcritureComptable) ReflectionTestUtils
				.getField(sequenceEcritureComptableRM, "sequenceEcritureComptable");

		// THEN
		if (resultSet != null) {
			assertThat(sequenceEcritureComptable.getJournalCode()).isEqualTo("string");
			assertThat(sequenceEcritureComptable.getAnnee()).isEqualTo(1);
			assertThat(sequenceEcritureComptable.getDerniereValeur()).isEqualTo(1);
		} else {
			assertThat(sequenceEcritureComptable).isNull();
		}
	}

}
