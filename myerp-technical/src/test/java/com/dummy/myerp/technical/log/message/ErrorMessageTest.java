package com.dummy.myerp.technical.log.message;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ErrorMessageTest {

	@Test
	public void getFormattedMessage_errorMessageThrowableNormalCauseNormal_returnsStrinfOfErrorMessage() {
		// GIVEN
		Throwable throwable = new Throwable("Message", new Throwable("Cause"));
		ErrorMessage errorMessage = new ErrorMessage(throwable);

		// WHEN
		String actualString = errorMessage.getFormattedMessage();

		// THEN
		assertThat(actualString).isEqualTo(
				String.format("Throwable levée !%s\tThrowable - Throwable : Message%s\tCause - Throwable : Cause",
						System.getProperty("line.separator"), System.getProperty("line.separator")));
	}

	@Test
	public void getFormattedMessage_errorMessageThrowableNormalCauseNull_returnsStrinfOfErrorMessage() {
		// GIVEN
		Throwable throwable = new Throwable("Message", null);
		ErrorMessage errorMessage = new ErrorMessage(throwable);

		// WHEN
		String actualString = errorMessage.getFormattedMessage();

		// THEN
		assertThat(actualString).isEqualTo(String.format("Throwable levée !%s\tThrowable - Throwable : Message",
				System.getProperty("line.separator")));
	}

}
