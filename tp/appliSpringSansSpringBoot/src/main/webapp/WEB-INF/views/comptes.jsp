<%@ taglib prefix="c" uri="jakarta.tags.core"%> 
<html>
<head><title>comptes</title></head>
<body>
<h3>Client et ses comptes</h3>
numero_client: ${numClient} <br/>
 <table border="1">
 <tr><th>numero</th><th>label</th><th>solde</th></tr>
 <c:forEach var="c" items="${listeComptes}">
 <tr>
 <td>${c.numero}</td><td>${c.label}</td><td>${c.solde}</td>
 </tr>
 </c:forEach>
 </table>
 <p><b>${message}</b></p>
 <p> <a href="virement" >nouveau virement</a> </p> 
 <p> <a href="../../index.html" >retour index</a> </p> 
</body>
</html>
