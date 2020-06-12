package com.dummy.myerp.testbusiness.business;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Classe de test de l'initialisation du contexte Spring
 */
public class InitSpringIT extends BusinessTestCase {

	/**
	 * Constructeur.
	 */
	public InitSpringIT() {
		super();
	}

	/**
	 * Teste l'initialisation du contexte Spring
	 */
	@Test
	public void testInit() {
		SpringRegistry.init();
		assertNotNull(SpringRegistry.getBusinessProxy());
		assertNotNull(SpringRegistry.getTransactionManager());
	}
}
