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
        <jsp:useBean id="hacienda" scope="page" class="Dto.Hacienda" />
        <jsp:setProperty name="hacienda" property="*"/>
        <%
            boolean registro = facade.registarHacienda(hacienda) ;
            
            if(!registro){
            %>
            
            <script type="text/javascript"> 
                  alert("ERROR...NO SE HA PODIDO COMPLETAR EL REGISTRO \n Verifique que la hacienda no se encuentre registrado aun en el sistema\n Vuelvalo a intentar")
                  location.href="registroHacienda.jsp";
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
    
    
    <%= hacienda.toString() %>
    </body>
</html>
