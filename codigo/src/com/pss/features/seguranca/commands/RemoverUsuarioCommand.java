package com.pss.features.seguranca.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.commands.Command;

public class RemoverUsuarioCommand extends Command {

	public RemoverUsuarioCommand() {
		commandName = "removerUsuario";
		urlForwardOK = "/seguranca/removerUsuario.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
