<%-- 
    Document   : validarRegistroPalmaSiembra
    Created on : 28/11/2014, 01:28:36 AM
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
                
        %>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <jsp:useBean id="facadeMauricio" scope="page" class="Facade.FacadeMauricio" />
        <jsp:useBean id="siembra" scope="page" class="Dto.PalmaSiembra" />
        <jsp:setProperty name="siembra" property="*"/>
        <%
        siembra.setIdLote(idLote);
                
        
        boolean registro = facadeMauricio.registrarPalmaSiembra(siembra);
        
        if(!registro){
            %>
            <script type="text/javascript"> 
                  alert("ERROR...El AREA DE LA SIEMBRA debe ser MENOR al AREA DEL LOTE registrado en la base de datos \nVuelvelo a intentar con un AREA MENOR o modifique en area del lote")
                  location.href="../jsp/tablaPalmaSiembra.jsp";
              </script>
            <%
            }else{
                %>
                <script type="text/javascript"> 
                  alert("REGISTRO EXITOSO\n Se ha registrado una nueva SIEMBRA DE PALMAS para el lote\nAhora mismo la podra ver en la TABLA DE SIEMBRA DE PALMAS")
                  location.href="../jsp/tablaPalmaSiembra.jsp";
                </script>
                <%
            }
        
            %>
    </body>
</html>
