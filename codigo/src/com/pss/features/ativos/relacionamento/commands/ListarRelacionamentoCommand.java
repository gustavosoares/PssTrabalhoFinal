package com.pss.features.ativos.relacionamento.commands;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.bo.AtivoBO;
import com.pss.core.commands.Command;
import com.pss.core.facade.FacadeBO;
import com.pss.core.model.Ativo;
import com.pss.features.ativos.relacionamento.bo.RelacionamentoAtivoBO;

public class ListarRelacionamentoCommand extends Command {

	public ListarRelacionamentoCommand() {
		commandName = "listarRelacionamento";
		urlForwardOK = "/relacionamento/listarRelacionamento.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RelacionamentoAtivoBO relBO =  FacadeBO.getRelacionamentoAtivoBOInstance();
		AtivoBO ativoBO = FacadeBO.getAtivoBOInstance();
		
		List lista_ativos = ativoBO.listarAtivos();
		List lista_relacionamentos = null;
		
		
		String action = "";
		String ativoIdStr = "";

		
		if (request.getParameter("subacao") !=  null) {
			action = request.getParameter("subacao").trim();
		}
		
		if (request.getParameter("ativo") != null) {
			ativoIdStr = request.getParameter("ativo").trim();
		}
		
		
		if (action.equalsIgnoreCase("lista") && ativoIdStr.length() > 0) {
			
			Integer ativoId = new Integer(ativoIdStr.trim());
			
			try {
				Ativo ativo = ativoBO.buscarAtivoPorId(ativoId);
				LinkedList pilha_relacionamentos = relBO.mapearRelacionamento(ativo);
				lista_relacionamentos = (List) pilha_relacionamentos.removeFirst();
				request.setAttribute("mensagemJsp", "Dependências do ativo "+ativo.getNome()+" mapeadas com sucesso ");
			} catch (Exception e) {
				request.setAttribute("temErroJsp", new Boolean(true));
				request.setAttribute("mensagemJsp", "Nao foi possivel mapear as dependências " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			request.setAttribute("temErroJsp", new Boolean(true));
			request.setAttribute("mensagemJsp", "Mapeamento de dependências. Selecione um ativo e clique em Mapear");
		}
		
		request.setAttribute("liAtivos", lista_ativos);
		request.setAttribute("liRelacionamentos", lista_relacionamentos);
		request.getRequestDispatcher(urlForwardOK).forward(request, response);
		
	}

}
