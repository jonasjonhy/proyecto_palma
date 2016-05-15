<%-- 
    Document   : validarSesion
    Created on : 1/10/2014, 03:35:44 PM
    Author     : mauricio uribe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        
        <%
          String id=request.getParameter("documento");
          String clave=request.getParameter("clave");
          
          %>
          
          <%
          
          if(id.isEmpty() || clave.isEmpty()){ 
              
              %>
              <script type="text/javascript"> 
                  alert('ERROR...FALTAN DATOS POR INGRESAR')
                  location.href='../html/index.html';
              </script>    
              
         <%     
          }else{
              String validado = facade.iniciarSesion(id, clave);
              
              if(validado.equals("false")){
                  %>
                  <script type="text/javascript"> 
                  alert("ERROR...USUARIO Y CONTRASEÑA NO COINCIDEN\n..POR FAVOR VERIFIQUE LOS DATOS DE INICIO DE SESION");
                  location.href="../html/index.html";
                  </script>   
                  <%
              }else{
                  HttpSession sesionU=request.getSession();
                  sesionU.setAttribute("id",id);
                  response.sendRedirect(validado);                                    
              }   
          }
         %> 
         
    </body>
</html>
