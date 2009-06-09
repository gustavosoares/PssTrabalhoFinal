package com.pss.core.bo;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import com.pss.core.factories.AtivoRepositoryFactory;
import com.pss.core.model.Ativo;
import com.pss.core.model.repository.interfaces.AtivoRepository;



public class AtivoBO implements AtivoRepository {

	private static String PERSISTENCE_TYPE = "hibernate";
	private static AtivoBO instance = null;
	private static AtivoRepository instanceRepository = null;
	
	public static AtivoBO getInstance() {
		if (instance == null) {
			instance = new AtivoBO();
			instanceRepository = AtivoRepositoryFactory.getInstance(PERSISTENCE_TYPE);
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

	public void removerAtivoPorId(Integer id) throws SQLException, NoResultException {
		instanceRepository.removerAtivoPorId(id);
		
	}

}
