package com.pss.core.factories;

import com.pss.core.model.repository.AtivoRepositoryHibernate;
import com.pss.core.model.repository.interfaces.AtivoRepository;

public class AtivoRepositoryFactory {
	
	private AtivoRepositoryFactory() {
		
	}
	
	public static AtivoRepository getInstance(String tipo) {
		if (tipo.equalsIgnoreCase("hibernate")) {
			return AtivoRepositoryHibernate.getInstance();
		}else{
			return null;
		}
	}

}
