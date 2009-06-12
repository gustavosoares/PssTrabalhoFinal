package com.pss.features.seguranca.bo;

import javax.persistence.NoResultException;

import com.pss.core.facade.FacadeUtil;
import com.pss.features.seguranca.factories.UsuarioRepositoryFactory;
import com.pss.features.seguranca.model.Usuario;
import com.pss.features.seguranca.repository.interfaces.UsuarioRepository;

public class UsuarioBO implements UsuarioRepository {

	private static String PERSISTENCE_TYPE = "hibernate";
	private static UsuarioBO instance = null;
	private static UsuarioRepository instanceRepository = null;
	
	public static UsuarioBO getInstance() {
		if (instance == null) {
			instance = new UsuarioBO();
			instanceRepository = UsuarioRepositoryFactory.getInstance(PERSISTENCE_TYPE);
		}		
		return instance;
	}
	
	public Usuario buscarUsuarioPorEmaileSenha(String email, String senha) throws NoResultException {
		return instanceRepository.buscarUsuarioPorEmaileSenha(email, FacadeUtil.encriptar(senha.trim()));
	}

}
