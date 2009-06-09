package com.pss.features.ativos.relacionamento.repository.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.NoResultException;

import com.pss.core.model.Ativo;
import com.pss.features.ativos.relacionamento.model.RelacionamentoAtivo;

public interface RelacionamentoAtivoRepository {

	public void cadastrarRelacionamento(Ativo ativoPai, Ativo ativoFilho) throws SQLException, NoResultException;
	public void removerRelacionamentoPorAtivoPai(Ativo ativoPai) throws SQLException;
	public void removerRelacionamento(RelacionamentoAtivo relacionamento) throws SQLException;
	public List<Ativo> buscarAtivosFilhosPorAtivoPai(Ativo ativoPai) throws NoResultException;
	public RelacionamentoAtivo buscarRelacionamento(Ativo ativoPai, Ativo ativoFilho) throws NoResultException;
	public RelacionamentoAtivo buscarRelacionamentoPorAtivoPai(Ativo ativoPai) throws NoResultException;
	public RelacionamentoAtivo buscarRelacionamentoPorAtivoFilho(Ativo ativoFilho) throws NoResultException;
	
}
