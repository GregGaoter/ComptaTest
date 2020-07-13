package com.dummy.myerp.testmodel;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;

public class CompteComptableIT extends AbstractConsumerIt {

	@BeforeAll
	public static void initDb() {
		initDbExpected();
	}

	@AfterAll
	public static void undefDb() {
		undefDbExpected();
	}

	// === getByNumero(List<? extends CompteComptable>, Integer) ===

	@Test
	public void getByNumero_withListAndNumeroValid_returnsCompteComptable() {
		// GIVEN
		CompteComptable compteExpected = mapCompteExpected.get(512).deepCopy();
		List<CompteComptable> listCompteExpected = mapCompteExpected.values().stream().map(compte -> compte.deepCopy())
				.collect(toList());

		// WHEN
		CompteComptable compteActual = CompteComptable.getByNumero(listCompteExpected, 512);

		// THEN
		assertThat(compteActual).isEqualToComparingFieldByField(compteExpected);
	}

}
