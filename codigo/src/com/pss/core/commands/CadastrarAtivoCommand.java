package com.pss.core.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.bo.AtivoBO;
import com.pss.core.facade.FacadeBO;
import com.pss.core.facade.FacadeUtil;
import com.pss.core.model.Ativo;


public class CadastrarAtivoCommand extends Command {

	public CadastrarAtivoCommand() {
		commandName = "cadastrarAtivo";
		urlForwardOK = "/core/cadastrarAtivo.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AtivoBO ativoBO = FacadeBO.getAtivoBOInstance();
		
		String action = "";
		String nomeStr = "";
		String tipoIdStr = "";
		String localizacaoIdStr = "";
		String descricao = "";
		
		if (request.getParameter("subacao") !=  null) {
			action = request.getParameter("subacao").trim();
		}
		
		if (request.getParameter("nome") != null) {
			nomeStr = request.getParameter("nome").trim();
		}
		
		if (request.getParameter("tipo") != null) {
			tipoIdStr = request.getParameter("tipo").trim();
		}
		
		if (request.getParameter("localizacao") != null) {
			localizacaoIdStr = request.getParameter("localizacao").trim();
		}
		
		if (request.getParameter("descricao") != null) {
			descricao = request.getParameter("descricao").trim();
		}
		
		if (action.equalsIgnoreCase("cadastra") && nomeStr.length() > 0 && tipoIdStr.length() > 0 && descricao.length() > 0 && localizacaoIdStr.length() > 0) {
			
			Ativo ativo = new Ativo();
			
			ativo.setNome(nomeStr);
			
			Integer tipoId = new Integer(tipoIdStr.trim());
			ativo.setTipo(tipoId);
			
			Integer localizacaoId = new Integer(localizacaoIdStr.trim());
			ativo.setLocalizacao(localizacaoId);
			
			ativo.setDescricao(descricao);
			
			try {
				ativoBO.cadastrarAtivo(ativo);
				request.setAttribute("mensagemJsp", "Ativo cadastrado com sucesso");
			} catch (Exception e) {
				request.setAttribute("temErroJsp", new Boolean(true));
				request.setAttribute("mensagemJsp", "Nao foi possivel cadastrar o ativo " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			request.setAttribute("temErroJsp", new Boolean(true));
			request.setAttribute("mensagemJsp", "<b>Cadastro de ativo, informe todos os dados e pressione cadastrar</br>");
		}
		
		request.getRequestDispatcher(urlForwardOK).forward(request, response);

	}

}
