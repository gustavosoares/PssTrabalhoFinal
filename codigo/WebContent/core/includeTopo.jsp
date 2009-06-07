<%@ page language="java" contentType="text/html; charset=UTF-8" 
	 pageEncoding="UTF-8"%>
<% 
	/* ADICIONAR TESTE PARA VERIFICAR SE ESTA LOGADO OU NAO CHECANDO O FEATURE MAPPER */
	
	String mensagemJsp = "";
	Boolean temErroJsp = new Boolean(false);

	if (request.getAttribute("mensagemJsp") != null ) {
		mensagemJsp = (String) request.getAttribute("mensagemJsp");
	}
	if (request.getAttribute("temErroJsp") != null ) {
		temErroJsp = (Boolean) request.getAttribute("temErroJsp");
	}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
<head> 
	<TITLE>LPS - SISTEMA DE INVENTARIO</TITLE>
	<META HTTP-equiv="Content-Type" content="text/html; charset=UTF-8">
	<META HTTP-EQUIV="pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="expires" CONTENT="-1">
	<META HTTP-EQUIV="cache-Control" CONTENT="no-cache">
</head>
<body>
<table border="3" width="800" valign="top" bordercolor="black" cellpadding="4">
	<tr>
		<td colspan="2" width="800">
			<table border="0"  align="center">
				<tr> 
					<td> 
						<a href="/gsoares/"><font size="6">LPS - Sistema de Inventario</font></a>
					</td> 
				</tr>			
			</table>
		</td>
	</tr>
	<tr>
		<td width="250" valign="top" border="0";>
		</td>
		<td width="550" >
			<br/><br/><font color="<%= temErroJsp.booleanValue() == false ? "black" : "red" %>">
				<%= mensagemJsp %> </font>
		</td>
	</tr>
	<tr>
