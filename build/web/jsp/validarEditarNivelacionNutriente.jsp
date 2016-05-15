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
        <jsp:useBean id="facadeMauricio" scope="page" class="Facade.FacadeMauricio" />
        <jsp:useBean id="nivelacionNutriente" scope="page" class="Dto.NivelacionNutriente" />
        <jsp:setProperty name="nivelacionNutriente" property="*" />
        <%
            String otroF = request.getParameter("otroFer");
            String otroU = request.getParameter("otroUni");
            String idFertilizante = request.getParameter("idF");
            String idUnidad = request.getParameter("idU");
            
            String cantidadFertilizante = request.getParameter("cantidadFertilizante_e");
            String subtotal = request.getParameter("subtotal_e");
            String precioAplicacion = request.getParameter("precioAplicacion_e");
            String precioFertilizante = request.getParameter("precioFertilizante_e");
            String areaLote = request.getParameter("areaLote_e");
            
        HttpSession sesionU = request.getSession();
        String idLote = (String) sesionU.getAttribute("idLote");
        String idPropietario = (String) sesionU.getAttribute("id");
        String hacienda = (String) sesionU.getAttribute("idHacienda");
        String idNivelacion = request.getParameter("id");
        nivelacionNutriente.setIdLote(idLote);
        nivelacionNutriente.setIdFertilizante(idFertilizante);
        nivelacionNutriente.setIdUnidad(idUnidad);
        nivelacionNutriente.setAreaLote(areaLote);
        nivelacionNutriente.setCantidadFertilizante(cantidadFertilizante);
        nivelacionNutriente.setSubtotal(subtotal);
        nivelacionNutriente.setPrecioAplicacion(precioAplicacion);
        nivelacionNutriente.setPrecioFertilizante(precioFertilizante);
        //nivelacionNutriente.setId(idNivelacion);
        
        
        if(idFertilizante.equals("n") || idUnidad.equals("n")){
            %>
            <script type="text/javascript"> 
            alert("ERROR..Debe seleccionar algun FERTILIZANTE y/o UNIDAD\nVuelva a intentarlo")
            location.href="../jsp/tablaNivelacionNutriente.jsp";
            </script>
            <%
        }else{
            boolean edito = facadeMauricio.editarNivelacionNutriente(nivelacionNutriente, otroF, otroU, idPropietario);
        if(!edito){
        %>
        <script type="text/javascript"> 
            alert("ERROR...NO SE HA PODIDO ACTUALIZAR EL REGISTRO DE NIVELACION \n Vuelvalo a intentar")
            location.href="../jsp/tablaNivelacionNutriente.jsp";
        </script>
        <%
        }else{   
        %>
            <script type="text/javascript"> 
                alert("EXITO...Se ha ACTUALIZADO el registro de nivelación de nutriente\n puede consultar la nueva informacion Ahora mismo!")
                location.href="../jsp/tablaNivelacionNutriente.jsp";
            </script>
        <%
        }
        }
        %>    
    </body>
</html>
