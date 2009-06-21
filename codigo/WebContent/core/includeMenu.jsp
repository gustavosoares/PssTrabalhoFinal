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
try {
	
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

<% 
} 

} catch (Exception e) {}
%>
<% 
try {
	
if (com.pss.core.facade.FacadeUtil.featureHabilitada("relacionamento")) {
%>
				<tr> 
					<td> 
						<a href="/gsoares/ServletController?acao=cadastrarRelacionamento">Cadastrar relacionamento</a>
					</td> 
				</tr>	
				<tr> 
					<td> 
						<a href="/gsoares/ServletController?acao=removerRelacionamento">Remover relacionamento</a>
					</td> 
				</tr>
				<tr> 
					<td> 
						<a href="/gsoares/ServletController?acao=listarRelacionamento">Mapear dependência</a>
					</td> 
				</tr>
<% 
} 

} catch (Exception e) {}
%>
<% 
try {
	
if (com.pss.core.facade.FacadeUtil.featureHabilitada("monitoracao")) {
%>
				<tr> 
					<td> 
						<a href="/gsoares/ServletController?acao=cadastrarMonitoracao">Adicionar observador de ativo</a>
					</td> 
				</tr>	
				<tr> 
					<td> 
						<a href="/gsoares/ServletController?acao=removerMonitoracao">Remover observador</a>
					</td> 
				</tr>
<% 
} 

} catch (Exception e) {}
%>
			</table>
		</td>
		
		
		
		