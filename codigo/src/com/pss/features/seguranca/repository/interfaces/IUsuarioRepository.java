package com.pss.features.seguranca.repository.interfaces;

import javax.persistence.NoResultException;



public interface IUsuarioRepository {
	
	public void buscarUsuarioPorEmaileSenha(String email, String senha) throws NoResultException;
	
}
