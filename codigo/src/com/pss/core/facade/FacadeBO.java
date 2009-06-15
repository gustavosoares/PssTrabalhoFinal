package com.pss.core.facade;

import com.pss.core.bo.AtivoBO;
import com.pss.core.model.Ativo;

public class FacadeBO {
	
	private static FacadeBO instance;
	
	private FacadeBO() {
		
	}
	
	public static FacadeBO getInstance() {
		if (instance == null) {
			instance = new FacadeBO();
		}		
		return instance;
	}
	
	public static AtivoBO getAtivoBOInstance() {
		return AtivoBO.getInstance();
	}
	
	public static com.pss.features.seguranca.bo.UsuarioBO getUsuarioBOInstance() {
		return com.pss.features.seguranca.bo.UsuarioBO.getInstance();
	}
	
	public static com.pss.features.ativos.relacionamento.bo.RelacionamentoAtivoBO getRelacionamentoAtivoBOInstance() {
		return com.pss.features.ativos.relacionamento.bo.RelacionamentoAtivoBO.getInstance();
	}
	
	public static com.pss.features.monitoracao.agente1.bo.Agente1BO getAgente1BOInstance() {
		return com.pss.features.monitoracao.agente1.bo.Agente1BO.getInstance();
	}
	
	public static boolean ativoExiste(Ativo ativo) {
		
		Ativo ativo_aux = null; 
		try{
			ativo_aux =	getAtivoBOInstance().buscarAtivoPorId(ativo.getId());
		}catch(Exception e) {
			
		}

		if (ativo_aux == null) {
			return false;
		}else{
			ativo_aux = null;
			return true;
		}
	}
	
}
