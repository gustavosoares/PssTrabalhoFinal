<%@ page import="com.pss.core.*, com.pss.core.bo.*, com.pss.core.facade.*, com.pss.core.model.*, 
com.pss.features.ativos.relacionamento.bo.*, com.pss.features.ativos.relacionamento.model.*, java.util.*"%>

<!--  include padrao para os jsps -->
<%@ include file="/core/includeTopo.jsp" %>
<%@ include file="/core/includeMenu.jsp" %>
<td width="550">
<table border="1" width="550" align="left">
<!-- fim padrao -->


<% 
List lista = (List)request.getAttribute("liAtivos");
Iterator itAtivos = lista.iterator();
List lista_relacionamentos = (List)request.getAttribute("liRelacionamentos");
%>
<tr valign="top">
	<td>
		<form name="listaForm" method="POST" action="/gsoares/ServletController" >
			<input type="hidden" name="acao" value="listarRelacionamento">	
			<input type="hidden" name="subacao" value="lista">
			<table>
				<tr align="left">
					<td>Ativo</td>
					<td>
			<select name="ativo" size="1" >
				<option value="">Selecione um ativo para mapear as depedências</option>
<% 
	while (itAtivos.hasNext()) { 
		Ativo ativo = (Ativo) itAtivos.next();		
%>
				<option value="<%= ativo.getId().intValue() %>"><%= ativo.getNome()%></option>
<% 
	} 
%>
			</select>						
					</td>
				</tr>
<%
if (lista_relacionamentos != null) {
%>
<table border="1">
	<tr>
		<td colspan="2"><center><b><i>Relacionamentos</i></b></center></td>
	</tr>
	<tr>
		<td><center><b>de</b></center></td>
		<td><center><b>para</b></center></td>
	</tr>
<% 
	for (int i = 0; i < lista_relacionamentos.size(); i++) { 
		RelacionamentoAtivo rel = (RelacionamentoAtivo) lista_relacionamentos.get(i);		
%>
				
	<tr>
		<td><center><%= rel.getAtivoPai().getNome() %></center></td>
		<td><center><%= rel.getAtivoFilho().getNome() %></center></td>
	</tr>

<% 
	} 
%>
</table>
<% 
}
%>				

				<tr align="center">
					<td colspan="2"> 
						<input type="submit" name="btnSubmit" value="Mapear dependência">
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