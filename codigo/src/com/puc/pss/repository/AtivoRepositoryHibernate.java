package com.puc.pss.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.NoResultException;

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
	
	public Ativo buscarAtivoPorId(Integer id) throws NoResultException {
		Ativo ativo = genericPersistence.findByNamedQuery("findById", id);
		return ativo;
	}
	
	public void cadastrarAtivo(Ativo ativo) throws SQLException {

		genericPersistence.save(ativo);

	}
	
	public void removerAtivoPorId(Integer id) throws SQLException {

		Ativo ativo = genericPersistence.findByNamedQuery("findById", id);
		genericPersistence.remove(ativo, id);

	}
	
	public List<Ativo> listarAtivos() {
		return genericPersistence.listAll();
	}
}
