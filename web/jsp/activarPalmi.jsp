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
        
        
        <%
            HttpSession sesionU = request.getSession();
            String id=request.getParameter("id");
            String tipo=request.getParameter("tipo");
            boolean registro = facade.activarPalmicultor(id,tipo) ;
            
            if(!registro){
            %>
            <script type="text/javascript"> 
                  alert("ERROR...NO SE HA PODIDO COMPLETAR LA ACTUALIZACION \n Faltan datos\n Vuelvalo a intentar")
                  location.href="administracionPalmicultor.jsp";
              </script>
            <%
            }else{
                %>
                <script type="text/javascript"> 
                  alert("La actualizacion de la Hacienda se ha llevado a cabo con EXITO\nAhora mismo puede consultar los nuevos datos")
                  location.href="administracionPalmicultor.jsp";
                </script>
                <%
            }
            %>
    </body>
</html>
