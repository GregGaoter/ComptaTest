package com.dummy.myerp.business.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.PlatformTransactionManager;

public class TransactionManagerTest {

	// ========== getInstance ==========

	@Test
	public void getInstance_returnsTransactionManager() {
		// GIVEN

		// WHEN

		// THEN
		assertThat(TransactionManager.getInstance()).isNotNull();
	}

	// ========== getInstance(PlatformTransactionManager) ==========

	@Test
	public void getInstance_platformTransactionManager_returnsTransactionManager() {
		// GIVEN
		PlatformTransactionManager platformTransactionManager = Mockito.mock(PlatformTransactionManager.class);

		// WHEN

		// THEN
		assertThat(TransactionManager.getInstance(platformTransactionManager)).isNotNull();
		assertThat(ReflectionTestUtils.getField(TransactionManager.class, "ptmMyERP"))
				.isEqualTo(platformTransactionManager);
	}

	// ========== beginTransactionMyERP ==========

	@Test
	public void beginTransactionMyERP_returnsTransactionManager() {
		// GIVEN

		// WHEN

		// THEN
		assertThat(TransactionManager.getInstance()).isNotNull();
	}

}
