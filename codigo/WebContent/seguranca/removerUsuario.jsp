<%@ page import="com.pss.core.*, com.pss.core.bo.*, com.pss.core.facade.*, com.pss.features.seguranca.bo.*, 
com.pss.features.seguranca.model.*, java.util.*"%>

<!--  include padrao para os jsps -->
<%@ include file="/core/includeTopo.jsp" %>
<%@ include file="/core/includeMenu.jsp" %>
<td width="550">
<table border="1" width="550" align="left">
<!-- fim padrao -->


<%

UsuarioBO usuarioBO = FacadeBO.getUsuarioBOInstance();
List lista = (List)request.getAttribute("liUsuarios");
Iterator itUsuarios = lista.iterator();

%>
<tr valign="top">
	<td>
		<form name="usuarioForm" method="POST" action="/gsoares/ServletController" >
			<input type="hidden" name="acao" value="removerUsuario">	
			<input type="hidden" name="subacao" value="remove">
			<table>
				<tr align="left">
					<td>Usuário</td>
					<td>
			<select name="usuarioId" size="1" >
				<option value="">Selecione o usuário para remoção</option>
<% 
	while (itUsuarios.hasNext()) { 
		Usuario usuario = (Usuario) itUsuarios.next();		
%>
				<option value="<%= usuario.getId().intValue()%>"><%= usuario.getNome()%> - <%= usuario.getEmail()%></option>
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