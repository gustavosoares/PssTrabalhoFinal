package com.puc.pss.testes;

import java.util.Date;

import com.puc.pss.model.Ativo;
import com.puc.pss.repository.AtivoRepositoryHibernate;

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
		
		ativoRepo.cadastrarAtivo(ativo);
		
	}

}
