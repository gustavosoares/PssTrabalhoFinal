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
		<td widht="100" bordercolor="white">&nbsp;</td>
		<td>Ativos do tipo Servidor</td>
		<td><%= ativo_servidor %></td>
	</tr>
	<tr>
		<td widht="100" bordercolor="white">&nbsp;</td>
		<td>Ativo do tipo Aplicação</td>
		<td><%= ativo_aplicacao %></td>
	</tr>
	<tr>
		<td widht="100" bordercolor="white">&nbsp;</td>
		<td>Ativo do tipo Roteador</td>
		<td><%= ativo_roteador %></td>
	</tr>

<!--  abaixo padrao para todos os jsp -->
</table>

</td>
<%@ include file="includeRodape.jsp" %> 
<!-- fim padrao -->