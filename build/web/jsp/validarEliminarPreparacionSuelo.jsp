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
        <jsp:useBean id="facade" scope="page" class="Facade.FacadeBrian" />
        
        <%
            HttpSession sesionU = request.getSession();
            String id = request.getParameter("id");
           
            boolean registro = facade.eliminarPreparacionSuelo(id) ;
            
            if(!registro){
         %>
            <script type="text/javascript"> 
                  alert("ERROR...NO SE HA PODIDO REALIZAR LA ELIMINACION \n Verifique que la preparación de suelo no se encuentre registrado aun en el sistema\n Vuelvalo a intentar")
                  location.href="informacionPalmicultor.jsp";
              </script>
            <%
            }else{
                %>
                <script type="text/javascript"> 
                  alert("REGISTRO EXITOSO")
                  location.href="informacionHacienda.jsp";
                </script>
                <%
            }
            %>
    </body>
</html>
