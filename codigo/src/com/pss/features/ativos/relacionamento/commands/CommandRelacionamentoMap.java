package com.pss.features.ativos.relacionamento.commands;

import java.util.HashMap;
import java.util.Map;

import com.pss.core.commands.Command;

public class CommandRelacionamentoMap {
	
	private static CommandRelacionamentoMap instance = null;

	private Map commandList;
	
	public static CommandRelacionamentoMap getInstance() {
		if (instance == null) {
			instance = new CommandRelacionamentoMap();
		}		
		return instance;
	}
	
	private CommandRelacionamentoMap() {
		
		this.commandList = new HashMap();
		
		Command comando = null;

		comando = new CadastrarRelacionamentoCommand();
		this.commandList.put(comando.getCommandName(), comando);

		comando = new RemoverRelacionamentoCommand();
		this.commandList.put(comando.getCommandName(), comando);

		comando = new ListarRelacionamentoCommand();
		this.commandList.put(comando.getCommandName(), comando);

	}


	public Map getCommandList() {
		return commandList;
	}

	public Command getCommand(String commandName) {
		return (Command) commandList.get(commandName);
	}

}

