package com.pss.core.testes;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.NoResultException;

import com.pss.core.model.Ativo;
import com.pss.core.model.repository.AtivoRepositoryHibernate;
import com.pss.features.ativos.relacionamento.bo.RelacionamentoAtivoBO;
import com.pss.features.ativos.relacionamento.repository.RelacionamentoAtivoRepositoryHibernate;

public class TestAtivoHibernate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ativo ativo = new Ativo();
		ativo.setDataCriacao(new Date());
		ativo.setNome("matrix");
		ativo.setDescricacao("blabla");
		ativo.setTipo(1);
		
		AtivoRepositoryHibernate ativoRepo = AtivoRepositoryHibernate.getInstance();
		
		try {
			ativoRepo.cadastrarAtivo(ativo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ativo = ativoRepo.buscarAtivoPorId(new Integer(1));
			System.out.println(ativo);
		} catch (NoResultException e) {
			System.out.println("objeto nao encontrado");
		}

		
		try {
			ativoRepo.removerAtivoPorId(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoResultException e) {
			System.out.println("objeto nao sera removido pois nao foi encontrado");
		}
		
		List listaAtivos = ativoRepo.listarAtivos();
		System.out.println(listaAtivos);
		
		RelacionamentoAtivoBO rAtivoBO = RelacionamentoAtivoBO.getInstance();
		Integer ativoPaiId = new Integer(2);
		Integer ativoFilhoId = new Integer(6);
		try {
			rAtivoBO.cadastrarRelacionamento(ativoPaiId, ativoFilhoId);
		} catch (SQLException e) {
			System.err.println("erro no cadastro de relacionamento");
			e.printStackTrace();
		}
	}

}
