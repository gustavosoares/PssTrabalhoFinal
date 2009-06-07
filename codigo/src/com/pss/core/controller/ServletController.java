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
import com.pss.core.util.FeatureMapper;


public class ServletController extends HttpServlet {
	
	public FeatureMapper featureMapper = null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		doPost(request, response); 
	} 
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		String acao = request.getParameter("acao");
		Command comando = (Command) CommandMap.getInstance().getCommand(acao);
		if (comando == null) {
			comando = (Command) CommandMap.getInstance().getCommand("Inicio");
		}
		comando.execute(request, response);
	} 
	
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    
	    //Load dos parametros de features;
	    featureMapper = FeatureMapper.getInstance();
	    String seguranca = config.getInitParameter("seguranca").toLowerCase();
	    String relacionamento = config.getInitParameter("relacionamento").toLowerCase();
	    String monitoracao = config.getInitParameter("monitoracao").toLowerCase();
	    String versionamento = config.getInitParameter("versionamento").toLowerCase();
	    
	    featureMapper.registrarFeature("segurance", seguranca);
	    featureMapper.registrarFeature("relacionamento", relacionamento);
	    featureMapper.registrarFeature("monitoracao", monitoracao);
	    featureMapper.registrarFeature("versionamento", versionamento);
	    
	    // Inicializando as bases de dados
	    /*
	    ColaboradorRepository.getInstance();
	    ProjetoRepository.getInstance();
	    PublicacaoRepository.getInstance();
	    OrientacaoRepository.getInstance();
		*/
	    
	    // Inicializa o Map de Commandos
	    CommandMap.getInstance();
	}
	
	
}