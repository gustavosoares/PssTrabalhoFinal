<%@ page import="com.pss.core.*, com.pss.core.bo.*, com.pss.core.facade.*, com.pss.core.model.*, 
com.pss.features.monitoracao.agente1.bo.*, com.pss.features.seguranca.model.*, java.util.*"%>

<!--  include padrao para os jsps -->
<%@ include file="/core/includeTopo.jsp" %>
<%@ include file="/core/includeMenu.jsp" %>
<td width="550">
<table border="1" width="550" align="left">
<!-- fim padrao -->


<% 
List lista_ativos = (List)request.getAttribute("liAtivos");
List lista_usuarios = (List)request.getAttribute("liUsuarios");
%>
<tr valign="top">
	<td>
		<form name="cadastraForm" method="POST" action="/gsoares/ServletController" >
			<input type="hidden" name="acao" value="cadastrarMonitoracao">	
			<input type="hidden" name="subacao" value="cadastra">
			<table>
				<tr align="left">
					<td>Usuário</td>
					<td>
			<select name="usuarioId" size="1" >
				<option value="">Selecione o usuário</option>
<% 
	for (int i = 0; i < lista_usuarios.size(); i++) {
		Usuario usuario = (Usuario) lista_usuarios.get(i);
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
						<input type="submit" name="btnSubmit" value="Adicionar observador">
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