package com.dummy.myerp.technical.log.message;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SortieMessageTest {

	@Test
	public void getFormattedMessage_entreeMessage_returnsEntree() {
		// GIVEN
		SortieMessage sortieMessage = new SortieMessage();

		// WHEN
		String actualString = sortieMessage.getFormattedMessage();

		// THEN
		assertThat(actualString).isEqualTo("Sortie");
	}

}
