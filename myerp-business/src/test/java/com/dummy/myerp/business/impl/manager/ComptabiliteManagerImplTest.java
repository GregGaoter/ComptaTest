package com.dummy.myerp.business.impl.manager;

import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

public class ComptabiliteManagerImplTest {

	private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();

	@Test
	public void test() throws FunctionalException {
		String nulExcpected = manager.test();
		assertNull(nulExcpected);
	}

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

	@Test(expected = FunctionalException.class)
	public void checkEcritureComptableUnitViolation() throws Exception {
		EcritureComptable vEcritureComptable;
		vEcritureComptable = new EcritureComptable();
		manager.checkEcritureComptableUnit(vEcritureComptable);
	}

	@Test(expected = FunctionalException.class)
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

	@Test(expected = FunctionalException.class)
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
