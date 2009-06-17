package com.pss.features.seguranca.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.commands.Command;
import com.pss.core.facade.FacadeBO;
import com.pss.features.seguranca.bo.UsuarioBO;
import com.pss.features.seguranca.model.Usuario;

public class CadastrarUsuarioCommand extends Command {

	public CadastrarUsuarioCommand() {
		commandName = "cadastrarUsuario";
		urlForwardOK = "/seguranca/cadastrarUsuario.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UsuarioBO usuarioBO = FacadeBO.getUsuarioBOInstance();
		
		String action = "";
		String nomeStr = "";
		String email = "";
		String senha = "";
		
		if (request.getParameter("subacao") !=  null) {
			action = request.getParameter("subacao").trim();
		}
		
		if (request.getParameter("nome") != null) {
			nomeStr = request.getParameter("nome").trim();
		}
		
		if (request.getParameter("email") != null) {
			email = request.getParameter("email").trim();
		}
		
		if (request.getParameter("senha") != null) {
			senha = request.getParameter("senha").trim();
		}
		
		if (action.equalsIgnoreCase("cadastra") && nomeStr.length() > 0 && email.length() > 0 && senha.length() > 0) {
			
			Usuario usuario = new Usuario();
			
			usuario.setNome(nomeStr);
			usuario.setEmail(email);
			usuario.setSenha(senha);
			
			try {
				usuarioBO.cadastrarUsuario(usuario);
				request.setAttribute("mensagemJsp", "Usuario cadastrado com sucesso");
			} catch (Exception e) {
				request.setAttribute("temErroJsp", new Boolean(true));
				request.setAttribute("mensagemJsp", "Nao foi possivel cadastrar o usuario " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			request.setAttribute("mensagemJsp", "Cadastro de Usuario, informe todos os dados e pressione cadastrar");
		}
		
		request.getRequestDispatcher(urlForwardOK).forward(request, response);
		
	}

}
