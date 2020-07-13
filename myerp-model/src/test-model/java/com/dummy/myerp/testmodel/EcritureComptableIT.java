package com.dummy.myerp.testmodel;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;

public class EcritureComptableIT extends AbstractConsumerIt {

	@BeforeAll
	public static void initDb() {
		initDbExpected();
	}

	@AfterAll
	public static void undefDb() {
		undefDbExpected();
	}

	// === getTotalDebit() ===

	@Test
	public void getTotalDebit_returnsTotalDebit() {
		// GIVEN
		EcritureComptable ecriture = mapEcritureExpected.get(-3).deepCopy();

		// WHEN
		BigDecimal debitActual = ecriture.getTotalDebit();

		// THEN
		assertThat(debitActual).isEqualByComparingTo(BigDecimal.valueOf(5274, 2));
	}

	// === getTotalCredit() ===

	@Test
	public void getTotalCredit_returnsTotalCredit() {
		// GIVEN
		EcritureComptable ecriture = mapEcritureExpected.get(-3).deepCopy();

		// WHEN
		BigDecimal creditActual = ecriture.getTotalCredit();

		// THEN
		assertThat(creditActual).isEqualByComparingTo(BigDecimal.valueOf(5274, 2));
	}

	// === isEquilibree() ===

	@Test
	public void isEquilibree_withDebitCreditValid_returnsTrue() {
		// GIVEN
		EcritureComptable ecriture = mapEcritureExpected.get(-3).deepCopy();

		// WHEN

		// THEN
		assertThat(ecriture.isEquilibree()).isTrue();
	}

}
