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
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;

public class CompteComptableDaoCacheTest {

	private CompteComptableDaoCache compteComptableDaoCache;
	private static List<CompteComptable> listCompteComptable;
	private static List<CompteComptable> listCompteComptableDb;
	private DaoProxy daoProxy;
	private ComptabiliteDao comptabiliteDao;

	@BeforeEach
	public void initTest() {
		compteComptableDaoCache = new CompteComptableDaoCache();
		daoProxy = Mockito.mock(DaoProxy.class);
		comptabiliteDao = Mockito.mock(ComptabiliteDao.class);
		initListCompteComptable();
		initListCompteComptableDb();
	}

	private static void initListCompteComptable() {
		listCompteComptable = new ArrayList<CompteComptable>(3);
		listCompteComptable.add(new CompteComptable(1, "c1"));
		listCompteComptable.add(new CompteComptable(2, "c2"));
		listCompteComptable.add(new CompteComptable(3, "c3"));
	}

	private static void initListCompteComptableDb() {
		listCompteComptableDb = new ArrayList<CompteComptable>(3);
		listCompteComptableDb.add(new CompteComptable(1, "c1"));
		listCompteComptableDb.add(new CompteComptable(2, "c2"));
		listCompteComptableDb.add(new CompteComptable(3, "c3"));
	}

	@AfterEach
	public void undefTest() {
		compteComptableDaoCache = null;
		daoProxy = null;
		comptabiliteDao = null;
		listCompteComptable = null;
		listCompteComptableDb = null;
	}

	// ========== getByNumero(Integer) ==========

	private static Stream<Arguments> getArgumentsPourGetByNumero() {
		initListCompteComptable();
		return Stream.of(Arguments.of(1, listCompteComptable, listCompteComptable.get(0)),
				Arguments.of(2, new ArrayList<CompteComptable>(), null), Arguments.of(null, listCompteComptable, null),
				Arguments.of(null, new ArrayList<CompteComptable>(), null), Arguments.of(null, null, null));
	}

	@ParameterizedTest(name = "numero = {0}, listCompteComptable = {1}, expectedCompteComptable = {2}")
	@MethodSource("getArgumentsPourGetByNumero")
	public void getByNumero_numeroNormalListCompteComptableVide_returnsCompteComptable(Integer numero,
			List<CompteComptable> listCompteComptable, CompteComptable expectedCompteComptable) {
		// GIVEN
		ReflectionTestUtils.setField(compteComptableDaoCache, "listCompteComptable", listCompteComptable);
		ReflectionTestUtils.setField(ConsumerHelper.class, "daoProxy", daoProxy);
		when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
		when(comptabiliteDao.getListCompteComptable()).thenReturn(listCompteComptableDb);

		// WHEN
		CompteComptable actualCompteComptable = compteComptableDaoCache.getByNumero(numero);

		// THEN
		assertThat(actualCompteComptable).isEqualTo(expectedCompteComptable);
	}

}
