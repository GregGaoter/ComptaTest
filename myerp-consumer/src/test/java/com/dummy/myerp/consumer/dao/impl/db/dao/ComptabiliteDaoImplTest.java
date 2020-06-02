package com.dummy.myerp.consumer.dao.impl.db.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.CompteComptableRM;
import com.dummy.myerp.consumer.db.DataSourcesEnum;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;

@ExtendWith(MockitoExtension.class)
public class ComptabiliteDaoImplTest {

	@Spy
	private ComptabiliteDaoImpl comptabiliteDaoImpl;
	@Mock
	private JdbcTemplate jdbcTemplate;
	@Mock
	private CompteComptableRM compteComptableRM;

	// === getListCompteComptable() ===

	@Test
	public void getListCompteComptable_returnsListCompteComptable() {
		// GIVEN
		List<CompteComptable> expectedListCompteComptable = new ArrayList<CompteComptable>();
		ReflectionTestUtils.setField(comptabiliteDaoImpl, "jdbcTemplate", jdbcTemplate);
		ReflectionTestUtils.setField(comptabiliteDaoImpl, "compteComptableRM", compteComptableRM);
		ReflectionTestUtils.setField(ComptabiliteDaoImpl.class, "SQLgetListCompteComptable", "");
		doNothing().when(comptabiliteDaoImpl).initJdbcTemplate(any(DataSourcesEnum.class));
		doNothing().when(comptabiliteDaoImpl).initCompteComptableRM();
		when(jdbcTemplate.query(anyString(), any(CompteComptableRM.class))).thenReturn(expectedListCompteComptable);

		// WHEN
		List<CompteComptable> actualListCompteComptable = comptabiliteDaoImpl.getListCompteComptable();

		// THEN
		assertThat(actualListCompteComptable).isEqualTo(expectedListCompteComptable);
	}

}
