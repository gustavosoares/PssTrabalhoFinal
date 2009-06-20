package com.pss.features.ativos.relacionamento.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.commands.Command;
import com.pss.core.facade.FacadeBO;
import com.pss.features.ativos.relacionamento.bo.RelacionamentoAtivoBO;
import com.pss.features.ativos.relacionamento.model.RelacionamentoAtivo;

public class RemoverRelacionamentoCommand extends Command {

	public RemoverRelacionamentoCommand() {
		commandName = "removerRelacionamento";
		urlForwardOK = "/relacionamento/removerRelacionamento.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RelacionamentoAtivoBO relBO =  FacadeBO.getRelacionamentoAtivoBOInstance();
		
		List lista_relacionamentos = relBO.listarRelacionamentos();
		
		String action = "";
		String relacionamentoIdStr = "";

		
		if (request.getParameter("subacao") !=  null) {
			action = request.getParameter("subacao").trim();
		}
		
		if (request.getParameter("relacionamentoId") != null) {
			relacionamentoIdStr = request.getParameter("relacionamentoId").trim();
		}
		
		
		if (action.equalsIgnoreCase("remove") && relacionamentoIdStr.length() > 0) {
			
			Integer relacionamentoId = new Integer(relacionamentoIdStr.trim());
			
			try {
				RelacionamentoAtivo rel = relBO.buscarRelacionamentoPorId(relacionamentoId);
				relBO.removerRelacionamento(rel);
				lista_relacionamentos = relBO.listarRelacionamentos();
				request.setAttribute("mensagemJsp", "Relacionamento "+rel.getAtivoPai().getNome()+" <-> "+rel.getAtivoFilho().getNome()+" removido com sucesso");
			} catch (Exception e) {
				request.setAttribute("temErroJsp", new Boolean(true));
				request.setAttribute("mensagemJsp", "Nao foi possivel remover o relacionamento " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			request.setAttribute("mensagemJsp", "Remoção de relacionamento de ativo, escolha um relacionamento e pressione remover");
		}
		
		request.setAttribute("liRelacionamentos", lista_relacionamentos);
		request.getRequestDispatcher(urlForwardOK).forward(request, response);
		
	}

}
