package com.pss.core.testes;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.NoResultException;


import com.pss.core.bo.AtivoBO;
import com.pss.core.facade.FacadeUtil;
import com.pss.core.model.Ativo;
import com.pss.features.ativos.relacionamento.bo.RelacionamentoAtivoBO;

public class TestAtivoHibernate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FacadeUtil.registrarFeature("relacionamento", "true");
		
		AtivoBO ativoBO = AtivoBO.getInstance();
		
		/**
		 * Cadastrar ativo
		 */
		/*
		for (int i=0; i < 5; i++) {
			System.out.println("iniciando...");
			Ativo ativo = new Ativo();
			ativo.setDataCriacao(new Date());
			ativo.setNome("matrix");
			ativo.setDescricacao("blabla");
			ativo.setTipo(1);
			
			try {
				ativoBO.cadastrarAtivo(ativo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		*/
		Ativo ativo = null;
		
		try {
			ativo = ativoBO.buscarAtivoPorId(1);
			System.out.println(ativo);
		} catch (NoResultException e) {
			System.out.println("objeto nao encontrado");
		}

		
		/**
		 * Editar um ativo
		 * 
		 */
		int ativo_editar_id = 1;
		try {
			System.out.println("Editando o ativo_id "+ativo_editar_id);
			ativo = ativoBO.buscarAtivoPorId(ativo_editar_id);
			System.out.println(ativo);
			ativo.setDescricacao("descricacao alteraaada");
			ativoBO.editarAtivo(ativo);
		} catch (NoResultException e) {
			System.out.println("ativo id "+ativo_editar_id+" nao encontrado para edicao");
		} catch (SQLException e) {
			System.err.println("Erro editando o ativo id "+ativo_editar_id+": "+e.getMessage());
		}
		
		/**
		 * REMOVER
		 */
		/*
		int ativo_remover_id = 11;
		try {
			ativoBO.removerAtivoPorId(ativo_remover_id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoResultException e) {
			System.out.println("AtivoId "+ativo_remover_id+" nao sera removido pois nao foi encontrado");
		}
		*/
		List listaAtivos = ativoBO.listarAtivos();
		
		//System.out.println("******************");
		//System.out.println(listaAtivos);
		//System.out.println("******************");
		
		RelacionamentoAtivoBO rAtivoBO = RelacionamentoAtivoBO.getInstance();
		
		try {
			Ativo ativoPai = ativoBO.buscarAtivoPorId(8);
			Ativo ativoFilho = ativoBO.buscarAtivoPorId(13);
			rAtivoBO.cadastrarRelacionamento(ativoPai, ativoFilho);
		} catch (SQLException e) {
			System.err.println("erro no cadastro de relacionamento: "+e.getMessage());
		} catch (NoResultException e) {
			System.err.println("relacionamento nao cadastrado: ativos nao encontrados");
		} 
	
		int ativo_relacionamento_id = 2;
		ativo = ativoBO.buscarAtivoPorId(ativo_relacionamento_id);
		
		LinkedList lista_relacionamento = rAtivoBO.mapearRelacionamento(ativo);
		System.out.println("Relacionamentos com o ativo id "+ativo_relacionamento_id+": "+lista_relacionamento);
		
	}

}
