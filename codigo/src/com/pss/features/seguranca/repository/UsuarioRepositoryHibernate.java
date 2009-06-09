package com.pss.features.seguranca.repository;

import javax.persistence.NoResultException;

import com.pss.core.util.GenericPersistence;
import com.pss.features.seguranca.model.Usuario;
import com.pss.features.seguranca.repository.interfaces.UsuarioRepository;

public class UsuarioRepositoryHibernate implements UsuarioRepository{

	private static UsuarioRepositoryHibernate instance = null;
	private GenericPersistence<Usuario> genericPersistence = new GenericPersistence<Usuario>(Usuario.class, "defaultUnit");
	
	public static UsuarioRepositoryHibernate getInstance() {
		if (instance == null) {
			instance = new UsuarioRepositoryHibernate();
		}
		return instance;
	}
	
	public Usuario buscarUsuarioPorEmaileSenha(String email, String senha) throws NoResultException {
		Usuario usuario = genericPersistence.findByNamedQuery("findByEmailAndPassword", email, senha);
		return usuario;
		
	}

}
