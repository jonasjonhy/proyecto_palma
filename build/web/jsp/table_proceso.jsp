<%-- 
    Document   : select_dependientes_proceso
    Created on : 19/10/2014, 07:56:33 PM
    Author     : brialxsfxubun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tabla Informacion</title>
    </head>
    <body>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <%
            int criterio = Integer.parseInt(request.getParameter("criterio"));
            String palabra = request.getParameter("palabra");
            String dpto = request.getParameter("dpto");
            String mun = request.getParameter("mun");
            String tipo = request.getParameter("tipo");

        %>

        
            
                <%= facade.consultarPorCriterio(criterio, palabra, dpto, mun, tipo)%>
                
            
        
    </body>
</html>
