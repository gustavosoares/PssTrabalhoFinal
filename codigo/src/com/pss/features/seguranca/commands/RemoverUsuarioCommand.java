package com.pss.features.seguranca.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.commands.Command;
import com.pss.core.facade.FacadeBO;
import com.pss.features.seguranca.bo.UsuarioBO;
import com.pss.features.seguranca.model.Usuario;

public class RemoverUsuarioCommand extends Command {

	public RemoverUsuarioCommand() {
		commandName = "removerUsuario";
		urlForwardOK = "/seguranca/removerUsuario.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UsuarioBO usuarioBO = FacadeBO.getUsuarioBOInstance();
		
		List lista_usuarios = usuarioBO.listarUsuarios();
		
		String action = "";
		String usuarioIdStr = "";

		
		if (request.getParameter("subacao") !=  null) {
			action = request.getParameter("subacao").trim();
		}
		
		if (request.getParameter("usuarioId") != null) {
			usuarioIdStr = request.getParameter("usuarioId").trim();
		}
		
		
		if (action.equalsIgnoreCase("remove") && usuarioIdStr.length() > 0) {
			
			Integer usuarioId = new Integer(usuarioIdStr.trim());
			
			
			try {
				Usuario usuario = usuarioBO.buscarUsuarioPorId(usuarioId);
				usuarioBO.removerUsuario(usuario);
				lista_usuarios = usuarioBO.listarUsuarios();
				request.setAttribute("mensagemJsp", "Usuario "+usuario.getNome()+" removido com sucesso");
			} catch (Exception e) {
				request.setAttribute("temErroJsp", new Boolean(true));
				request.setAttribute("mensagemJsp", "Nao foi possivel remover o usuario " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			request.setAttribute("mensagemJsp", "Remoção de ativo, escolha um ativo e pressione remover");
		}
		
		request.setAttribute("liUsuarios", lista_usuarios);
		request.getRequestDispatcher(urlForwardOK).forward(request, response);
		
	}

}
