		<td width="250" valign="top">
			<table border="0" width="200"  align="left" valign="top" cellpadding="3">
				<tr> 
					<td> 
						<a href="/gsoares/ServletController?acao=cadastrarAtivo">Cadastrar um ativo</a>
					</td> 
				</tr>
				<tr> 
					<td> 
						<a href="/gsoares/ServletController?acao=removerAtivo">Remover um ativo</a>
					</td> 
				</tr>	
				<tr> 
					<td> 
						<a href="/gsoares/ServletController?acao=editarAtivo">Editar um ativo</a>
					</td> 
				</tr>
				<tr> 
					<td> 
						<a href="/gsoares/ServletController?acao=listarAtivo">Listar ativos</a>
					</td> 
				</tr>
<% 
if (com.pss.core.facade.FacadeUtil.featureHabilitada("seguranca")) {
%>
				<tr> 
					<td> 
						<a href="/gsoares/ServletController?acao=cadastrarUsuario">Cadastrar usuário</a>
					</td> 
				</tr>	
				<tr> 
					<td> 
						<a href="/gsoares/ServletController?acao=removerUsuario">Remover usuário</a>
					</td> 
				</tr>

<% } %>
			</table>
		</td>
		
		
		
		