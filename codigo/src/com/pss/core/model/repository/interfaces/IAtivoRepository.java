package com.pss.core.model.repository.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.NoResultException;

import com.pss.core.model.Ativo;

public interface IAtivoRepository {

	public Ativo buscarAtivoPorId(Integer id) throws NoResultException;
	public void cadastrarAtivo(Ativo ativo) throws SQLException;
	public void removerAtivoPorId(Integer id) throws SQLException;
	public List listarAtivos();
	
}