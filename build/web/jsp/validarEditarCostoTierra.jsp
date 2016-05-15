<%-- 
    Document   : validarEditarNivelacionNutriente
    Created on : 22/11/2014, 09:30:03 AM
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
        <jsp:useBean id="costo" scope="page" class="Dto.CostoTierra" />
        
        <%
        
        String fecha = request.getParameter("fecha");
        String arriendo = request.getParameter("arriendo");
            
        HttpSession sesionU = request.getSession();
        
        String idPropietario = (String) sesionU.getAttribute("id");
        String hacienda = (String) sesionU.getAttribute("idHacienda");
        String idCosto = request.getParameter("id");
        String area=request.getParameter("area");
        costo.setFecha(fecha);
        costo.setPrecioArriendo(arriendo);
        costo.setAreaLotes(area);
        costo.setId(idCosto);
        boolean cosa=facadeRosemberg.editarCostoTierra(costo,hacienda);
        
        System.out.println(fecha+" "+arriendo+" "+idCosto);
       // boolean edito = facadeMauricio.editarNivelacionNutriente(nivelacionNutriente, otroF, otroU, idPropietario);
        
        if(!cosa){
        %>
        <script type="text/javascript"> 
            alert("ERROR... Este año ya se realizó un costo de tierra")
                  location.href="../jsp/tablaCostoTierra.jsp";
        </script>
        <%
        }else{   
        %>
            <script type="text/javascript"> 
                alert("EXITO...Se ha ACTUALIZADO el registro de costos de tierra\n puede consultar la nueva informacion Ahora mismo!")
                location.href="../jsp/tablaCostoTierra.jsp";
            </script>
        <%
        
        }
        %>    
    </body>
</html>
