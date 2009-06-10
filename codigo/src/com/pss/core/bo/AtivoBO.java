package com.pss.core.bo;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import com.pss.core.factories.AtivoRepositoryFactory;
import com.pss.core.model.Ativo;
import com.pss.core.model.repository.interfaces.AtivoRepository;
import com.pss.core.util.FeatureMapper;
import com.pss.core.util.Logging;

public class AtivoBO implements AtivoRepository {
	
	private static String PERSISTENCE_TYPE = "hibernate";
	private static AtivoBO instance = null;
	private FeatureMapper featureMapper = FeatureMapper.getInstance();
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
		//Possui relacionamento?
		boolean relacionamento = featureMapper.getInstance().featureHabilitada("relacionamento");
		Logging.log("verificando se existe relacionamento: "+relacionamento);
		if (relacionamento) {
			Logging.log("removendo o relacionamento primeiro");
			com.pss.features.ativos.relacionamento.bo.RelacionamentoAtivoBO rBO = com.pss.features.ativos.relacionamento.bo.RelacionamentoAtivoBO.getInstance();
			Ativo ativo = buscarAtivoPorId(id);
			//pai
			List lista_relacionamentos = rBO.buscarRelacionamentoPorAtivoPai(ativo);
			Logging.log("Relacionamentos por ativo pai: "+lista_relacionamentos);
			rBO.removerRelacionamento(lista_relacionamentos);
			//filho
			lista_relacionamentos = rBO.buscarRelacionamentoPorAtivoFilho(ativo);
			Logging.log("Relacionamentos por ativo filho: "+lista_relacionamentos);
			rBO.removerRelacionamento(lista_relacionamentos);
			//remove ativo
			instanceRepository.removerAtivoPorId(id);
		}else{
			instanceRepository.removerAtivoPorId(id);
		}
		
		
	}

}
