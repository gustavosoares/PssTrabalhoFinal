package com.pss.features.seguranca.repository;

import java.sql.SQLException;
import java.util.List;

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

	public Usuario buscarUsuarioPorId(Integer id) throws NoResultException {
		Usuario usuario = genericPersistence.findByNamedQuery("findById", id);
		return usuario;
	}

	public void cadastrarUsuario(Usuario usuario) throws SQLException {
		genericPersistence.save(usuario);
	}

	public List<Usuario> listarUsuarios() {
		return genericPersistence.listAll();
	}

	public void removerUsuario(Usuario usuario) throws SQLException, NoResultException {
		genericPersistence.remove(usuario, usuario.getId());
	}

}
