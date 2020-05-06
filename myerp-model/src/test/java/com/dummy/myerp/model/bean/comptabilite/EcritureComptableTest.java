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
import org.mockito.Mockito;
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

	// ==================== getTotalDebit ====================

	@Test
	void getTotalDebit_ecritureComptableDebitNormal_returnsBigDecimal() {
		// GIVEN
		LigneEcritureComptable ligneEcritureComptable01 = Mockito.mock(LigneEcritureComptable.class);
		LigneEcritureComptable ligneEcritureComptable02 = Mockito.mock(LigneEcritureComptable.class);
		LigneEcritureComptable ligneEcritureComptable03 = Mockito.mock(LigneEcritureComptable.class);
		when(ligneEcritureComptable01.getDebit()).thenReturn(BigDecimal.valueOf(4395L, 2));
		when(ligneEcritureComptable02.getDebit()).thenReturn(BigDecimal.valueOf(879L, 2));
		when(ligneEcritureComptable03.getDebit()).thenReturn(null);

		List<LigneEcritureComptable> listLigneEcriture = new ArrayList<>(3);
		listLigneEcriture.add(ligneEcritureComptable01);
		listLigneEcriture.add(ligneEcritureComptable02);
		listLigneEcriture.add(ligneEcritureComptable03);

		EcritureComptable ecritureComptable = new EcritureComptable();
		ecritureComptable.setListLigneEcriture(listLigneEcriture);

		// WHEN
		BigDecimal actualTotalDebit = ecritureComptable.getTotalDebit();

		// THEN
		assertThat(actualTotalDebit).isEqualTo(BigDecimal.valueOf(5274L, 2));

	}

	@Test
	void getTotalDebit_ecritureComptableDebitNull_returnsBigDecimalZero() {
		// GIVEN
		LigneEcritureComptable ligneEcritureComptable01 = Mockito.mock(LigneEcritureComptable.class);
		LigneEcritureComptable ligneEcritureComptable02 = Mockito.mock(LigneEcritureComptable.class);
		LigneEcritureComptable ligneEcritureComptable03 = Mockito.mock(LigneEcritureComptable.class);
		when(ligneEcritureComptable01.getDebit()).thenReturn(null);
		when(ligneEcritureComptable02.getDebit()).thenReturn(null);
		when(ligneEcritureComptable03.getDebit()).thenReturn(null);

		List<LigneEcritureComptable> listLigneEcriture = new ArrayList<>(3);
		listLigneEcriture.add(ligneEcritureComptable01);
		listLigneEcriture.add(ligneEcritureComptable02);
		listLigneEcriture.add(ligneEcritureComptable03);

		EcritureComptable ecritureComptable = new EcritureComptable();
		ecritureComptable.setListLigneEcriture(listLigneEcriture);

		// WHEN
		BigDecimal actualTotalDebit = ecritureComptable.getTotalDebit();

		// THEN
		assertThat(actualTotalDebit).isEqualTo(BigDecimal.ZERO);

	}

	@Test
	void getTotalDebit_ecritureComptableListLigneEcritureVide_returnsBigDecimalZero() {
		// GIVEN
		List<LigneEcritureComptable> listLigneEcriture = new ArrayList<>();

		EcritureComptable ecritureComptable = new EcritureComptable();
		ecritureComptable.setListLigneEcriture(listLigneEcriture);

		// WHEN
		BigDecimal actualTotalDebit = ecritureComptable.getTotalDebit();

		// THEN
		assertThat(actualTotalDebit).isEqualTo(BigDecimal.ZERO);

	}

	// ==================== toString ====================

	@Disabled
	@Test
	void toString_ecritureComptable_returnsEcritureComptableString() {
		// GIVEN
		ecritureComptable.setId(1);
		ecritureComptable.setJournal(journalComptable);
		ecritureComptable.setReference("JC-2020/00001");
		ecritureComptable.setDate(Date.valueOf("2020-04-12"));
		ecritureComptable.setLibelle("Développement tests");
		when(ecritureComptable.getTotalCredit()).thenReturn(BigDecimal.valueOf(20050L, 2));
		when(ecritureComptable.getTotalDebit()).thenReturn(BigDecimal.valueOf(20050L, 2));
		when(ecritureComptable.toString()).thenCallRealMethod();

		// WHEN
		String actualString = ecritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo(
				"{id=1, journal=journal, reference='reference', date=date, libelle='libelle', totalDebit=200.50, totalCredit=200.50, listLigneEcriture=[nlistLigneEcriture]}");
	}

	@Disabled
	@Test
	public void toString_ecritureComptable_returnsStringOfEcritureComptable() {
		// GIVEN
		EcritureComptable ecritureComptable = new EcritureComptable();
		ecritureComptable.setId(1);
		ecritureComptable.setJournal(journalComptable);
		ecritureComptable.setReference("JC-2020/00001");
		ecritureComptable.setDate(Date.valueOf("2020-04-12"));
		ecritureComptable.setLibelle("Développement tests");
		when(ecritureComptable.getTotalCredit()).thenReturn(BigDecimal.valueOf(20050L, 2));
		when(ecritureComptable.getTotalDebit()).thenReturn(BigDecimal.valueOf(20050L, 2));

		// WHEN
		String actualString = ecritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo(
				"{id=1, journal=journal, reference='reference', date=date, libelle='libelle', totalDebit=200.50, totalCredit=200.50, listLigneEcriture=[null]}");
	}

}
