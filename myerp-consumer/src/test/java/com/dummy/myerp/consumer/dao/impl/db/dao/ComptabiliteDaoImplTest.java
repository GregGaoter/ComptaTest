package com.dummy.myerp.consumer.dao.impl.db.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;

public class ComptabiliteDaoImplTest {

	@Autowired
	private ComptabiliteDaoImpl comptabiliteDaoImpl;

	@Test
	void getListCompteComptable() {
		List<CompteComptable> listeComptesComptables = comptabiliteDaoImpl.getListCompteComptable();
		assertThat(listeComptesComptables).isNotEmpty();
	}

}
