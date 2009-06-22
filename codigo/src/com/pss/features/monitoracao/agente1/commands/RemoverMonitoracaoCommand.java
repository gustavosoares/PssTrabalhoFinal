package com.pss.features.monitoracao.agente1.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.bo.AtivoBO;
import com.pss.core.commands.Command;
import com.pss.core.facade.FacadeBO;
import com.pss.core.facade.FacadeUtil;
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
		String agenteIdStr = "";
		String usuarioIdStr = "";
		
		if (request.getParameter("subacao") !=  null) {
			action = request.getParameter("subacao").trim();
		}
		
		if (request.getParameter("agenteId") != null) {
			agenteIdStr = request.getParameter("agenteId").trim();
		}
		
		if (request.getParameter("usuarioId") != null) {
			usuarioIdStr = request.getParameter("usuarioId").trim();
		}
		
		if (action.equalsIgnoreCase("remove") && agenteIdStr.length() > 0 && usuarioIdStr.length() > 0) {
							
			Integer agenteId = new Integer(agenteIdStr);
			Integer usuarioId = new Integer(usuarioIdStr);
			
			Ativo ativo = ativoBO.buscarAtivoPorId(agenteId);
			Usuario usuario = usuarioBO.buscarUsuarioPorId(usuarioId);
			
			Agente1 agente1 = agente1BO.buscarAgente1PorId(agenteId);
			
			try {
				agente1BO.removerObservador(agente1);
				request.setAttribute("mensagemJsp", "Usuario "+usuario.getNome()+" não está mais observando o ativo "+ativo.getNome());
			} catch (Exception e) {
				request.setAttribute("temErroJsp", new Boolean(true));
				request.setAttribute("mensagemJsp", "Nao foi possivel remover observador para o ativo selecionado: " + e.getMessage());
				e.printStackTrace();
			}
	
		} else if (action.equalsIgnoreCase("remove") && agenteIdStr.length() == 0 && usuarioIdStr.length() > 0){
			
			Integer usuarioId = new Integer(usuarioIdStr);
			Usuario usuario = usuarioBO.buscarUsuarioPorId(usuarioId);
			try {
				FacadeUtil.log(this, "obtendo lista de ativos do usuario "+usuario.getNome());
				lista_ativos = agente1BO.listarAtivoPorUsuario(usuario);
				/*
				lista_ativos = new ArrayList();
				for (int i = 0; i < lista_agentes_usuarios_por_ativo.size(); i++) {
					Agente1 agente_aux = (Agente1) lista_agentes_usuarios_por_ativo.get(i);
					lista_ativos.add(agente_aux.getAtivo());
				}
				*/
			} catch (Exception e) {
				request.setAttribute("temErroJsp", new Boolean(true));
				request.setAttribute("mensagemJsp", "Nao foi possivel listar os ativos do usuario "+usuario.getNome()+": " + e.getMessage());
				e.printStackTrace();
			}
			
		} else {
			request.setAttribute("mensagemJsp", "Remoção de monitoração de edição de um ativo");
		}
		
		request.setAttribute("liAgentes", lista_agentes);
		request.setAttribute("liAtivos", lista_ativos);
		request.getRequestDispatcher(urlForwardOK).forward(request, response);

	}

}
