package com.dummy.myerp.business.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
	public void beginTransactionMyERP_returnsTransactionStatus() {
		// GIVEN
		PlatformTransactionManager platformTransactionManager = Mockito.mock(PlatformTransactionManager.class);
		TransactionStatus expectedTransactionStatus = Mockito.mock(TransactionStatus.class);
		when(platformTransactionManager.getTransaction(any(DefaultTransactionDefinition.class)))
				.thenReturn(expectedTransactionStatus);

		// WHEN
		TransactionStatus actualTransactionStatus = TransactionManager.getInstance(platformTransactionManager)
				.beginTransactionMyERP();

		// THEN
		assertThat(actualTransactionStatus).isEqualTo(expectedTransactionStatus);
	}

	// ========== commitMyERP(TransactionStatus) ==========

	@Test
	public void commitMyERP_transactionStatusNotNull_callsCommit() {
		// GIVEN
		PlatformTransactionManager platformTransactionManager = Mockito.mock(PlatformTransactionManager.class);
		TransactionStatus transactionStatus = Mockito.mock(TransactionStatus.class);
		when(transactionStatus.toString()).thenReturn("commitMyERP_transactionStatusNotNull_callsCommit");
		doAnswer(invocation -> {
			Object ts = invocation.getArgument(0);
			assertThat(ts.toString()).isEqualTo("commitMyERP_transactionStatusNotNull_callsCommit");
			return null;
		}).when(platformTransactionManager).commit(any(TransactionStatus.class));

		// WHEN
		platformTransactionManager.commit(transactionStatus);

		// THEN
	}

	// ========== rollbackMyERP(TransactionStatus) ==========

	@Test
	public void rollbackMyERP_transactionStatusNotNull_callsrollback() {
		// GIVEN
		PlatformTransactionManager platformTransactionManager = Mockito.mock(PlatformTransactionManager.class);
		TransactionStatus transactionStatus = Mockito.mock(TransactionStatus.class);
		when(transactionStatus.toString()).thenReturn("rollbackMyERP_transactionStatusNotNull_callsrollback");
		doAnswer(invocation -> {
			Object ts = invocation.getArgument(0);
			assertThat(ts.toString()).isEqualTo("rollbackMyERP_transactionStatusNotNull_callsrollback");
			return null;
		}).when(platformTransactionManager).rollback(any(TransactionStatus.class));

		// WHEN
		platformTransactionManager.rollback(transactionStatus);

		// THEN
	}

}
