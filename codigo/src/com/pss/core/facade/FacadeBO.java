package com.pss.core.facade;

import com.pss.core.bo.AtivoBO;

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
	
}