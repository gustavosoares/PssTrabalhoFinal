<%@ page import="com.pss.core.facade.*, javax.servlet.http.HttpSession, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	 pageEncoding="UTF-8"%>
<% 

String urlForwardNotOK = urlForwardNotOK = "/seguranca/index.jsp";
session.setAttribute("usuario", null);
session.invalidate();
request.getRequestDispatcher(urlForwardNotOK).forward(request, response);

	try {

	
	} catch (Exception e) {}

%>