<%-- 
    Document   : tablaCostoTierra
    Created on : 29-nov-2014, 12:06:05
    Author     : Rosemberg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<html lang="es">
    <head>
        <link rel="shortcut icon"  href="../images/ico.png">
        <title>Costos Fijos de Administracion</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        
         <script type="text/javascript" src="../js/paginacion.js"></script>
        <script type="text/javascript" src="../js/sortable.js"> </script>
        <%-- Estas lineas permiten el recargo de los formularios --%>
        <script src="../js/jquery-1.8.3.min.js"></script>
        <script src="../js/jquery-2.1.1.js"></script>
        <script src="../js/jquery-ui-1.10.4.min.js"></script>
         <script type="text/javascript" src="../js/Funciones.js"></script>
        <%-- Estas lineas permiten el recargo de los formularios --%>
        
    </head>

       <body>
         <%
        HttpSession sesionU = request.getSession();
        String idLote = (String) sesionU.getAttribute("idLote");
        String idPropietario = (String) sesionU.getAttribute("id");
        String hacienda = (String) sesionU.getAttribute("idHacienda");
        %>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <jsp:useBean id="facadeRosemberg" scope="page" class="Facade.FacadeRosemberg" />
        <header class="row ">
            
               <div class="col-xs-4 col-xs-offset-7 col-sm-3 col-md-offset-9 row">
                    <div class="usuario z-index-2">
                          <label>Bienvenido <%=facade.consultarNombrePropietario(idPropietario)%></label><br>  
                         </div>
                </div>
                <div><img  src="../images/logo.png"   class=""/></div>
        </header>
                    
           <div class="container-fluyd row" >
            <div class="col-xs-3">
                    
                    
                    <div class="btn-group-vertical" id="menu-botones">
                       <button type="button" class="btn btn-success btn-lg" onclick="location.href='../jsp/informacionPalmicultor.jsp'">Regresar vista Palmicultor</button><br>
                        <button type="button" class="btn btn-success btn-lg" onclick="location.href='../jsp/informacionHacienda.jsp?id_hacienda=<%=hacienda%>'">Regresar vista Hacienda</button><br>
                        <button type="button" class="btn btn-success btn-lg" onclick="location.href='consultarDatosLote.html'">Editar datos Hacienda</button><br>
                        <button type="button" class="btn btn-success btn-lg" onclick="location.href='edicionLote.html'">Desactivar Hacienda</button><br>
                        

                        <div class="btn-group">

                        <button type="button" class="btn btn-success btn-lg dropdown-toggle" data-toggle="dropdown">Costos Fijos <span class="caret"></span></button>
                                <ul class="dropdown-menu menu" role="menu">
                                <li><a href="../jsp/tablaCostoTierra.jsp">Tierra</a></li>
                                <li><a href="../jsp/tablaCFMaquinaria.jsp">Maquinaria</a></li>
                                <li><a href="../jsp/tablaCFAnimales.jsp">Animales</a></li>
                                <li><a href="../jsp/tablaCFHerramientas.jsp">Herramientas</a></li>
                                <li><a href="../jsp/tablaAdministracion.jsp">Administracion</a></li>
                                <li><a href="#">Informe Costos Fijos</a></li>
                                
                              </ul>

                         </div>     

                        <br>
                        
                       <button type="button" class="btn btn-success btn-lg" onclick="location.href='../jsp/cerrarSesion.jsp'">Cerrar Sesion</button>
                        

                    </div>


                </div>

        <div class="col-xs-7 row" id="central" >
                    
                                               
                       <div id="load">
                                
                            </div>
                    <br>
                    <br>
                    <br>
                    <br>
                        
                           <center><%=facadeRosemberg.tablaAdministracion(hacienda)%></center>
                           
                                                   <%--PARA LA PAGINACION--%>
                       <div id="navegacion"></div>
        <script type="text/javascript">
            var pager = new Pager("tablaAdministracion",5);
            pager.init();
            pager.showPageNav('pager','navegacion');
            pager.showPage(1);
        </script>
        <%--PARA LA PAGINACION--%>
        
                        <br>
                        <br>
                            <br>
                            <button class="btn btn-lg btn-success" data-toggle="modal" data-target="#agregarCosto">Agregar  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
                            <br>
                            <br>
                            <br>
                            

                            <%=facadeRosemberg.tablaSubtotalesAdministracion(hacienda)%>
                </div>
          </div>

        <footer class="z-index-3">
            
            <div class="icon_box"> 
          
               <a href="http://ingsistemas.ufps.edu.co/"> <img src="../images/is-icon.png"/></a>
               <a href="http://www.ufps.edu.co/ufps/index.php"><img src="../images/ufps-icon.png"/></a>
               
                
            </div>
            
        </footer>
        
<%--nuevo costo--%>
         <!-- Modal -->

         <%=facadeRosemberg.botonAgregarCostosAdministracion()%>
         
            
         <%--Editar--%>
             <!-- Modal -->
<div class="modal fade" id="editarCosto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    
      <div class="modal-body">     
          <%--div para cargar el formulario de edicion de la nivelacion.. se carga la info desde la BD--%>
          <div id="formEditarCostoAdministracion">
            
          </div>    
          
      </div>
      
  </div>
</div>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
            
    </body>

</html>