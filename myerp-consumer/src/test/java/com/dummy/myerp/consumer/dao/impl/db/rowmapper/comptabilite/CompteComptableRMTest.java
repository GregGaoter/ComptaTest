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

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;

@ExtendWith(MockitoExtension.class)
public class CompteComptableRMTest {

	private CompteComptableRM compteComptableRM;

	@BeforeEach
	private void init() {
		compteComptableRM = new CompteComptableRM();
	}

	@AfterEach
	private void undef() {
		compteComptableRM = null;
	}

	// === CompteComptableRM() ===

	@Test
	public void CompteComptableRM_createsCompteComptable() {
		CompteComptable compteComptable = (CompteComptable) ReflectionTestUtils.getField(compteComptableRM,
				"compteComptable");
		assertThat(compteComptable).isNotNull();
	}

	// === mapRow(ResultSet, int) ===

	private static Stream<Arguments> getArgumentsPourMapRowReturnsCompteComptable() {
		ResultSet resultSet = Mockito.mock(ResultSet.class);
		return Stream.of(Arguments.of(null, Integer.MAX_VALUE), Arguments.of(resultSet, Integer.MIN_VALUE),
				Arguments.of(resultSet, 0), Arguments.of(null, 924630082), Arguments.of(resultSet, -1472322202));
	}

	@ParameterizedTest
	@MethodSource("getArgumentsPourMapRowReturnsCompteComptable")
	public void mapRow_returnsCompteComptable(ResultSet resultSet, int rowNum) throws SQLException {
		// GIVEN
		if (resultSet != null) {
			when(resultSet.getInt(anyString())).thenReturn(1);
			when(resultSet.getString(anyString())).thenReturn("string");
		}

		// WHEN
		compteComptableRM.mapRow(resultSet, rowNum);
		CompteComptable compteComptable = (CompteComptable) ReflectionTestUtils.getField(compteComptableRM,
				"compteComptable");

		// THEN
		if (resultSet != null) {
			assertThat(compteComptable.getNumero()).isEqualTo(1);
			assertThat(compteComptable.getLibelle()).isEqualTo("string");
		} else {
			assertThat(compteComptable).isNull();
		}
	}

}
