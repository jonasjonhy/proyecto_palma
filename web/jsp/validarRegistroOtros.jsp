
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
        
        String otroI = request.getParameter("otroI");
        String otroU = request.getParameter("otroU");
        
        //String precioAplic = request.getParameter("precioAplicacion");
        //String idItem = request.getParameter("idFertilizante");
        
        %>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <jsp:useBean id="facadeMauricio" scope="page" class="Facade.FacadeMauricio" />
        <jsp:useBean id="facadePedro" scope="page" class="Facade.FacadePedro" />
        <jsp:useBean id="otro" scope="page" class="Dto.Otro" />
        <jsp:setProperty name="otro" property="*"/>
        <%
	otro.setIdLote(idLote);
        
        
        if(otro.getIdItem().equals("n") || otro.getIdUnidad().equals("n")){
            %>
            <script type="text/javascript"> 
            alert("ERROR..Debe seleccionar algun ITEM y/o UNIDAD\nVuelva a intentarlo")
            location.href="../jsp/tablaOtros.jsp";
            </script>
            <%
        }else{
        
        boolean registro = facadePedro.registraOtro(otro,otroI, otroU, idPropietario);
        
        if(!registro){
            %>
            <script type="text/javascript"> 
                  alert("ERROR...El AREA DE LOS OTROS COSTOS debe ser MENOR al AREA DEL LOTE registrado en la base de datos \nVuelvelo a intentar con un AREA MENOR o modifique en area del lote")
                  location.href="../jsp/tablaOtros.jsp";
              </script>
            <%
            }else{
                %>
                <script type="text/javascript"> 
                  alert("REGISTRO EXITOSO\n Se ha registrado otro costo asociado para el lote\nAhora mismo la podra ver en la TABLA DE OTROS COSTOS")
                  location.href="../jsp/tablaOtros.jsp";
                </script>
                <%
            }
        }
            %>
    </body>
</html>
