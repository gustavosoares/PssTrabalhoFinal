package com.puc.pss.repository;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import com.puc.pss.model.RelacionamentoAtivo;
import com.puc.pss.repository.interfaces.IRelacionamentoAtivoRepository;
import com.puc.pss.util.persistence.GenericPersistence;

public class RelacionamentoAtivoRepositoryHibernate implements IRelacionamentoAtivoRepository {
	
	private static RelacionamentoAtivoRepositoryHibernate instance = null;
	private GenericPersistence<RelacionamentoAtivo> genericPersistence = new GenericPersistence<RelacionamentoAtivo>(RelacionamentoAtivo.class, "defaultUnit");
	
	public static RelacionamentoAtivoRepositoryHibernate getInstance() {
		if (instance == null) {
			instance = new RelacionamentoAtivoRepositoryHibernate();
		}
		return instance;
	}

	public List buscarAtivosFilhosPorAtivoPaiId(Integer ativoPaiId) throws NoResultException {
		return genericPersistence.listByQuery("findByAtivoIdPai", ativoPaiId);
	}

	public void cadastrarRelacionamento(Integer ativoPaiId, Integer ativoFilhoId) throws SQLException {
		RelacionamentoAtivo r = null;
		r = new RelacionamentoAtivo();
		r.setAtivoIdPai(ativoPaiId);
		r.setAtivoIdFilho(ativoFilhoId);
		genericPersistence.save(r);
	}

	public RelacionamentoAtivo buscarRelacionamento(Integer ativoPaiId, Integer ativoFilhoId) throws NoResultException {
		return genericPersistence.findByNamedQuery("findByRelacionamento", ativoPaiId, ativoFilhoId);
	}
	
	public void removerRelacionamentoPorAtivoPaiId(Integer ativoPaiId)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	
}
