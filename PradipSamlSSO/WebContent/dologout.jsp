<%@page import="com.onelogin.saml2.Auth"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<%
		Auth auth = new Auth(request, response);

		//String nameIdFormat="urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress";
		String nameIdFormat="urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified";
		
		String nameId=request.getParameter("name");
		//String nameId="009697";
		String sessionIndex=request.getParameter("session");
		String nameidNameQualifier="";
		String nameidSPNameQualifier="";
		
		//auth.logout(null, nameId, sessionIndex, nameIdFormat, nameidNameQualifier, nameidSPNameQualifier);
		auth.logout(null, nameId, sessionIndex, nameIdFormat);
		
	%>
</body>
</html>
