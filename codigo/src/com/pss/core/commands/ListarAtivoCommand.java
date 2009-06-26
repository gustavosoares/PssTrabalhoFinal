package com.pss.core.commands;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.bo.AtivoBO;
import com.pss.core.facade.FacadeBO;
import com.pss.core.model.Ativo;

public class ListarAtivoCommand extends Command {

	public ListarAtivoCommand() {
		commandName = "listarAtivo";
		urlForwardOK = "/core/listarAtivo.jsp";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AtivoBO ativoBO = FacadeBO.getAtivoBOInstance();
		
		int count_servidor = 0;
		int count_aplicacao = 0;
		int count_roteador = 0;
		int count_total = 0;
		//Localizacao
		int count_estoque = 0;
		int count_producao = 0;
		int count_manutencao = 0;
		
		Iterator itAtivos = ativoBO.listarAtivos().iterator();
		while (itAtivos.hasNext()) {
			Ativo ativo = (Ativo) itAtivos.next();
			if (ativo.getTipo().intValue() == ativo.TIPO_SERVIDOR) {
				count_servidor++;
			}else if (ativo.getTipo().intValue() == ativo.TIPO_APLICACAO) {
				count_aplicacao++;
			}else if (ativo.getTipo().intValue() == ativo.TIPO_ROTEADOR) {
				count_roteador++;
			}
			
			//Localizacao
			if (ativo.getLocalizacaoId().intValue() == ativo.LOCALIZACAO_ESTOQUE) {
				count_estoque++;
			}else if (ativo.getLocalizacaoId().intValue() == ativo.LOCALIZACAO_MANUTENCAO) {
				count_manutencao++;
			}else if (ativo.getLocalizacaoId().intValue() == ativo.LOCALIZACAO_PRODUCAO) {
				count_producao++;
			}
		}
		
		count_total = count_roteador + count_servidor + count_aplicacao;
		
		request.setAttribute("count_servidor", count_servidor);
		request.setAttribute("count_roteador", count_roteador);
		request.setAttribute("count_aplicacao", count_aplicacao);
		request.setAttribute("count_estoque", count_estoque);
		request.setAttribute("count_manutencao", count_manutencao);
		request.setAttribute("count_producao", count_producao);
		request.setAttribute("count_total", count_total);
		request.setAttribute("mensagemJsp", "Listagem de ativos");
		request.getRequestDispatcher(urlForwardOK).forward(request, response);

	}

}
