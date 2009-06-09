package com.pss.core.model.repository.interfaces;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import com.pss.core.model.Ativo;

public interface AtivoRepository {

	public Ativo buscarAtivoPorId(Integer id) throws NoResultException;
	public void cadastrarAtivo(Ativo ativo) throws SQLException;
	public void removerAtivoPorId(Integer id) throws SQLException, NoResultException;
	public List listarAtivos();
	
}
