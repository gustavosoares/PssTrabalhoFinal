<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
	<TITLE>LPS - AUTENTICACAO DO SISTEMA DE INVENTARIO</TITLE>
	<META HTTP-equiv="Content-Type" content="text/html; charset=UTF-8">
	<META HTTP-EQUIV="pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="expires" CONTENT="-1">
	<META HTTP-EQUIV="cache-Control" CONTENT="no-cache">
</head>
<body>
<center>
<table border="1" width="550" align="left">
<tr valign="top">
	<td>
		<form name="cadastraForm" method="POST" action="/gsoares/ServletController" >
			<input type="hidden" name="acao" value="fazerLogin">	
			<input type="hidden" name="subacao" value="login">
			<table>
				<tr>
					<td></td>
					<td colspan="2"><b>Login<b></td>
				</tr>
				<tr align="left">
					<td><b>Email</b></td>
					<td>
						<input type="text" size="40" name="email">
					</td>
				</tr>
				<tr align="left">
					<td><b>Senha</b></td>
					<td>
						<input type="password" name="senha">
					</td>
				</tr>
				<tr align="center">
					<td colspan="2"> 
						<input type="submit" name="btnSubmit" value="Login">
					</td>
				</tr>
			</table>	
		</form>
	</td>
</tr>
</table>
</center>
</body>
</html>
