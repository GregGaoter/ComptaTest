package com.dummy.myerp.model.bean.comptabilite;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class JournalComptableTest {

	// ==================== toString ====================

	@Test
	public void toString_journalComptableWithCodeTabAndLibelleNull_returnsStringOfJournalComptable() {
		// GIVEN
		JournalComptable journalComptable = new JournalComptable("\t", null);

		// WHEN
		String actualString = journalComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("JournalComptable{code='\t', libelle='null'}");
	}

	@Test
	public void toString_journalComptableWithCodeRetourLigneAndLibelle1Espace_returnsStringOfJournalComptable() {
		// GIVEN
		JournalComptable journalComptable = new JournalComptable("\n", " ");

		// WHEN
		String actualString = journalComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("JournalComptable{code='\n', libelle=' '}");
	}

	@Test
	public void toString_journalComptableWithCode2EspacesAndLibelleNormal_returnsStringOfJournalComptable() {
		// GIVEN
		JournalComptable journalComptable = new JournalComptable("  ", "Achat");

		// WHEN
		String actualString = journalComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("JournalComptable{code='  ', libelle='Achat'}");
	}

	@Test
	public void toString_journalComptableWithCodeLongAndLibelleVide_returnsStringOfJournalComptable() {
		// GIVEN
		JournalComptable journalComptable = new JournalComptable(
				"xâkànrCoîVdBScxrËÀaïçÛoYùFmvAÊÎOâÀÔÿQÀÙÜHXDaRWfMSéÙyPÔÊbNÈvCDÂYïîÏnuMüCcépoqçpÊnIYgËeÎzqzXiçôzâëZtUcutFbWàuAidiùÿaPàdVUxvxÛqHgfeôVZHlvGKÊYfZLÂflCRifRôbèBÔsSÉWgChRÜAFLMÂtcJÙQIbÏÀiëWiVYNûàEüÀbéwDneTkrÙUÛêÎÿGàÈjDèëêÔtcYvSnûkÉékCzvZÉXdÛêîÿSgOjlBûâBêHbaéénÎËDPsJTdûOYslÏIFéoËLiquÛÉÊGySpïImuKüÊpbÇFrÎqCHdÊûTÀNERÀQùUÿOwîvkVxPcëoïjehÔSôUîHÜKÏLuëÉuuegcPQÛîÙMÀXzMÙVülÔRââOeôÛZYlDçÂçÊIùSüTKÊMïPËÈàikuJdroçwgèDqgÉvÎuvêèTàfÏGnsQëÛùeÉmBBühnÏzxQpÛÜfNhfUîÎsàreRcqGÇâéËvHÎUvÔXVZÀNfyéÊPrMZÂgaKwhBÇOûTEeÜûzêyylÏéFéHjËüXwüOpWugèËèVôwâÛWNBjKPaÛFIJÂÿNÎyWTGlJlmtDVÉxÿcESçxPTÀôgÔÂYfKaÙngpyÜÜùÇBïzNâpHJùÇXLGUSJjëQVIqLzlÔÜÔjËFkXbutxÈhUAÉqZsïXÙwÂjëkhûÈWÇEHutVçmésaÂîBbgsüwUyZÊÿËmzÜëÈPbgêAMLiDDeIÙPôïPCÏîVsiQÀAIhÔOyZcUÿSübjêîTÉÏÇÀêHEûçtïàèFAjrmStZjGÙGGüQoàÈbÈûehGèOYèïYMÈFnmüRkyRQJpsEÈÔLWJIÏùYôowcBëRxCXôàAGoÏÿmiEMôNûDXFaçiÇômÇîGplEJUrxEÙÇdOrKÎWKèBwÿàIÂÉfyÈmÊNëÇkLsÎYHCyTaÉTÿÉnrFîcOèâïFÂKDÿéCJëISèpâQAKêAÎRdMzûZËNHêBhünçÇOzùdôEîjÊêqtÀïhïÏËZsËxÏÈToLeLayrÈorbiWIWùÛùèRÛRqwLùwokTQqkqnVhJmlCKJQmÜpKeâDfëAÜAâÙjsOfvÂÙXMxWÎowPdtpEMÇàdÜûÂUÔzùCdêÜaAtLkaNçtlvhNcEXç",
				"");

		// WHEN
		String actualString = journalComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo(
				"JournalComptable{code='xâkànrCoîVdBScxrËÀaïçÛoYùFmvAÊÎOâÀÔÿQÀÙÜHXDaRWfMSéÙyPÔÊbNÈvCDÂYïîÏnuMüCcépoqçpÊnIYgËeÎzqzXiçôzâëZtUcutFbWàuAidiùÿaPàdVUxvxÛqHgfeôVZHlvGKÊYfZLÂflCRifRôbèBÔsSÉWgChRÜAFLMÂtcJÙQIbÏÀiëWiVYNûàEüÀbéwDneTkrÙUÛêÎÿGàÈjDèëêÔtcYvSnûkÉékCzvZÉXdÛêîÿSgOjlBûâBêHbaéénÎËDPsJTdûOYslÏIFéoËLiquÛÉÊGySpïImuKüÊpbÇFrÎqCHdÊûTÀNERÀQùUÿOwîvkVxPcëoïjehÔSôUîHÜKÏLuëÉuuegcPQÛîÙMÀXzMÙVülÔRââOeôÛZYlDçÂçÊIùSüTKÊMïPËÈàikuJdroçwgèDqgÉvÎuvêèTàfÏGnsQëÛùeÉmBBühnÏzxQpÛÜfNhfUîÎsàreRcqGÇâéËvHÎUvÔXVZÀNfyéÊPrMZÂgaKwhBÇOûTEeÜûzêyylÏéFéHjËüXwüOpWugèËèVôwâÛWNBjKPaÛFIJÂÿNÎyWTGlJlmtDVÉxÿcESçxPTÀôgÔÂYfKaÙngpyÜÜùÇBïzNâpHJùÇXLGUSJjëQVIqLzlÔÜÔjËFkXbutxÈhUAÉqZsïXÙwÂjëkhûÈWÇEHutVçmésaÂîBbgsüwUyZÊÿËmzÜëÈPbgêAMLiDDeIÙPôïPCÏîVsiQÀAIhÔOyZcUÿSübjêîTÉÏÇÀêHEûçtïàèFAjrmStZjGÙGGüQoàÈbÈûehGèOYèïYMÈFnmüRkyRQJpsEÈÔLWJIÏùYôowcBëRxCXôàAGoÏÿmiEMôNûDXFaçiÇômÇîGplEJUrxEÙÇdOrKÎWKèBwÿàIÂÉfyÈmÊNëÇkLsÎYHCyTaÉTÿÉnrFîcOèâïFÂKDÿéCJëISèpâQAKêAÎRdMzûZËNHêBhünçÇOzùdôEîjÊêqtÀïhïÏËZsËxÏÈToLeLayrÈorbiWIWùÛùèRÛRqwLùwokTQqkqnVhJmlCKJQmÜpKeâDfëAÜAâÙjsOfvÂÙXMxWÎowPdtpEMÇàdÜûÂUÔzùCdêÜaAtLkaNçtlvhNcEXç', libelle=''}");
	}

	// ==================== getByCode ====================

	private List<JournalComptable> createListJournalComptable() {
		List<JournalComptable> listJournalComptable = new ArrayList<>(5);
		listJournalComptable.add(new JournalComptable("C1", "L1"));
		listJournalComptable.add(new JournalComptable("C2", "L2"));
		listJournalComptable.add(new JournalComptable("C3", "L3"));
		listJournalComptable.add(new JournalComptable("C4", "L4"));
		listJournalComptable.add(new JournalComptable("C5", "L5"));
		return listJournalComptable;
	}

	@Test
	public void getByCode_journalComptableListeVideCodeVide_returnsNull() {
		// GIVEN
		List<JournalComptable> listJournalComptable = new ArrayList<>();
		String code = "";

		// WHEN
		JournalComptable actualJournalComptable = JournalComptable.getByCode(listJournalComptable, code);

		// THEN
		assertThat(actualJournalComptable).isNull();
	}

	@Test
	public void getByCode_journalComptableListeNormaleCode1Espace_returnsNull() {
		// GIVEN
		List<JournalComptable> listJournalComptable = createListJournalComptable();
		String code = " ";

		// WHEN
		JournalComptable actualJournalComptable = JournalComptable.getByCode(listJournalComptable, code);

		// THEN
		assertThat(actualJournalComptable).isNull();
	}

	@Test
	public void getByCode_journalComptableListeNormaleCodeTab_returnsNull() {
		// GIVEN
		List<JournalComptable> listJournalComptable = createListJournalComptable();
		String code = "\t";

		// WHEN
		JournalComptable actualJournalComptable = JournalComptable.getByCode(listJournalComptable, code);

		// THEN
		assertThat(actualJournalComptable).isNull();
	}

	@Test
	public void getByCode_journalComptableListeNullCodeRetourLigne_returnsNull() {
		// GIVEN
		String code = "\n";

		// WHEN
		JournalComptable actualJournalComptable = JournalComptable.getByCode(null, code);

		// THEN
		assertThat(actualJournalComptable).isNull();
	}

	@Test
	public void getByCode_journalComptableListeNullCode2Espaces_returnsNull() {
		// GIVEN
		String code = "  ";

		// WHEN
		JournalComptable actualJournalComptable = JournalComptable.getByCode(null, code);

		// THEN
		assertThat(actualJournalComptable).isNull();
	}

	@Test
	public void getByCode_journalComptableListeVideCodeNull_returnsNull() {
		// GIVEN
		List<JournalComptable> listJournalComptable = new ArrayList<>();

		// WHEN
		JournalComptable actualJournalComptable = JournalComptable.getByCode(listJournalComptable, null);

		// THEN
		assertThat(actualJournalComptable).isNull();
	}

	@Test
	public void getByCode_journalComptableListeVideCodeNormal_returnsNull() {
		// GIVEN
		List<JournalComptable> listJournalComptable = new ArrayList<>();
		String code = "AC";

		// WHEN
		JournalComptable actualJournalComptable = JournalComptable.getByCode(listJournalComptable, code);

		// THEN
		assertThat(actualJournalComptable).isNull();
	}

	@Test
	public void getByCode_journalComptableListeNormalCodeLong_returnsNull() {
		// GIVEN
		List<JournalComptable> listJournalComptable = createListJournalComptable();
		String code = "xâkànrCoîVdBScxrËÀaïçÛoYùFmvAÊÎOâÀÔÿQÀÙÜHXDaRWfMSéÙyPÔÊbNÈvCDÂYïîÏnuMüCcépoqçpÊnIYgËeÎzqzXiçôzâëZtUcutFbWàuAidiùÿaPàdVUxvxÛqHgfeôVZHlvGKÊYfZLÂflCRifRôbèBÔsSÉWgChRÜAFLMÂtcJÙQIbÏÀiëWiVYNûàEüÀbéwDneTkrÙUÛêÎÿGàÈjDèëêÔtcYvSnûkÉékCzvZÉXdÛêîÿSgOjlBûâBêHbaéénÎËDPsJTdûOYslÏIFéoËLiquÛÉÊGySpïImuKüÊpbÇFrÎqCHdÊûTÀNERÀQùUÿOwîvkVxPcëoïjehÔSôUîHÜKÏLuëÉuuegcPQÛîÙMÀXzMÙVülÔRââOeôÛZYlDçÂçÊIùSüTKÊMïPËÈàikuJdroçwgèDqgÉvÎuvêèTàfÏGnsQëÛùeÉmBBühnÏzxQpÛÜfNhfUîÎsàreRcqGÇâéËvHÎUvÔXVZÀNfyéÊPrMZÂgaKwhBÇOûTEeÜûzêyylÏéFéHjËüXwüOpWugèËèVôwâÛWNBjKPaÛFIJÂÿNÎyWTGlJlmtDVÉxÿcESçxPTÀôgÔÂYfKaÙngpyÜÜùÇBïzNâpHJùÇXLGUSJjëQVIqLzlÔÜÔjËFkXbutxÈhUAÉqZsïXÙwÂjëkhûÈWÇEHutVçmésaÂîBbgsüwUyZÊÿËmzÜëÈPbgêAMLiDDeIÙPôïPCÏîVsiQÀAIhÔOyZcUÿSübjêîTÉÏÇÀêHEûçtïàèFAjrmStZjGÙGGüQoàÈbÈûehGèOYèïYMÈFnmüRkyRQJpsEÈÔLWJIÏùYôowcBëRxCXôàAGoÏÿmiEMôNûDXFaçiÇômÇîGplEJUrxEÙÇdOrKÎWKèBwÿàIÂÉfyÈmÊNëÇkLsÎYHCyTaÉTÿÉnrFîcOèâïFÂKDÿéCJëISèpâQAKêAÎRdMzûZËNHêBhünçÇOzùdôEîjÊêqtÀïhïÏËZsËxÏÈToLeLayrÈorbiWIWùÛùèRÛRqwLùwokTQqkqnVhJmlCKJQmÜpKeâDfëAÜAâÙjsOfvÂÙXMxWÎowPdtpEMÇàdÜûÂUÔzùCdêÜaAtLkaNçtlvhNcEXç";

		// WHEN
		JournalComptable actualJournalComptable = JournalComptable.getByCode(listJournalComptable, code);

		// THEN
		assertThat(actualJournalComptable).isNull();
	}

	@Test
	public void getByCode_journalComptableListeNormaleCodeNormal_returnsJournalComptable() {
		// GIVEN
		List<JournalComptable> listJournalComptable = createListJournalComptable();
		String code = "C3";

		// WHEN
		JournalComptable actualJournalComptable = JournalComptable.getByCode(listJournalComptable, code);

		// THEN
		assertThat(actualJournalComptable).isEqualTo(listJournalComptable.get(2));
	}

}
