package com.dummy.myerp.consumer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.dummy.myerp.consumer.dao.contrat.DaoProxy;

@ExtendWith(MockitoExtension.class)
public class ConsumerHelperTest {

	@Mock
	private DaoProxy daoProxy;

	// === configure(DaoProxy) ===

	@Test
	public void configure_daoProxy_setsDaoProxy() {
		// GIVEN

		// WHEN
		ConsumerHelper.configure(daoProxy);
		DaoProxy actualDaoProxy = (DaoProxy) ReflectionTestUtils.getField(ConsumerHelper.class, "daoProxy");

		// THEN
		assertThat(actualDaoProxy).isEqualTo(daoProxy);
	}

}
