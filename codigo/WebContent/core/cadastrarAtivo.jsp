<%@ page import="com.pss.core.*, com.pss.core.bo.*, com.pss.core.facade.*, java.util.*"%>

<!--  include padrao para os jsps -->
<%@ include file="includeTopo.jsp" %>
<%@ include file="includeMenu.jsp" %>
<td width="550">
<table border="1" width="550" align="left">
<!-- fim padrao -->


<% 
AtivoBO ativoBO = FacadeBO.getAtivoBOInstance();
%>
<tr valign="top">
	<td>
		<form name="cadastraAtivoForm" method="POST" action="/gsoares/ServletController" >
			<input type="hidden" name="acao" value="cadastrarAtivo">	
			<input type="hidden" name="subacao" value="cadastra">
			<table>
				<tr align="left">
					<td>Nome</td>
					<td>
						<input type="text" name="nome">
					</td>
				</tr>
				<tr align="left">
					<td>Descrição</td>
					<td>
					<!-- 
						<input type="text" name="nome">
					-->	
					<textarea name="descricao" rows="20" cols="70"></textarea>
					</td>
				</tr>
				
				<tr>
					<td>Tipo</td>
					<td> 
						<select name="tipo" size="1" >
							<option value="">Selecione o tipo do ativo</option>
							<option value="1">Servidor</option>
							<option value="2">Aplicação</option>
							<option value="3">Roteador</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Localização</td>
					<td> 
						<select name="localizacao" size="1" >
							<option value="">Selecione a localização do ativo</option>
							<option value="1">estoque</option>
							<option value="2">manutencao</option>
							<option value="3">producao</option>
						</select>
					</td>
				</tr>			
				<tr align="center">
					<td colspan="2"> 
						<input type="submit" name="btnSubmit" value="Cadastrar ativo">
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