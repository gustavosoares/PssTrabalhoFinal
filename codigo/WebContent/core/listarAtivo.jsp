<%@ page import="com.pss.core.*, com.pss.core.bo.*, com.pss.core.facade.*, java.util.*"%>

<!--  include padrao para os jsps -->
<%@ include file="includeTopo.jsp" %>
<%@ include file="includeMenu.jsp" %>
<td width="550">
<table border="1" width="550" align="left">
<!-- fim padrao -->


<% 
AtivoBO ativoBO = FacadeBO.getAtivoBOInstance();

Integer ativo_servidor = (Integer) request.getAttribute("count_servidor");
Integer ativo_aplicacao = (Integer) request.getAttribute("count_aplicacao");
Integer ativo_roteador = (Integer) request.getAttribute("count_roteador");

Integer ativo_estoque = (Integer) request.getAttribute("count_estoque");
Integer ativo_manutencao = (Integer) request.getAttribute("count_manutencao");
Integer ativo_producao = (Integer) request.getAttribute("count_producao");

Integer total_ativos = (Integer) request.getAttribute("count_total");
%>
	<tr>
		<td colspan="3"><b><i>Relatório de Ativos</i></b></td>
	</tr>
	<tr>
		<td colspan="2"><b>Informação</b></td>
		<td><b>Quantidade</b></td>
	</tr>
	<tr>
		<td colspan="2">Total de Ativos</td>
		<td><%= total_ativos %></td>
	</tr>
	<tr>
		<td colspan="3"><b><i>Por tipo de ativo</i></b></td>
	</tr>
	<tr>
		<td widht="100" bordercolor="white">&nbsp;</td>
		<td>Servidor</td>
		<td><%= ativo_servidor %></td>
	</tr>
	<tr>
		<td widht="100" bordercolor="white">&nbsp;</td>
		<td>Aplicação</td>
		<td><%= ativo_aplicacao %></td>
	</tr>
	<tr>
		<td widht="100" bordercolor="white">&nbsp;</td>
		<td>Roteador</td>
		<td><%= ativo_roteador %></td>
	</tr>
	<tr>
		<td colspan="3"><b><i>Localização</i></b></td>
	</tr>
	<tr>
		<td widht="100" bordercolor="white">&nbsp;</td>
		<td>em Estoque</td>
		<td><%= ativo_estoque %></td>
	</tr>
	<tr>
		<td widht="100" bordercolor="white">&nbsp;</td>
		<td>em Manutenção</td>
		<td><%= ativo_manutencao %></td>
	</tr>
	<tr>
		<td widht="100" bordercolor="white">&nbsp;</td>
		<td>em Produção</td>
		<td><%= ativo_producao %></td>
	</tr>	
<!--  abaixo padrao para todos os jsp -->
</table>

</td>
<%@ include file="includeRodape.jsp" %> 
<!-- fim padrao -->