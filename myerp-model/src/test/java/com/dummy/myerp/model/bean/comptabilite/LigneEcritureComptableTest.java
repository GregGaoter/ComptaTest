package com.dummy.myerp.model.bean.comptabilite;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class LigneEcritureComptableTest {

	@Mock
	private CompteComptable compteComptable;

	// ==================== toString ====================

	@Test
	public void toString_ligneEcritureComptableCompteComptableNullLibelleNullDebitDemiMinCreditNormalPositif_returnsStringOfLigneEcritureComptable() {
		// GIVEN
		LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable(null, null,
				BigDecimal.valueOf(Long.MIN_VALUE / 2), BigDecimal.valueOf(20050, 2));

		// WHEN
		String actualString = ligneEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo(
				"LigneEcritureComptable{compteComptable=null, libelle='null', debit=-4611686018427387904, credit=200.50}");
	}

	@Test
	public void toString_ligneEcritureComptableCompteComptableNormalLibelleLongDebitNormalCreditNull_returnsStringOfLigneEcritureComptable() {
		// GIVEN
		LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable(new CompteComptable(411, "Clients"),
				"xâkànrCoîVdBScxrËÀaïçÛoYùFmvAÊÎOâÀÔÿQÀÙÜHXDaRWfMSéÙyPÔÊbNÈvCDÂYïîÏnuMüCcépoqçpÊnIYgËeÎzqzXiçôzâëZtUcutFbWàuAidiùÿaPàdVUxvxÛqHgfeôVZHlvGKÊYfZLÂflCRifRôbèBÔsSÉWgChRÜAFLMÂtcJÙQIbÏÀiëWiVYNûàEüÀbéwDneTkrÙUÛêÎÿGàÈjDèëêÔtcYvSnûkÉékCzvZÉXdÛêîÿSgOjlBûâBêHbaéénÎËDPsJTdûOYslÏIFéoËLiquÛÉÊGySpïImuKüÊpbÇFrÎqCHdÊûTÀNERÀQùUÿOwîvkVxPcëoïjehÔSôUîHÜKÏLuëÉuuegcPQÛîÙMÀXzMÙVülÔRââOeôÛZYlDçÂçÊIùSüTKÊMïPËÈàikuJdroçwgèDqgÉvÎuvêèTàfÏGnsQëÛùeÉmBBühnÏzxQpÛÜfNhfUîÎsàreRcqGÇâéËvHÎUvÔXVZÀNfyéÊPrMZÂgaKwhBÇOûTEeÜûzêyylÏéFéHjËüXwüOpWugèËèVôwâÛWNBjKPaÛFIJÂÿNÎyWTGlJlmtDVÉxÿcESçxPTÀôgÔÂYfKaÙngpyÜÜùÇBïzNâpHJùÇXLGUSJjëQVIqLzlÔÜÔjËFkXbutxÈhUAÉqZsïXÙwÂjëkhûÈWÇEHutVçmésaÂîBbgsüwUyZÊÿËmzÜëÈPbgêAMLiDDeIÙPôïPCÏîVsiQÀAIhÔOyZcUÿSübjêîTÉÏÇÀêHEûçtïàèFAjrmStZjGÙGGüQoàÈbÈûehGèOYèïYMÈFnmüRkyRQJpsEÈÔLWJIÏùYôowcBëRxCXôàAGoÏÿmiEMôNûDXFaçiÇômÇîGplEJUrxEÙÇdOrKÎWKèBwÿàIÂÉfyÈmÊNëÇkLsÎYHCyTaÉTÿÉnrFîcOèâïFÂKDÿéCJëISèpâQAKêAÎRdMzûZËNHêBhünçÇOzùdôEîjÊêqtÀïhïÏËZsËxÏÈToLeLayrÈorbiWIWùÛùèRÛRqwLùwokTQqkqnVhJmlCKJQmÜpKeâDfëAÜAâÙjsOfvÂÙXMxWÎowPdtpEMÇàdÜûÂUÔzùCdêÜaAtLkaNçtlvhNcEXç",
				BigDecimal.valueOf(20050, 2), null);

		// WHEN
		String actualString = ligneEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo(
				"LigneEcritureComptable{compteComptable=CompteComptable{numero=411, libelle='Clients'}, libelle='xâkànrCoîVdBScxrËÀaïçÛoYùFmvAÊÎOâÀÔÿQÀÙÜHXDaRWfMSéÙyPÔÊbNÈvCDÂYïîÏnuMüCcépoqçpÊnIYgËeÎzqzXiçôzâëZtUcutFbWàuAidiùÿaPàdVUxvxÛqHgfeôVZHlvGKÊYfZLÂflCRifRôbèBÔsSÉWgChRÜAFLMÂtcJÙQIbÏÀiëWiVYNûàEüÀbéwDneTkrÙUÛêÎÿGàÈjDèëêÔtcYvSnûkÉékCzvZÉXdÛêîÿSgOjlBûâBêHbaéénÎËDPsJTdûOYslÏIFéoËLiquÛÉÊGySpïImuKüÊpbÇFrÎqCHdÊûTÀNERÀQùUÿOwîvkVxPcëoïjehÔSôUîHÜKÏLuëÉuuegcPQÛîÙMÀXzMÙVülÔRââOeôÛZYlDçÂçÊIùSüTKÊMïPËÈàikuJdroçwgèDqgÉvÎuvêèTàfÏGnsQëÛùeÉmBBühnÏzxQpÛÜfNhfUîÎsàreRcqGÇâéËvHÎUvÔXVZÀNfyéÊPrMZÂgaKwhBÇOûTEeÜûzêyylÏéFéHjËüXwüOpWugèËèVôwâÛWNBjKPaÛFIJÂÿNÎyWTGlJlmtDVÉxÿcESçxPTÀôgÔÂYfKaÙngpyÜÜùÇBïzNâpHJùÇXLGUSJjëQVIqLzlÔÜÔjËFkXbutxÈhUAÉqZsïXÙwÂjëkhûÈWÇEHutVçmésaÂîBbgsüwUyZÊÿËmzÜëÈPbgêAMLiDDeIÙPôïPCÏîVsiQÀAIhÔOyZcUÿSübjêîTÉÏÇÀêHEûçtïàèFAjrmStZjGÙGGüQoàÈbÈûehGèOYèïYMÈFnmüRkyRQJpsEÈÔLWJIÏùYôowcBëRxCXôàAGoÏÿmiEMôNûDXFaçiÇômÇîGplEJUrxEÙÇdOrKÎWKèBwÿàIÂÉfyÈmÊNëÇkLsÎYHCyTaÉTÿÉnrFîcOèâïFÂKDÿéCJëISèpâQAKêAÎRdMzûZËNHêBhünçÇOzùdôEîjÊêqtÀïhïÏËZsËxÏÈToLeLayrÈorbiWIWùÛùèRÛRqwLùwokTQqkqnVhJmlCKJQmÜpKeâDfëAÜAâÙjsOfvÂÙXMxWÎowPdtpEMÇàdÜûÂUÔzùCdêÜaAtLkaNçtlvhNcEXç', debit=200.50, credit=null}");
	}

	@Test
	public void toString_ligneEcritureComptableCompteComptableNullLibelleTabDebitMaxCreditMax_returnsStringOfLigneEcritureComptable() {
		// GIVEN
		LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable(null, "\t",
				BigDecimal.valueOf(Long.MAX_VALUE), BigDecimal.valueOf(Long.MAX_VALUE));

		// WHEN
		String actualString = ligneEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo(
				"LigneEcritureComptable{compteComptable=null, libelle='\t', debit=9223372036854775807, credit=9223372036854775807}");
	}

	@Test
	public void toString_ligneEcritureComptableCompteComptableNullLibelleNormalDebitNormalNégatifCreditDemiMax_returnsStringOfLigneEcritureComptable() {
		// GIVEN
		LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable(null, "Développement tests",
				BigDecimal.valueOf(-20050, 2), BigDecimal.valueOf(Long.MAX_VALUE / 2));

		// WHEN
		String actualString = ligneEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo(
				"LigneEcritureComptable{compteComptable=null, libelle='Développement tests', debit=-200.50, credit=4611686018427387903}");
	}

	@Test
	public void toString_ligneEcritureComptableCompteComptableNullLibelle2EspacesDebitNullNégatifCreditDemiMin_returnsStringOfLigneEcritureComptable() {
		// GIVEN
		LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable(null, "  ", null,
				BigDecimal.valueOf(Long.MIN_VALUE / 2));

		// WHEN
		String actualString = ligneEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo(
				"LigneEcritureComptable{compteComptable=null, libelle='  ', debit=null, credit=-4611686018427387904}");
	}

	@Test
	public void toString_ligneEcritureComptableCompteComptableNormalLibelleVideDebitMinNégatifCreditNormalNégatif_returnsStringOfLigneEcritureComptable() {
		// GIVEN
		LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable(new CompteComptable(411, "Clients"),
				"", BigDecimal.valueOf(Long.MIN_VALUE), BigDecimal.valueOf(-20050, 2));

		// WHEN
		String actualString = ligneEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo(
				"LigneEcritureComptable{compteComptable=CompteComptable{numero=411, libelle='Clients'}, libelle='', debit=-9223372036854775808, credit=-200.50}");
	}

	@Test
	public void toString_ligneEcritureComptableCompteComptableNormalLibelleRetourLigneDebit0Credit0_returnsStringOfLigneEcritureComptable() {
		// GIVEN
		LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable(new CompteComptable(411, "Clients"),
				"\n", BigDecimal.ZERO, BigDecimal.ZERO);

		// WHEN
		String actualString = ligneEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo(
				"LigneEcritureComptable{compteComptable=CompteComptable{numero=411, libelle='Clients'}, libelle='\n', debit=0, credit=0}");
	}

	@Test
	public void toString_ligneEcritureComptableCompteComptableNormalLibelle1EspaceDebitDemiMaxCreditMin_returnsStringOfLigneEcritureComptable() {
		// GIVEN
		LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable(new CompteComptable(411, "Clients"),
				" ", BigDecimal.valueOf(Long.MAX_VALUE / 2), BigDecimal.valueOf(Long.MIN_VALUE));

		// WHEN
		String actualString = ligneEcritureComptable.toString();

		// THEN
		assertThat(actualString).isEqualTo(
				"LigneEcritureComptable{compteComptable=CompteComptable{numero=411, libelle='Clients'}, libelle=' ', debit=4611686018427387903, credit=-9223372036854775808}");
	}

}
