package com.dummy.myerp.model.bean.comptabilite;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EcritureComptableTest {

	@Mock
	private EcritureComptable ecritureComptable;

	// ==================== isEquilibree ====================

	@ParameterizedTest
	@ValueSource(longs = { 20050L, -20050L, Long.MAX_VALUE, Long.MIN_VALUE, Long.MAX_VALUE / 2, Long.MIN_VALUE / 2, 0 })
	void isEquilibree_EcritureComptableCreditAndDebitWithMultipleEgalUnscaledVal_returnsTrue(long unscaledVal) {
		// GIVEN
		when(ecritureComptable.getTotalCredit()).thenReturn(BigDecimal.valueOf(unscaledVal, 2));
		when(ecritureComptable.getTotalDebit()).thenReturn(BigDecimal.valueOf(unscaledVal, 2));
		when(ecritureComptable.isEquilibree()).thenCallRealMethod();

		// WHEN
		boolean isEquilibree = ecritureComptable.isEquilibree();

		// THEN
		assertThat(isEquilibree).isTrue();
	}

	@Test
	void isEquilibree_EcritureComptableCreditAndDebitNull_returnsFalse() {
		// GIVEN
		when(ecritureComptable.getTotalCredit()).thenReturn(null);
		when(ecritureComptable.getTotalDebit()).thenReturn(null);
		when(ecritureComptable.isEquilibree()).thenCallRealMethod();

		// WHEN
		boolean isEquilibree = ecritureComptable.isEquilibree();

		// THEN
		assertThat(isEquilibree).isFalse();
	}

	@Test
	void isEquilibree_EcritureComptableCreditNullAndDebitNotNull_returnsFalse() {
		// GIVEN
		when(ecritureComptable.getTotalCredit()).thenReturn(null);
		when(ecritureComptable.getTotalDebit()).thenReturn(BigDecimal.valueOf(20050L, 2));
		when(ecritureComptable.isEquilibree()).thenCallRealMethod();

		// WHEN
		boolean isEquilibree = ecritureComptable.isEquilibree();

		// THEN
		assertThat(isEquilibree).isFalse();
	}

	@Test
	void isEquilibree_EcritureComptableCreditNotNullAndDebitNull_returnsFalse() {
		// GIVEN
		when(ecritureComptable.getTotalCredit()).thenReturn(BigDecimal.valueOf(20050L, 2));
		when(ecritureComptable.getTotalDebit()).thenReturn(null);
		when(ecritureComptable.isEquilibree()).thenCallRealMethod();

		// WHEN
		boolean isEquilibree = ecritureComptable.isEquilibree();

		// THEN
		assertThat(isEquilibree).isFalse();
	}

	@ParameterizedTest
	@ValueSource(ints = { 2, -2, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE / 2, Integer.MIN_VALUE / 2,
			0 })
	void isEquilibree_EcritureComptableCreditAndDebitWithMultipleEgalScale_returnsTrue(int scale) {
		// GIVEN
		when(ecritureComptable.getTotalCredit()).thenReturn(BigDecimal.valueOf(20050L, scale));
		when(ecritureComptable.getTotalDebit()).thenReturn(BigDecimal.valueOf(20050L, scale));
		when(ecritureComptable.isEquilibree()).thenCallRealMethod();

		// WHEN
		boolean isEquilibree = ecritureComptable.isEquilibree();

		// THEN
		assertThat(isEquilibree).isTrue();
	}

	@Test
	void isEquilibree_EcritureComptableCreditAndDebitNotEgal_returnsFalse() {
		// GIVEN
		when(ecritureComptable.getTotalCredit()).thenReturn(BigDecimal.valueOf(-538L, 2));
		when(ecritureComptable.getTotalDebit()).thenReturn(BigDecimal.valueOf(620L, 1));
		when(ecritureComptable.isEquilibree()).thenCallRealMethod();

		// WHEN
		boolean isEquilibree = ecritureComptable.isEquilibree();

		// THEN
		assertThat(isEquilibree).isFalse();
	}

}
