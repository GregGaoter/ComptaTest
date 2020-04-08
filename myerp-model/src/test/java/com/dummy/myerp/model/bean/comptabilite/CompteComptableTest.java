package com.dummy.myerp.model.bean.comptabilite;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompteComptableTest {

	private CompteComptable compteComptable;

	@BeforeEach
	void initCompteComptable() {
		compteComptable = new CompteComptable();
	}

	@AfterEach
	void undefCompteComptable() {
		compteComptable = null;
	}

	// ==================== toString ====================

	@Test
	void toString_compteComptableNumeroMinValue_returnsCompteComptableString() {
		// GIVEN
		compteComptable.setNumero(-2147483648);// Integer.MIN_VALUE
		compteComptable.setLibelle("Libellé");

		// WHEN
		String actualString = compteComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("CompteComptable{numero=-2147483648, libelle='Libellé'}");
	}

	@Test
	void toString_compteComptableNumeroMaxValue_returnsCompteComptableString() {
		// GIVEN
		compteComptable.setNumero(2147483647);// Integer.MAX_VALUE
		compteComptable.setLibelle("Libellé");

		// WHEN
		String actualString = compteComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("CompteComptable{numero=2147483647, libelle='Libellé'}");
	}

	@Test
	void toString_compteComptableNumeroMinValueMoitie_returnsCompteComptableString() {
		// GIVEN
		compteComptable.setNumero(-1073741824);// Integer.MIN_VALUE/2
		compteComptable.setLibelle("Libellé");

		// WHEN
		String actualString = compteComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("CompteComptable{numero=-1073741824, libelle='Libellé'}");
	}

	@Test
	void toString_compteComptableNumeroMaxValueMoitie_returnsCompteComptableString() {
		// GIVEN
		compteComptable.setNumero(1073741824);// Integer.MIN_VALUE/2
		compteComptable.setLibelle("Libellé");

		// WHEN
		String actualString = compteComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("CompteComptable{numero=1073741824, libelle='Libellé'}");
	}

	@Test
	void toString_compteComptableNumeroZero_returnsCompteComptableString() {
		// GIVEN
		compteComptable.setNumero(0);
		compteComptable.setLibelle("Libellé");

		// WHEN
		String actualString = compteComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("CompteComptable{numero=0, libelle='Libellé'}");
	}

	@Test
	void toString_compteComptableNumeroNull_returnsCompteComptableString() {
		// GIVEN
		compteComptable.setNumero(null);
		compteComptable.setLibelle("Libellé");

		// WHEN
		String actualString = compteComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("CompteComptable{numero=null, libelle='Libellé'}");
	}

	@Test
	void toString_compteComptableLibelleNull_returnsCompteComptableString() {
		// GIVEN
		compteComptable.setNumero(1);
		compteComptable.setLibelle(null);

		// WHEN
		String actualString = compteComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("CompteComptable{numero=1, libelle='null'}");
	}

	@Test
	void toString_compteComptableLibelleVide_returnsCompteComptableString() {
		// GIVEN
		compteComptable.setNumero(1);
		compteComptable.setLibelle("");

		// WHEN
		String actualString = compteComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("CompteComptable{numero=1, libelle=''}");
	}

	@Test
	void toString_compteComptableLibelleEspace_returnsCompteComptableString() {
		// GIVEN
		compteComptable.setNumero(1);
		compteComptable.setLibelle(" ");

		// WHEN
		String actualString = compteComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("CompteComptable{numero=1, libelle=' '}");
	}

	@Test
	void toString_compteComptableLibelleRetourLigne_returnsCompteComptableString() {
		// GIVEN
		compteComptable.setNumero(1);
		compteComptable.setLibelle("\n");

		// WHEN
		String actualString = compteComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("CompteComptable{numero=1, libelle='\n'}");
	}

	@Test
	void toString_compteComptableLibelleTab_returnsCompteComptableString() {
		// GIVEN
		compteComptable.setNumero(1);
		compteComptable.setLibelle("\t");

		// WHEN
		String actualString = compteComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo("CompteComptable{numero=1, libelle='\t'}");
	}

	@Test
	void toString_compteComptableLibelleLong_returnsCompteComptableString() {
		// GIVEN
		compteComptable.setNumero(1);
		compteComptable.setLibelle(
				"xâkànrCoîVdBScxrËÀaïçÛoYùFmvAÊÎOâÀÔÿQÀÙÜHXDaRWfMSéÙyPÔÊbNÈvCDÂYïîÏnuMüCcépoqçpÊnIYgËeÎzqzXiçôzâëZtUcutFbWàuAidiùÿaPàdVUxvxÛqHgfeôVZHlvGKÊYfZLÂflCRifRôbèBÔsSÉWgChRÜAFLMÂtcJÙQIbÏÀiëWiVYNûàEüÀbéwDneTkrÙUÛêÎÿGàÈjDèëêÔtcYvSnûkÉékCzvZÉXdÛêîÿSgOjlBûâBêHbaéénÎËDPsJTdûOYslÏIFéoËLiquÛÉÊGySpïImuKüÊpbÇFrÎqCHdÊûTÀNERÀQùUÿOwîvkVxPcëoïjehÔSôUîHÜKÏLuëÉuuegcPQÛîÙMÀXzMÙVülÔRââOeôÛZYlDçÂçÊIùSüTKÊMïPËÈàikuJdroçwgèDqgÉvÎuvêèTàfÏGnsQëÛùeÉmBBühnÏzxQpÛÜfNhfUîÎsàreRcqGÇâéËvHÎUvÔXVZÀNfyéÊPrMZÂgaKwhBÇOûTEeÜûzêyylÏéFéHjËüXwüOpWugèËèVôwâÛWNBjKPaÛFIJÂÿNÎyWTGlJlmtDVÉxÿcESçxPTÀôgÔÂYfKaÙngpyÜÜùÇBïzNâpHJùÇXLGUSJjëQVIqLzlÔÜÔjËFkXbutxÈhUAÉqZsïXÙwÂjëkhûÈWÇEHutVçmésaÂîBbgsüwUyZÊÿËmzÜëÈPbgêAMLiDDeIÙPôïPCÏîVsiQÀAIhÔOyZcUÿSübjêîTÉÏÇÀêHEûçtïàèFAjrmStZjGÙGGüQoàÈbÈûehGèOYèïYMÈFnmüRkyRQJpsEÈÔLWJIÏùYôowcBëRxCXôàAGoÏÿmiEMôNûDXFaçiÇômÇîGplEJUrxEÙÇdOrKÎWKèBwÿàIÂÉfyÈmÊNëÇkLsÎYHCyTaÉTÿÉnrFîcOèâïFÂKDÿéCJëISèpâQAKêAÎRdMzûZËNHêBhünçÇOzùdôEîjÊêqtÀïhïÏËZsËxÏÈToLeLayrÈorbiWIWùÛùèRÛRqwLùwokTQqkqnVhJmlCKJQmÜpKeâDfëAÜAâÙjsOfvÂÙXMxWÎowPdtpEMÇàdÜûÂUÔzùCdêÜaAtLkaNçtlvhNcEXç");

		// WHEN
		String actualString = compteComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo(
				"CompteComptable{numero=1, libelle='xâkànrCoîVdBScxrËÀaïçÛoYùFmvAÊÎOâÀÔÿQÀÙÜHXDaRWfMSéÙyPÔÊbNÈvCDÂYïîÏnuMüCcépoqçpÊnIYgËeÎzqzXiçôzâëZtUcutFbWàuAidiùÿaPàdVUxvxÛqHgfeôVZHlvGKÊYfZLÂflCRifRôbèBÔsSÉWgChRÜAFLMÂtcJÙQIbÏÀiëWiVYNûàEüÀbéwDneTkrÙUÛêÎÿGàÈjDèëêÔtcYvSnûkÉékCzvZÉXdÛêîÿSgOjlBûâBêHbaéénÎËDPsJTdûOYslÏIFéoËLiquÛÉÊGySpïImuKüÊpbÇFrÎqCHdÊûTÀNERÀQùUÿOwîvkVxPcëoïjehÔSôUîHÜKÏLuëÉuuegcPQÛîÙMÀXzMÙVülÔRââOeôÛZYlDçÂçÊIùSüTKÊMïPËÈàikuJdroçwgèDqgÉvÎuvêèTàfÏGnsQëÛùeÉmBBühnÏzxQpÛÜfNhfUîÎsàreRcqGÇâéËvHÎUvÔXVZÀNfyéÊPrMZÂgaKwhBÇOûTEeÜûzêyylÏéFéHjËüXwüOpWugèËèVôwâÛWNBjKPaÛFIJÂÿNÎyWTGlJlmtDVÉxÿcESçxPTÀôgÔÂYfKaÙngpyÜÜùÇBïzNâpHJùÇXLGUSJjëQVIqLzlÔÜÔjËFkXbutxÈhUAÉqZsïXÙwÂjëkhûÈWÇEHutVçmésaÂîBbgsüwUyZÊÿËmzÜëÈPbgêAMLiDDeIÙPôïPCÏîVsiQÀAIhÔOyZcUÿSübjêîTÉÏÇÀêHEûçtïàèFAjrmStZjGÙGGüQoàÈbÈûehGèOYèïYMÈFnmüRkyRQJpsEÈÔLWJIÏùYôowcBëRxCXôàAGoÏÿmiEMôNûDXFaçiÇômÇîGplEJUrxEÙÇdOrKÎWKèBwÿàIÂÉfyÈmÊNëÇkLsÎYHCyTaÉTÿÉnrFîcOèâïFÂKDÿéCJëISèpâQAKêAÎRdMzûZËNHêBhünçÇOzùdôEîjÊêqtÀïhïÏËZsËxÏÈToLeLayrÈorbiWIWùÛùèRÛRqwLùwokTQqkqnVhJmlCKJQmÜpKeâDfëAÜAâÙjsOfvÂÙXMxWÎowPdtpEMÇàdÜûÂUÔzùCdêÜaAtLkaNçtlvhNcEXç'}");
	}

	// ==================== getByNumero ====================

	@Test
	void getByNumero_compteComptableNumeroPresent_returnsCompteComptable() {
		// GIVEN
		List<CompteComptable> listCompteComptables = new ArrayList<>(5);
		listCompteComptables.add(new CompteComptable(1, "c1"));
		listCompteComptables.add(new CompteComptable(2, "c2"));
		listCompteComptables.add(new CompteComptable(3, "c3"));
		listCompteComptables.add(new CompteComptable(4, "c4"));
		listCompteComptables.add(new CompteComptable(5, "c5"));
		Integer numero = 3;

		// WHEN
		CompteComptable actualCompteComptable = CompteComptable.getByNumero(listCompteComptables, numero);

		// THEN
		assertThat(actualCompteComptable).isEqualTo(listCompteComptables.get(2));
	}

	@Test
	void getByNumero_compteComptableNumeroNonPresent_returnsNull() {
		// GIVEN
		List<CompteComptable> listCompteComptables = new ArrayList<>(5);
		listCompteComptables.add(new CompteComptable(1, "c1"));
		listCompteComptables.add(new CompteComptable(2, "c2"));
		listCompteComptables.add(new CompteComptable(3, "c3"));
		listCompteComptables.add(new CompteComptable(4, "c4"));
		listCompteComptables.add(new CompteComptable(5, "c5"));
		Integer numero = 6;

		// WHEN
		CompteComptable actualCompteComptable = CompteComptable.getByNumero(listCompteComptables, numero);

		// THEN
		assertThat(actualCompteComptable).isNull();
	}

	@Test
	void getByNumero_compteComptableNumeroNull_returnsNull() {
		// GIVEN
		List<CompteComptable> listCompteComptables = new ArrayList<>(5);
		listCompteComptables.add(new CompteComptable(1, "c1"));
		listCompteComptables.add(new CompteComptable(2, "c2"));
		listCompteComptables.add(new CompteComptable(3, "c3"));
		listCompteComptables.add(new CompteComptable(4, "c4"));
		listCompteComptables.add(new CompteComptable(5, "c5"));
		Integer numero = null;

		// WHEN
		CompteComptable actualCompteComptable = CompteComptable.getByNumero(listCompteComptables, numero);

		// THEN
		assertThat(actualCompteComptable).isNull();
	}

	@Test
	void getByNumero_compteComptableListVide_returnsNull() {
		// GIVEN
		List<CompteComptable> listCompteComptables = new ArrayList<>();
		Integer numero = 1;

		// WHEN
		CompteComptable actualCompteComptable = CompteComptable.getByNumero(listCompteComptables, numero);

		// THEN
		assertThat(actualCompteComptable).isNull();
	}

	@Test
	void getByNumero_compteComptableListNull_returnsNull() {
		// GIVEN
		Integer numero = 1;

		// WHEN
		CompteComptable actualCompteComptable = CompteComptable.getByNumero(null, numero);

		// THEN
		assertThat(actualCompteComptable).isNull();
	}

}
