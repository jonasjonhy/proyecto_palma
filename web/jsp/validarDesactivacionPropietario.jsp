<%-- 
    Document   : validarDesactivacionPropietario
    Created on : 21/10/2014, 01:46:08 AM
    Author     : mauricio uribe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <jsp:useBean id="propietario" scope="page" class="Dto.Propietario" />
        <jsp:setProperty name="propietario" property="*" />
        
        <%
            boolean desactivo = facade.desactivarCuentaPropietario(propietario.getNumDocumento(), propietario.getPassword());
            
            if(desactivo){
                %>
                <script type="text/javascript"> 
                  alert("Su cuenta ha sido desactivada con exito \n Para volver a reactivarla pongase en contacto con el administrador del sistema")
                  location.href="../html/index.html";
                </script>
                <%
            }else{
                %>
                    <script type="text/javascript"> 
                    alert("El usuario y/o contraseña no coinciden\n verifique los datos para poder desactivar la cuenta")
                    location.href="../jsp/informacionPalmicultor.jsp";
                    </script>
                
                <%
            }
            %>
    </body>
</html>
