package com.dummy.myerp.testbusiness.business;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dummy.myerp.business.impl.manager.ComptabiliteManagerImpl;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;

public class ComptabiliteManagerImplIT extends AbstractBusinessIt {

	private ComptabiliteManagerImpl comptabiliteManagerImpl;

	@BeforeEach
	public void init() {
		comptabiliteManagerImpl = new ComptabiliteManagerImpl();
	}

	@AfterEach
	public void undef() {
		comptabiliteManagerImpl = null;
	}

	// ==================== getListCompteComptable() ====================

	@Test
	public void getListCompteComptable_returnsListCompteComptable() {
		// GIVEN

		// WHEN
		List<CompteComptable> listCompteComptable = comptabiliteManagerImpl.getListCompteComptable();

		// THEN
		assertThat(listCompteComptable.size()).isEqualTo(7);
	}

	// ==================== getListJournalComptable() ====================

	@Test
	public void getListJournalComptable_returnsListJournalComptable() {
		// GIVEN

		// WHEN
		List<JournalComptable> listJournalComptable = comptabiliteManagerImpl.getListJournalComptable();

		// THEN
		assertThat(listJournalComptable.size()).isEqualTo(4);
	}

	// ==================== getListEcritureComptable() ====================

	@Test
	public void getListEcritureComptable_returnsListEcritureComptable() {
		// GIVEN

		// WHEN
		List<EcritureComptable> listEcritureComptable = comptabiliteManagerImpl.getListEcritureComptable();

		// THEN
		assertThat(listEcritureComptable.size()).isEqualTo(5);
	}

	// ==================== getListSequenceEcritureComptable() ====================

	@Test
	public void getListSequenceEcritureComptable_returnsListSequenceEcritureComptable() {
		// GIVEN

		// WHEN
		List<SequenceEcritureComptable> listSequenceEcritureComptable = comptabiliteManagerImpl
				.getListSequenceEcritureComptable();

		// THEN
		assertThat(listSequenceEcritureComptable.size()).isEqualTo(4);
	}

}
