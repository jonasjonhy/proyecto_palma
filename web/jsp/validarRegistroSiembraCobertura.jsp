<%-- 
    Document   : validarRegistroSiembraCobertura
    Created on : 29/11/2014, 10:38:43 PM
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
        
        String precioAplic = request.getParameter("precioAplicacion");
        String idInsumo = request.getParameter("idFertilizante");
        
        %>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <jsp:useBean id="facadeMauricio" scope="page" class="Facade.FacadeMauricio" />
        <jsp:useBean id="facadePedro" scope="page" class="Facade.FacadePedro" />
        <jsp:useBean id="cobertura" scope="page" class="Dto.Siembra_cobertura" />
        <jsp:setProperty name="cobertura" property="*"/>
        <%
        cobertura.setIdLote(idLote);
        cobertura.setIdInsumo(idInsumo);
        cobertura.setPrecioAplic(precioAplic);
        
        if(idInsumo.equals("n") || cobertura.getIdUnidad().equals("n")){
            %>
            <script type="text/javascript"> 
            alert("ERROR..Debe seleccionar algun INSUMO y/o UNIDAD\nVuelva a intentarlo")
            location.href="../jsp/tablaSiembraCobertura.jsp";
            </script>
            <%
        }else{
        
        boolean registro = facadePedro.registraSiembraCobertura(cobertura, otroF, otroU, idPropietario);
        
        if(!registro){
            %>
            <script type="text/javascript"> 
                  alert("ERROR...El AREA DE LA SIEMBRA DE COBERTURA debe ser MENOR al AREA DEL LOTE registrado en la base de datos \nVuelvelo a intentar con un AREA MENOR o modifique en area del lote")
                  location.href="../jsp/tablaSiembraCobertura.jsp";
              </script>
            <%
            }else{
                %>
                <script type="text/javascript"> 
                  alert("REGISTRO EXITOSO\n Se ha registrado una nueva SIEMBRA DE COBERTURA para el lote\nAhora mismo la podra ver en la TABLA DE SIEMBRA DE COBERTURAS")
                  location.href="../jsp/tablaSiembraCobertura.jsp";
                </script>
                <%
            }
        }
            %>
    </body>
</html>
