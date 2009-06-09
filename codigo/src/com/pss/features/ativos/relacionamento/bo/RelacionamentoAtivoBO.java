package com.pss.features.ativos.relacionamento.bo;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import com.pss.core.bo.AtivoBO;
import com.pss.core.model.Ativo;
import com.pss.features.ativos.relacionamento.factories.RelacionamentoAtivoRepositoryFactory;
import com.pss.features.ativos.relacionamento.model.RelacionamentoAtivo;
import com.pss.features.ativos.relacionamento.repository.RelacionamentoAtivoRepositoryHibernate;
import com.pss.features.ativos.relacionamento.repository.interfaces.RelacionamentoAtivoRepository;

public class RelacionamentoAtivoBO implements RelacionamentoAtivoRepository{

	private static String PERSISTENCE_TYPE = "hibernate";
	private static RelacionamentoAtivoBO instance = null;
	private static AtivoBO instanceAtivo = null;
	private static RelacionamentoAtivoRepository instanceRepository = null;

	public static RelacionamentoAtivoBO getInstance() {
		if (instance == null) {
			instance = new RelacionamentoAtivoBO();
			instanceAtivo = AtivoBO.getInstance();
			instanceRepository = RelacionamentoAtivoRepositoryFactory.getInstance(PERSISTENCE_TYPE);
		}		
		return instance;
	}
	
	public List buscarAtivosFilhosPorAtivoPai(Ativo ativoPai) throws NoResultException {
		return instanceRepository.buscarAtivosFilhosPorAtivoPai(ativoPai);
	}

	public RelacionamentoAtivo buscarRelacionamento(Ativo ativoPai, Ativo ativoFilho) {
		RelacionamentoAtivo r = null;
		try{
			r = instanceRepository.buscarRelacionamento(ativoPai, ativoFilho);
		}catch (NoResultException e) {
			System.out.println("Relacionamento: "+ativoPai+" -> "+ativoFilho+" nao encontrado");
		}
		
		return r;
	}

	public void removerRelacionamentoPorAtivoPai(Ativo ativoPai) throws SQLException {
		instanceRepository.removerRelacionamentoPorAtivoPai(ativoPai);
		
	}

	public RelacionamentoAtivo buscarRelacionamentoPorAtivoFilho(Ativo ativoFilho) throws NoResultException {
		return instanceRepository.buscarRelacionamentoPorAtivoFilho(ativoFilho);
	}

	public RelacionamentoAtivo buscarRelacionamentoPorAtivoPai(Ativo ativoPai) throws NoResultException {
		return instanceRepository.buscarRelacionamentoPorAtivoPai(ativoPai);
	}

	public void removerRelacionamento(RelacionamentoAtivo relacionamento) throws SQLException {
		instanceRepository.removerRelacionamento(relacionamento);
	}

	public void cadastrarRelacionamento(Ativo ativoPai, Ativo ativoFilho) throws SQLException, NoResultException {
		instanceRepository.cadastrarRelacionamento(ativoPai, ativoFilho);
	}


}
