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
Ativo ativo = (Ativo) request.getAttribute("ativo");
Iterator itAtivos = lista.iterator();

%>
<tr valign="top">
	<td>
		<form name="AtivoForm" method="POST" action="/gsoares/ServletController" >
			<input type="hidden" name="acao" value="editarAtivo">	
			<input type="hidden" name="subacao" value="edita">
<% if (ativo == null) { %>
			<table>
				<tr align="left">
					<td>Ativo</td>
					<td>
			<select name="ativoId" size="1" >
				<option value="">Selecione um ativo para edição</option>
<% 
	while (itAtivos.hasNext()) { 
		Ativo ativo_aux = (Ativo) itAtivos.next();		
%>
				<option value="<%= ativo_aux.getId().intValue()%>"><%= ativo_aux.getNome()%></option>
<% 
	} 
%>
			</select>						
					</td>
				</tr>
				<tr align="center">
					<td colspan="2"> 
						<input type="submit" name="btnSubmit" value="Editar ativo">
					</td>
				</tr>
			</table>
<% } else { %>
			<table>
				<tr align="left">
					<td>Id</td>
					<td>
						<input type="text" size="10" readonly="true" name="ativoId" value="<%= ativo.getId().intValue() %>">
					</td>
				</tr>
				<tr align="left">
					<td>Data de criação</td>
					<td>
						<input type="text" size="15" readonly="true" name="dataCriacao" value="<%= ativo.getDataCriacaoFormatada() %>">
					</td>
				</tr>
				<tr align="left">
					<td>Data de alteração</td>
					<td>
						<input type="text" size="15" readonly="true" name="dataAlteracao" value="<%= ativo.getDataAlteracaoFormatada() %>">
					</td>
				</tr>				
				<tr align="left">
					<td>Nome</td>
					<td>
						<input type="text" name="nome" value="<%= ativo.getNome() %>">
					</td>
				</tr>
				<tr align="left">
					<td>Descrição</td>
					<td>
					<!-- 
						<input type="text" name="nome">
					-->	
					<textarea name="descricao" rows="30" cols="70"><%= ativo.getDescricao() %></textarea>
					</td>
				</tr>
				<tr>
					<td>Localização</td>
					<td> 
						<select name="localizacaoId" size="1" >
							<option value="">Selecione a localização do ativo</option>
							<option value="1" <% if (String.valueOf(ativo.getLocalizacaoId().intValue()).equals("1")) { %> selected <% } %> >Estoque</option>
							<option value="2" <% if (String.valueOf(ativo.getLocalizacaoId().intValue()).equals("2")) { %> selected <% } %> >Manutenção</option>
							<option value="3" <% if (String.valueOf(ativo.getLocalizacaoId().intValue()).equals("3")) { %> selected <% } %> >Produção</option>
						</select>
					</td>
				</tr>	
				<tr>
					<td>Tipo</td>
					<td> 
						<select name="tipoId" size="1" >
							<option value="">Selecione o tipo do ativo</option>
							<option value="1" <% if (String.valueOf(ativo.getTipo().intValue()).equals("1")) { %> selected <% } %> >Servidor</option>
							<option value="2" <% if (String.valueOf(ativo.getTipo().intValue()).equals("2")) { %> selected <% } %> >Aplicação</option>
							<option value="3" <% if (String.valueOf(ativo.getTipo().intValue()).equals("3")) { %> selected <% } %> >Roteador</option>
						</select>
					</td>
				</tr>				
				<tr align="center">
					<td colspan="2"> 
						<input type="submit" name="btnSubmit" value="Atualizar ativo">
					</td>
				</tr>
			</table>
<% } %>
		</form>
	</td>
</tr>

<!--  abaixo padrao para todos os jsp -->
</table>

</td>
<%@ include file="includeRodape.jsp" %> 
<!-- fim padrao -->