package com.pss.core.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.bo.AtivoBO;
import com.pss.core.facade.FacadeBO;
import com.pss.core.model.Ativo;

public class ListarAtivoCommand extends Command {

	public ListarAtivoCommand() {
		commandName = "listarAtivo";
		urlForwardOK = "/core/listarAtivo.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AtivoBO ativoBO = FacadeBO.getAtivoBOInstance();
		
		request.setAttribute("mensagemJsp", "Listagem de ativo por tipo");
		request.getRequestDispatcher(urlForwardOK).forward(request, response);

	}

}
