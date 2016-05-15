<%-- 
    Document   : cerrarSesion
    Created on : 22/10/2014, 08:34:59 AM
    Author     : mauricio uribe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <%
            HttpSession sesionU = request.getSession();
            sesionU.invalidate();
            response.sendRedirect("../html/index.html");
            %>
            
    </body>
</html>
