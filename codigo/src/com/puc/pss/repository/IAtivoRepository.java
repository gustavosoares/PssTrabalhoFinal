package com.puc.pss.repository;

import com.puc.pss.model.Ativo;

public interface IAtivoRepository {

	public Ativo buscarAtivoPorId(Integer id);
	public void cadastrarAtivo(Ativo ativo);
	
}
