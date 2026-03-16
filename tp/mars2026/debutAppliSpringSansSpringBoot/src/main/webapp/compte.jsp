<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="tp.appliSpring.core.service.ServiceCompte" %>
<%@ page import="tp.appliSpring.core.entity.Compte" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>compte</title>
</head>
<%
WebApplicationContext contextSpringWeb =
       WebApplicationContextUtils.getWebApplicationContext(application);
ServiceCompte serviceCompte = contextSpringWeb.getBean(ServiceCompte.class);
%>
<body>
    <h3>liste des comptes</h3>
    <ul>
   <% 
      java.util.List<Compte> comptes = serviceCompte.rechercherTousComptes(); 
	for(Compte c : comptes) {%>
		<li> <%=c%>	 </li>
	<% }%>	
	</ul>
</body>
</html>