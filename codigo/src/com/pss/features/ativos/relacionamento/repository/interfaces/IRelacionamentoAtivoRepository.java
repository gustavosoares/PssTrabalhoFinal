package com.pss.features.ativos.relacionamento.repository.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.NoResultException;

import com.pss.core.model.Ativo;
import com.pss.features.ativos.relacionamento.model.RelacionamentoAtivo;

public interface IRelacionamentoAtivoRepository {

	public void cadastrarRelacionamento(Ativo ativoPai, Ativo ativoFilho) throws SQLException, NoResultException;
	public void removerRelacionamentoPorAtivoPaiId(Ativo ativoPai) throws SQLException;
	public void removerRelacionamento(RelacionamentoAtivo relacionamento) throws SQLException;
	public List buscarAtivosFilhosPorAtivoPaiId(Integer ativoPaiId) throws NoResultException;
	public RelacionamentoAtivo buscarRelacionamento(Integer ativoPaiId, Integer ativoFilhoId) throws NoResultException;
	public RelacionamentoAtivo buscarRelacionamentoPorAtivoPaiId(Integer id) throws NoResultException;
	public RelacionamentoAtivo buscarRelacionamentoPorAtivoFilhoId(Integer id) throws NoResultException;
	
}
