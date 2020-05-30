package com.dummy.myerp.business.impl.manager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.TransactionStatus;

import com.dummy.myerp.business.impl.AbstractBusinessManager;
import com.dummy.myerp.business.impl.TransactionManager;
import com.dummy.myerp.business.test.BusinessTestCase;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;

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

	// ==================== checkEcritureComptable ====================

	@Test
	public void checkEcritureComptable_ecritureNormale_callscheckEcritureComptableUnitAndcheckEcritureComptableContext()
			throws FunctionalException {
		// GIVEN
		ComptabiliteManagerImpl manager = Mockito.mock(ComptabiliteManagerImpl.class);
		EcritureComptable ecriture = new EcritureComptable();
		doCallRealMethod().when(manager).checkEcritureComptable(any(EcritureComptable.class));

		// WHEN
		manager.checkEcritureComptable(ecriture);

		// THEN
		verify(manager).checkEcritureComptableUnit(any(EcritureComptable.class));
		verify(manager).checkEcritureComptableContext(any(EcritureComptable.class));
	}

	// ==================== checkEcritureComptableUnit ====================

	@Test
	public void checkEcritureComptableUnit_ecritureNormale_callsViolationAndRG2AndRG3AndReference()
			throws FunctionalException {
		// GIVEN
		ComptabiliteManagerImpl manager = Mockito.mock(ComptabiliteManagerImpl.class);
		doCallRealMethod().when(manager).checkEcritureComptableUnit(any(EcritureComptable.class));

		// WHEN
		manager.checkEcritureComptableUnit(new EcritureComptable());

		// THEN
		verify(manager).checkEcritureComptableUnitViolation(any(EcritureComptable.class));
		verify(manager).checkEcritureComptableUnitRG2(any(EcritureComptable.class));
		verify(manager).checkEcritureComptableUnitRG3(any(EcritureComptable.class));
		verify(manager).checkEcritureComptableUnitReference(any(EcritureComptable.class));
	}

	// ==================== checkEcritureComptableUnitViolation ====================

	@Test
	public void checkEcritureComptableUnitViolation_ecritureWithViolations_throwsFunctionalException() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();

		// WHEN
		Exception exception = assertThrows(FunctionalException.class, () -> {
			manager.checkEcritureComptableUnitViolation(vEcritureComptable);
		});

		// THEN
		assertThat(exception.getMessage()).isEqualTo("L'écriture comptable ne respecte pas les règles de gestion.");
	}

	@Test
	public void checkEcritureComptableUnitViolation_ecritureWithoutViolations_notThrowsFunctionalException() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setDate(new Date());
		vEcritureComptable.setId(1);
		vEcritureComptable.setJournal(new JournalComptable());
		vEcritureComptable.setLibelle("Libellé");
		vEcritureComptable.setListLigneEcriture(
				Arrays.asList(new LigneEcritureComptable(new CompteComptable(), "LE1", BigDecimal.ZERO, null),
						new LigneEcritureComptable(new CompteComptable(), "LE2", null, BigDecimal.ZERO)));
		vEcritureComptable.setReference("BQ-2020/00001");

		// WHEN

		// THEN
		assertThatCode(() -> manager.checkEcritureComptableUnitViolation(vEcritureComptable))
				.doesNotThrowAnyException();
	}

	// ==================== checkEcritureComptableUnitRG2 ====================

	@Test
	public void checkEcritureComptableUnitRG2_ecritureEquilibree_notThrowsFunctionalException() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setDate(new Date());
		vEcritureComptable.setId(1);
		vEcritureComptable.setJournal(new JournalComptable());
		vEcritureComptable.setLibelle("Libellé");
		vEcritureComptable.setListLigneEcriture(
				Arrays.asList(new LigneEcritureComptable(new CompteComptable(), "LE1", BigDecimal.ZERO, null),
						new LigneEcritureComptable(new CompteComptable(), "LE2", null, BigDecimal.ZERO)));
		vEcritureComptable.setReference("BQ-2020/00001");

		// WHEN

		// THEN
		assertThatCode(() -> manager.checkEcritureComptableUnitRG2(vEcritureComptable)).doesNotThrowAnyException();
	}

	@Test
	public void checkEcritureComptableUnitRG2_ecritureNotEquilibree_throwsFunctionalException() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setDate(new Date());
		vEcritureComptable.setId(1);
		vEcritureComptable.setJournal(new JournalComptable());
		vEcritureComptable.setLibelle("Libellé");
		vEcritureComptable.setListLigneEcriture(
				Arrays.asList(new LigneEcritureComptable(new CompteComptable(), "LE1", BigDecimal.ZERO, null),
						new LigneEcritureComptable(new CompteComptable(), "LE2", null, BigDecimal.ONE)));
		vEcritureComptable.setReference("BQ-2020/00001");

		// WHEN
		Exception exception = assertThrows(FunctionalException.class, () -> {
			manager.checkEcritureComptableUnitRG2(vEcritureComptable);
		});

		// THEN
		assertThat(exception.getMessage()).isEqualTo("L'écriture comptable n'est pas équilibrée.");
	}

	// ==================== getNbCredit ====================

	@Test
	public void getNbCredit_ecritureCreditOne_returnsUn() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setListLigneEcriture(
				Arrays.asList(new LigneEcritureComptable(new CompteComptable(), "LE1", BigDecimal.ONE, null),
						new LigneEcritureComptable(new CompteComptable(), "LE2", null, BigDecimal.ONE)));

		// WHEN
		int nbCreditActual = manager.getNbCredit(vEcritureComptable);

		// THEN
		assertThat(nbCreditActual).isEqualTo(1);
	}

	@Test
	public void getNbCredit_ecritureCreditZero_returnsZero() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setListLigneEcriture(
				Arrays.asList(new LigneEcritureComptable(new CompteComptable(), "LE1", BigDecimal.ZERO, null),
						new LigneEcritureComptable(new CompteComptable(), "LE2", null, BigDecimal.ZERO)));

		// WHEN
		int nbCreditActual = manager.getNbCredit(vEcritureComptable);

		// THEN
		assertThat(nbCreditActual).isEqualTo(0);
	}

	@Test
	public void getNbCredit_ecritureCreditNull_returnsZero() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setListLigneEcriture(
				Arrays.asList(new LigneEcritureComptable(new CompteComptable(), "LE1", null, null),
						new LigneEcritureComptable(new CompteComptable(), "LE2", null, null)));

		// WHEN
		int nbCreditActual = manager.getNbCredit(vEcritureComptable);

		// THEN
		assertThat(nbCreditActual).isEqualTo(0);
	}

	// ==================== getNbDebit ====================

	@Test
	public void getNbDebit_ecritureDebitOne_returnsUn() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setListLigneEcriture(
				Arrays.asList(new LigneEcritureComptable(new CompteComptable(), "LE1", BigDecimal.ONE, null),
						new LigneEcritureComptable(new CompteComptable(), "LE2", null, BigDecimal.ONE)));

		// WHEN
		int nbDebitActual = manager.getNbDebit(vEcritureComptable);

		// THEN
		assertThat(nbDebitActual).isEqualTo(1);
	}

	@Test
	public void getNbDebit_ecritureDebitZero_returnsZero() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setListLigneEcriture(
				Arrays.asList(new LigneEcritureComptable(new CompteComptable(), "LE1", BigDecimal.ZERO, null),
						new LigneEcritureComptable(new CompteComptable(), "LE2", null, BigDecimal.ZERO)));

		// WHEN
		int nbDebitActual = manager.getNbDebit(vEcritureComptable);

		// THEN
		assertThat(nbDebitActual).isEqualTo(0);
	}

	@Test
	public void getNbDebit_ecritureCreditNull_returnsZero() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setListLigneEcriture(
				Arrays.asList(new LigneEcritureComptable(new CompteComptable(), "LE1", null, null),
						new LigneEcritureComptable(new CompteComptable(), "LE2", null, null)));

		// WHEN
		int nbDebitActual = manager.getNbDebit(vEcritureComptable);

		// THEN
		assertThat(nbDebitActual).isEqualTo(0);
	}

	// ==================== checkEcritureComptableUnitRG3 ====================

	@Test
	public void checkEcritureComptableUnitRG3_listeLigneEcritureValide_notThrowsFunctionalException() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setListLigneEcriture(
				Arrays.asList(new LigneEcritureComptable(new CompteComptable(), "LE1", BigDecimal.ONE, null),
						new LigneEcritureComptable(new CompteComptable(), "LE2", null, BigDecimal.ONE)));

		// WHEN

		// THEN
		assertThatCode(() -> manager.checkEcritureComptableUnitRG3(vEcritureComptable)).doesNotThrowAnyException();
	}

	@Test
	public void checkEcritureComptableUnitRG3_listeLigneEcritureSizeUn_throwsFunctionalException() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setListLigneEcriture(Arrays.asList(new LigneEcritureComptable()));

		// WHEN
		Exception exception = assertThrows(FunctionalException.class, () -> {
			manager.checkEcritureComptableUnitRG3(vEcritureComptable);
		});

		// THEN
		assertThat(exception.getMessage()).isEqualTo(
				"L'écriture comptable doit avoir au moins deux lignes : une ligne au débit et une ligne au crédit.");
	}

	@Test
	public void checkEcritureComptableUnitRG3_nbCreditZero_throwsFunctionalException() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setListLigneEcriture(
				Arrays.asList(new LigneEcritureComptable(new CompteComptable(), "LE1", null, null),
						new LigneEcritureComptable(new CompteComptable(), "LE2", null, BigDecimal.ONE)));

		// WHEN
		Exception exception = assertThrows(FunctionalException.class, () -> {
			manager.checkEcritureComptableUnitRG3(vEcritureComptable);
		});

		// THEN
		assertThat(exception.getMessage()).isEqualTo(
				"L'écriture comptable doit avoir au moins deux lignes : une ligne au débit et une ligne au crédit.");
	}

	@Test
	public void checkEcritureComptableUnitRG3_nbDebitZero_throwsFunctionalException() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setListLigneEcriture(
				Arrays.asList(new LigneEcritureComptable(new CompteComptable(), "LE1", BigDecimal.ONE, null),
						new LigneEcritureComptable(new CompteComptable(), "LE2", null, null)));

		// WHEN
		Exception exception = assertThrows(FunctionalException.class, () -> {
			manager.checkEcritureComptableUnitRG3(vEcritureComptable);
		});

		// THEN
		assertThat(exception.getMessage()).isEqualTo(
				"L'écriture comptable doit avoir au moins deux lignes : une ligne au débit et une ligne au crédit.");
	}

	// ==================== checkEcritureComptableUnitReference ====================

	@Test
	public void checkEcritureComptableUnitReference_ecritureNormale_callsFormatAndAnneeAndCodeJournalAndNumeroSequence()
			throws FunctionalException {
		// GIVEN
		ComptabiliteManagerImpl manager = Mockito.mock(ComptabiliteManagerImpl.class);
		doCallRealMethod().when(manager).checkEcritureComptableUnitReference(any(EcritureComptable.class));

		// WHEN
		manager.checkEcritureComptableUnitReference(new EcritureComptable());

		// THEN
		verify(manager).checkEcritureComptableReferenceFormatValid(any(EcritureComptable.class));
		verify(manager).checkEcritureComptableReferenceAnneeValid(any(EcritureComptable.class));
		verify(manager).checkEcritureComptableReferenceCodeJournalValid(any(EcritureComptable.class));
		verify(manager).checkEcritureComptableReferenceNumeroSequenceValid(any(EcritureComptable.class));
	}

	// ==================== checkEcritureComptableReferenceFormatValid
	// ====================

	@Test
	public void checkEcritureComptableReferenceFormatValid_formatValide_notThrowsFunctionalException() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setReference("BQ-2020/00001");

		// WHEN

		// THEN
		assertThatCode(() -> manager.checkEcritureComptableReferenceFormatValid(vEcritureComptable))
				.doesNotThrowAnyException();
	}

	@Test
	public void checkEcritureComptableReferenceFormatValid_formatNonValide_throwsFunctionalException() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setReference("BQ-2020-00001");

		// WHEN
		Exception exception = assertThrows(FunctionalException.class, () -> {
			manager.checkEcritureComptableReferenceFormatValid(vEcritureComptable);
		});

		// THEN
		assertThat(exception.getMessage()).isEqualTo("Le format de la référence est invalide.");
	}

	// ==================== checkEcritureComptableReferenceAnneeValid
	// ====================

	@Test
	public void checkEcritureComptableReferenceAnneeValid_anneeValide_notThrowsFunctionalException() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2020);
		calendar.set(Calendar.MONTH, 5);
		calendar.set(Calendar.DAY_OF_MONTH, 21);
		Date date = calendar.getTime();
		vEcritureComptable.setDate(date);
		vEcritureComptable.setReference("BQ-2020/00001");

		// WHEN

		// THEN
		assertThatCode(() -> manager.checkEcritureComptableReferenceAnneeValid(vEcritureComptable))
				.doesNotThrowAnyException();
	}

	@Test
	public void checkEcritureComptableReferenceAnneeValid_anneeNonValide_throwsFunctionalException() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2019);
		calendar.set(Calendar.MONTH, 5);
		calendar.set(Calendar.DAY_OF_MONTH, 21);
		Date date = calendar.getTime();
		vEcritureComptable.setDate(date);
		vEcritureComptable.setReference("BQ-2020/00001");

		// WHEN
		Exception exception = assertThrows(FunctionalException.class, () -> {
			manager.checkEcritureComptableReferenceAnneeValid(vEcritureComptable);
		});

		// THEN
		assertThat(exception.getMessage())
				.isEqualTo("L'année de la référence ne correspond pas à l'année de l'écriture.");
	}

	// ==================== checkEcritureComptableReferenceCodeJournalValid
	// ====================

	@Test
	public void checkEcritureComptableReferenceCodeJournalValid_codeJournalValide_notThrowsFunctionalException() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		JournalComptable journal = new JournalComptable();
		journal.setCode("BQ");
		vEcritureComptable.setJournal(journal);
		vEcritureComptable.setReference("BQ-2020/00001");

		// WHEN

		// THEN
		assertThatCode(() -> manager.checkEcritureComptableReferenceCodeJournalValid(vEcritureComptable))
				.doesNotThrowAnyException();
	}

	@Test
	public void checkEcritureComptableReferenceCodeJournalValid_codeJournalNonValide_throwsFunctionalException() {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable vEcritureComptable = new EcritureComptable();
		JournalComptable journal = new JournalComptable();
		journal.setCode("AC");
		vEcritureComptable.setJournal(journal);
		vEcritureComptable.setReference("BQ-2020/00001");

		// WHEN
		Exception exception = assertThrows(FunctionalException.class, () -> {
			manager.checkEcritureComptableReferenceCodeJournalValid(vEcritureComptable);
		});

		// THEN
		assertThat(exception.getMessage())
				.isEqualTo("Le code journal de la référence ne correspond pas au code journal de l'écriture.");
	}

	// ==================== checkEcritureComptableReferenceNumeroSequenceValid
	// ====================

	@Test
	public void checkEcritureComptableReferenceNumeroSequenceValid_numeroSequenceValide_notThrowsFunctionalException()
			throws FunctionalException {
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
		calendar.set(Calendar.DAY_OF_MONTH, 22);
		Date date = calendar.getTime();

		ecriture.setJournal(journal);
		journal.setCode("BQ");
		ecriture.setDate(date);
		ecriture.setReference("BQ-2020/00001");

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

		doCallRealMethod().when(manager).getDerniereValeurNumeroSequence(any(EcritureComptable.class));
		doCallRealMethod().when(manager)
				.checkEcritureComptableReferenceNumeroSequenceValid(any(EcritureComptable.class));

		// WHEN

		// THEN
		assertThatCode(() -> manager.checkEcritureComptableReferenceNumeroSequenceValid(ecriture))
				.doesNotThrowAnyException();
	}

	@ParameterizedTest
	@CsvSource({ "2019,51", "2020,50", "2019,50" })
	public void checkEcritureComptableReferenceNumeroSequenceValid_numeroSequenceNonValide_throwsFunctionalException(
			Integer annee, Integer numero) throws FunctionalException {
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
		calendar.set(Calendar.DAY_OF_MONTH, 22);
		Date date = calendar.getTime();

		ecriture.setJournal(journal);
		journal.setCode("BQ");
		ecriture.setDate(date);
		ecriture.setReference("BQ-2020/00051");

		sequence01.setJournalCode("AC");
		sequence01.setAnnee(2020);
		sequence01.setDerniereValeur(40);

		sequence02.setJournalCode("VE");
		sequence02.setAnnee(2020);
		sequence02.setDerniereValeur(41);

		sequence03.setJournalCode("BQ");
		sequence03.setAnnee(annee);
		sequence03.setDerniereValeur(numero);

		sequence04.setJournalCode("OD");
		sequence04.setAnnee(2020);
		sequence04.setDerniereValeur(88);

		List<SequenceEcritureComptable> listeSequences = new ArrayList<>(4);
		listeSequences.add(sequence01);
		listeSequences.add(sequence02);
		listeSequences.add(sequence03);
		listeSequences.add(sequence04);
		when(manager.getListSequenceEcritureComptable()).thenReturn(listeSequences);

		doCallRealMethod().when(manager).getDerniereValeurNumeroSequence(any(EcritureComptable.class));
		doCallRealMethod().when(manager)
				.checkEcritureComptableReferenceNumeroSequenceValid(any(EcritureComptable.class));

		// WHEN
		Exception exception = assertThrows(FunctionalException.class, () -> {
			manager.checkEcritureComptableReferenceNumeroSequenceValid(ecriture);
		});

		// THEN
		assertThat(exception.getMessage()).isEqualTo("Le numéro de séquence de la référence n'est pas valide.");
	}

	// ==================== getDerniereValeurNumeroSequence
	// ====================

	private static Stream<Arguments> getArgumentsPourGetDerniereValeurNumeroSequence() {
		return Stream.of(Arguments.of("BQ", 2020, 51), Arguments.of("OC", 2020, null), Arguments.of("BQ", 2019, null),
				Arguments.of("OC", 2019, null));
	}

	@ParameterizedTest
	@MethodSource("getArgumentsPourGetDerniereValeurNumeroSequence")
	public void getDerniereValeurNumeroSequence_returnsDerniereValeur(String code, Integer annee, Integer numero) {
		// GIVEN
		ComptabiliteManagerImpl manager = Mockito.mock(ComptabiliteManagerImpl.class);
		EcritureComptable ecriture = new EcritureComptable();
		JournalComptable journal = new JournalComptable();
		SequenceEcritureComptable sequence01 = new SequenceEcritureComptable();
		SequenceEcritureComptable sequence02 = new SequenceEcritureComptable();
		SequenceEcritureComptable sequence03 = new SequenceEcritureComptable();
		SequenceEcritureComptable sequence04 = new SequenceEcritureComptable();

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, annee);
		calendar.set(Calendar.MONTH, 5);
		calendar.set(Calendar.DAY_OF_MONTH, 22);
		Date date = calendar.getTime();

		ecriture.setJournal(journal);
		journal.setCode(code);
		ecriture.setDate(date);

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

		doCallRealMethod().when(manager).getDerniereValeurNumeroSequence(any(EcritureComptable.class));

		// WHEN
		Integer numeroSequenceActual = manager.getDerniereValeurNumeroSequence(ecriture);

		// THEN
		assertThat(numeroSequenceActual).isEqualTo(numero);
	}

	// ==================== checkEcritureComptableContext ====================

	@ParameterizedTest
	@NullAndEmptySource
	public void checkEcritureComptableContext_ecritureReferenceNullEmpty_doesNotThrowAnyException(String reference) {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable ecriture = new EcritureComptable();
		ecriture.setReference(reference);

		// WHEN

		// THEN
		assertThatCode(() -> manager.checkEcritureComptableContext(ecriture)).doesNotThrowAnyException();
	}

	@Test
	public void checkEcritureComptableContext_ecritureReferenceUnique_notCallsGetId()
			throws NotFoundException, FunctionalException {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable ecritureToCheck = Mockito.mock(EcritureComptable.class);
		DaoProxy daoProxy = Mockito.mock(DaoProxy.class);
		ComptabiliteDao comptabiliteDao = Mockito.mock(ComptabiliteDao.class);

		when(ecritureToCheck.getReference()).thenReturn("BQ-2020/00001");
		ReflectionTestUtils.setField(AbstractBusinessManager.class, "daoProxy", daoProxy);
		when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
		when(comptabiliteDao.getEcritureComptableByRef(anyString())).thenThrow(NotFoundException.class);

		// WHEN
		manager.checkEcritureComptableContext(ecritureToCheck);

		// THEN
		verify(ecritureToCheck, times(0)).getId();
	}

	private static String messFuncExcCheckEcrComCon = "Une autre écriture comptable existe déjà avec la même référence.";

	private static Stream<Arguments> getArgumentsPourCheckEcritureComptableContextEcritureReferenceNonUniqueThrowsFunctionalException() {
		return Stream.of(Arguments.of(1, 1, null), Arguments.of(1, 2, messFuncExcCheckEcrComCon),
				Arguments.of(1, null, messFuncExcCheckEcrComCon), Arguments.of(null, 1, messFuncExcCheckEcrComCon),
				Arguments.of(null, null, messFuncExcCheckEcrComCon));
	}

	@ParameterizedTest(name = "idToCheck = {0}, idResult = {1}, excpectedMessage = {2}")
	@MethodSource("getArgumentsPourCheckEcritureComptableContextEcritureReferenceNonUniqueThrowsFunctionalException")
	public void checkEcritureComptableContext_ecritureReferenceNonUnique_throwsFunctionalException(Integer idToCheck,
			Integer idResult, String excpectedMessage) throws NotFoundException {
		// GIVEN
		ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
		EcritureComptable ecritureToCheck = new EcritureComptable(idToCheck, "BQ-2020/00001");
		DaoProxy daoProxy = Mockito.mock(DaoProxy.class);
		ComptabiliteDao comptabiliteDao = Mockito.mock(ComptabiliteDao.class);

		ReflectionTestUtils.setField(AbstractBusinessManager.class, "daoProxy", daoProxy);
		when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
		when(comptabiliteDao.getEcritureComptableByRef(anyString())).thenReturn(new EcritureComptable(idResult));

		String actualMessage;

		// WHEN
		try {
			manager.checkEcritureComptableContext(ecritureToCheck);
			actualMessage = null;
		} catch (FunctionalException e) {
			actualMessage = e.getMessage();
		}

		// THEN
		assertThat(actualMessage).isEqualTo(excpectedMessage);
	}

	// ==================== getListCompteComptable() ====================

	@Test
	public void getListCompteComptable_returnsListCompteComptable() {
		// GIVEN
		ComptabiliteManagerImpl comptabiliteManagerImpl = new ComptabiliteManagerImpl();
		DaoProxy daoProxy = Mockito.mock(DaoProxy.class);
		ComptabiliteDao comptabiliteDao = Mockito.mock(ComptabiliteDao.class);
		List<CompteComptable> expectedListCompteComptable = new ArrayList<>();

		ReflectionTestUtils.setField(AbstractBusinessManager.class, "daoProxy", daoProxy);
		when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
		when(comptabiliteDao.getListCompteComptable()).thenReturn(expectedListCompteComptable);

		// WHEN
		List<CompteComptable> actualListCompteComptable = comptabiliteManagerImpl.getListCompteComptable();

		// THEN
		assertThat(actualListCompteComptable).isEqualTo(expectedListCompteComptable);
	}

	// ==================== getListJournalComptable() ====================

	@Test
	public void getListJournalComptable_returnsListJournalComptable() {
		// GIVEN
		ComptabiliteManagerImpl comptabiliteManagerImpl = new ComptabiliteManagerImpl();
		DaoProxy daoProxy = Mockito.mock(DaoProxy.class);
		ComptabiliteDao comptabiliteDao = Mockito.mock(ComptabiliteDao.class);
		List<JournalComptable> expectedListJournalComptable = new ArrayList<>();

		ReflectionTestUtils.setField(AbstractBusinessManager.class, "daoProxy", daoProxy);
		when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
		when(comptabiliteDao.getListJournalComptable()).thenReturn(expectedListJournalComptable);

		// WHEN
		List<JournalComptable> actualListJournalComptable = comptabiliteManagerImpl.getListJournalComptable();

		// THEN
		assertThat(actualListJournalComptable).isEqualTo(expectedListJournalComptable);
	}

	// ==================== getListEcritureComptable() ====================

	@Test
	public void getListEcritureComptable_returnsListEcritureComptable() {
		// GIVEN
		ComptabiliteManagerImpl comptabiliteManagerImpl = new ComptabiliteManagerImpl();
		DaoProxy daoProxy = Mockito.mock(DaoProxy.class);
		ComptabiliteDao comptabiliteDao = Mockito.mock(ComptabiliteDao.class);
		List<EcritureComptable> expectedListEcritureComptable = new ArrayList<>();

		ReflectionTestUtils.setField(AbstractBusinessManager.class, "daoProxy", daoProxy);
		when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
		when(comptabiliteDao.getListEcritureComptable()).thenReturn(expectedListEcritureComptable);

		// WHEN
		List<EcritureComptable> actualListEcritureComptable = comptabiliteManagerImpl.getListEcritureComptable();

		// THEN
		assertThat(actualListEcritureComptable).isEqualTo(expectedListEcritureComptable);
	}

	// =============== insertEcritureComptable(EcritureComptable) ===============

	@Test
	public void insertEcritureComptable_insertEcritureComptable() throws FunctionalException {
		// GIVEN
		ComptabiliteManagerImpl manager = Mockito.spy(new ComptabiliteManagerImpl());
		EcritureComptable ecriture = new EcritureComptable();
		TransactionManager transactionManager = Mockito.mock(TransactionManager.class);
		TransactionStatus transactionStatus = Mockito.mock(TransactionStatus.class);
		DaoProxy daoProxy = Mockito.mock(DaoProxy.class);
		ComptabiliteDao comptabiliteDao = Mockito.mock(ComptabiliteDao.class);

		doNothing().when(manager).checkEcritureComptable(any(EcritureComptable.class));
		ReflectionTestUtils.setField(AbstractBusinessManager.class, "transactionManager", transactionManager);
		when(transactionManager.beginTransactionMyERP()).thenReturn(transactionStatus);
		ReflectionTestUtils.setField(AbstractBusinessManager.class, "daoProxy", daoProxy);
		when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);

		// WHEN
		manager.insertEcritureComptable(ecriture);

		// THEN
		verify(comptabiliteDao).insertEcritureComptable(ecriture);
		verify(transactionManager).commitMyERP(transactionStatus);
		verify(transactionManager).rollbackMyERP(null);
	}

}
