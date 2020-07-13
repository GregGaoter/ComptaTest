package com.dummy.myerp.testmodel;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.dummy.myerp.model.bean.comptabilite.JournalComptable;

public class JournalComptableIT extends AbstractConsumerIt {

	@BeforeAll
	public static void initDb() {
		initDbExpected();
	}

	@AfterAll
	public static void undefDb() {
		undefDbExpected();
	}

	// === getByCode(List<? extends JournalComptable>, String) ===

	@Test
	public void getByCode_withListAndCodeValid_returnsJournalComptable() {
		// GIVEN
		JournalComptable journalExpected = mapJournalExpected.get("BQ").deepCopy();
		List<JournalComptable> listJournalExpected = mapJournalExpected.values().stream()
				.map(journal -> journal.deepCopy()).collect(toList());

		// WHEN
		JournalComptable journalActual = JournalComptable.getByCode(listJournalExpected, "BQ");

		// THEN
		assertThat(journalActual).isEqualToComparingFieldByField(journalExpected);
	}

}
