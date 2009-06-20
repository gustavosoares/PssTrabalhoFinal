package com.pss.core.bo;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;

import com.pss.core.facade.FacadeUtil;
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
		ativo.setDataCriacao(new Date());
		ativo.setDataAlteracao(new Date());
		instanceRepository.cadastrarAtivo(ativo);
	}

	public List listarAtivos() {
		return instanceRepository.listarAtivos();
	}

	public void removerAtivoPorId(Integer id) throws SQLException, NoResultException {
		//Possui relacionamento?
		boolean relacionamento = FacadeUtil.featureHabilitada("relacionamento");
		FacadeUtil.log(this, "verificando se existe relacionamento: "+relacionamento);
		if (relacionamento) {
			FacadeUtil.log(this, "removendo o relacionamento primeiro");
			com.pss.features.ativos.relacionamento.bo.RelacionamentoAtivoBO rBO = com.pss.features.ativos.relacionamento.bo.RelacionamentoAtivoBO.getInstance();
			Ativo ativo = buscarAtivoPorId(id);
			//pai
			List lista_relacionamentos = rBO.buscarRelacionamentoPorAtivoPai(ativo);
			FacadeUtil.log(this, "Relacionamentos por ativo pai: "+lista_relacionamentos);
			rBO.removerRelacionamento(lista_relacionamentos);
			//filho
			lista_relacionamentos = rBO.buscarRelacionamentoPorAtivoFilho(ativo);
			FacadeUtil.log(this, "Relacionamentos por ativo filho: "+lista_relacionamentos);
			rBO.removerRelacionamento(lista_relacionamentos);
			//remove ativo
			instanceRepository.removerAtivoPorId(id);
		}else{
			instanceRepository.removerAtivoPorId(id);
		}
		
		
	}

	public void editarAtivo(Ativo ativo) throws SQLException {
		ativo.setDataAlteracao(new Date());
		instanceRepository.editarAtivo(ativo);
		
		//possui monitoracao?
		boolean monitoracao = FacadeUtil.featureHabilitada("monitoracao");
		if (monitoracao) {
			//notificar observadores
			com.pss.features.monitoracao.agente1.bo.Agente1BO agenteBO = com.pss.features.monitoracao.agente1.bo.Agente1BO.getInstance();
			agenteBO.notificarObservadores(ativo);
		}
	}
	
	public int obterTotalDeAtivosServidor() {
		return obterTotaldeAtivosPorTipo(Ativo.TIPO_SERVIDOR);
	}

	public int obterTotalDeAtivosAplicacao() {
		return obterTotaldeAtivosPorTipo(Ativo.TIPO_APLICACAO);
	}
	
	public int obterTotalDeAtivosRoteador() {
		return obterTotaldeAtivosPorTipo(Ativo.TIPO_ROTEADOR);
	}
	
	public int obterTotaldeAtivosPorTipo(int tipo) {
		return instanceRepository.obterTotaldeAtivosPorTipo(tipo);
	}
}
