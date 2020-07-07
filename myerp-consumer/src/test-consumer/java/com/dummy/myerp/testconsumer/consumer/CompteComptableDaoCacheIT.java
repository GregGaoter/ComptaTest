package com.dummy.myerp.testconsumer.consumer;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dummy.myerp.consumer.dao.impl.cache.CompteComptableDaoCache;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;

/**
 * Tests d'intégration de {@link CompteComptableDaoCache}
 * 
 * @author Grégory Gautier
 *
 */
public class CompteComptableDaoCacheIT extends AbstractConsumerIt {

	private CompteComptableDaoCache compteComptableDaoCache;

	@BeforeEach
	public void init() {
		compteComptableDaoCache = new CompteComptableDaoCache();
		Assertions.setAllowExtractingPrivateFields(false);
	}

	@AfterEach
	public void undef() {
		compteComptableDaoCache = null;
	}

	// === getByNumero(Integer) ===

	@Test
	public void getByNumero_returnsCompteComptable() {
		dockerEnvironment.start();
		// GIVEN
		CompteComptable compteExpected = new CompteComptable(512, "Banque");

		// WHEN
		CompteComptable compteActual = compteComptableDaoCache.getByNumero(512);

		// THEN
		assertThat(compteActual).isEqualToComparingFieldByField(compteExpected);
		dockerEnvironment.stop();
	}

}
