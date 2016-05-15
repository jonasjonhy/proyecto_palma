<%-- 
    Document   : validarEditarSiembraCobertura
    Created on : 30/11/2014, 12:58:07 AM
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
        <jsp:useBean id="cobertura" scope="page" class="Dto.Siembra_cobertura" />
        <jsp:setProperty name="cobertura" property="*" />
        <%
            String otroInsumo = request.getParameter("otroFer");
            String otraUnidad = request.getParameter("otroUni");
            String idInsumo = request.getParameter("idF");
            String idUnidad = request.getParameter("idU");
            
            String cantidadInsumo = request.getParameter("cantidad_e");
            String subtotal = request.getParameter("subtotal_e");
            String precioAplic = request.getParameter("precioAplicacion_e");
            String precioInsumo = request.getParameter("precioUnidad_e");
            String area_lote = request.getParameter("area_lote_e");
            
        HttpSession sesionU = request.getSession();
        String idLote = (String) sesionU.getAttribute("idLote");
        String idPropietario = (String) sesionU.getAttribute("id");
        String hacienda = (String) sesionU.getAttribute("idHacienda");
        //String idNivelacion = request.getParameter("id");
        cobertura.setIdLote(idLote);
        cobertura.setIdInsumo(idInsumo);
        cobertura.setIdUnidad(idUnidad);
        cobertura.setArea_lote(area_lote);
        cobertura.setCantidad(cantidadInsumo);
        cobertura.setSubtotal(subtotal);
        cobertura.setPrecioAplic(precioAplic);
        cobertura.setPrecioUnidad(precioInsumo);
        //nivelacionNutriente.setId(idNivelacion);
        
        
        if(idInsumo.equals("n") || idUnidad.equals("n")){
            %>
            <script type="text/javascript"> 
            alert("ERROR..Debe seleccionar algun INSUMO y/o UNIDAD\nVuelva a intentarlo")
            location.href="../jsp/tablaSiembraCobertura.jsp";
            </script>
            <%
        }else{
            boolean edito = facadePedro.editarCobertura(cobertura, otroInsumo, otraUnidad, idPropietario);
        if(!edito){
        %>
        <script type="text/javascript"> 
            alert("ERROR...NO SE HA PODIDO ACTUALIZAR EL REGISTRO DE SIEMBRA DE COBERTURA \n Vuelvalo a intentar")
            location.href="../jsp/tablaSiembraCobertura.jsp";
        </script>
        <%
        }else{   
        %>
            <script type="text/javascript"> 
                alert("EXITO...Se ha ACTUALIZADO el registro de SIEMBRA DE COBERTURA\n puede consultar la nueva informacion Ahora mismo!")
                location.href="../jsp/tablaSiembraCobertura.jsp";
            </script>
        <%
        }
        }
        %>    
    </body>
</html>