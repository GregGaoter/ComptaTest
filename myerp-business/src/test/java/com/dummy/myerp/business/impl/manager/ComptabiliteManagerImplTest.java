package com.dummy.myerp.business.impl.manager;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dummy.myerp.business.impl.AbstractBusinessManager;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;

@ExtendWith(MockitoExtension.class)
public class ComptabiliteManagerImplTest {

	@Mock
	private AbstractBusinessManager abstractBusinessManager;
	@Mock
	private ComptabiliteManagerImpl comptabiliteManagerImpl;

	private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();

	// ==================== getListCompteComptable ====================

	@Test
	public void getListCompteComptable_comptabiliteManagerImpl_returnsListCompteComptable() {
		// GIVEN
		

		// WHEN

		// THEN

	}

	// ==================== checkEcritureComptableUnit ====================

	@Disabled
	@Test
	public void checkEcritureComptableUnit() throws Exception {
		EcritureComptable vEcritureComptable;
		vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
		vEcritureComptable.setDate(new Date());
		vEcritureComptable.setLibelle("Libelle");
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(2), null, null, new BigDecimal(123)));
		manager.checkEcritureComptableUnit(vEcritureComptable);
	}

	// ==================== checkEcritureComptableUnitViolation ====================

	@Disabled
	@Test
	// @Test(expected = FunctionalException.class)
	public void checkEcritureComptableUnitViolation() throws Exception {
		EcritureComptable vEcritureComptable;
		vEcritureComptable = new EcritureComptable();
		manager.checkEcritureComptableUnit(vEcritureComptable);
	}

	// ==================== checkEcritureComptableUnitRG2 ====================

	@Disabled
	@Test
	// @Test(expected = FunctionalException.class)
	public void checkEcritureComptableUnitRG2() throws Exception {
		EcritureComptable vEcritureComptable;
		vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
		vEcritureComptable.setDate(new Date());
		vEcritureComptable.setLibelle("Libelle");
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(2), null, null, new BigDecimal(1234)));
		manager.checkEcritureComptableUnit(vEcritureComptable);
	}

	// ==================== checkEcritureComptableUnitRG3 ====================

	@Disabled
	@Test
	// @Test(expected = FunctionalException.class)
	public void checkEcritureComptableUnitRG3() throws Exception {
		EcritureComptable vEcritureComptable;
		vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
		vEcritureComptable.setDate(new Date());
		vEcritureComptable.setLibelle("Libelle");
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));
		vEcritureComptable.getListLigneEcriture()
				.add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));
		manager.checkEcritureComptableUnit(vEcritureComptable);
	}

}
