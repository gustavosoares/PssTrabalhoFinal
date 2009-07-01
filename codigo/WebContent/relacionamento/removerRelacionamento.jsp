<%@ page import="com.pss.core.*, com.pss.core.bo.*, com.pss.core.facade.*, 
com.pss.features.ativos.relacionamento.bo.*, com.pss.features.ativos.relacionamento.model.*, java.util.*"%>

<!--  include padrao para os jsps -->
<%@ include file="/core/includeTopo.jsp" %>
<%@ include file="/core/includeMenu.jsp" %>
<td width="550">
<table border="1" width="550" align="left">
<!-- fim padrao -->


<%

List lista = (List)request.getAttribute("liRelacionamentos");
Iterator itRelacionamentos = lista.iterator();

%>
<tr valign="top">
	<td>
		<form name="removeForm" method="POST" action="/gsoares/ServletController" >
			<input type="hidden" name="acao" value="removerRelacionamento">	
			<input type="hidden" name="subacao" value="remove">
			<table>
				<tr align="left">
					<td>Relacionamentos</td>
					<td>
			<select name="relacionamentoId" size="1" >
				<option value="">Selecione o relacionamento para remoção</option>
<% 
	while (itRelacionamentos.hasNext()) { 
		RelacionamentoAtivo rel = (RelacionamentoAtivo) itRelacionamentos.next();		
%>
				<option value="<%= rel.getId().intValue()%>"><%= rel.getAtivoPai().getNome()%> COM <%= rel.getAtivoFilho().getNome() %></option>
<% 
	} 
%>
			</select>						
					</td>
				</tr>
				<tr align="center">
					<td colspan="2"> 
						<input type="submit" name="btnSubmit" value="Remover">
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