<%@ page import="com.pss.core.*, com.pss.core.bo.*, com.pss.core.facade.*, com.pss.features.ativos.relacionamento.bo.*, java.util.*"%>

<!--  include padrao para os jsps -->
<%@ include file="/core/includeTopo.jsp" %>
<%@ include file="/core/includeMenu.jsp" %>
<td width="550">
<table border="1" width="550" align="left">
<!-- fim padrao -->


<% 
UsuarioBO usuarioBO = FacadeBO.getUsuarioBOInstance();
%>
<tr valign="top">
	<td>
		<form name="cadastraForm" method="POST" action="/gsoares/ServletController" >
			<input type="hidden" name="acao" value="cadastrarUsuario">	
			<input type="hidden" name="subacao" value="cadastra">
			<table>
				<tr align="left">
					<td>Nome</td>
					<td>
						<input type="text" name="nome">
					</td>
				</tr>
				<tr align="left">
					<td>Email</td>
					<td>
						<input type="text" name="email">
					</td>
				</tr>
				<tr align="left">
					<td>Senha</td>
					<td>
						<input type="password" name="senha">
					</td>
				</tr>
				<!-- lista de orientadores -->
			
				<tr align="center">
					<td colspan="2"> 
						<input type="submit" name="btnSubmit" value="Cadastrar usuario">
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