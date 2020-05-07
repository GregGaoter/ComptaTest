package com.dummy.myerp.model.bean.comptabilite;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class EcritureComptableTest {

	// ==================== isEquilibree ====================

	@ParameterizedTest
	@ValueSource(longs = { 20050L, -20050L, Long.MAX_VALUE, Long.MIN_VALUE, Long.MAX_VALUE / 2, Long.MIN_VALUE / 2, 0 })
	void isEquilibree_EcritureComptableCreditAndDebitWithMultipleEgalUnscaledVal_returnsTrue(long unscaledVal) {
		// GIVEN
		EcritureComptable ecritureComptable = Mockito.mock(EcritureComptable.class);
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
		EcritureComptable ecritureComptable = Mockito.mock(EcritureComptable.class);
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
		EcritureComptable ecritureComptable = Mockito.mock(EcritureComptable.class);
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
		EcritureComptable ecritureComptable = Mockito.mock(EcritureComptable.class);
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
		EcritureComptable ecritureComptable = Mockito.mock(EcritureComptable.class);
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
		EcritureComptable ecritureComptable = Mockito.mock(EcritureComptable.class);
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

	// ==================== getTotalCredit ====================

	@Test
	void getTotalCredit_ecritureComptableCreditNormal_returnsBigDecimal() {
		// GIVEN
		LigneEcritureComptable ligneEcritureComptable01 = Mockito.mock(LigneEcritureComptable.class);
		LigneEcritureComptable ligneEcritureComptable02 = Mockito.mock(LigneEcritureComptable.class);
		LigneEcritureComptable ligneEcritureComptable03 = Mockito.mock(LigneEcritureComptable.class);
		when(ligneEcritureComptable01.getCredit()).thenReturn(null);
		when(ligneEcritureComptable02.getCredit()).thenReturn(BigDecimal.valueOf(2500L, 0));
		when(ligneEcritureComptable03.getCredit()).thenReturn(BigDecimal.valueOf(500L, 0));

		List<LigneEcritureComptable> listLigneEcriture = new ArrayList<>(3);
		listLigneEcriture.add(ligneEcritureComptable01);
		listLigneEcriture.add(ligneEcritureComptable02);
		listLigneEcriture.add(ligneEcritureComptable03);

		EcritureComptable ecritureComptable = new EcritureComptable();
		ecritureComptable.setListLigneEcriture(listLigneEcriture);

		// WHEN
		BigDecimal actualTotalCredit = ecritureComptable.getTotalCredit();

		// THEN
		assertThat(actualTotalCredit).isEqualTo(BigDecimal.valueOf(3000L, 0));

	}

	@Test
	void getTotalCredit_ecritureComptableCreditNull_returnsBigDecimalZero() {
		// GIVEN
		LigneEcritureComptable ligneEcritureComptable01 = Mockito.mock(LigneEcritureComptable.class);
		LigneEcritureComptable ligneEcritureComptable02 = Mockito.mock(LigneEcritureComptable.class);
		LigneEcritureComptable ligneEcritureComptable03 = Mockito.mock(LigneEcritureComptable.class);
		when(ligneEcritureComptable01.getCredit()).thenReturn(null);
		when(ligneEcritureComptable02.getCredit()).thenReturn(null);
		when(ligneEcritureComptable03.getCredit()).thenReturn(null);

		List<LigneEcritureComptable> listLigneEcriture = new ArrayList<>(3);
		listLigneEcriture.add(ligneEcritureComptable01);
		listLigneEcriture.add(ligneEcritureComptable02);
		listLigneEcriture.add(ligneEcritureComptable03);

		EcritureComptable ecritureComptable = new EcritureComptable();
		ecritureComptable.setListLigneEcriture(listLigneEcriture);

		// WHEN
		BigDecimal actualTotalCredit = ecritureComptable.getTotalCredit();

		// THEN
		assertThat(actualTotalCredit).isEqualTo(BigDecimal.ZERO);

	}

	@Test
	void getTotalCredit_ecritureComptableListLigneEcritureVide_returnsBigDecimalZero() {
		// GIVEN
		List<LigneEcritureComptable> listLigneEcriture = new ArrayList<>();

		EcritureComptable ecritureComptable = new EcritureComptable();
		ecritureComptable.setListLigneEcriture(listLigneEcriture);

		// WHEN
		BigDecimal actualTotalCredit = ecritureComptable.getTotalCredit();

		// THEN
		assertThat(actualTotalCredit).isEqualTo(BigDecimal.ZERO);

	}

	// ==================== toString ====================

	@Test
	public void toString_ecritureComptableNormale_returnsStringOfEcritureComptable() {
		// GIVEN
		EcritureComptable ecritureComptable = Mockito.mock(EcritureComptable.class);
		ReflectionTestUtils.setField(ecritureComptable, "id", 1);
		JournalComptable journalComptable = Mockito.mock(JournalComptable.class);
		when(journalComptable.toString()).thenReturn("journal");
		ReflectionTestUtils.setField(ecritureComptable, "journal", journalComptable);
		ReflectionTestUtils.setField(ecritureComptable, "reference", "JC-2020/00001");
		ReflectionTestUtils.setField(ecritureComptable, "date", Date.valueOf("2020-05-07"));
		ReflectionTestUtils.setField(ecritureComptable, "libelle", "Développement tests");
		when(ecritureComptable.getTotalCredit()).thenReturn(BigDecimal.valueOf(20050L, 2));
		when(ecritureComptable.getTotalDebit()).thenReturn(BigDecimal.valueOf(20050L, 2));

		LigneEcritureComptable ligneEcritureComptable01 = Mockito.mock(LigneEcritureComptable.class);
		LigneEcritureComptable ligneEcritureComptable02 = Mockito.mock(LigneEcritureComptable.class);
		LigneEcritureComptable ligneEcritureComptable03 = Mockito.mock(LigneEcritureComptable.class);
		when(ligneEcritureComptable01.toString()).thenReturn("l1");
		when(ligneEcritureComptable02.toString()).thenReturn("l2");
		when(ligneEcritureComptable03.toString()).thenReturn("l3");

		List<LigneEcritureComptable> listLigneEcriture = new ArrayList<>(3);
		listLigneEcriture.add(ligneEcritureComptable01);
		listLigneEcriture.add(ligneEcritureComptable02);
		listLigneEcriture.add(ligneEcritureComptable03);

		ReflectionTestUtils.setField(ecritureComptable, "listLigneEcriture", listLigneEcriture);

		when(ecritureComptable.toString()).thenCallRealMethod();

		// WHEN
		String actualString = ecritureComptable.toString().split("\\{|\\}")[1];

		// THEN
		assertThat(actualString).isEqualTo(
				"id=1, journal=journal, reference='JC-2020/00001', date=2020-05-07, libelle='Développement tests', totalDebit=200.50, totalCredit=200.50, listLigneEcriture=[\nl1\nl2\nl3\n]");
	}

}
