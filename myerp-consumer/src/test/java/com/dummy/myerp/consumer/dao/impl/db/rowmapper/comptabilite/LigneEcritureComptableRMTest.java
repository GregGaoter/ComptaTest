package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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

import com.dummy.myerp.consumer.dao.impl.cache.CompteComptableDaoCache;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;

@ExtendWith(MockitoExtension.class)
public class LigneEcritureComptableRMTest {

	private LigneEcritureComptableRM ligneEcritureComptableRM;

	@Mock
	private CompteComptableDaoCache compteComptableDaoCache;

	@BeforeEach
	private void init() {
		ligneEcritureComptableRM = new LigneEcritureComptableRM();
	}

	@AfterEach
	private void undef() {
		ligneEcritureComptableRM = null;
	}

	// === initLigneEcritureComptable() ===

	@Test
	public void initLigneEcritureComptable_createsLigneEcritureComptable() {
		ReflectionTestUtils.invokeMethod(ligneEcritureComptableRM, "initLigneEcritureComptable");
		LigneEcritureComptable ligneEcritureComptable = (LigneEcritureComptable) ReflectionTestUtils
				.getField(ligneEcritureComptableRM, "ligneEcritureComptable");
		assertThat(ligneEcritureComptable).isNotNull();
	}

	// === mapRow(ResultSet, int) ===

	private static Stream<Arguments> getArgumentsPourMapRowReturnsLigneEcritureComptable() {
		ResultSet resultSet = Mockito.mock(ResultSet.class);
		return Stream.of(Arguments.of(null, Integer.MAX_VALUE), Arguments.of(null, Integer.MIN_VALUE),
				Arguments.of(resultSet, 725075609), Arguments.of(resultSet, 0), Arguments.of(resultSet, -2085791583));
	}

	@ParameterizedTest
	@MethodSource("getArgumentsPourMapRowReturnsLigneEcritureComptable")
	public void mapRow_returnsLigneEcritureComptable(ResultSet resultSet, int rowNum) throws SQLException {
		// GIVEN
		if (resultSet != null) {
			when(resultSet.getObject(anyString(), eq(Integer.class))).thenReturn(1);
			when(resultSet.getBigDecimal(anyString())).thenReturn(BigDecimal.ONE);
			when(resultSet.getString(anyString())).thenReturn("string");
			when(compteComptableDaoCache.getByNumero(any(Integer.class))).thenReturn(new CompteComptable(1));
			ReflectionTestUtils.setField(ligneEcritureComptableRM, "compteComptableDaoCache", compteComptableDaoCache);
		}

		// WHEN
		ligneEcritureComptableRM.mapRow(resultSet, rowNum);
		LigneEcritureComptable ligneEcritureComptable = (LigneEcritureComptable) ReflectionTestUtils
				.getField(ligneEcritureComptableRM, "ligneEcritureComptable");

		// THEN
		if (resultSet != null) {
			assertThat(ligneEcritureComptable.getCompteComptable().getNumero()).isEqualTo(1);
			assertThat(ligneEcritureComptable.getCredit()).isEqualTo(BigDecimal.ONE);
			assertThat(ligneEcritureComptable.getDebit()).isEqualTo(BigDecimal.ONE);
			assertThat(ligneEcritureComptable.getLibelle()).isEqualTo("string");
		} else {
			assertThat(ligneEcritureComptable).isNull();
		}
	}

}
