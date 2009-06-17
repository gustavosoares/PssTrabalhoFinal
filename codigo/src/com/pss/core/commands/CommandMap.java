package com.pss.core.commands;

import java.util.HashMap;
import java.util.Map;

import com.pss.core.facade.FacadeUtil;

public class CommandMap {
	private static CommandMap instance = null;

	private Map commandList;
	
	public static CommandMap getInstance() {
		if (instance == null) {
			instance = new CommandMap();
		}		
		return instance;
	}
	
	private CommandMap() {
		
		this.commandList = new HashMap();
		
		Command comando = new InicioCommand();
		this.commandList.put(comando.getCommandName(), comando);
		
		/**
		 * CORE
		 */
		comando = new CadastrarAtivoCommand();
		this.commandList.put(comando.getCommandName(), comando);

		comando = new EditarAtivoCommand();
		this.commandList.put(comando.getCommandName(), comando);

		comando = new RemoverAtivoCommand();
		this.commandList.put(comando.getCommandName(), comando);

		comando = new ListarAtivoCommand();
		this.commandList.put(comando.getCommandName(), comando);

		
		/**
		 * FEATURES
		 */
		
		/**
		 * SEGURANCA
		 */
		if (FacadeUtil.featureHabilitada("seguranca")) {
			FacadeUtil.log("carregando commands de seguranca");
			this.commandList.putAll(com.pss.features.seguranca.commands.CommandSegurancaMap.getInstance().getCommandList());
		}
	
	}

	/*
	public Map getCommandList() {
		return commandList;
	}
	*/
	public Command getCommand(String commandName) {
		return (Command) commandList.get(commandName);
	}

}
