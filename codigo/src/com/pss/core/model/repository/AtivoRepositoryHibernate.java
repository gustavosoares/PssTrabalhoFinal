package com.pss.core.model.repository;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;

import com.pss.core.model.Ativo;
import com.pss.core.model.repository.interfaces.AtivoRepository;
import com.pss.core.util.GenericPersistence;

public class AtivoRepositoryHibernate implements AtivoRepository {
	
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

	public void editarAtivo(Ativo ativo) throws SQLException {
		genericPersistence.update(ativo);
	}

	public int obterTotaldeAtivosPorTipo(int tipo) {
		int count = 0;
		Iterator itAtivos = listarAtivos().iterator();
		while (itAtivos.hasNext()) {
			Ativo ativo = (Ativo) itAtivos.next();
			if (ativo.getTipo().intValue() == tipo) {
				count++;
			}
		}
		return count;
	}
}
