package com.pss.core.bo;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import com.pss.core.model.Ativo;
import com.pss.core.model.repository.AtivoRepositoryHibernate;
import com.pss.core.model.repository.interfaces.AtivoRepository;



public class AtivoBO implements AtivoRepository {

	private static AtivoBO instance = null;
	private static AtivoRepositoryHibernate instanceRepository = null;
	
	public static AtivoBO getInstance() {
		if (instance == null) {
			instance = new AtivoBO();
			instanceRepository = AtivoRepositoryHibernate.getInstance();
		}		
		return instance;
	}
	
	public Ativo buscarAtivoPorId(Integer id) throws NoResultException {
		return instanceRepository.buscarAtivoPorId(id);
	}

	public void cadastrarAtivo(Ativo ativo) throws SQLException {
		instanceRepository.cadastrarAtivo(ativo);
	}

	public List listarAtivos() {
		return instanceRepository.listarAtivos();
	}

	public void removerAtivoPorId(Integer id) throws SQLException {
		instanceRepository.removerAtivoPorId(id);
		
	}

}
