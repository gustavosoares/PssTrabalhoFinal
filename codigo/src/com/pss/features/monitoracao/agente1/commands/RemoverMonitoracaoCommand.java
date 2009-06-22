package com.pss.features.monitoracao.agente1.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.bo.AtivoBO;
import com.pss.core.commands.Command;
import com.pss.core.facade.FacadeBO;
import com.pss.core.model.Ativo;
import com.pss.features.monitoracao.agente1.bo.Agente1BO;
import com.pss.features.monitoracao.agente1.model.Agente1;
import com.pss.features.seguranca.bo.UsuarioBO;
import com.pss.features.seguranca.model.Usuario;

public class RemoverMonitoracaoCommand extends Command {

	public RemoverMonitoracaoCommand() {
		commandName = "removerMonitoracao";
		urlForwardOK = "/monitoracao/removerMonitoracao.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Agente1BO agente1BO = FacadeBO.getAgente1BOInstance();
		UsuarioBO usuarioBO = FacadeBO.getUsuarioBOInstance();
		AtivoBO ativoBO = FacadeBO.getAtivoBOInstance();
		
		List lista_agentes = agente1BO.listarUsuariosComMonitoracao();
		List lista_ativos = null;
		
		String action = "";
		String ativoIdStr = "";
		String usuarioIdStr = "";
		
		if (request.getParameter("subacao") !=  null) {
			action = request.getParameter("subacao").trim();
		}
		
		if (request.getParameter("ativoId") != null) {
			ativoIdStr = request.getParameter("ativoId").trim();
		}
		
		if (request.getParameter("usuarioId") != null) {
			usuarioIdStr = request.getParameter("usuarioId").trim();
		}
		
		if (action.equalsIgnoreCase("remove") && ativoIdStr.length() > 0 && usuarioIdStr.length() > 0) {
				
			Agente1 agente1 = new Agente1();
			
			Integer ativoId = new Integer(ativoIdStr);
			Integer usuarioId = new Integer(usuarioIdStr);
			
			Ativo ativo = ativoBO.buscarAtivoPorId(ativoId);
			Usuario usuario = usuarioBO.buscarUsuarioPorId(usuarioId);
			
			agente1.setUsuario(usuario);
			agente1.setAtivo(ativo);
			
			try {
				agente1BO.removerObservador(agente1);
				request.setAttribute("mensagemJsp", "Usuario "+usuario.getNome()+" não está mais observando o ativo "+ativo.getNome());
			} catch (Exception e) {
				request.setAttribute("temErroJsp", new Boolean(true));
				request.setAttribute("mensagemJsp", "Nao foi possivel remover observador para o ativo selecionado: " + e.getMessage());
				e.printStackTrace();
			}
	
		} else {
			request.setAttribute("mensagemJsp", "Cadastro de monitoração de edição de um ativo");
		}
		
		request.setAttribute("liAgentes", lista_agentes);
		request.setAttribute("liAtivos", lista_ativos);
		request.getRequestDispatcher(urlForwardOK).forward(request, response);

	}

}
