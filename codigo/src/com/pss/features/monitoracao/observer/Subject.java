package com.pss.features.monitoracao.observer;

public interface Subject {

	public void adicionarObservador(Observer o);
	public void removerObservador(Observer o);
	
}
