<%-- 
    Document   : validarEliminarSiembraCobertura
    Created on : 29/11/2014, 11:06:28 PM
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
        String idCobertura = request.getParameter("idCobertura");
        
        boolean elimino = facadePedro.eliminarSiembraCobertura(idCobertura);
        
        if(!elimino){
        %>
        <script type="text/javascript"> 
            var id=<%= idLote %>;
            alert("ERROR...NO SE HA PODIDO ELIMINAR EL REGISTRO DE SIEMBRA DE COBERTURA \n Vuelvalo a intentar")
            location.href="../jsp/tablaSiembraCobertura.jsp";
        </script>
        <%
        }else{   
        %>
            <script type="text/javascript"> 
                alert("EXITO...Se ha eliminado el registro de SIEMBRA DE COBERTURA!")
                location.href="../jsp/tablaSiembraCobertura.jsp";
            </script>
        <%
        }
        %>    
    </body>
</html>

