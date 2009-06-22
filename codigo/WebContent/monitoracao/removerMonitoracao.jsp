<%@ page import="com.pss.core.*, com.pss.core.bo.*, com.pss.core.facade.*, com.pss.core.model.*, 
com.pss.features.monitoracao.agente1.bo.*, com.pss.features.seguranca.model.*, 
com.pss.features.monitoracao.agente1.model.*, java.util.*"%>

<!--  include padrao para os jsps -->
<%@ include file="/core/includeTopo.jsp" %>
<%@ include file="/core/includeMenu.jsp" %>
<td width="550">
<table border="1" width="550" align="left">
<!-- fim padrao -->


<% 
List lista_agentes = (List)request.getAttribute("liAgentes");
List lista_ativos = (List)request.getAttribute("liAtivos");
%>
<tr valign="top">
	<td>
		<form name="cadastraForm" method="POST" action="/gsoares/ServletController" >
			<input type="hidden" name="acao" value="removerMonitoracao">	
			<input type="hidden" name="subacao" value="remove">
			<table>
				<tr align="left">
					<td>Usu�rio</td>
					<td>
			<select name="usuarioId" size="1" >
				<option value="">Selecione o usu�rio</option>
<% 
	for (int i = 0; i < lista_agentes.size(); i++) {
		Agente1 agente1 = (Agente1) lista_agentes.get(i);
		Usuario usuario = agente1.getUsuario();
%>
				<option value="<%= usuario.getId().intValue() %>"><%= usuario.getNome()%></option>
<% 
	} 
%>
			</select>						
					</td>
				</tr>
				<tr align="left">
					<td>Ativo</td>
					<td>
			<select name="ativoId" size="1" >
				<option value="">Selecione o ativo</option>
<% 
	for (int i = 0; i < lista_ativos.size(); i++) {
		Ativo ativo = (Ativo) lista_ativos.get(i);
%>
				<option value="<%= ativo.getId().intValue() %>"><%= ativo.getNome()%></option>
<% 
	} 
%>
			</select>						
					</td>
				</tr>
				<tr align="center">
					<td colspan="2"> 
						<input type="submit" name="btnSubmit" value="Remover monitora��o">
					</td>
				</tr>
			</table>	
		</form>
	</td>
</tr>

<!--  abaixo padrao para todos os jsp -->
</table>

</td>
<%@ include file="/core/includeRodape.jsp" %> 
<!-- fim padrao -->