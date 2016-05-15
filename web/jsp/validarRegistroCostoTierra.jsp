<%-- 
    Document   : validarRegistroNivelacionNutriente
    Created on : 19/11/2014, 11:35:47 PM
    Author     : mauricio uribe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
         <%
    

        %>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <jsp:useBean id="facadeRosemberg" scope="page" class="Facade.FacadeRosemberg" />
        <jsp:useBean id="costo" scope="page" class="Dto.CostoTierra" />

        <%
        
        String fecha = request.getParameter("fecha");
        String arriendo = request.getParameter("arriendo");
        
        HttpSession sesionU = request.getSession();
        
        String idPropietario = (String) sesionU.getAttribute("id");
        String hacienda = (String) sesionU.getAttribute("idHacienda");
        
        costo.setFecha(fecha);
        costo.setPrecioArriendo(arriendo);
                
        boolean registro = facadeRosemberg.registrarCostoTierra(costo,hacienda);
        
        if(!registro){
            %>
            <script type="text/javascript"> 
                  alert("ERROR... Este año ya se realizó un costo de tierra")
                  location.href="../jsp/tablaCostoTierra.jsp";
              </script>
            <%
            }else{
                %>
                <script type="text/javascript"> 
                  alert("REGISTRO EXITOSO\n Se ha registrado un nuevo registro de costos de tierra para este año")
                  location.href="../jsp/tablaCostoTierra.jsp";
                </script>
                <%
            }
            %>
    </body>
</html>
