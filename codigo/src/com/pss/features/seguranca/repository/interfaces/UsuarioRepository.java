package com.pss.features.seguranca.repository.interfaces;

import javax.persistence.NoResultException;



public interface UsuarioRepository {
	
	public Usuario buscarUsuarioPorEmaileSenha(String email, String senha) throws NoResultException;
	
}
