package com.pss.features.ativos.relacionamento.repository.interfaces;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import com.pss.core.model.Ativo;
import com.pss.features.ativos.relacionamento.model.RelacionamentoAtivo;

public interface RelacionamentoAtivoRepository {

	public void cadastrarRelacionamento(Ativo ativoPai, Ativo ativoFilho) throws SQLException, NoResultException ;
	public void removerRelacionamento(RelacionamentoAtivo relacionamento) throws SQLException;
	public List<Ativo> buscarAtivosFilhosPorAtivoPai(Ativo ativoPai) throws NoResultException;
	public RelacionamentoAtivo buscarRelacionamento(Ativo ativoPai, Ativo ativoFilho) throws NoResultException;
	public RelacionamentoAtivo buscarRelacionamentoPorId(Integer id) throws NoResultException;
	public List<RelacionamentoAtivo> buscarRelacionamentoPorAtivoPai(Ativo ativoPai) throws NoResultException;
	public List<RelacionamentoAtivo> buscarRelacionamentoPorAtivoFilho(Ativo ativoFilho) throws NoResultException;
	public List<RelacionamentoAtivo> buscarRelacionamentoPorAtivo(Ativo ativo) throws NoResultException;
	public List<RelacionamentoAtivo> listarRelacionamentos();
	
}
