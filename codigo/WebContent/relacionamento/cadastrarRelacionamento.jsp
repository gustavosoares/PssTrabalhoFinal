<%@ page import="com.pss.core.*, com.pss.core.bo.*, com.pss.core.facade.*, com.pss.core.model.*, 
com.pss.features.ativos.relacionamento.bo.*, java.util.*"%>

<!--  include padrao para os jsps -->
<%@ include file="/core/includeTopo.jsp" %>
<%@ include file="/core/includeMenu.jsp" %>
<td width="550">
<table border="1" width="550" align="left">
<!-- fim padrao -->


<% 
List lista = (List)request.getAttribute("liAtivos");
Iterator itAtivos = lista.iterator();
%>
<tr valign="top">
	<td>
		<form name="cadastraForm" method="POST" action="/gsoares/ServletController" >
			<input type="hidden" name="acao" value="cadastrarRelacionamento">	
			<input type="hidden" name="subacao" value="cadastra">
			<table>
				<tr align="left">
					<td>Ativo Pai</td>
					<td>
			<select name="ativoPai" size="1" >
				<option value="">Selecione o ativo pai</option>
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
				<tr align="left">
					<td>Ativo Filho</td>
					<td>
			<select name="ativoFilho" size="1" >
				<option value="">Selecione o ativo filho</option>
<% 
	for (int i=0; i < lista.size(); i++) {
		Ativo ativo = (Ativo) lista.get(i);
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
						<input type="submit" name="btnSubmit" value="Cadastrar">
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