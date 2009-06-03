package com.puc.pss.repository;

import java.sql.SQLException;

import com.puc.pss.model.Ativo;
import com.puc.pss.util.persistence.GenericPersistence;

public class AtivoRepositoryHibernate implements IAtivoRepository {
	
	private static AtivoRepositoryHibernate instance = null;
	private GenericPersistence<Ativo> genericPersistence = new GenericPersistence<Ativo>(Ativo.class, "defaultUnit");
	
	public static AtivoRepositoryHibernate getInstance() {
		if (instance == null) {
			instance = new AtivoRepositoryHibernate();
		}
		return instance;
	}
	
	public Ativo buscarAtivoPorId(Integer id) {
		Ativo ativo = genericPersistence.findByNamedQuery("findById", id);
		return ativo;
	}
	
	public void cadastrarAtivo(Ativo ativo) {
		try {
			genericPersistence.save(ativo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
