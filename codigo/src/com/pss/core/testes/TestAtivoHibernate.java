package com.pss.core.testes;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.exception.ConstraintViolationException;

import com.pss.core.bo.AtivoBO;
import com.pss.core.model.Ativo;
import com.pss.core.model.repository.AtivoRepositoryHibernate;
import com.pss.core.util.FeatureMapper;
import com.pss.features.ativos.relacionamento.bo.RelacionamentoAtivoBO;

public class TestAtivoHibernate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FeatureMapper featureMapper = FeatureMapper.getInstance();
		featureMapper.registrarFeature("relacionamento", "true");
		AtivoBO ativoBO = AtivoBO.getInstance();
		
		for (int i=0; i < 3; i++) {
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

		Ativo ativo = null;
		
		try {
			ativo = ativoBO.buscarAtivoPorId(1);
			System.out.println(ativo);
		} catch (NoResultException e) {
			System.out.println("objeto nao encontrado");
		}

		
		/**
		 * REMOVER
		 */
		int ativo_remover_id = 11;
		try {
			ativoBO.removerAtivoPorId(ativo_remover_id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoResultException e) {
			System.out.println("AtivoId "+ativo_remover_id+" nao sera removido pois nao foi encontrado");
		}

		List listaAtivos = ativoBO.listarAtivos();
		
		System.out.println("******************");
		System.out.println(listaAtivos);
		System.out.println("******************");
		
		RelacionamentoAtivoBO rAtivoBO = RelacionamentoAtivoBO.getInstance();
		
		try {
			Ativo ativoPai = ativoBO.buscarAtivoPorId(11);
			Ativo ativoFilho = ativoBO.buscarAtivoPorId(22);
			rAtivoBO.cadastrarRelacionamento(ativoPai, ativoFilho);
		} catch (SQLException e) {
			System.err.println("erro no cadastro de relacionamento: "+e.getMessage());
		} catch (NoResultException e) {
			System.err.println("relacionamento nao cadastrado: ativos nao encontrados");
		} 
	
		
	}

}
