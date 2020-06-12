package com.dummy.myerp.testbusiness.business;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.dummy.myerp.business.impl.manager.ComptabiliteManagerImpl;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;

public class ComptabiliteManagerImplIT {

	// ==================== getListSequenceEcritureComptable() ====================

	@Test
	public void getListSequenceEcritureComptable_returnsListSequenceEcritureComptable() {
		// GIVEN
		ComptabiliteManagerImpl comptabiliteManagerImpl = new ComptabiliteManagerImpl();

		// WHEN
		List<SequenceEcritureComptable> listSequenceEcritureComptable = comptabiliteManagerImpl
				.getListSequenceEcritureComptable();

		// THEN
		assertThat(listSequenceEcritureComptable.size()).isEqualTo(5);
	}

}
