package com.pss.features.ativos.relacionamento.bo;

import java.sql.SQLException;
import java.util.ArrayList;
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

	public List<RelacionamentoAtivo> buscarRelacionamentoPorAtivo(Ativo ativo) throws NoResultException {
		return instanceRepository.buscarRelacionamentoPorAtivo(ativo);
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

	public LinkedList mapearRelacionamento(Ativo ativo) {
		
		//para fazer a DFS
		LinkedList stack_open = new LinkedList();
		LinkedList stack_closed = new LinkedList();
		List lista_relacionamentos = new ArrayList();
		
		//Buscar primeiro como sendo ativo filho
		List lista_ativos_pai = buscarRelacionamentoPorAtivoFilho(ativo);
		FacadeUtil.log("Ativos que sao pai do ativo "+ativo.getId()+" :");
		for (int i = 0; i < lista_ativos_pai.size(); i++) {
			RelacionamentoAtivo r = (RelacionamentoAtivo) lista_ativos_pai.get(i);
			Ativo ativo_aux = r.getAtivoPai();
			stack_closed.addFirst(ativo_aux.getId());
			lista_relacionamentos.add(r);
			System.out.println(ativo_aux.getId());
		}
		System.out.println("**********************************************************************");
		stack_open.addFirst(ativo.getId());
		//Obter a arvore de dependencia
		List lista_relacionamento_ativos = null;
		while (stack_open.size() != 0) {
			
			FacadeUtil.log("stack_open: "+stack_open);
			FacadeUtil.log("stack_close: "+stack_closed);
			System.out.println("*************");
			Integer id_node = (Integer) stack_open.removeFirst();
			Ativo ativo_node = instanceAtivo.buscarAtivoPorId(id_node);
			lista_relacionamento_ativos = buscarRelacionamentoPorAtivo(ativo_node);
			FacadeUtil.log("Ativos que relacionados ao ativo "+ativo_node.getId()+" :");
			for (int i = 0; i < lista_relacionamento_ativos.size(); i++) {
				RelacionamentoAtivo r = (RelacionamentoAtivo) lista_relacionamento_ativos.get(i);
				Ativo ativo_aux_filho = r.getAtivoFilho(); //filho
				Integer filho_id = ativo_aux_filho.getId();
				Ativo ativo_aux_pai = r.getAtivoPai(); //pai
				Integer pai_id = ativo_aux_pai.getId();
				System.out.println(pai_id+" ->>> "+filho_id);
				//filho
				if (stack_closed.contains(filho_id)){
					//System.out.println("stack_close contem "+filho_id);
				}else if (filho_id.intValue() != id_node.intValue() && ! stack_open.contains(filho_id)) {
					stack_open.addFirst(filho_id);
					lista_relacionamentos.add(r);
				}
				//pai
				if (stack_closed.contains(pai_id)){
					//System.out.println("stack_close contem "+pai_id);
				}else if (pai_id.intValue() != id_node.intValue() && ! stack_open.contains(pai_id)){
					stack_open.addFirst(pai_id);
					lista_relacionamentos.add(r);
				}
			}
			stack_closed.addFirst(id_node);

		}
	
		stack_closed.addFirst(lista_relacionamentos);
		return stack_closed;
		
	}

}
