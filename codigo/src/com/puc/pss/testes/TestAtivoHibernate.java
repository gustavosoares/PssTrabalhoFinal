package com.puc.pss.testes;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.NoResultException;

import com.puc.pss.model.Ativo;
import com.puc.pss.repository.AtivoRepositoryHibernate;
import com.puc.pss.repository.RelacionamentoAtivoRepositoryHibernate;

public class TestAtivoHibernate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ativo ativo = new Ativo();
		ativo.setId(null);
		ativo.setDataCriacao(new Date());
		ativo.setNome("matrix");
		ativo.setTipo("servidor");
		
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
			ativoRepo.removerAtivoPorId(new Integer(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoResultException e) {
			System.out.println("objeto nao encontrado");
		}
		
		List listaAtivos = ativoRepo.listarAtivos();
		System.out.println(listaAtivos);
		
		RelacionamentoAtivoRepositoryHibernate rAtivoRepo = RelacionamentoAtivoRepositoryHibernate.getInstance();
		Integer ativoPaiId = new Integer(2);
		Integer ativoFilhoId = new Integer(3);
		try {
			rAtivoRepo.cadastrarRelacionamento(ativoPaiId, ativoFilhoId);
		} catch (SQLException e) {
			System.err.println("erro no cadastro de relacionamento");
			e.printStackTrace();
		}
	}

}
