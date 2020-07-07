package com.dummy.myerp.testconsumer.consumer;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dummy.myerp.consumer.dao.impl.cache.JournalComptableDaoCache;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;

public class JournalComptableDaoCacheIT extends AbstractConsumerIt {

	private JournalComptableDaoCache journalComptableDaoCache;

	@BeforeEach
	public void init() {
		journalComptableDaoCache = new JournalComptableDaoCache();
		Assertions.setAllowExtractingPrivateFields(false);
	}

	@AfterEach
	public void undef() {
		journalComptableDaoCache = null;
	}

	// === getByCode(String) ===

	@Test
	public void getByCode_returnsJournalComptable() {
		dockerEnvironment.start();
		// GIVEN
		JournalComptable journalExpected = new JournalComptable("BQ", "Banque");

		// WHEN
		JournalComptable journalActual = journalComptableDaoCache.getByCode("BQ");

		// THEN
		assertThat(journalActual).isEqualToComparingFieldByField(journalExpected);
		dockerEnvironment.stop();
	}

}
