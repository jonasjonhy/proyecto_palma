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
        <jsp:useBean id="ps" scope="page" class="Dto.PreparacionSuelo" />
        <jsp:setProperty name="ps" property="*"/>
        <%
            HttpSession sesionU = request.getSession();
            
           
            boolean registro = facade.registrarPreparacionSuelo(ps) ;
            
            if(!registro){
         %>
            <script type="text/javascript"> 
                  alert("ERROR...NO SE HA PODIDO COMPLETAR EL REGISTRO \n Verifique que la preparación de suelo no se encuentre registrado aun en el sistema\n Vuelvalo a intentar")
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
