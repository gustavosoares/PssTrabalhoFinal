package com.pss.core.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.bo.AtivoBO;
import com.pss.core.facade.FacadeBO;
import com.pss.core.model.Ativo;

public class EditarAtivoCommand extends Command {

	public EditarAtivoCommand() {
		commandName = "editarAtivo";
		urlForwardOK = "/core/editarAtivo.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AtivoBO ativoBO = FacadeBO.getAtivoBOInstance();
		Ativo ativo = null;
		List lista_ativos = ativoBO.listarAtivos();
		
		String action = "";
		
		String AtivoIdStr = "";
		String ativoNome = "";
		String ativoDescricao = "";
		String ativoTipoIdStr = "";
		String localizacaoIdStr = "";
		
		if (request.getParameter("subacao") !=  null) {
			action = request.getParameter("subacao").trim();
		}
		
		if (request.getParameter("ativoId") != null) {
			AtivoIdStr = request.getParameter("ativoId").trim();
		}
		
		if (request.getParameter("nome") != null) {
			ativoNome = request.getParameter("nome").trim();
		}
		
		if (request.getParameter("descricao") != null) {
			ativoDescricao = request.getParameter("descricao").trim();
		}
		
		if (request.getParameter("tipoId") != null) {
			ativoTipoIdStr = request.getParameter("tipoId").trim();
		}
		
		if (request.getParameter("localizacaoId") != null) {
			localizacaoIdStr = request.getParameter("localizacaoId").trim();
		}
		
		/*
		System.out.println("ativoNome: "+ativoNome);
		System.out.println("ativodesc: "+ativoDescricao);
		System.out.println("ativoid: "+AtivoIdStr);
		System.out.println("ativotipo: "+ativoTipoIdStr);
		*/
		
		if (action.equalsIgnoreCase("edita") && AtivoIdStr.length() > 0 && ativoNome.length() == 0) {
			
			Integer ativoId = new Integer(AtivoIdStr.trim());
			
			try {
				ativo = ativoBO.buscarAtivoPorId(ativoId);
			} catch (Exception e) {
				request.setAttribute("temErroJsp", new Boolean(true));
				request.setAttribute("mensagemJsp", "Nao foi possivel buscar o ativo selecionado para edição" + e.getMessage());
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("edita") && AtivoIdStr.length() > 0 && ativoNome.length() > 0 && ativoDescricao.length() > 0 && ativoTipoIdStr.length() > 0) { 
			
			Integer ativoId = new Integer(AtivoIdStr.trim());
			Integer ativoTipoId = new Integer(ativoTipoIdStr.trim());
			Integer localizacaoId = new Integer(localizacaoIdStr.trim());
			
			try {
				ativo = ativoBO.buscarAtivoPorId(ativoId);
				ativo.setNome(ativoNome);
				ativo.setDescricao(ativoDescricao);
				ativo.setLocalizacao(localizacaoId);
				ativo.setTipo(ativoTipoId);
				
				ativoBO.editarAtivo(ativo);
				
				request.setAttribute("mensagemJsp", "Ativo "+ativo.getNome()+" editado com sucesso");
			} catch (Exception e) {
				request.setAttribute("temErroJsp", new Boolean(true));
				request.setAttribute("mensagemJsp", "Nao foi possivel editar o ativo" + e.getMessage());
				e.printStackTrace();
			}
			
		} else {
			request.setAttribute("temErroJsp", new Boolean(true));
			request.setAttribute("mensagemJsp", "Edição de ativo, escolha um ativo e pressione editar");
		}
		
		request.setAttribute("liAtivos", lista_ativos);
		request.setAttribute("ativo", ativo);
		request.getRequestDispatcher(urlForwardOK).forward(request, response);

	}

}
