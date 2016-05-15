<%-- 
    Document   : tablaCFMaquinaria
    Created on : 29/11/2014, 05:13:26 PM
    Author     : Oscar Torres
--%>

<%@page import="Facade.FacadeOscar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.0//EN">
<html lang="es">
    <head>
        <link rel="shortcut icon"  href="../images/ico.png">
        <title>Costos Maquinaria</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="../css/bootstrap.css">
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
       
<header class="row ">
       
        <%

            HttpSession sesionU = request.getSession();
            String idPropietario = (String) sesionU.getAttribute("id");
            String hacienda = (String) sesionU.getAttribute("idHacienda");
            String lote = request.getParameter("idLote");
            sesionU.setAttribute("idLote", lote);
            String idLote = (String) sesionU.getAttribute("idLote");

        %>

         <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <jsp:useBean id="FacadeOscar" scope="page" class="Facade.FacadeOscar" />
        
        <div class="col-xs-4 col-xs-offset-7 col-sm-3 col-md-offset-9 row">
            <div class="usuario z-index-2">
                <label>Bienvenido <%= facade.consultarNombrePropietario(idPropietario)%></label><br>
                <label>Hacienda <%= facade.consultarNombreHacienda(hacienda)%></label>
            </div>
        </div>
        <div><img  src="../images/logo.png"   class=""/></div>
    </header>

                    
           <div class="container-fluyd row" >
            <div class="col-xs-3">
                    
                    
                    <div class="btn-group-vertical" id="menu-botones">
                        <button type="button" class="btn btn-success btn-lg" onclick="location.href='informacionPalmicultor.jsp'">Regresar vista Palmicultor</button><br>
                        <button type="button" class="btn btn-success btn-lg" onclick="location.href='informacionHacienda.jsp'">Consultar Datos Hacienda</button><br>
                        <button type="button" class="btn btn-success btn-lg" onclick="location.href='consultarDatosLote.jsp'">Editar datos Hacienda</button><br>
                        <button type="button" class="btn btn-success btn-lg" onclick="location.href='desactivarLote.jsp'">Desactivar Hacienda</button><br>
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
                <div class="col-xs-9 row" id="central" >             
                        <br><br><br><br><br>                       
                        <center><%=FacadeOscar.tablaCFMaquinaria(Integer.parseInt(hacienda))%></center>

                            <br>
                            <button type="button" class="btn btn-lg btn-success" data-toggle="modal" data-target="#agregarCosto">Agregar  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
                            <br><br><br>                         
                            <table class="table table-bordered tablaP">                               
                                    <td class="negra">Total Costo Fijos de Maquinaria y Equipos</td>
                                    <td></td>
                                </tr>
                            </table>

<%--PARA LA PAGINACION--%>
                       <div id="navegacion"></div>
        <script type="text/javascript">
            var pager = new Pager("tablaCFMaquinaria",5);
            pager.init();
            pager.showPageNav('pager','navegacion');
            pager.showPage(1);
        </script>
        <%--PARA LA PAGINACION--%>
              </div>
          </div>
        

                 <!-- Modal -->
<div class="modal fade" id="agregarCosto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">          
      <div class="modal-body">
          <form action="../jsp/validarRegistroCFMaquina.jsp" method="post">
                                <div class="recuadro row blanco">  

                                    <button type="button" class="close" data-dismiss="modal">
                                    <span aria-hidden="true">&times;</span>
                                    <span class="sr-only">Close</span></button>
                                    <center><h2 class="verde">Nuevo costo</h2></center>
                                    <br>
                                    <div class="col-sm-8">
                                    <label>Fecha<label class="requerido"></label></label>
                                        <input type="date" id="fechaRegistro" name="fechaRegistro" class="form-control" placeholder="" required><br>
                                        
                                        <label>Maquina o Equipo<label class="requerido">*</label></label>
                                        <select id="idMaquina" name="idMaquina" class="form-control" onchange="habilitarCampoOtraM_registro()">
                                        <%=FacadeOscar.getSelectMaquina()%>
                                        <br>
                                    <div class="otraM">        
                                       <label>¿Otra? indicanos cual</label>
                                                            
                                            <input type="text" id="otraM" name="otraM" disabled = "disabled" class="form-control" placeholder="otra Maquina"required><br>
                                          </div>  
                                    </div>     
                                        <label> Cantidad </label>
                                            <input type="text" id="cantidad" name="cantidad" class="form-control" placeholder="" onchange="cargarSubtotalPrecioXUnd_registrar(this, '#precio')" value="0" required><br>
                                        <label>Precio x Unidad</label>
                                        <input type="text" id="precioUnd" name="precioUnd" class="form-control" placeholder="" onchange="cargarSubtotalPrecioXUnd_registrar(this, '#cantidad')" value="0"><br>

                                        <label>Total Compra</label>
                                        <input type="text" id="subtotalc" name="subtotalc" class="form-control" placeholder="" readonly value="0"><br>
                                        <label>Vida Util</label>        
                                            <input type="number" id="vidaUtil" name="vidaUtil"  class="form-control" placeholder="" onchange="cargarPrecioXañoVidaUtil_registrar(this, '#subtotalc')" value="0"><br>
                                        <label>Precio x Año de Vida Util</label>
                                        <input type="text" id="preautil" name="preautil" class="form-control" placeholder=""readonly value="0"><br>
                                        <label>Area de Servicio</label>
                                       <input type="text" id="areaServicio" name="areaServicio" class="form-control" placeholder="" onchange="cargarCostoHanio(this, '#preautil')"  value='0'><br>
                                       
                                        <label>Costo por Hectarea al Año</label>
                                            <input type="number" id="costoHectarea" name="costoHectarea" min="0" class="form-control" placeholder=""readonly><br>
                                        <label>Area total de Lotes</label>
                                            <input type="number" id="areal_lotes" name="areal_lotes"  class="form-control" placeholder="" onchange="cargarTotalM(this, '#costoHectarea')" ><br>
                                        <label>Subtotal maquina</label>
                                            <input type="text" id="subtotalMaquinaria" name="subtotalMaquinaria" class="form-control" placeholder="" readonly><br>
                                            <center><button class="btn btn-success" type="submit">Guardar <span class="glyphicon glyphicon-save" aria-hidden="true"></span></button><br></center>
                                    </div>
                                   
      </div>
                                      
                            </form>
      </div>
  </div>
    </div>

            

             <!-- Modal -->
<div class="modal fade" id="editarCosto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    
      
      <div class="modal-body">
          
          <div id="formEditarMaquinaria">
          
          </div>
                     
      </div>
      
    </div>
  </div>
    
     <footer class="z-index-3">
            
            <div class="icon_box"> 
          
               <a href="http://ingsistemas.ufps.edu.co/"> <img src="../images/is-icon.png"/></a>
               <a href="http://www.ufps.edu.co/ufps/index.php"><img src="../images/ufps-icon.png"/></a>
               
                
            </div>
            
        </footer>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
            
    </body>

</html>
