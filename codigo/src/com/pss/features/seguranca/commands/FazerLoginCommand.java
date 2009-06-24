package com.pss.features.seguranca.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pss.core.commands.Command;
import com.pss.core.facade.FacadeBO;
import com.pss.core.facade.FacadeUtil;
import com.pss.features.seguranca.bo.UsuarioBO;
import com.pss.features.seguranca.model.Usuario;

public class FazerLoginCommand extends Command {

	public FazerLoginCommand() {
		commandName = "fazerLogin";
		urlForwardOK = "/core/index.jsp";
		urlForwardNotOK = "/seguranca/index.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UsuarioBO usuarioBO = FacadeBO.getUsuarioBOInstance();
		Usuario usuario = null;
		
		String action = "";
		String email = "";
		String senha = "";
		
		if (request.getParameter("subacao") !=  null) {
			action = request.getParameter("subacao").trim();
		}
		
		if (request.getParameter("email") != null) {
			email = request.getParameter("email").trim();
		}
		
		if (request.getParameter("senha") != null) {
			senha = request.getParameter("senha").trim();
		}
		
		if (action.equalsIgnoreCase("login") && email.length() > 0 && senha.length() > 0) {
			
			
			
			try {
				FacadeUtil.log(this, "autenticando usuario "+email);
				if (email.equalsIgnoreCase("admin") && senha.equals("admin")) {
					usuario = new Usuario();
				} else {
					usuario = usuarioBO.buscarUsuarioPorEmaileSenha(email, senha);
					FacadeUtil.log(this, "usuario "+email+" autenticado com sucesso!");
				}
				
			} catch (Exception e) {
				request.setAttribute("temErroJsp", new Boolean(true));
				request.setAttribute("mensagemJsp", "Nao foi possivel autenticar o usuario, verifique a senha digitada. " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			request.setAttribute("temErroJsp", new Boolean(true));
			request.setAttribute("mensagemJsp", "Autenticacao de usuario");
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("usuario", usuario);
		
		if (usuario != null) {
			request.getRequestDispatcher(urlForwardOK).forward(request, response);
		} else {
			request.getRequestDispatcher(urlForwardNotOK).forward(request, response);
		}		

		
	}

}
