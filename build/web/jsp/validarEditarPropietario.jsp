<%-- 
    Document   : validarEdicionPropietario
    Created on : 20/10/2014, 07:57:17 PM
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
            String idViejo = request.getParameter("idViejo");
            String codDepartamento = request.getParameter("departamento");
            if(codDepartamento.equals("0")){
                %>
                <script type="text/javascript"> 
                  alert("ERROR...Por favor seleccionar la ubicacion de su residencia\n DEPARTAMENTO Y MUNICIPIO")
                  location.href="../jsp/administracionPalmicultor.jsp";
              </script>
                <%
            }else{
                boolean editado = facade.actualizarEdicionPropietario(propietario, idViejo) ;
            
                if(!editado){
            %>
                <script type="text/javascript"> 
                  alert("ERROR...NO se ha podido llevar a cabo la actualizacion \n Verifique nuevamente los datos de actualizacion e intente de nuevo")
                  location.href="../jsp/administracionPalmicultor.jsp";
                </script>
            <%
                }else{
                %>
                    <script type="text/javascript"> 
                     alert("La Actualización se ha llevado a cabo con EXITO!\n Podra consultar la nueva informacion ahora mismo")
                     location.href="../jsp/administracionPalmicultor.jsp";
                    </script>
                <%
                }
            }
            %>
    </body>
</html>
