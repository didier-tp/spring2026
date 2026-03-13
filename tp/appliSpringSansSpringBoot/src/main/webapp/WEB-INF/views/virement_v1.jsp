<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>virement</title>
</head>
<body>
   <form action="effectuerVirement" method="POST">
        montant : <input name="montant" /> <br/>
        numCptDeb : <input name="numCptDeb" /> <br/>
        numCptCred : <input name="numCptCred" /> <br/>
        <input type="submit" value="effectuer virement"/>
   </form>
</body>
</html>