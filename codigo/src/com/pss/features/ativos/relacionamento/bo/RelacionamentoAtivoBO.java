package com.pss.features.ativos.relacionamento.bo;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import com.pss.features.ativos.relacionamento.model.RelacionamentoAtivo;
import com.pss.features.ativos.relacionamento.repository.RelacionamentoAtivoRepositoryHibernate;
import com.pss.features.ativos.relacionamento.repository.interfaces.IRelacionamentoAtivoRepository;

public class RelacionamentoAtivoBO implements IRelacionamentoAtivoRepository{

	private static RelacionamentoAtivoBO instance = null;
	private static RelacionamentoAtivoRepositoryHibernate instanceRepository = null;

	public static RelacionamentoAtivoBO getInstance() {
		if (instance == null) {
			instance = new RelacionamentoAtivoBO();
			instanceRepository = RelacionamentoAtivoRepositoryHibernate.getInstance();
		}		
		return instance;
	}
	
	public List buscarAtivosFilhosPorAtivoPaiId(Integer ativoPaiId) throws NoResultException {
		return instanceRepository.buscarAtivosFilhosPorAtivoPaiId(ativoPaiId);
	}

	public RelacionamentoAtivo buscarRelacionamento(Integer ativoPaiId, Integer ativoFilhoId) {
		RelacionamentoAtivo r = null;
		try{
			r = instanceRepository.buscarRelacionamento(ativoPaiId, ativoFilhoId);
		}catch (NoResultException e) {
			System.out.println("Relacionamento: "+ativoPaiId+" -> "+ativoFilhoId+" nao encontrado");
		}
		
		return r;
	}

	public void cadastrarRelacionamento(Integer ativoPaiId, Integer ativoFilhoId) throws SQLException {
		RelacionamentoAtivo r = buscarRelacionamento(ativoPaiId, ativoFilhoId);
		if (r == null ) {
			instanceRepository.cadastrarRelacionamento(ativoPaiId, ativoFilhoId);
		}
		
	}

	public void removerRelacionamentoPorAtivoPaiId(Integer ativoPaiId)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public RelacionamentoAtivo buscarRelacionamentoPorAtivoFilhoId(Integer id)
			throws NoResultException {
		// TODO Auto-generated method stub
		return null;
	}

	public RelacionamentoAtivo buscarRelacionamentoPorAtivoPaiId(Integer id)
			throws NoResultException {
		// TODO Auto-generated method stub
		return null;
	}

	public void removerRelacionamento(RelacionamentoAtivo relacionamento)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
