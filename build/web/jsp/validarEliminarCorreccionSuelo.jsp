<%-- 
    Document   : validarEliminarCorreccionSuelo
    Created on : 29/11/2014, 02:12:27 PM
    Author     : mauricio uribe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
        <jsp:useBean id="facadePedro" scope="page" class="Facade.FacadePedro" />
        <%
        HttpSession sesionU = request.getSession();
        String idLote = (String) sesionU.getAttribute("idLote");
        String idPropietario = (String) sesionU.getAttribute("id");
        String hacienda = (String) sesionU.getAttribute("idHacienda");
        String idCorreccion = request.getParameter("idCorreccion");
        
        boolean elimino = facadePedro.eliminarCorreccionSuelo(idCorreccion);
        
        if(!elimino){
        %>
        <script type="text/javascript"> 
            var id=<%= idLote %>;
            alert("ERROR...NO SE HA PODIDO ELIMINAR EL REGISTRO DE CORRECCION \n Vuelvalo a intentar")
            location.href="../jsp/tablaCorreccionSuelos.jsp";
        </script>
        <%
        }else{   
        %>
            <script type="text/javascript"> 
                alert("EXITO...Se ha eliminado el registro de nivelación!")
                location.href="../jsp/tablaCorreccionSuelos.jsp";
            </script>
        <%
        }
        %>    
    </body>
</html>

