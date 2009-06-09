package com.pss.features.seguranca.factories;

import com.pss.features.seguranca.repository.UsuarioRepositoryHibernate;
import com.pss.features.seguranca.repository.interfaces.UsuarioRepository;

public class UsuarioRepositoryFactory {

	private UsuarioRepositoryFactory() {
		
	}
	
	public static UsuarioRepository getInstance(String tipo) {
		if (tipo.equalsIgnoreCase("hibernate")) {
			return UsuarioRepositoryHibernate.getInstance();
		}else{
			return null;
		}
	}
}
