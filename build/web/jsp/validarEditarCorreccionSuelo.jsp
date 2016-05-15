<%-- 
    Document   : validarEditarCorreccionSuelo
    Created on : 29/11/2014, 11:47:25 AM
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
        <jsp:useBean id="correccion" scope="page" class="Dto.Correccion_suelo" />
        <jsp:setProperty name="correccion" property="*" />
        <%
            String otraEnmienda = request.getParameter("otroFer");
            String otraUnidad = request.getParameter("otroUni");
            String idEnmienda = request.getParameter("idF");
            String idUnidad = request.getParameter("idU");
            
            String cantidadEnmienda = request.getParameter("cantidad_e");
            String subtotal = request.getParameter("subtotal_e");
            String precioAplicacion = request.getParameter("precioAplicacion_e");
            String precioEnmienda = request.getParameter("precio_enmienda_e");
            String areaLote = request.getParameter("area_aplicacion_e");
            
        HttpSession sesionU = request.getSession();
        String idLote = (String) sesionU.getAttribute("idLote");
        String idPropietario = (String) sesionU.getAttribute("id");
        String hacienda = (String) sesionU.getAttribute("idHacienda");
        //String idNivelacion = request.getParameter("id");
        correccion.setIdLote(idLote);
        correccion.setIdEnmienda(idEnmienda);
        correccion.setIdUnidad(idUnidad);
        correccion.setArea_aplicacion(areaLote);
        correccion.setCantidad(cantidadEnmienda);
        correccion.setSubtotal(subtotal);
        correccion.setPrecio_aplic_enmienda(precioAplicacion);
        correccion.setPrecio_enmienda(precioEnmienda);
        //nivelacionNutriente.setId(idNivelacion);
        
        
        if(idEnmienda.equals("n") || idUnidad.equals("n")){
            %>
            <script type="text/javascript"> 
            alert("ERROR..Debe seleccionar alguna ENMIENDA y/o UNIDAD\nVuelva a intentarlo")
            location.href="../jsp/tablaCorreccionSuelos.jsp";
            </script>
            <%
        }else{
            boolean edito = facadePedro.editarCorreccionSuelo(correccion, otraEnmienda, otraUnidad, idPropietario);
        if(!edito){
        %>
        <script type="text/javascript"> 
            alert("ERROR...NO SE HA PODIDO ACTUALIZAR EL REGISTRO DE CORRECCION DE SUELO \n Vuelvalo a intentar")
            location.href="../jsp/tablaCorreccionSuelos.jsp";
        </script>
        <%
        }else{   
        %>
            <script type="text/javascript"> 
                alert("EXITO...Se ha ACTUALIZADO el registro de CORRECCION DE SUELO\n puede consultar la nueva informacion Ahora mismo!")
                location.href="../jsp/tablaCorreccionSuelos.jsp";
            </script>
        <%
        }
        }
        %>    
    </body>
</html>
