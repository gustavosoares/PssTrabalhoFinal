package com.pss.features.monitoracao.agente2;

public class Agente2 {

	private static Agente2 instance = null;
	
	private Agente2() {
		
	}
	
	public static Agente2 getInstance() {
		if (instance == null){
			instance = new Agente2();
			return instance;
		} else {
			return instance;
		}
	}
	
	
}
