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
        <jsp:useBean id="facadePedro" scope="page" class="Facade.FacadePedro" />
        <jsp:useBean id="otro" scope="page" class="Dto.Otro" />
        <jsp:setProperty name="otro" property="*" />
        <%
            String otroItem = request.getParameter("otroIte");
            String otraUnidad = request.getParameter("otroUni");
            String idItem = request.getParameter("idI");
            String idUnidad = request.getParameter("idU");
            
            String cantidadItem = request.getParameter("cantidadItem_e");
            String subtotal = request.getParameter("subtotal_e");
            String precioAplic = request.getParameter("precioAplicacion_e");
            String precioItem = request.getParameter("precioItem_e");
            String area_lote = request.getParameter("areaLote_e");
            
        HttpSession sesionU = request.getSession();
        String idLote = (String) sesionU.getAttribute("idLote");
        String idPropietario = (String) sesionU.getAttribute("id");
        String hacienda = (String) sesionU.getAttribute("idHacienda");
       
        otro.setIdLote(idLote);
        otro.setIdItem(idItem);
        otro.setIdUnidad(idUnidad);
        otro.setAreaLote(area_lote);
	otro.setCantidadItem(cantidadItem);
        otro.setSubtotal(subtotal);
	otro.setPrecioAplicacion(precioAplic);
	otro.setPrecioItem(precioItem);
       
        
        
        
        if(idItem.equals("n") || idUnidad.equals("n")){
            %>
            <script type="text/javascript"> 
            alert("ERROR..Debe seleccionar algun Item y/o UNIDAD\nVuelva a intentarlo")
            location.href="../jsp/tablaOtros.jsp";
            </script>
            <%
        }else{
            boolean edito = facadePedro.editarOtro(otro, otroItem, otraUnidad, idPropietario);
        if(!edito){
        %>
        <script type="text/javascript"> 
            alert("ERROR...NO SE HA PODIDO ACTUALIZAR EL REGISTRO DE OTROS COSTOS \n Vuelvalo a intentar")
            location.href="../jsp/tablaOtros.jsp";
        </script>
        <%
        }else{   
        %>
            <script type="text/javascript"> 
                alert("EXITO...Se ha ACTUALIZADO el registro de otros costos \n puede consultar la nueva informacion Ahora mismo!")
                location.href="../jsp/tablaOtros.jsp";
            </script>
        <%
        }
        }
        %>    
    </body>
</html>
