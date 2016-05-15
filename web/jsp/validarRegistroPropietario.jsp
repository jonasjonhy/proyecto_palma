<%-- 
    Document   : validarRegistro
    Created on : 16/10/2014, 07:53:33 AM
    Author     : mauricio uribe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>validaRegistro</title>
    </head>
    <body>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <jsp:useBean id="propietario" scope="page" class="Dto.Propietario" />
        <jsp:useBean id="eje" scope="page" class="Dto.Lote" />
        <jsp:setProperty name="eje" property="*"/>
        <jsp:setProperty name="propietario" property="*"/>
        <%
            boolean registro = facade.registrarPropietario(propietario);
            
            if(!registro){
            %>
            <script type="text/javascript"> 
                  alert("ERROR...NO SE HA PODIDO COMPLETAR EL REGISTRO \n Verifique que el usuario no se encuentre registrado aun en el sistema\n Vuelvalo a intentar")
                  location.href="../jsp/registroPalmicultor.jsp";
              </script>
            <%
            }else{
                    HttpSession sesionU=request.getSession();
                    String id = propietario.getNumDocumento();
                    sesionU.setAttribute("id",id);
                %>
                <script type="text/javascript"> 
                  alert("REGISTRO EXITOSO\n BIENVENIDO AL SISTEMA PROYECTO PALMA\nAHORA PUEDE INGRESAR AL SISTEMA PROYECTO PALMA")
                  location.href="../html/index.html";
                </script>
                <%
            }
            %>
    </body>
</html>
