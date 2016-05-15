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
            String idHacienda = (String) sesionU.getAttribute("idHacienda");
           
            boolean registro = facade.registrarLote(lote) ;
            System.out.println("ACA ESTA EL FLUJO DE VALIDACION LOTE "+ lote.getFechaPlantacion()+" "+lote.getHaciendaId());
            if(!registro){
            %>
            <script type="text/javascript"> 
                  alert("ERROR...NO SE HA PODIDO COMPLETAR EL REGISTRO \n Verifique que el lote no se encuentre registrado aun en el sistema\n Vuelvalo a intentar")
                  location.href="informacionPalmicultor.jsp";
              </script>
            <%
            }else{
                %>
                <script type="text/javascript"> 
                  alert("REGISTRO EXITOSO")
                  location.href="informacionPalmicultor.jsp";
                </script>
                <%
            }
            %>
    </body>
</html>
