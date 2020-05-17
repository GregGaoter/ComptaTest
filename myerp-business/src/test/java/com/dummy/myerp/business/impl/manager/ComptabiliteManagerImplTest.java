package com.dummy.myerp.business.impl.manager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.testbusiness.business.BusinessTestCase;

@ExtendWith(MockitoExtension.class)
public class ComptabiliteManagerImplTest extends BusinessTestCase {

	/*
	 * @Mock private AbstractBusinessManager abstractBusinessManager;
	 * 
	 * @Mock private ComptabiliteManagerImpl comptabiliteManagerImpl;
	 * 
	 * private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
	 */

	// ==================== getYear ====================

	@Test
	public void getYear_dateNormale_returnsYear() {
		// GIVEN
		ComptabiliteManagerImpl comptabiliteManagerImpl = new ComptabiliteManagerImpl();
		Date date = Date.from(Instant.EPOCH);

		// WHEN
		int actualYear = ReflectionTestUtils.invokeMethod(comptabiliteManagerImpl, "getYear", date);

		// THEN
		assertThat(actualYear).isEqualTo(1970);
	}

	@Test
	public void getYear_dateNull_thrownsNullPointerException() {
		// GIVEN
		ComptabiliteManagerImpl comptabiliteManagerImpl = new ComptabiliteManagerImpl();
		Date date = null;

		// WHEN

		// THEN
		assertThatNullPointerException().isThrownBy(() -> {
			ReflectionTestUtils.invokeMethod(comptabiliteManagerImpl, "getYear", date);
		});
	}

	// ==================== addReference ====================

	@Test
	public void addReference_ecritureComptableNormaleSequencePresente_addReferenceAndUpdateSequence() {
		// GIVEN
		ComptabiliteManagerImpl manager = Mockito.mock(ComptabiliteManagerImpl.class);
		EcritureComptable ecriture = new EcritureComptable();
		JournalComptable journal = new JournalComptable();
		SequenceEcritureComptable sequence01 = new SequenceEcritureComptable();
		SequenceEcritureComptable sequence02 = new SequenceEcritureComptable();
		SequenceEcritureComptable sequence03 = new SequenceEcritureComptable();
		SequenceEcritureComptable sequence04 = new SequenceEcritureComptable();

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2020);
		calendar.set(Calendar.MONTH, 5);
		calendar.set(Calendar.DAY_OF_MONTH, 17);
		Date date = calendar.getTime();

		ecriture.setJournal(journal);
		journal.setCode("BQ");
		ecriture.setDate(date);
		// when(ReflectionTestUtils.invokeMethod(manager, "getYear",
		// any(Date.class))).thenReturn(2020);

		sequence01.setJournalCode("AC");
		sequence01.setAnnee(2020);
		sequence01.setDerniereValeur(40);

		sequence02.setJournalCode("VE");
		sequence02.setAnnee(2020);
		sequence02.setDerniereValeur(41);

		sequence03.setJournalCode("BQ");
		sequence03.setAnnee(2020);
		sequence03.setDerniereValeur(51);

		sequence04.setJournalCode("OD");
		sequence04.setAnnee(2020);
		sequence04.setDerniereValeur(88);

		List<SequenceEcritureComptable> listeSequences = new ArrayList<>(4);
		listeSequences.add(sequence01);
		listeSequences.add(sequence02);
		listeSequences.add(sequence03);
		listeSequences.add(sequence04);
		when(manager.getListSequenceEcritureComptable()).thenReturn(listeSequences);

		doCallRealMethod().when(manager).addReference(any(EcritureComptable.class));

		// WHEN
		manager.addReference(ecriture);

		// THEN
		verify(manager).updateSequenceEcritureComptable(any(SequenceEcritureComptable.class));
		assertThat(ecriture.getReference()).isEqualTo("BQ-2020/00052");
	}

	@Test
	public void addReference_ecritureComptableNormaleSequenceNonPresente_addReferenceAndInsertSequence() {
		// GIVEN
		ComptabiliteManagerImpl manager = Mockito.mock(ComptabiliteManagerImpl.class);
		EcritureComptable ecriture = new EcritureComptable();
		JournalComptable journal = new JournalComptable();
		SequenceEcritureComptable sequence01 = new SequenceEcritureComptable();
		SequenceEcritureComptable sequence02 = new SequenceEcritureComptable();
		SequenceEcritureComptable sequence03 = new SequenceEcritureComptable();
		SequenceEcritureComptable sequence04 = new SequenceEcritureComptable();

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2020);
		calendar.set(Calendar.MONTH, 5);
		calendar.set(Calendar.DAY_OF_MONTH, 17);
		Date date = calendar.getTime();

		ecriture.setJournal(journal);
		journal.setCode("BQ");
		ecriture.setDate(date);
		// when(ReflectionTestUtils.invokeMethod(manager, "getYear",
		// any(Date.class))).thenReturn(2020);

		sequence01.setJournalCode("AC");
		sequence01.setAnnee(2020);
		sequence01.setDerniereValeur(40);

		sequence02.setJournalCode("VE");
		sequence02.setAnnee(2020);
		sequence02.setDerniereValeur(41);

		sequence03.setJournalCode("BQ");
		sequence03.setAnnee(2019);
		sequence03.setDerniereValeur(51);

		sequence04.setJournalCode("OD");
		sequence04.setAnnee(2020);
		sequence04.setDerniereValeur(88);

		List<SequenceEcritureComptable> listeSequences = new ArrayList<>(4);
		listeSequences.add(sequence01);
		listeSequences.add(sequence02);
		listeSequences.add(sequence03);
		listeSequences.add(sequence04);
		when(manager.getListSequenceEcritureComptable()).thenReturn(listeSequences);

		doCallRealMethod().when(manager).addReference(any(EcritureComptable.class));

		// WHEN
		manager.addReference(ecriture);

		// THEN
		verify(manager).insertSequenceEcritureComptable(any(SequenceEcritureComptable.class));
		assertThat(ecriture.getReference()).isEqualTo("BQ-2020/00001");
	}

	// ========== getDerniereValeurSequenceEcritureComptable ==========

	@Test
	public void getDerniereValeurSequenceEcritureComptable_ecritureComptableNormale_returnsDerniereValeur() {
		// GIVEN
		ComptabiliteManagerImpl manager = Mockito.mock(ComptabiliteManagerImpl.class);
		EcritureComptable ecriture = Mockito.mock(EcritureComptable.class);
		JournalComptable journal = Mockito.mock(JournalComptable.class);
		SequenceEcritureComptable sequence01 = Mockito.mock(SequenceEcritureComptable.class);
		SequenceEcritureComptable sequence02 = Mockito.mock(SequenceEcritureComptable.class);
		SequenceEcritureComptable sequence03 = Mockito.mock(SequenceEcritureComptable.class);
		SequenceEcritureComptable sequence04 = Mockito.mock(SequenceEcritureComptable.class);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2020);
		calendar.set(Calendar.MONTH, 5);
		calendar.set(Calendar.DAY_OF_MONTH, 12);
		Date date = calendar.getTime();

		when(ecriture.getJournal()).thenReturn(journal);
		when(journal.getCode()).thenReturn("BQ");
		when(ecriture.getDate()).thenReturn(date);

		when(sequence01.getJournalCode()).thenReturn("AC");
		when(sequence01.getAnnee()).thenReturn(2020);
		when(sequence01.getDerniereValeur()).thenReturn(40);

		when(sequence02.getJournalCode()).thenReturn("VE");
		when(sequence02.getAnnee()).thenReturn(2020);
		when(sequence02.getDerniereValeur()).thenReturn(41);

		when(sequence03.getJournalCode()).thenReturn("BQ");
		when(sequence03.getAnnee()).thenReturn(2020);
		when(sequence03.getDerniereValeur()).thenReturn(51);

		when(sequence04.getJournalCode()).thenReturn("OD");
		when(sequence04.getAnnee()).thenReturn(2020);
		when(sequence04.getDerniereValeur()).thenReturn(88);

		List<SequenceEcritureComptable> listeSequences = new ArrayList<>(4);
		listeSequences.add(sequence01);
		listeSequences.add(sequence02);
		listeSequences.add(sequence03);
		listeSequences.add(sequence04);
		when(manager.getListSequenceEcritureComptable()).thenReturn(listeSequences);

		// WHEN
		Integer actualValeur = manager.getDerniereValeurSequenceEcritureComptable(ecriture);

		// THEN
		assertThat(actualValeur).isEqualTo(51);
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
		// manager.checkEcritureComptableUnit(vEcritureComptable);
	}

	// ==================== checkEcritureComptableUnitViolation ====================

	@Disabled
	@Test
	// @Test(expected = FunctionalException.class)
	public void checkEcritureComptableUnitViolation() throws Exception {
		EcritureComptable vEcritureComptable;
		vEcritureComptable = new EcritureComptable();
		// manager.checkEcritureComptableUnit(vEcritureComptable);
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
		// manager.checkEcritureComptableUnit(vEcritureComptable);
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
		// manager.checkEcritureComptableUnit(vEcritureComptable);
	}

}
