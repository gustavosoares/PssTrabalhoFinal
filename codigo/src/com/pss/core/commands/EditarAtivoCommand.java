package com.pss.core.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditarAtivoCommand extends Command {

	public EditarAtivoCommand() {
		commandName = "editarAtivo";
		urlForwardOK = "/core/editarAtivo.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
