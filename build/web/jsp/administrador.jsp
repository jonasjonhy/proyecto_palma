<%-- 
    Document   : administrador
    Created on : 14/11/2014, 10:48:42 AM
    Author     : mauricio uribe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <link rel="shortcut icon"  href="../images/ico.png">
        <title>Administrador</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../css/bootstrap.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        
        
    </head>

    <body>
        <%
            HttpSession sesionU = request.getSession();
            String idAdmin = (String) sesionU.getAttribute("id");
        %>
        
        <header class="row ">
            <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
               <div class="col-xs-4 col-xs-offset-7 col-sm-3 col-md-offset-9 row">
                    <div class="usuario z-index-2">
                         <label>Bienvenido <%=facade.consultarNombrePropietario(idAdmin)%></label><br>
                    <label>Administrador</label>
                    </div>
                </div>
                <div><img  src="../images/logo.png"   class=""/></div>
        </header>
                    
           <div class="container-fluyd row" >
            <div class="col-xs-3">
                    
                    
                    <div class="btn-group-vertical" id="menu-botones">
                        <button type="button" class="btn btn-success btn-lg" onclick="location.href='../jsp/administracionPalmicultor.jsp'">&nbsp;&nbsp;&nbsp;&nbsp;Administrar Propietario&nbsp;&nbsp;&nbsp;&nbsp;</button><br>
                        <button type="button" class="btn btn-success btn-lg" onclick="location.href='../jsp/administracionHacienda.jsp'">Administrar Hacienda</button><br>
                        <button type="button" class="btn btn-success btn-lg" onclick="location.href='../jsp/cerrarSesion.jsp'">Cerrar Sesion</button>
                        <br>
                        <br>
                        <br>
                        <br>

                    </div>


                </div>

                <div class="col-xs-7" id="central" >
                    
                        <center>
                          <h2 class="verde hidden-xs">Modulo  <span class="naranja"> Administrador</span></h2>
                          <h3 class="verde visible-xs">Modulo <span class="naranja"> Administrador</span></h3>
                        </center>
                        <br>
                    
                    </div>
               
               <div class="col-xs-7"><center><img class="portada" src="../images/administrador.jpg"></center><br></div>
                    </div>
                
                
                
            
       

        <footer class="z-index-3">
            
            <div class="icon_box"> 
          
               <a href="http://ingsistemas.ufps.edu.co/"> <img src="../images/is-icon.png"/></a>
               <a href="http://www.ufps.edu.co/ufps/index.php"><img src="../images/ufps-icon.png"/></a>
               
                
            </div>
            
        </footer>
            
    </body>

</html>
