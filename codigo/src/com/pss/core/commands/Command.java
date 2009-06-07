package com.pss.core.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {
	
	protected String commandName;
	protected String urlForwardOK;
	protected String urlForwardNotOK;
	
	public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	public String getUrlForwardNotOK() {
		return urlForwardNotOK;
	}
	public String getUrlForwardOK() {
		return urlForwardOK;
	}
	public String getCommandName() {
		return commandName;
	}
}
