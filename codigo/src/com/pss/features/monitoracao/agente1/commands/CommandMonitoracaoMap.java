package com.pss.features.monitoracao.agente1.commands;

import java.util.HashMap;
import java.util.Map;

import com.pss.core.commands.Command;

public class CommandMonitoracaoMap {
	
	private static CommandMonitoracaoMap instance = null;

	private Map commandList;
	
	public static CommandMonitoracaoMap getInstance() {
		if (instance == null) {
			instance = new CommandMonitoracaoMap();
		}		
		return instance;
	}
	
	private CommandMonitoracaoMap() {
		
		this.commandList = new HashMap();
		
		Command comando = null;

		comando = new CadastrarMonitoracaoCommand();
		this.commandList.put(comando.getCommandName(), comando);

		comando = new RemoverMonitoracaoCommand();
		this.commandList.put(comando.getCommandName(), comando);

	}


	public Map getCommandList() {
		return commandList;
	}

	public Command getCommand(String commandName) {
		return (Command) commandList.get(commandName);
	}

}

