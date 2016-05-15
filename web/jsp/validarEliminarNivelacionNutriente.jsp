<%-- 
    Document   : validarEliminarNivelacionNutriente
    Created on : 19/11/2014, 07:05:51 PM
    Author     : mauricio uribe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
        <jsp:useBean id="facadeMauricio" scope="page" class="Facade.FacadeMauricio" />
        <%
        HttpSession sesionU = request.getSession();
        String idLote = (String) sesionU.getAttribute("idLote");
        String idPropietario = (String) sesionU.getAttribute("id");
        String hacienda = (String) sesionU.getAttribute("idHacienda");
        String idNivelacion = request.getParameter("idNivelacion");
        
        boolean elimino = facadeMauricio.eliminarNivelacionNutriente(idNivelacion);
        
        if(!elimino){
        %>
        <script type="text/javascript"> 
            var id=<%= idLote %>;
            alert("ERROR...NO SE HA PODIDO ELIMINAR EL REGISTRO DE NIVELACION \n Vuelvalo a intentar")
            location.href="../jsp/tablaNivelacionNutriente.jsp";
        </script>
        <%
        }else{   
        %>
            <script type="text/javascript"> 
                alert("EXITO...Se ha eliminado el registro de nivelación!")
                location.href="../jsp/tablaNivelacionNutriente.jsp";
            </script>
        <%
        }
        %>    
    </body>
</html>
