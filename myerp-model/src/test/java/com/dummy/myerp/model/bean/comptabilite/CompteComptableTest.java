package com.dummy.myerp.model.bean.comptabilite;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompteComptableTest {

	private CompteComptable compteComptable;

	@BeforeEach
	void initCompteComptable() {
		compteComptable = new CompteComptable();
	}

	@AfterEach
	void undefCompteComptable() {
		compteComptable = null;
	}

	@Test
	void toString_compteComptable_returnsStringWithClassNaneAndNumeroAndLibelle() {
		// GIVEN
		compteComptable.setNumero(10);
		compteComptable.setLibelle("Compte comptable sous test");

		// WHEN
		String compteComptableString = compteComptable.toString();

		// THEN
		assertThat(compteComptableString).isEqualTo("CompteComptable{numero=10, libelle='Compte comptable sous test'}");
	}

}
