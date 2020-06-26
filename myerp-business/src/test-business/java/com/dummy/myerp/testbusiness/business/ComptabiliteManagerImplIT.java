package com.dummy.myerp.testbusiness.business;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.dummy.myerp.business.helper.DateHelper;
import com.dummy.myerp.business.impl.manager.ComptabiliteManagerImpl;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;

@Testcontainers
public class ComptabiliteManagerImplIT extends AbstractBusinessIt {

	private ComptabiliteManagerImpl comptabiliteManagerImpl;

	private final Path projectPath = Path.of("").toAbsolutePath();

	@Container
	private DockerComposeContainer<?> dockerEnvironment = new DockerComposeContainer<>(
			new File(projectPath.getParent().toFile(), "docker/dev/docker-compose.yml"))
					.withExposedService("myerp.db_1", 5432);

	@BeforeEach
	public void init() {
		comptabiliteManagerImpl = new ComptabiliteManagerImpl();
		Assertions.setAllowExtractingPrivateFields(false);
	}

	@AfterEach
	public void undef() {
		comptabiliteManagerImpl = null;
	}

	// ==================== getListCompteComptable() ====================

	@Test
	public void getListCompteComptable_returnsListCompteComptable() {
		// GIVEN
		List<CompteComptable> listCompteComptableExpected = Arrays.asList(new CompteComptable(401, "Fournisseurs"),
				new CompteComptable(411, "Clients"),
				new CompteComptable(4456, "Taxes sur le chiffre d'affaires déductibles"),
				new CompteComptable(4457, "Taxes sur le chiffre d'affaires collectées par l'entreprise"),
				new CompteComptable(512, "Banque"),
				new CompteComptable(606, "Achats non stockés de matières et fournitures"),
				new CompteComptable(706, "Prestations de services"));

		// WHEN
		List<CompteComptable> listCompteComptableActual = comptabiliteManagerImpl.getListCompteComptable();

		// THEN
		assertThat(listCompteComptableActual).usingFieldByFieldElementComparator()
				.containsAll(listCompteComptableExpected);
	}

	// ==================== getListJournalComptable() ====================

	@Test
	public void getListJournalComptable_returnsListJournalComptable() {
		// GIVEN
		List<JournalComptable> listJournalComptableExpected = Arrays.asList(new JournalComptable("AC", "Achat"),
				new JournalComptable("VE", "Vente"), new JournalComptable("BQ", "Banque"),
				new JournalComptable("OD", "Opérations Diverses"));

		// WHEN
		List<JournalComptable> listJournalComptableActual = comptabiliteManagerImpl.getListJournalComptable();

		// THEN
		assertThat(listJournalComptableActual).usingFieldByFieldElementComparator()
				.containsAll(listJournalComptableExpected);
	}

	// ==================== getListEcritureComptable() ====================

	@Test
	public void getListEcritureComptable_returnsListEcritureComptable() {
		// GIVEN
		List<EcritureComptable> listEcritureComptableExpected = Arrays.asList(
				new EcritureComptable(-1, new JournalComptable("AC"), "AC-2016/00001", DateHelper.getDate(31, 12, 2016),
						"Cartouches d’imprimante"),
				new EcritureComptable(-2, new JournalComptable("VE"), "VE-2016/00002", DateHelper.getDate(30, 12, 2016),
						"TMA Appli Xxx"),
				new EcritureComptable(-3, new JournalComptable("BQ"), "BQ-2016/00003", DateHelper.getDate(29, 12, 2016),
						"Paiement Facture F110001"),
				new EcritureComptable(-4, new JournalComptable("VE"), "VE-2016/00004", DateHelper.getDate(28, 12, 2016),
						"TMA Appli Yyy"),
				new EcritureComptable(-5, new JournalComptable("BQ"), "BQ-2016/00005", DateHelper.getDate(27, 12, 2016),
						"Paiement Facture C110002"));

		// WHEN
		List<EcritureComptable> listEcritureComptableActual = comptabiliteManagerImpl.getListEcritureComptable();

		// THEN
		assertThat(listEcritureComptableActual).usingElementComparator(new Comparator<EcritureComptable>() {
			@Override
			public int compare(EcritureComptable ec1, EcritureComptable ec2) {
				int compareId = ec1.getId().compareTo(ec2.getId());
				int compareJournal = ec1.getJournal().getCode().equals(ec2.getJournal().getCode()) ? 0 : 1;
				int compareReference = ec1.getReference().equals(ec2.getReference()) ? 0 : 1;
				int compareDate = ec1.getDate().compareTo(ec2.getDate());
				int compareLibelle = ec1.getLibelle().equals(ec2.getLibelle()) ? 0 : 1;
				boolean zeroQ = compareId == 0 && compareJournal == 0 && compareReference == 0 && compareDate == 0
						&& compareLibelle == 0;
				int somme = compareId + compareJournal + compareReference + compareDate + compareLibelle;
				if (zeroQ) {
					return 0;
				} else if (somme > 0) {
					return 1;
				} else {
					return -1;
				}
			}
		}).containsAll(listEcritureComptableExpected);
	}

	// ==================== getListSequenceEcritureComptable() ====================

	@Test
	public void getListSequenceEcritureComptable_returnsListSequenceEcritureComptable() {
		// GIVEN
		List<SequenceEcritureComptable> listSequenceEcritureComptableExpected = Arrays.asList(
				new SequenceEcritureComptable("AC", 2016, 40), new SequenceEcritureComptable("VE", 2016, 41),
				new SequenceEcritureComptable("BQ", 2016, 51), new SequenceEcritureComptable("OD", 2016, 88));

		// WHEN
		List<SequenceEcritureComptable> listSequenceEcritureComptableActual = comptabiliteManagerImpl
				.getListSequenceEcritureComptable();

		// THEN
		assertThat(listSequenceEcritureComptableActual).usingFieldByFieldElementComparator()
				.containsAll(listSequenceEcritureComptableExpected);
	}

	// ==================== addReference(EcritureComptable) ====================

	@Test
	public void addReference_addReferenceAndInsertSequence() {
		// GIVEN
		JournalComptable journal = new JournalComptable("BQ", "Banque");
		Date date = DateHelper.getDate(25, 6, 2020);
		EcritureComptable ecriture = new EcritureComptable(journal, date, "Test intégration addReference.");

		// WHEN
		comptabiliteManagerImpl.addReference(ecriture);
		List<SequenceEcritureComptable> listSequenceEcritureComptable = comptabiliteManagerImpl
				.getListSequenceEcritureComptable();

		// THEN
		assertThat(ecriture.getReference()).isEqualTo("BQ-2020/00001");
		assertThat(listSequenceEcritureComptable).hasSize(5);
	}

	@Test
	public void addReference_addReferenceAndUpdateSequence() {
		// GIVEN
		JournalComptable journal = new JournalComptable("BQ", "Banque");
		Date date = DateHelper.getDate(25, 6, 2016);
		EcritureComptable ecriture = new EcritureComptable(journal, date, "Test intégration addReference.");

		// WHEN
		comptabiliteManagerImpl.addReference(ecriture);
		List<SequenceEcritureComptable> listSequenceEcritureComptable = comptabiliteManagerImpl
				.getListSequenceEcritureComptable();
		SequenceEcritureComptable sequenceEcritureComptable = listSequenceEcritureComptable.stream()
				.filter(seq -> seq.getJournalCode().equals("BQ") && seq.getAnnee().compareTo(2016) == 0).findFirst()
				.get();

		// THEN
		assertThat(ecriture.getReference()).isEqualTo("BQ-2016/00052");
		assertThat(sequenceEcritureComptable.getDerniereValeur()).isEqualTo(52);
	}

}
