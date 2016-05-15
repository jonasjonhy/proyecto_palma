<%-- 
    Document   : validarEditarPalmaSiembra
    Created on : 28/11/2014, 01:34:48 AM
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
        <jsp:useBean id="siembra" scope="page" class="Dto.PalmaSiembra" />
        <jsp:setProperty name="siembra" property="*" />
        <%
            System.out.println("id id id id --> "+siembra.getId());
            String cantidadPlantulas = request.getParameter("cantidadPlantulas_e");
            String subtotal = request.getParameter("subtotal_e");
            String precioPlantula = request.getParameter("precioPlantula_e");
            String areaLote = request.getParameter("areaLote_e");
            
        HttpSession sesionU = request.getSession();
        String idLote = (String) sesionU.getAttribute("idLote");
        String idPropietario = (String) sesionU.getAttribute("id");
        String hacienda = (String) sesionU.getAttribute("idHacienda");
        String idSiembra = request.getParameter("id");
        
        siembra.setCantidadPlantulas(cantidadPlantulas);
        siembra.setSubtotal(subtotal);
        siembra.setPrecioPlantula(precioPlantula);
        siembra.setAreaLote(areaLote);
        siembra.setIdLote(idLote);
        //nivelacionNutriente.setId(idNivelacion);
        
        boolean edito = facadeMauricio.editarPalmaSiembra(siembra);
        
        if(!edito){
        %>
        <script type="text/javascript"> 
            alert("ERROR...NO SE HA PODIDO ACTUALIZAR EL REGISTRO DE SIEMBRA DE PALMA \n Vuelvalo a intentar")
            location.href="../jsp/tablaPalmaSiembra.jsp";
        </script>
        <%
        }else{   
        %>
            <script type="text/javascript"> 
                alert("EXITO...Se ha ACTUALIZADO el registro de SIEMBRA DE PALMAS\n puede consultar la nueva informacion Ahora mismo!")
                location.href="../jsp/tablaPalmaSiembra.jsp";
            </script>
        <%
        }
        
        %>    
    </body>
</html>
