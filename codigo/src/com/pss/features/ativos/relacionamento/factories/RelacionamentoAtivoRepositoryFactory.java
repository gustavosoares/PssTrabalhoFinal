package com.pss.features.ativos.relacionamento.factories;

import com.pss.features.ativos.relacionamento.repository.RelacionamentoAtivoRepositoryHibernate;
import com.pss.features.ativos.relacionamento.repository.interfaces.RelacionamentoAtivoRepository;

public class RelacionamentoAtivoRepositoryFactory {

	private RelacionamentoAtivoRepositoryFactory() {
		
	}
	
	public static RelacionamentoAtivoRepository getInstance(String tipo) {
		if (tipo.equalsIgnoreCase("hibernate")) {
			return RelacionamentoAtivoRepositoryHibernate.getInstance();
		}else{
			return null;
		}
	}
}
