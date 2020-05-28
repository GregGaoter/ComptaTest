package com.dummy.myerp.consumer.dao.impl.cache;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;

public class JournalComptableDaoCacheTest {

	private final static String stringVide = "";
	private final static String string1Espace = " ";
	private final static String string2Espaces = "  ";
	private final static String stringRetourLigne = "\n";
	private final static String stringTabulation = "\t";
	private final static String stringLong = "xâkànrCoîVdBScxrËÀaïçÛoYùFmvAÊÎOâÀÔÿQÀÙÜHXDaRWfMSéÙyPÔÊbNÈvCDÂYïîÏnuMüCcépoqçpÊnIYgËeÎzqzXiçôzâëZtUcutFbWàuAidiùÿaPàdVUxvxÛqHgfeôVZHlvGKÊYfZLÂflCRifRôbèBÔsSÉWgChRÜAFLMÂtcJÙQIbÏÀiëWiVYNûàEüÀbéwDneTkrÙUÛêÎÿGàÈjDèëêÔtcYvSnûkÉékCzvZÉXdÛêîÿSgOjlBûâBêHbaéénÎËDPsJTdûOYslÏIFéoËLiquÛÉÊGySpïImuKüÊpbÇFrÎqCHdÊûTÀNERÀQùUÿOwîvkVxPcëoïjehÔSôUîHÜKÏLuëÉuuegcPQÛîÙMÀXzMÙVülÔRââOeôÛZYlDçÂçÊIùSüTKÊMïPËÈàikuJdroçwgèDqgÉvÎuvêèTàfÏGnsQëÛùeÉmBBühnÏzxQpÛÜfNhfUîÎsàreRcqGÇâéËvHÎUvÔXVZÀNfyéÊPrMZÂgaKwhBÇOûTEeÜûzêyylÏéFéHjËüXwüOpWugèËèVôwâÛWNBjKPaÛFIJÂÿNÎyWTGlJlmtDVÉxÿcESçxPTÀôgÔÂYfKaÙngpyÜÜùÇBïzNâpHJùÇXLGUSJjëQVIqLzlÔÜÔjËFkXbutxÈhUAÉqZsïXÙwÂjëkhûÈWÇEHutVçmésaÂîBbgsüwUyZÊÿËmzÜëÈPbgêAMLiDDeIÙPôïPCÏîVsiQÀAIhÔOyZcUÿSübjêîTÉÏÇÀêHEûçtïàèFAjrmStZjGÙGGüQoàÈbÈûehGèOYèïYMÈFnmüRkyRQJpsEÈÔLWJIÏùYôowcBëRxCXôàAGoÏÿmiEMôNûDXFaçiÇômÇîGplEJUrxEÙÇdOrKÎWKèBwÿàIÂÉfyÈmÊNëÇkLsÎYHCyTaÉTÿÉnrFîcOèâïFÂKDÿéCJëISèpâQAKêAÎRdMzûZËNHêBhünçÇOzùdôEîjÊêqtÀïhïÏËZsËxÏÈToLeLayrÈorbiWIWùÛùèRÛRqwLùwokTQqkqnVhJmlCKJQmÜpKeâDfëAÜAâÙjsOfvÂÙXMxWÎowPdtpEMÇàdÜûÂUÔzùCdêÜaAtLkaNçtlvhNcEXç";

	private JournalComptableDaoCache journalComptableDaoCache;
	private static List<JournalComptable> listJournalComptable;
	private static List<JournalComptable> listJournalComptableDb;
	private DaoProxy daoProxy;
	private ComptabiliteDao comptabiliteDao;

	@BeforeEach
	public void initTest() {
		journalComptableDaoCache = new JournalComptableDaoCache();
		daoProxy = Mockito.mock(DaoProxy.class);
		comptabiliteDao = Mockito.mock(ComptabiliteDao.class);
		initListJournalComptable();
		initListJournalComptableDb();
	}

	private static void initListJournalComptable() {
		listJournalComptable = new ArrayList<JournalComptable>(3);
		listJournalComptable.add(new JournalComptable("AC", "j1"));
		listJournalComptable.add(new JournalComptable("BQ", "j2"));
		listJournalComptable.add(new JournalComptable("FO", "j3"));
	}

	private static void initListJournalComptableDb() {
		listJournalComptableDb = new ArrayList<JournalComptable>(3);
		listJournalComptable.add(new JournalComptable("AC", "j1"));
		listJournalComptable.add(new JournalComptable("BQ", "j2"));
		listJournalComptable.add(new JournalComptable("FO", "j3"));
	}

	@AfterEach
	public void undefTest() {
		journalComptableDaoCache = null;
		daoProxy = null;
		comptabiliteDao = null;
		listJournalComptable = null;
		listJournalComptableDb = null;
	}

	// ========== getByCode(String) ==========

	private static Stream<Arguments> getArgumentsPourGetByCode() {
		initListJournalComptable();
		return Stream.of(
				Arguments.of("AC", listJournalComptable, listJournalComptable.get(0)),
				Arguments.of("BQ", new ArrayList<JournalComptable>(), null),
				Arguments.of("FO", null, null),
				Arguments.of(stringVide, listJournalComptable, null),
				Arguments.of(stringVide, new ArrayList<JournalComptable>(), null),
				Arguments.of(stringVide, null, null),
				Arguments.of(string1Espace, listJournalComptable, null),
				Arguments.of(string1Espace, new ArrayList<JournalComptable>(), null),
				Arguments.of(string1Espace, null, null),
				Arguments.of(string2Espaces, listJournalComptable, null),
				Arguments.of(string2Espaces, new ArrayList<JournalComptable>(), null),
				Arguments.of(string2Espaces, null, null),
				Arguments.of(stringRetourLigne, listJournalComptable, null),
				Arguments.of(stringRetourLigne, new ArrayList<JournalComptable>(), null),
				Arguments.of(stringRetourLigne, null, null),
				Arguments.of(stringTabulation, listJournalComptable, null),
				Arguments.of(stringTabulation, new ArrayList<JournalComptable>(), null),
				Arguments.of(stringTabulation, null, null),
				Arguments.of(stringLong, listJournalComptable, null),
				Arguments.of(stringLong, new ArrayList<JournalComptable>(), null),
				Arguments.of(stringLong, listJournalComptable, null),
				Arguments.of(null, listJournalComptable, null),
				Arguments.of(null, new ArrayList<JournalComptable>(), null),
				Arguments.of(null, null, null));
	}

	@ParameterizedTest(name = "code = {0}, listJournalComptable = {1}, expectedJournalComptable = {2}")
	@MethodSource("getArgumentsPourGetByCode")
	public void getByCode_code_returnsJournalComptable(String code, List<JournalComptable> listJournalComptable,
			JournalComptable expectedJournalComptable) {
		// GIVEN
		ReflectionTestUtils.setField(journalComptableDaoCache, "listJournalComptable", listJournalComptable);
		ReflectionTestUtils.setField(ConsumerHelper.class, "daoProxy", daoProxy);
		when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
		when(comptabiliteDao.getListJournalComptable()).thenReturn(listJournalComptableDb);

		// WHEN
		JournalComptable actualJournalComptable = journalComptableDaoCache.getByCode(code);

		// THEN
		assertThat(actualJournalComptable).isEqualTo(expectedJournalComptable);
	}

}
