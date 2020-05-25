package com.dummy.myerp.business.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.dummy.myerp.consumer.dao.contrat.DaoProxy;

public class BusinessProxyImplTest {

	// ========== getInstance ==========

	@Test
	public void getInstance_daoProxyNotNull_returnsBusinessProxyImpl() {
		// GIVEN
		DaoProxy daoProxy = Mockito.mock(DaoProxy.class);
		ReflectionTestUtils.setField(BusinessProxyImpl.class, "daoProxy", daoProxy);

		// WHEN

		// THEN
		assertThat(BusinessProxyImpl.getInstance()).isNotNull();
	}

	@Test
	public void getInstance_daoProxyNull_throwsUnsatisfiedLinkError() {
		// GIVEN
		ReflectionTestUtils.setField(BusinessProxyImpl.class, "daoProxy", null);

		// WHEN
		UnsatisfiedLinkError error = assertThrows(UnsatisfiedLinkError.class, () -> {
			BusinessProxyImpl.getInstance();
		});

		// THEN
		assertThat(error.getMessage()).isEqualTo("La classe BusinessProxyImpl n'a pas été initialisée.");
	}

	// ========== getInstance(DaoProxy, TransactionManager) ==========

	@Test
	public void getInstance_DaoProxyAndTransactionManager_returnsBusinessProxyImpl() {
		// GIVEN
		DaoProxy daoProxy = Mockito.mock(DaoProxy.class);
		TransactionManager transactionManager = Mockito.mock(TransactionManager.class);

		// WHEN

		// THEN
		assertThat(BusinessProxyImpl.getInstance(daoProxy, transactionManager)).isNotNull();
	}

}
