package com.pss.core.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.bo.AtivoBO;
import com.pss.core.facade.FacadeBO;
import com.pss.core.model.Ativo;

public class RemoverAtivoCommand extends Command {

	public RemoverAtivoCommand() {
		commandName = "removerAtivo";
		urlForwardOK = "/core/removerAtivo.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AtivoBO ativoBO = FacadeBO.getAtivoBOInstance();
		
		List lista_ativos = ativoBO.listarAtivos();
		
		String action = "";
		String AtivoIdStr = "";

		
		if (request.getParameter("subacao") !=  null) {
			action = request.getParameter("subacao").trim();
		}
		
		if (request.getParameter("ativoId") != null) {
			AtivoIdStr = request.getParameter("ativoId").trim();
		}
		
		
		if (action.equalsIgnoreCase("remove") && AtivoIdStr.length() > 0) {
			
			Integer ativoId = new Integer(AtivoIdStr.trim());
			
			
			try {
				Ativo ativo = ativoBO.buscarAtivoPorId(ativoId);
				ativoBO.removerAtivoPorId(ativoId);
				request.setAttribute("mensagemJsp", "Ativo "+ativo.getNome()+" removido com sucesso");
			} catch (Exception e) {
				request.setAttribute("temErroJsp", new Boolean(true));
				request.setAttribute("mensagemJsp", "Nao foi possivel remover o ativo " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			request.setAttribute("mensagemJsp", "Remoção de ativo, escolha um ativo e pressione remover");
		}
		
		request.setAttribute("liAtivos", lista_ativos);
		request.getRequestDispatcher(urlForwardOK).forward(request, response);

	}

}
