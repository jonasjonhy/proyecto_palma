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
        HttpSession sesionU = request.getSession();
        String idLote = (String) sesionU.getAttribute("idLote");
        String idPropietario = (String) sesionU.getAttribute("id");
        String hacienda = (String) sesionU.getAttribute("idHacienda");
        
        String otroF = request.getParameter("otroF");
        String otroU = request.getParameter("otroU");
        
        %>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <jsp:useBean id="facadeMauricio" scope="page" class="Facade.FacadeMauricio" />
        <jsp:useBean id="nivelacion" scope="page" class="Dto.NivelacionNutriente" />
        <jsp:setProperty name="nivelacion" property="*"/>
        <%
        nivelacion.setIdLote(idLote);
        
        if(nivelacion.getIdFertilizante().equals("n") || nivelacion.getIdUnidad().equals("n")){
            %>
            <script type="text/javascript"> 
            alert("ERROR..Debe seleccionar algun FERTILIZANTE y/o UNIDAD\nVuelva a intentarlo")
            location.href="../jsp/tablaNivelacionNutriente.jsp";
            </script>
            <%
        }else{
        
        boolean registro = facadeMauricio.registrarNivelacion(nivelacion, otroF, otroU, idPropietario);
        
        if(!registro){
            %>
            <script type="text/javascript"> 
                  alert("ERROR...El AREA DE LA NIVELACION DE NUTRIENTE debe ser MENOR al AREA DEL LOTE registrado en la base de datos \nVuelvelo a intentar con un AREA MENOR o modifique en area del lote")
                  location.href="../jsp/tablaNivelacionNutriente.jsp";
              </script>
            <%
            }else{
                %>
                <script type="text/javascript"> 
                  alert("REGISTRO EXITOSO\n Se ha registrado una nueva NIVELACION DE NUTRIENTE para el lote\nAhora mismo la podra ver en la TABLA DE NIVELACION DE NUTRIENTES")
                  location.href="../jsp/tablaNivelacionNutriente.jsp";
                </script>
                <%
            }
        }
            %>
    </body>
</html>
