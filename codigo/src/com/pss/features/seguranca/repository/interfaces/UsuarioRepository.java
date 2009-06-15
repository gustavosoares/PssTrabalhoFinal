package com.pss.features.seguranca.repository.interfaces;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import com.pss.features.seguranca.model.Usuario;

public interface UsuarioRepository {
	
	public Usuario buscarUsuarioPorEmaileSenha(String email, String senha) throws NoResultException;
	public Usuario buscarUsuarioPorId(Integer id) throws NoResultException;
	public void cadastrarUsuario(Usuario usuario) throws SQLException;
	public void removerUsuario(Usuario usuario) throws SQLException, NoResultException;
	public List<Usuario> listarUsuarios();
	
}
