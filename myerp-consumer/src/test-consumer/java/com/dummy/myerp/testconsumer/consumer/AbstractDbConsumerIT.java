package com.dummy.myerp.testconsumer.consumer;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.dummy.myerp.consumer.db.AbstractDbConsumer;
import com.dummy.myerp.consumer.db.DataSourcesEnum;

public class AbstractDbConsumerIT extends AbstractConsumerIt {

	private AbstractDbConsumerSubClass abstractDbConsumer;

	@BeforeEach
	public void init() {
		abstractDbConsumer = new AbstractDbConsumerSubClass();
	}

	@AfterEach
	public void undef() {
		abstractDbConsumer = null;
	}

	// === createJdbcTemplate(DataSourcesEnum) ===

	@Test
	public void createJdbcTemplate_createsJdbcTemplate() {
		// GIVEN

		// WHEN
		ReflectionTestUtils.invokeMethod(abstractDbConsumer, "createJdbcTemplate", DataSourcesEnum.MYERP);
		JdbcTemplate jdbcTemplate = (JdbcTemplate) ReflectionTestUtils.getField(abstractDbConsumer, "jdbcTemplate");

		// THEN
		assertThat(jdbcTemplate).isNotNull();
	}

	// === getDataSource(DataSourcesEnum) ===

	@Test
	public void getDataSource_returnsDataSource() {
		// GIVEN

		// WHEN
		DataSource dataSource = ReflectionTestUtils.invokeMethod(abstractDbConsumer, "getDataSource",
				DataSourcesEnum.MYERP);

		// THEN
		assertThat(dataSource).isNotNull();
	}

	// === getSequenceValue(DataSourcesEnum, String) ===

	@Test
	public void getSequenceValue_returnsSequenceValue() {
		dockerEnvironment.start();
		try {
			// GIVEN

			// WHEN
			Integer sequenceValue = abstractDbConsumer.getSequenceValue(DataSourcesEnum.MYERP,
					"myerp.ecriture_comptable_id_seq");

			// THEN
			assertThat(sequenceValue).isEqualTo(1);
		} finally {
			dockerEnvironment.stop();
		}
	}

	// === configure(Map<DataSourcesEnum, DataSource>) ===

	@Test
	public void configure_configuresMapDataSource() {
		// GIVEN

		// WHEN

		// THEN
		assertThat(ReflectionTestUtils.getField(AbstractDbConsumer.class, "mapDataSource")).isNotNull();
	}

}
