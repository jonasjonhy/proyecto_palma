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
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <%
        String departamento=request.getParameter("opcion");
        %>
        <%= facade.cargarMunicipiosPorDepartamento(departamento) %>
    </body>
</html>
