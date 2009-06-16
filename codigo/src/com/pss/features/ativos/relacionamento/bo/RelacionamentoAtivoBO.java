package com.pss.features.ativos.relacionamento.bo;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.NoResultException;

import com.pss.core.bo.AtivoBO;
import com.pss.core.facade.FacadeBO;
import com.pss.core.facade.FacadeUtil;
import com.pss.core.model.Ativo;
import com.pss.features.ativos.relacionamento.factories.RelacionamentoAtivoRepositoryFactory;
import com.pss.features.ativos.relacionamento.model.RelacionamentoAtivo;
import com.pss.features.ativos.relacionamento.repository.interfaces.RelacionamentoAtivoRepository;

public class RelacionamentoAtivoBO implements RelacionamentoAtivoRepository{

	private static String PERSISTENCE_TYPE = "hibernate";
	private static RelacionamentoAtivoBO instance = null;
	private static AtivoBO instanceAtivo = null;
	private static RelacionamentoAtivoRepository instanceRepository = null;

	private RelacionamentoAtivoBO() {
		
	}
	
	public static RelacionamentoAtivoBO getInstance() {
		if (instance == null) {
			instance = new RelacionamentoAtivoBO();
			instanceAtivo = FacadeBO.getAtivoBOInstance();
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

	public List<RelacionamentoAtivo> buscarRelacionamentoPorAtivoFilho(Ativo ativoFilho) throws NoResultException {
		return instanceRepository.buscarRelacionamentoPorAtivoFilho(ativoFilho);
	}

	public List<RelacionamentoAtivo> buscarRelacionamentoPorAtivoPai(Ativo ativoPai) throws NoResultException {
		return instanceRepository.buscarRelacionamentoPorAtivoPai(ativoPai);
	}

	public void removerRelacionamento(RelacionamentoAtivo relacionamento) throws SQLException {
		instanceRepository.removerRelacionamento(relacionamento);
	}
	
	/**
	 * Remove lista de relacionamento
	 * @param lista_relacionamentos
	 * @throws SQLException
	 */
	public void removerRelacionamento(List<RelacionamentoAtivo> lista_relacionamentos) throws SQLException {
		for (int i = 0; i < lista_relacionamentos.size(); i++) {
			RelacionamentoAtivo relacionamento = (RelacionamentoAtivo) lista_relacionamentos.get(i);
			FacadeUtil.log("removendo relacionamento: "+relacionamento);
			removerRelacionamento(relacionamento);
		}
	}
	
	public void cadastrarRelacionamento(Ativo ativoPai, Ativo ativoFilho) throws SQLException, NoResultException {
		instanceRepository.cadastrarRelacionamento(ativoPai, ativoFilho);
	}

	public void mapearRelacionamento(Ativo ativo) {
		
		//Buscar primeiro como sendo ativo filho
		List lista_ativos_pai = buscarRelacionamentoPorAtivoFilho(ativo);
		FacadeUtil.log("Ativos que sao pai do ativo "+ativo.getId()+" :");
		for (int i = 0; i < lista_ativos_pai.size(); i++) {
			RelacionamentoAtivo r = (RelacionamentoAtivo) lista_ativos_pai.get(i);
			Ativo ativo_aux = r.getAtivoPai();
			System.out.println(ativo_aux.getId());
		}
		System.out.println("**********************************************************************");
		//Busca de ativos que sao filhos do ativo
		List lista_ativos_filho = buscarRelacionamentoPorAtivoPai(ativo);
		FacadeUtil.log("Ativos que sao filho do ativo "+ativo.getId()+" :");
		for (int i = 0; i < lista_ativos_filho.size(); i++) {
			RelacionamentoAtivo r = (RelacionamentoAtivo) lista_ativos_filho.get(i);
			Ativo ativo_aux = r.getAtivoFilho();
			System.out.println(ativo_aux.getId());
		}
		//Obter a arvore de dependencia
		LinkedList stack_open = new LinkedList();
		LinkedList stack_closed = new LinkedList();
		
		/*
	    // Push on top of stack
	    stack.addFirst(new Integer(1));
	    stack.addFirst(new Integer(2));
	    stack.addFirst(new Integer(3));
	    stack.addFirst(new Integer(4));
	    
	    System.out.println(stack);
	    // Pop off top of stack
	    
	    System.out.println("first: "+stack.removeFirst());
	    System.out.println(stack);
	    System.out.println("first: "+stack.removeFirst());
	    System.out.println(stack);
	    System.out.println("first: "+stack.removeFirst());
	    // If the queue is to be used by multiple threads,
		*/
	}

}
