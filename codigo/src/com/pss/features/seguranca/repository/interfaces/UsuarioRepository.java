package com.pss.features.seguranca.repository.interfaces;

import javax.persistence.NoResultException;

import com.pss.features.seguranca.model.Usuario;

public interface UsuarioRepository {
	
	public Usuario buscarUsuarioPorEmaileSenha(String email, String senha) throws NoResultException;
	
}
