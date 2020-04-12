package com.dummy.myerp.model.bean.comptabilite;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SequenceEcritureComptableTest {

	// ==================== toString ====================

	@Test
	public void toString_sequenceEcritureComptableAnneeMinDerniereValeurMin_returnsStringOfSequenceEcritureComptable() {
		// GIVEN
		SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable(Integer.MIN_VALUE,
				Integer.MIN_VALUE);

		// WHEN
		String actualString = sequenceEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("SequenceEcritureComptable{annee=-2147483648, derniereValeur=-2147483648}");
	}

	@Test
	public void toString_sequenceEcritureComptableAnneeDemiMinDerniereValeurNormaleNegative_returnsStringOfSequenceEcritureComptable() {
		// GIVEN
		SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable(Integer.MIN_VALUE / 2, -10);

		// WHEN
		String actualString = sequenceEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("SequenceEcritureComptable{annee=-1073741824, derniereValeur=-10}");
	}

	@Test
	public void toString_sequenceEcritureComptableAnneeDemiMaxDerniereValeurNull_returnsStringOfSequenceEcritureComptable() {
		// GIVEN
		SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable(Integer.MAX_VALUE / 2,
				null);

		// WHEN
		String actualString = sequenceEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("SequenceEcritureComptable{annee=1073741823, derniereValeur=null}");
	}

	@Test
	public void toString_sequenceEcritureComptableAnnee0DerniereValeur0_returnsStringOfSequenceEcritureComptable() {
		// GIVEN
		SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable(0, 0);

		// WHEN
		String actualString = sequenceEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("SequenceEcritureComptable{annee=0, derniereValeur=0}");
	}

	@Test
	public void toString_sequenceEcritureComptableAnneeMaxDerniereValeurDemiMax_returnsStringOfSequenceEcritureComptable() {
		// GIVEN
		SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable(Integer.MAX_VALUE,
				Integer.MAX_VALUE / 2);

		// WHEN
		String actualString = sequenceEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("SequenceEcritureComptable{annee=2147483647, derniereValeur=1073741823}");
	}

	@Test
	public void toString_sequenceEcritureComptableAnneeNullDerniereValeurDemiMin_returnsStringOfSequenceEcritureComptable() {
		// GIVEN
		SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable(null,
				Integer.MIN_VALUE / 2);

		// WHEN
		String actualString = sequenceEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("SequenceEcritureComptable{annee=null, derniereValeur=-1073741824}");
	}

	@Test
	public void toString_sequenceEcritureComptableAnneeNormalePositiveDerniereValeurMax_returnsStringOfSequenceEcritureComptable() {
		// GIVEN
		SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable(2020, Integer.MAX_VALUE);

		// WHEN
		String actualString = sequenceEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("SequenceEcritureComptable{annee=2020, derniereValeur=2147483647}");
	}

	@Test
	public void toString_sequenceEcritureComptableAnneeNormaleNegativeDerniereValeurNormalePositive_returnsStringOfSequenceEcritureComptable() {
		// GIVEN
		SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable(-2020, 10);

		// WHEN
		String actualString = sequenceEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("SequenceEcritureComptable{annee=-2020, derniereValeur=10}");
	}

}
