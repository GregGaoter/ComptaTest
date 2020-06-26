package com.dummy.myerp.business.helper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.time.Instant;
import java.util.Date;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DateHelperTest {

	private static Stream<Date> sourcePourParametreDate() {
		return Stream.of(Date.from(Instant.EPOCH), null);
	}

	// ==================== getJour(Date) ====================

	@ParameterizedTest
	@MethodSource("sourcePourParametreDate")
	public void getJour_returnsJour(Date date) {
		if (date != null) {
			int actualJour = DateHelper.getJour(date);
			assertThat(actualJour).isEqualTo(1);
		} else {
			assertThatNullPointerException().isThrownBy(() -> {
				DateHelper.getJour(date);
			});
		}
	}

	// ==================== getMois(Date) ====================

	@ParameterizedTest
	@MethodSource("sourcePourParametreDate")
	public void getMois_returnsMois(Date date) {
		if (date != null) {
			int actualMois = DateHelper.getMois(date);
			assertThat(actualMois).isEqualTo(1);
		} else {
			assertThatNullPointerException().isThrownBy(() -> {
				DateHelper.getMois(date);
			});
		}
	}

	// ==================== getAnnee(Date) ====================

	@ParameterizedTest
	@MethodSource("sourcePourParametreDate")
	public void getAnnee_returnsAnnee(Date date) {
		if (date != null) {
			int actualAnnee = DateHelper.getAnnee(date);
			assertThat(actualAnnee).isEqualTo(1970);
		} else {
			assertThatNullPointerException().isThrownBy(() -> {
				DateHelper.getAnnee(date);
			});
		}
	}

	// ==================== getDate(int, int, int) ====================

	private static Stream<Arguments> getDate_returnsDate() {
		return Stream.of(Arguments.of(26, 6, 2020));
	}

	@ParameterizedTest
	@MethodSource
	public void getDate_returnsDate(int jour, int mois, int annee) {
		Date date = DateHelper.getDate(jour, mois, annee);
		assertThat(DateHelper.getJour(date)).isEqualTo(jour);
		assertThat(DateHelper.getMois(date)).isEqualTo(mois);
		assertThat(DateHelper.getAnnee(date)).isEqualTo(annee);
	}

}
