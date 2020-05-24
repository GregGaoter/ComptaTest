package com.dummy.myerp.business.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.dummy.myerp.business.contrat.BusinessProxy;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;

public class AbstractBusinessManagerTest {

	// ==================== configure ====================

	@Disabled
	@Test
	public void configure_businessProxyAndDaoProxyAndTransactionManager_setsBusinessProxyAndDaoProxyAndTransactionManager() {
		// GIVEN
		BusinessProxy businessProxy = Mockito.mock(BusinessProxy.class);
		DaoProxy daoProxy = Mockito.mock(DaoProxy.class);
		TransactionManager transactionManager = Mockito.mock(TransactionManager.class);

		// WHEN
		AbstractBusinessManager.configure(businessProxy, daoProxy, transactionManager);

		// THEN
		assertThat(ReflectionTestUtils.invokeGetterMethod(null, "")).isEqualTo(1970);
	}

}
