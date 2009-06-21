package com.pss.features.monitoracao.agente1.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.commands.Command;

public class RemoverMonitoracaoCommand extends Command {

	public RemoverMonitoracaoCommand() {
		commandName = "cadastrarMonitoracao";
		urlForwardOK = "/monitoracao/cadastrarMonitoracao.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
