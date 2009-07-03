package com.pss.core.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.commands.Command;
import com.pss.core.commands.CommandMap;
import com.pss.core.facade.FacadeUtil;


public class ServletController extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		doPost(request, response); 
	} 
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		String acao = request.getParameter("acao");
		//FacadeUtil.log(this, " acao: "+acao);
		Command comando = (Command) CommandMap.getInstance().getCommand(acao);
		if (comando == null) {
			comando = (Command) CommandMap.getInstance().getCommand("Inicio");
		}
		comando.execute(request, response);
	} 
	
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    
	    //Load dos parametros de features;
	    String seguranca = config.getInitParameter("seguranca").toLowerCase();
	    String relacionamento = config.getInitParameter("relacionamento").toLowerCase();
	    String monitoracao = config.getInitParameter("monitoracao").toLowerCase();
	    String agente2_str = config.getInitParameter("agente2").toLowerCase();
	    
	    
	    FacadeUtil.registrarFeature("seguranca", seguranca);
	    FacadeUtil.registrarFeature("relacionamento", relacionamento);
	    FacadeUtil.registrarFeature("monitoracao", monitoracao);
	    FacadeUtil.registrarFeature("agente2", agente2_str);
	    
	    FacadeUtil.log(this, FacadeUtil.obterEstadoDasFeatures());
	    // Inicializando a thread do Agente2
	    if (FacadeUtil.featureHabilitada("agente2")){
	    	String percentualMinimoEstoque = config.getInitParameter("percentualMinimoEstoque").toLowerCase();
	    	String agente2CheckInterval = config.getInitParameter("agente2CheckInterval").toLowerCase();
	    	com.pss.features.monitoracao.agente2.Agente2 agente2 = com.pss.features.monitoracao.agente2.Agente2.getInstance();
	    	try {
		    	agente2.setCheckInterval(Integer.valueOf(agente2CheckInterval));
		    	agente2.setPercentualMinimo(Double.valueOf(percentualMinimoEstoque));
	    	} catch (Exception e) {
	    		FacadeUtil.log(this, "erro na leitura dos parametros do agente2. Utilizando os parametros default");
	    	}

	    	agente2.start();
	    }

	    
	    // Inicializa o Map de Commandos
	    CommandMap.getInstance();
	    FacadeUtil.log(this, "Commandos incializados");
	}
	
	public void destroy() {
		
		FacadeUtil.log(this, "destroy");
	    if (FacadeUtil.featureHabilitada("monitoracao")){
	    	FacadeUtil.log(this, "parando agente2");
	    	com.pss.features.monitoracao.agente2.Agente2 agente2 = com.pss.features.monitoracao.agente2.Agente2.getInstance();
	    	agente2.setLoop(false);
	    	agente2.stop();
	    }
		
	}
	
	
}
