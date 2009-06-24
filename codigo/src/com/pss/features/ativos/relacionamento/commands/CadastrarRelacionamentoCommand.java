package com.pss.features.ativos.relacionamento.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.bo.AtivoBO;
import com.pss.core.commands.Command;
import com.pss.core.facade.FacadeBO;
import com.pss.core.model.Ativo;
import com.pss.features.ativos.relacionamento.bo.RelacionamentoAtivoBO;
import com.pss.features.ativos.relacionamento.model.RelacionamentoAtivo;

public class CadastrarRelacionamentoCommand extends Command {

	public CadastrarRelacionamentoCommand() {
		commandName = "cadastrarRelacionamento";
		urlForwardOK = "/relacionamento/cadastrarRelacionamento.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RelacionamentoAtivoBO relBO =  FacadeBO.getRelacionamentoAtivoBOInstance();
		AtivoBO ativoBO = FacadeBO.getAtivoBOInstance();
		
		List lista_ativos = ativoBO.listarAtivos();
		
		String action = "";
		String ativoPaiIdStr = "";
		String ativoFilhoIdStr = "";
		
		if (request.getParameter("subacao") !=  null) {
			action = request.getParameter("subacao").trim();
		}
		
		if (request.getParameter("ativoPai") != null) {
			ativoPaiIdStr = request.getParameter("ativoPai").trim();
		}
		
		if (request.getParameter("ativoFilho") != null) {
			ativoFilhoIdStr = request.getParameter("ativoFilho").trim();
		}
		
		if (action.equalsIgnoreCase("cadastra") && ativoPaiIdStr.length() > 0 && ativoFilhoIdStr.length() > 0) {
			
			if (ativoPaiIdStr.equalsIgnoreCase(ativoFilhoIdStr)) {
				request.setAttribute("temErroJsp", new Boolean(true));
				request.setAttribute("mensagemJsp", "Por favor, escolha ativos diferentes no cadastro do relacionamento");				
			} else {
				RelacionamentoAtivo rel = new RelacionamentoAtivo();
				
				Integer ativoPaiId = new Integer(ativoPaiIdStr);
				Integer ativoFilhoId = new Integer(ativoFilhoIdStr);
				
				Ativo ativoPai = ativoBO.buscarAtivoPorId(ativoPaiId);
				Ativo ativoFilho = ativoBO.buscarAtivoPorId(ativoFilhoId);
				
				rel.setAtivoPai(ativoPai);
				rel.setAtivoFilho(ativoFilho);
				
				try {
					relBO.cadastrarRelacionamento(ativoPai, ativoFilho);
					request.setAttribute("mensagemJsp", "Relacionamento cadastrado com sucesso");
				} catch (Exception e) {
					request.setAttribute("temErroJsp", new Boolean(true));
					request.setAttribute("mensagemJsp", "Nao foi possivel cadastrar o relaciomaneto " + e.getMessage());
					e.printStackTrace();
				}
			}
		} else {
			request.setAttribute("temErroJsp", new Boolean(true));
			request.setAttribute("mensagemJsp", "Cadastro de Relacionamento de ativos, informe todos os dados e pressione cadastrar");
		}
		
		request.setAttribute("liAtivos", lista_ativos);
		request.getRequestDispatcher(urlForwardOK).forward(request, response);
		
	}

}
