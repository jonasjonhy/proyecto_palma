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
        String idOtro = request.getParameter("idOtro");
        
        boolean elimino = facadePedro.eliminarOtro(idOtro);
        
        if(!elimino){
        %>
        <script type="text/javascript"> 
            var id=<%= idLote %>;
            alert("ERROR...NO SE HA PODIDO ELIMINAR EL REGISTRO DE SIEMBRA DE OTROS COSTOS \n Vuelvalo a intentar")
            location.href="../jsp/tablaOtros.jsp";
        </script>
        <%
        }else{   
        %>
            <script type="text/javascript"> 
                alert("EXITO...Se ha eliminado el registro de SIEMBRA DE OTROS COSTOS!")
                location.href="../jsp/tablaOtros.jsp";
            </script>
        <%
        }
        %>    
    </body>
</html>

