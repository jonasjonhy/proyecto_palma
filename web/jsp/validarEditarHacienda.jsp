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
            HttpSession sesionU = request.getSession();
            String idHacienda = request.getParameter("hacienda");
            hacienda.setId(idHacienda);
            
            String codDepartamento = request.getParameter("departamento");
            if(codDepartamento.equals("0")){
                %>
                <script type="text/javascript"> 
                   
                  alert("ERROR...Por favor seleccionar la ubicacion de la hacienda\n DEPARTAMENTO Y MUNICIPIO")
                  location.href="../jsp/administracionHacienda.jsp"
                  
              </script>
                <%
                
            }else{
            boolean registro = facade.actualizarEdicionHacienda(hacienda) ;
            
            if(!registro){
            %>
            <script type="text/javascript"> 
                  alert("ERROR...NO SE HA PODIDO COMPLETAR LA ACTUALIZACION \n Faltan datos\n Vuelvalo a intentar")
                  location.href="administracionHacienda.jsp";
              </script>
            <%
            }else{
                %>
                <script type="text/javascript"> 
                  alert("La actualizacion de la Hacienda se ha llevado a cabo con EXITO\nAhora mismo puede consultar los nuevos datos")
                  location.href="administracionHacienda.jsp";
                </script>
                <%
             }
            }
            %>
    </body>
</html>
