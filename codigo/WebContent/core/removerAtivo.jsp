<%@ page import="com.pss.core.*, com.pss.core.bo.*, com.pss.core.facade.*, com.pss.core.model.*, java.util.*"%>

<!--  include padrao para os jsps -->
<%@ include file="includeTopo.jsp" %>
<%@ include file="includeMenu.jsp" %>
<td width="550">
<table border="1" width="550" align="left">
<!-- fim padrao -->


<% 
AtivoBO ativoBO = FacadeBO.getAtivoBOInstance();
List lista = (List)request.getAttribute("liAtivos");
Iterator itAtivos = lista.iterator();

%>
<tr valign="top">
	<td>
		<form name="AtivoForm" method="POST" action="/gsoares/ServletController" >
			<input type="hidden" name="acao" value="removerAtivo">	
			<input type="hidden" name="subacao" value="remove">
			<table>
				<tr align="left">
					<td>Ativo</td>
					<td>
			<select name="ativoId" size="1" >
				<option value="">Selecione um projeto</option>
<% 
	while (itAtivos.hasNext()) { 
		Ativo ativo = (Ativo) itAtivos.next();		
%>
				<option value="<%= ativo.getId().intValue()%>"><%= ativo.getNome()%></option>
<% 
	} 
%>
			</select>						
					</td>
				</tr>
				<tr align="center">
					<td colspan="2"> 
						<input type="submit" name="btnSubmit" value="Remover ativo">
					</td>
				</tr>
			</table>	
		</form>
	</td>
</tr>

<!--  abaixo padrao para todos os jsp -->
</table>

</td>
<%@ include file="includeRodape.jsp" %> 
<!-- fim padrao -->