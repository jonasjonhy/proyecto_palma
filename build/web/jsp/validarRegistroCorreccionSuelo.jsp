<%-- 
    Document   : validarRegistroCorreccionSuelo
    Created on : 29/11/2014, 04:55:23 PM
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
        HttpSession sesionU = request.getSession();
        String idLote = (String) sesionU.getAttribute("idLote");
        String idPropietario = (String) sesionU.getAttribute("id");
        String hacienda = (String) sesionU.getAttribute("idHacienda");
        
        String otroE = request.getParameter("otroF");
        String otroU = request.getParameter("otroU");
        
        String idEnmienda = request.getParameter("idFertilizante");
        String precio_aplic_enmienda = request.getParameter("precioAplicacion");
        
        %>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <jsp:useBean id="facadePedro" scope="page" class="Facade.FacadePedro" />
        <jsp:useBean id="correccion" scope="page" class="Dto.Correccion_suelo" />
        <jsp:setProperty name="correccion" property="*"/>
        <%
        correccion.setIdLote(idLote);
        correccion.setIdEnmienda(idEnmienda);
        correccion.setPrecio_aplic_enmienda(precio_aplic_enmienda);
        
        if(correccion.getIdEnmienda().equals("n") || correccion.getIdUnidad().equals("n")){
            %>
            <script type="text/javascript">
            alert("ERROR..Debe seleccionar algun FERTILIZANTE y/o UNIDAD\nVuelva a intentarlo")
            location.href="../jsp/tablaCorreccionSuelos.jsp";
            </script>
            <%
        }else{
        
        boolean registro = facadePedro.registraCorreccion(correccion, otroE, otroU, idPropietario);
        
        if(!registro){
            %>
            <script type="text/javascript"> 
                  alert("ERROR...El AREA DE LA CORRECCIION DE SUELO debe ser MENOR al AREA DEL LOTE registrado en la base de datos \nVuelvelo a intentar con un AREA MENOR o modifique en area del lote")
                  location.href="../jsp/tablaCorreccionSuelos.jsp";
              </script>
            <%
            }else{
                %>
                <script type="text/javascript"> 
                  alert("REGISTRO EXITOSO\n Se ha registrado una nueva CORRECCION DE SUELO para el lote\nAhora mismo la podra ver en la TABLA DE NIVELACION DE NUTRIENTES")
                  location.href="../jsp/tablaCorreccionSuelos.jsp";
                </script>
                <%
            }
        }
            %>
    </body>
</html>

