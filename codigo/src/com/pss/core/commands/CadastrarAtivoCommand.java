package com.pss.core.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarAtivoCommand extends Command {

	public CadastrarAtivoCommand() {
		commandName = "CadastrarAtivo";
		urlForwardOK = "/core/cadastrarAtivo.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
