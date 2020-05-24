package com.dummy.myerp.business.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.dummy.myerp.consumer.dao.contrat.DaoProxy;

public class BusinessProxyImplTest {

	// ========== getInstance(DaoProxy, TransactionManager) ==========

	@Test
	public void getInstance_DaoProxyAndTransactionManager_returnsBusinessProxyImpl() {
		// GIVEN
		DaoProxy daoProxy = Mockito.mock(DaoProxy.class);
		TransactionManager transactionManager = Mockito.mock(TransactionManager.class);

		// WHEN
		BusinessProxyImpl businessProxy = BusinessProxyImpl.getInstance(daoProxy, transactionManager);

		// THEN
		assertThat(businessProxy).isNotNull();
	}

}
