package com.dummy.myerp.testconsumer.consumer;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.javatuples.Pair;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.dummy.myerp.consumer.DateHelper;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;

@SpringJUnitConfig(locations = "classpath:/com/dummy/myerp/consumer/applicationContext.xml")
@Testcontainers
public abstract class AbstractConsumerIt {

	private final static int NB_COMPTES = 7;
	private final static int NB_JOURNAUX = 4;
	private final static int NB_ECRITURES = 5;
	private final static int NB_LIGNES_ECRITURES = 13;
	private final static int NB_SEQUENCES_ECRITURES = 4;

	private final Path projectPath = Path.of("").toAbsolutePath();

	// @Container
	protected DockerComposeContainer<?> dockerEnvironment = new DockerComposeContainer<>(
			new File(projectPath.getParent().toFile(), "docker/dev/docker-compose.yml"))
					.withExposedService("myerp.db_1", 5432);

	protected final static Map<Integer, CompteComptable> mapCompteExpected = new HashMap<>(NB_COMPTES);
	protected final static Map<String, JournalComptable> mapJournalExpected = new HashMap<>(NB_JOURNAUX);
	protected final static Map<Integer, EcritureComptable> mapEcritureExpected = new HashMap<>(NB_ECRITURES);
	protected final static Map<Pair<Integer, Integer>, LigneEcritureComptable> mapLigneEcritureExpected = new HashMap<>(
			NB_LIGNES_ECRITURES);
	protected final static Map<Pair<String, Integer>, SequenceEcritureComptable> mapSequenceEcritureExpected = new HashMap<>(
			NB_SEQUENCES_ECRITURES);

	protected Comparator<EcritureComptable> comparatorEcriture = new Comparator<EcritureComptable>() {
		@Override
		public int compare(EcritureComptable e1, EcritureComptable e2) {
			int compareId = comparer(e1.getId(), e2.getId());
			int compareJournal = comparer(e1.getJournal().getCode(), e2.getJournal().getCode());
			int compareReference = comparer(e1.getReference(), e2.getReference());
			int compareDate = comparer(e1.getDate(), e2.getDate());
			int compareLibelle = comparer(e1.getLibelle(), e2.getLibelle());
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
	};

	protected Comparator<LigneEcritureComptable> comparatorLigneEcriture = new Comparator<LigneEcritureComptable>() {
		@Override
		public int compare(LigneEcritureComptable le1, LigneEcritureComptable le2) {
			int compareCompte = comparer(le1.getCompteComptable().getNumero(), le1.getCompteComptable().getNumero());
			int compareLibelle = comparer(le1.getLibelle(), le2.getLibelle());
			int compareDebit = comparer(le1.getDebit(), le2.getDebit());
			int compareCredit = comparer(le1.getCredit(), le2.getCredit());
			boolean zeroQ = compareCompte == 0 && compareLibelle == 0 && compareDebit == 0 && compareCredit == 0;
			int somme = compareCompte + compareLibelle + compareDebit + compareCredit;
			if (zeroQ) {
				return 0;
			} else if (somme > 0) {
				return 1;
			} else {
				return -1;
			}
		}
	};

	private <T extends Comparable<T>> int comparer(T t1, T t2) {
		return t1 == null && t2 == null ? 0 : (t1 != null && t2 != null ? t1.compareTo(t2) : 1);
	}

	protected static void initDbExpected() {
		initMapCompteExpected();
		initMapJournalExpected();
		initMapEcritureExpected();
		initMapLigneEcritureExpected();
		initMapSequenceEcritureExpected();
	}

	protected static void undefDbExpected() {
		mapCompteExpected.clear();
		mapEcritureExpected.clear();
		mapJournalExpected.clear();
		mapLigneEcritureExpected.clear();
		mapSequenceEcritureExpected.clear();
	}

	private static void initMapCompteExpected() {
		mapCompteExpected.put(401, new CompteComptable(401, "Fournisseurs"));
		mapCompteExpected.put(411, new CompteComptable(411, "Clients"));
		mapCompteExpected.put(4456, new CompteComptable(4456, "Taxes sur le chiffre d'affaires déductibles"));
		mapCompteExpected.put(4457,
				new CompteComptable(4457, "Taxes sur le chiffre d'affaires collectées par l'entreprise"));
		mapCompteExpected.put(512, new CompteComptable(512, "Banque"));
		mapCompteExpected.put(606, new CompteComptable(606, "Achats non stockés de matières et fournitures"));
		mapCompteExpected.put(706, new CompteComptable(706, "Prestations de services"));
	}

	private static void initMapJournalExpected() {
		mapJournalExpected.put("AC", new JournalComptable("AC", "Achat"));
		mapJournalExpected.put("VE", new JournalComptable("VE", "Vente"));
		mapJournalExpected.put("BQ", new JournalComptable("BQ", "Banque"));
		mapJournalExpected.put("OD", new JournalComptable("OD", "Opérations Diverses"));
	}

	private static void initMapEcritureExpected() {
		mapEcritureExpected.put(-1, new EcritureComptable(-1, mapJournalExpected.get("AC"), "AC-2016/00001",
				DateHelper.getDate(31, 12, 2016), "Cartouches d’imprimante"));
		mapEcritureExpected.put(-2, new EcritureComptable(-2, mapJournalExpected.get("VE"), "VE-2016/00002",
				DateHelper.getDate(30, 12, 2016), "TMA Appli Xxx"));
		mapEcritureExpected.put(-3, new EcritureComptable(-3, mapJournalExpected.get("BQ"), "BQ-2016/00003",
				DateHelper.getDate(29, 12, 2016), "Paiement Facture F110001"));
		mapEcritureExpected.put(-4, new EcritureComptable(-4, mapJournalExpected.get("VE"), "VE-2016/00004",
				DateHelper.getDate(28, 12, 2016), "TMA Appli Yyy"));
		mapEcritureExpected.put(-5, new EcritureComptable(-5, mapJournalExpected.get("BQ"), "BQ-2016/00005",
				DateHelper.getDate(27, 12, 2016), "Paiement Facture C110002"));
	}

	private static void initMapLigneEcritureExpected() {
		mapLigneEcritureExpected.put(Pair.with(-1, 1), new LigneEcritureComptable(mapCompteExpected.get(606),
				"Cartouches d’imprimante", BigDecimal.valueOf(4395, 2), null));
		mapLigneEcritureExpected.put(Pair.with(-1, 2),
				new LigneEcritureComptable(mapCompteExpected.get(4456), "TVA 20%", BigDecimal.valueOf(879, 2), null));
		mapLigneEcritureExpected.put(Pair.with(-1, 3), new LigneEcritureComptable(mapCompteExpected.get(401),
				"Facture F110001", null, BigDecimal.valueOf(5274, 2)));
		mapLigneEcritureExpected.put(Pair.with(-2, 1), new LigneEcritureComptable(mapCompteExpected.get(411),
				"Facture C110002", BigDecimal.valueOf(3000, 0), null));
		mapLigneEcritureExpected.put(Pair.with(-2, 2), new LigneEcritureComptable(mapCompteExpected.get(706),
				"TMA Appli Xxx", null, BigDecimal.valueOf(2500, 0)));
		mapLigneEcritureExpected.put(Pair.with(-2, 3),
				new LigneEcritureComptable(mapCompteExpected.get(4457), "TVA 20%", null, BigDecimal.valueOf(500, 0)));
		mapLigneEcritureExpected.put(Pair.with(-3, 1),
				new LigneEcritureComptable(mapCompteExpected.get(401), null, BigDecimal.valueOf(5274, 2), null));
		mapLigneEcritureExpected.put(Pair.with(-3, 2),
				new LigneEcritureComptable(mapCompteExpected.get(512), null, null, BigDecimal.valueOf(5274, 2)));
		mapLigneEcritureExpected.put(Pair.with(-4, 1), new LigneEcritureComptable(mapCompteExpected.get(411),
				"Facture C110004", BigDecimal.valueOf(5700, 0), null));
		mapLigneEcritureExpected.put(Pair.with(-4, 2), new LigneEcritureComptable(mapCompteExpected.get(706),
				"TMA Appli Xxx", null, BigDecimal.valueOf(4750, 0)));
		mapLigneEcritureExpected.put(Pair.with(-4, 3),
				new LigneEcritureComptable(mapCompteExpected.get(4457), "TVA 20%", null, BigDecimal.valueOf(950, 0)));
		mapLigneEcritureExpected.put(Pair.with(-5, 1),
				new LigneEcritureComptable(mapCompteExpected.get(512), null, BigDecimal.valueOf(3000, 0), null));
		mapLigneEcritureExpected.put(Pair.with(-5, 2),
				new LigneEcritureComptable(mapCompteExpected.get(411), null, null, BigDecimal.valueOf(3000, 0)));
	}

	private static void initMapSequenceEcritureExpected() {
		mapSequenceEcritureExpected.put(Pair.with("AC", 2016), new SequenceEcritureComptable("AC", 2016, 40));
		mapSequenceEcritureExpected.put(Pair.with("VE", 2016), new SequenceEcritureComptable("VE", 2016, 41));
		mapSequenceEcritureExpected.put(Pair.with("BQ", 2016), new SequenceEcritureComptable("BQ", 2016, 51));
		mapSequenceEcritureExpected.put(Pair.with("OD", 2016), new SequenceEcritureComptable("OD", 2016, 88));
	}

}
