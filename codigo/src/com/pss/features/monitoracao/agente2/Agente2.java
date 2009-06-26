package com.pss.features.monitoracao.agente2;

import java.math.BigDecimal;
import java.util.List;

import com.pss.core.bo.AtivoBO;
import com.pss.core.facade.FacadeBO;
import com.pss.core.facade.FacadeUtil;
import com.pss.core.model.Ativo;

public class Agente2 extends Thread {

	private static Agente2 instance = null;
	private int checkInterval = 60000; // em milisegundos  ms
	private boolean loop = true;
	private double percentualMinimo = 30.0;
	
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
	
	
	public int getCheckInterval() {
		return checkInterval;
	}

	public void setCheckInterval(int checkInterval) {
		this.checkInterval = checkInterval;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	
	public double getPercentualMinimo() {
		return percentualMinimo;
	}

	public void setPercentualMinimo(double threshold) {
		this.percentualMinimo = threshold;
	}

	public void run() {
		
		FacadeUtil.log(this, "Iniciando o agente2");
		FacadeUtil.log(this, "Minimo no estoque: "+getPercentualMinimo());
		AtivoBO ativoBO = FacadeBO.getAtivoBOInstance();
		Ativo ativo;
		double percentual = 0.0;
		int count_estoque = 0;
		int count_total = 0;
		List lista_ativos = null;
		while (isLoop()) {
			count_estoque = 0;
			count_total = 0;
			lista_ativos = ativoBO.listarAtivos();
			for (int i = 0; i < lista_ativos.size(); i++) {
				ativo = (Ativo) lista_ativos.get(i);
				if (ativo.getTipo() == Ativo.TIPO_ROTEADOR ||ativo.getTipo() == Ativo.TIPO_SERVIDOR) {
					if (ativo.getLocalizacaoId() == Ativo.LOCALIZACAO_ESTOQUE) {
						count_estoque++;
					}
					count_total++;
				}
			}

			percentual = ( (double) count_estoque / count_total) * 100;
			FacadeUtil.log(this, "Estoque: "+count_estoque+" Total: "+count_total+" Percentual: "+percentual+" Minimo: "+getPercentualMinimo());
			if (percentual < getPercentualMinimo()) {
				//FacadeUtil.log(this, "Estoque: "+count_estoque+" Total: "+count_total+" Percentual: "+percentual+" Minimo: "+getPercentualMinimo());
				FacadeUtil.log(this, "!!!!! ATENCAO!!!! Estoque esta em "+percentual);
			}
			try {
				Thread.sleep(getCheckInterval());
			} catch (InterruptedException e) {
				FacadeUtil.log(this, "Erro no sleep: "+e);
				e.printStackTrace();
			}
			
		}
		
		FacadeUtil.log(this, "parando o agente2");
	}
	
	
}
