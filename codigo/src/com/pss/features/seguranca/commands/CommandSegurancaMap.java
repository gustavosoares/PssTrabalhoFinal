package com.pss.features.seguranca.commands;

import java.util.HashMap;
import java.util.Map;

import com.pss.core.commands.Command;

public class CommandSegurancaMap {
	
	private static CommandSegurancaMap instance = null;

	private Map commandList;
	
	public static CommandSegurancaMap getInstance() {
		if (instance == null) {
			instance = new CommandSegurancaMap();
		}		
		return instance;
	}
	
	private CommandSegurancaMap() {
		
		this.commandList = new HashMap();
		
		Command comando = null;

		comando = new CadastrarUsuarioCommand();
		this.commandList.put(comando.getCommandName(), comando);

		comando = new RemoverUsuarioCommand();
		this.commandList.put(comando.getCommandName(), comando);

		comando = new FazerLoginCommand();
		this.commandList.put(comando.getCommandName(), comando);

	}


	public Map getCommandList() {
		return commandList;
	}

	public Command getCommand(String commandName) {
		return (Command) commandList.get(commandName);
	}

}

