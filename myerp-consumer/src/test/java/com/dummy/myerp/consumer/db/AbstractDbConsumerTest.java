package com.dummy.myerp.consumer.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;

@ExtendWith(MockitoExtension.class)
public class AbstractDbConsumerTest {

	@Spy
	private AbstractDbConsumer abstractDbConsumer;

	@Mock
	private DaoProxy daoProxy;
	@Mock
	private Map<DataSourcesEnum, DataSource> mapDataSource;
	@Mock
	private DataSource dataSource;
	@Mock
	private JdbcTemplate jdbcTemplate;

	// === getDaoProxy() ===

	@Test
	public void getDaoProxy_returnsDaoProxy() {
		// GIVEN
		ReflectionTestUtils.setField(ConsumerHelper.class, "daoProxy", daoProxy);

		// WHEN
		DaoProxy actualDaoProxy = AbstractDbConsumer.getDaoProxy();

		// THEN
		assertThat(actualDaoProxy).isEqualTo(daoProxy);
	}

	// === getDataSource(DataSourcesEnum) ===

	@ParameterizedTest
	@EnumSource(DataSourcesEnum.class)
	@NullSource
	public void getDataSource_returnsDataSource(DataSourcesEnum dataSourcesEnum) {
		// GIVEN
		ReflectionTestUtils.setField(AbstractDbConsumer.class, "mapDataSource", mapDataSource);
		lenient().when(mapDataSource.get(any(DataSourcesEnum.class))).thenReturn(dataSource);

		// WHEN
		DataSource actualDataSource = abstractDbConsumer.getDataSource(dataSourcesEnum);

		// THEN
		if (dataSourcesEnum != null) {
			assertThat(actualDataSource).isEqualTo(dataSource);
		} else {
			assertThat(actualDataSource).isNull();
		}
	}

	@ParameterizedTest
	@EnumSource(DataSourcesEnum.class)
	public void getDataSource_throwsUnsatisfiedLinkError(DataSourcesEnum dataSourcesEnum) {
		// GIVEN
		ReflectionTestUtils.setField(AbstractDbConsumer.class, "mapDataSource", mapDataSource);
		when(mapDataSource.get(any(DataSourcesEnum.class))).thenReturn(null);

		// WHEN
		Throwable unsatisfiedLinkError = assertThrows(UnsatisfiedLinkError.class, () -> {
			abstractDbConsumer.getDataSource(dataSourcesEnum);
		});

		// THEN
		assertThat(unsatisfiedLinkError.getMessage()).startsWith("La DataSource suivante n'a pas été initialisée : ");
	}

	// === queryGetSequenceValuePostgreSQL(DataSourcesEnum, String, Class<T>) ===

	private static Stream<Arguments> queryGetSequenceValuePostgreSQL_integerType_returnsInteger() {
		DataSourcesEnum dataSourcesEnum = DataSourcesEnum.MYERP;
		Integer excpectedSequence = 1;
		return Stream.of(Arguments.of(dataSourcesEnum, "  ", null), Arguments.of(dataSourcesEnum,
				"xâkànrCoîVdBScxrËÀaïçÛoYùFmvAÊÎOâÀÔÿQÀÙÜHXDaRWfMSéÙyPÔÊbNÈvCDÂYïîÏnuMüCcépoqçpÊnIYgËeÎzqzXiçôzâëZtUcutFbWàuAidiùÿaPàdVUxvxÛqHgfeôVZHlvGKÊYfZLÂflCRifRôbèBÔsSÉWgChRÜAFLMÂtcJÙQIbÏÀiëWiVYNûàEüÀbéwDneTkrÙUÛêÎÿGàÈjDèëêÔtcYvSnûkÉékCzvZÉXdÛêîÿSgOjlBûâBêHbaéénÎËDPsJTdûOYslÏIFéoËLiquÛÉÊGySpïImuKüÊpbÇFrÎqCHdÊûTÀNERÀQùUÿOwîvkVxPcëoïjehÔSôUîHÜKÏLuëÉuuegcPQÛîÙMÀXzMÙVülÔRââOeôÛZYlDçÂçÊIùSüTKÊMïPËÈàikuJdroçwgèDqgÉvÎuvêèTàfÏGnsQëÛùeÉmBBühnÏzxQpÛÜfNhfUîÎsàreRcqGÇâéËvHÎUvÔXVZÀNfyéÊPrMZÂgaKwhBÇOûTEeÜûzêyylÏéFéHjËüXwüOpWugèËèVôwâÛWNBjKPaÛFIJÂÿNÎyWTGlJlmtDVÉxÿcESçxPTÀôgÔÂYfKaÙngpyÜÜùÇBïzNâpHJùÇXLGUSJjëQVIqLzlÔÜÔjËFkXbutxÈhUAÉqZsïXÙwÂjëkhûÈWÇEHutVçmésaÂîBbgsüwUyZÊÿËmzÜëÈPbgêAMLiDDeIÙPôïPCÏîVsiQÀAIhÔOyZcUÿSübjêîTÉÏÇÀêHEûçtïàèFAjrmStZjGÙGGüQoàÈbÈûehGèOYèïYMÈFnmüRkyRQJpsEÈÔLWJIÏùYôowcBëRxCXôàAGoÏÿmiEMôNûDXFaçiÇômÇîGplEJUrxEÙÇdOrKÎWKèBwÿàIÂÉfyÈmÊNëÇkLsÎYHCyTaÉTÿÉnrFîcOèâïFÂKDÿéCJëISèpâQAKêAÎRdMzûZËNHêBhünçÇOzùdôEîjÊêqtÀïhïÏËZsËxÏÈToLeLayrÈorbiWIWùÛùèRÛRqwLùwokTQqkqnVhJmlCKJQmÜpKeâDfëAÜAâÙjsOfvÂÙXMxWÎowPdtpEMÇàdÜûÂUÔzùCdêÜaAtLkaNçtlvhNcEXç",
				excpectedSequence), Arguments.of(null, " ", null), Arguments.of(null, "\n", null),
				Arguments.of(dataSourcesEnum, "compétitive", excpectedSequence),
				Arguments.of(dataSourcesEnum, "\t", null), Arguments.of(null, null, null),
				Arguments.of(null, "", null));
	}

	@ParameterizedTest
	@MethodSource
	public void queryGetSequenceValuePostgreSQL_integerType_returnsInteger(DataSourcesEnum dataSourcesEnum,
			String seqName, Integer excpectedSequence) {
		// GIVEN
		ReflectionTestUtils.setField(abstractDbConsumer, "jdbcTemplate", jdbcTemplate);
		lenient().doNothing().when(abstractDbConsumer).createJdbcTemplate(any(DataSourcesEnum.class));
		lenient().when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class))).thenReturn(1);

		// WHEN
		Integer actualSequence = abstractDbConsumer.queryGetSequenceValuePostgreSQL(dataSourcesEnum, seqName,
				Integer.class);

		// THEN
		assertThat(actualSequence).isEqualTo(excpectedSequence);
	}

	// === getSequenceValue(DataSourcesEnum, String) ===

	private static Stream<Arguments> getSequenceValue_callsQueryGetSequenceValuePostgreSQL() {
		DataSourcesEnum dataSourcesEnum = DataSourcesEnum.MYERP;
		return Stream.of(Arguments.of(dataSourcesEnum, "\t"), Arguments.of(dataSourcesEnum, "\n"),
				Arguments.of(dataSourcesEnum, "calvaires"), Arguments.of(dataSourcesEnum, ""));
	}

	@ParameterizedTest
	@MethodSource
	public void getSequenceValue_callsQueryGetSequenceValuePostgreSQL(DataSourcesEnum dataSourcesEnum, String seqName) {
		// GIVEN
		doReturn(1).when(abstractDbConsumer).queryGetSequenceValuePostgreSQL(any(DataSourcesEnum.class), anyString(),
				eq(Integer.class));

		// WHEN
		Integer actualSequenceValue = abstractDbConsumer.getSequenceValue(dataSourcesEnum, seqName);

		// THEN
		assertThat(actualSequenceValue).isEqualTo(1);
	}

}
