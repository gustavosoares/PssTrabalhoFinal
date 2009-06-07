package com.pss.core.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InicioCommand extends Command {

	
	public InicioCommand() {
		commandName = "Inicio";
		urlForwardOK = "/core/index.jsp";
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(urlForwardOK).forward(request, response);
	}
}
