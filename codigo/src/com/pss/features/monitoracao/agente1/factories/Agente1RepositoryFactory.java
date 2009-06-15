package com.pss.features.monitoracao.agente1.factories;

import com.pss.features.monitoracao.agente1.repositoy.Agente1RepositoryHibernate;
import com.pss.features.monitoracao.agente1.repositoy.interfaces.Agente1ObserverSujeito;

public class Agente1RepositoryFactory {
	
	private Agente1RepositoryFactory() {
		
	}

	public static Agente1ObserverSujeito getInstance(String tipo) {
		if (tipo.equalsIgnoreCase("hibernate")) {
			return Agente1RepositoryHibernate.getInstance();
		}else{
			return null;
		}
	}
	
}
