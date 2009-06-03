package com.puc.pss.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.NoResultException;

import com.puc.pss.model.Ativo;
import com.puc.pss.model.RelacionamentoAtivo;

public interface IRelacionamentoAtivoRepository {

	public void cadastrarRelacionamento(Integer ativoPaiId, Integer ativoFilhoId) throws SQLException;
	public void removerRelacionamentoPorAtivoId(Integer ativoPaiId) throws SQLException;
	public List buscarAtivosFilhosPorId(Integer ativoPaiId) throws NoResultException;
	
}
