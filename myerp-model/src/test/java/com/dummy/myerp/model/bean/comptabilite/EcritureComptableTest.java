package com.dummy.myerp.model.bean.comptabilite;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
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

	@Mock
	private JournalComptable journalComptable;

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

	// ==================== toString ====================

	@Disabled
	@Test
	void toString_ecritureComptable_returnsEcritureComptableString() {
		// GIVEN
		EcritureComptable ecritureComptable = new EcritureComptable();
		ecritureComptable.setId(1);
		ecritureComptable.setJournal(journalComptable);
		ecritureComptable.setReference("JC-2020/00001");
		ecritureComptable.setDate(Date.valueOf("2020-04-09"));
		ecritureComptable.setLibelle("Ecriture comptable test");
		when(ecritureComptable.getTotalCredit()).thenReturn(BigDecimal.valueOf(20050L, 2));
		when(ecritureComptable.getTotalDebit()).thenReturn(BigDecimal.valueOf(20050L, 2));
		List<LigneEcritureComptable> listLigneEcriture = new ArrayList<>();
		listLigneEcriture.add(new LigneEcritureComptable(new CompteComptable(1, "C1"), "L1",
				BigDecimal.valueOf(888L, -2), BigDecimal.valueOf(-813L, -1)));
		listLigneEcriture.add(new LigneEcritureComptable(new CompteComptable(2, "C2"), "L2",
				BigDecimal.valueOf(-77L, 2), BigDecimal.valueOf(43L, -2)));
		listLigneEcriture.add(new LigneEcritureComptable(new CompteComptable(3, "C3"), "L3",
				BigDecimal.valueOf(-473L, -1), BigDecimal.valueOf(85L, 1)));

		// WHEN
		String actualString = ecritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("CompteComptable{numero=-2147483648, libelle='Libellé'}");
	}

}
