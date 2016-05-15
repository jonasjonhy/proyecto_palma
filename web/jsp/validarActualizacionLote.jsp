<%-- 
    Document   : validarRegistroHacienda
    Created on : 19/10/2014, 11:00:23 PM
    Author     : brialxsfxubun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>validaRegistro</title>
    </head>
    <body>
        
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <jsp:useBean id="lote" scope="page" class="Dto.Lote" />
        <jsp:setProperty name="lote" property="*"/>
        <%
            HttpSession sesionU = request.getSession();
            String idLote = (String) sesionU.getAttribute("idLote");
            lote.setId(idLote);
            boolean registro = facade.actulizarLote(lote) ;
            
            if(!registro){
            %>
            <script type="text/javascript"> 
                  alert("ERROR...NO SE HA PODIDO COMPLETAR LA ACTUALIZACION \n Faltan datos\n Vuelvalo a intentar")
                  location.href="informacionPalmicultor.jsp";
              </script>
            <%
            }else{
                %>
                <script type="text/javascript"> 
                  alert("La ACTUALIZACION se ha completado EXITOSAMENE\nPuede consultar la nueva informacion del lote ahora mismo")
                  location.href="informacionPalmicultor.jsp";
                </script>
                <%
            }
            %>
    </body>
</html>
