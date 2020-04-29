package com.dummy.myerp.technical.log.message;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class EntreeMessageTest {

	@Test
	public void getFormattedMessage_entreeMessage_returnsEntree() {
		// GIVEN
		EntreeMessage entreeMessage = new EntreeMessage();

		// WHEN
		String actualString = entreeMessage.getFormattedMessage();

		// THEN
		assertThat(actualString).isEqualTo("Entrée");
	}

}
