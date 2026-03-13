<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>virement</title>
</head>
<body>
   <h2>Virement interne pour le client ${numClient}</h2>
   <form:form action="effectuerVirement"  modelAttribute="virementForm" method="POST">
        montant :  <form:input path="montant" /> <br/>
        numCptDeb : <form:select path="numCptDeb" >
						 <form:options items="${listeComptes}" itemLabel="numero" itemValue="numero"/>
 					</form:select> <br/>
        numCptCred :  <form:select path="numCptCred" >
						 <form:options items="${listeComptes}" itemLabel="numero" itemValue="numero"/>
 					</form:select>  <br/>
        <input type="submit" value="effectuer virement"/>
   </form:form>
</body>
</html>