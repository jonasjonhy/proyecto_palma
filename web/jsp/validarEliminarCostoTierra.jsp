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
        <jsp:useBean id="facadeRosemberg" scope="page" class="Facade.FacadeRosemberg" />
        <%
        HttpSession sesionU = request.getSession();
        
        String idPropietario = (String) sesionU.getAttribute("id");
        String hacienda = (String) sesionU.getAttribute("idHacienda");
        String idCosto = request.getParameter("idCosto");
        
        boolean elimino = facadeRosemberg.eliminarCostoTierra(idCosto);
        
        if(!elimino){
        %>
        <script type="text/javascript"> 
           
            alert("ERROR...NO SE HA PODIDO ELIMINAR EL REGISTRO DE COSTO DE TIERRA \n Vuelvalo a intentar")
            location.href="../jsp/tablaCostoTierra.jsp";
        </script>
        <%
        }else{   
        %>
            <script type="text/javascript"> 
                alert("EXITO...Se ha eliminado el registro de COSTO DE TIERRA!")
                location.href="../jsp/tablaCostoTierra.jsp";
            </script>
        <%
        }
        %>    
    </body>
</html>
