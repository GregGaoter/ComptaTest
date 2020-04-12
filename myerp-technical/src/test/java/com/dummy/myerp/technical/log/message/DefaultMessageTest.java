package com.dummy.myerp.technical.log.message;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefaultMessageTest {

	private DefaultMessage defaultMessage;

	@BeforeEach
	void initDefaultMessage() {
		defaultMessage = new DefaultMessage();
	}

	@AfterEach
	void undefDefaultMessage() {
		defaultMessage = null;
	}

	@Test
	public void getFormattedMessage_DefaultMessage_returnsNull() {
		// GIVEN

		// WHEN
		String actualString = defaultMessage.getFormattedMessage();

		// THEN
		assertThat(actualString).isNull();
	}

	@Test
	public void getFormat_DefaultMessage_returnsNull() {
		// GIVEN

		// WHEN
		String actualString = defaultMessage.getFormat();

		// THEN
		assertThat(actualString).isNull();
	}

	@Test
	public void getParameters_DefaultMessage_returnsNull() {
		// GIVEN

		// WHEN
		Object[] actualString = defaultMessage.getParameters();

		// THEN
		assertThat(actualString).isNull();
	}

	@Test
	public void getThrowable_DefaultMessage_returnsNull() {
		// GIVEN

		// WHEN
		Throwable actualString = defaultMessage.getThrowable();

		// THEN
		assertThat(actualString).isNull();
	}

}
