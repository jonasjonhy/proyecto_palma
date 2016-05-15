<%-- 
    Document   : tablaNivelacionNutriente
    Created on : 20/11/2014, 02:47:38 PM
    Author     : mauricio uribe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.0//EN">
<html lang="es">
    <head>
        <link rel="shortcut icon"  href="../images/ico.png">
        <title>Nivelación Nutrientes</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/style.css">
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
         <%
        HttpSession sesionU = request.getSession();
        String idLote = (String) sesionU.getAttribute("idLote");
        String idPropietario = (String) sesionU.getAttribute("id");
        String hacienda = (String) sesionU.getAttribute("idHacienda");
        %>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <jsp:useBean id="facadeMauricio" scope="page" class="Facade.FacadeMauricio" />
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
                        <button type="button" class="btn btn-success btn-lg" onclick="location.href='../jsp/informacionLote.jsp?idLote=<%=idLote%>'">Consultar datos lote</button><br>
               

                        <div class="btn-group">

                        <button type="button" class="btn btn-success btn-lg dropdown-toggle" data-toggle="dropdown">Costos Establecimiento <span class="caret"></span></button>
                                <ul class="dropdown-menu menu" role="menu">
                                <li><a href="../jsp/tablaCorreccionSuelos.jsp">Correcion de suelos</a></li>
                                <li><a href="../jsp/tablaPreparacionSuelo.jsp">Preparacion Suelo</a></li>
                                <li><a href="../jsp/tablaSiembraCobertura.jsp">Siembra Coberturas</a></li>
                                <li><a href="../jsp/tablaPalmaSiembra.jsp">Siembra Palmas</a></li>
                                <li><a href="../jsp/tablaLaboresSiembra.jsp">Labores Siembra</a></li>
                                <li><a href="../jsp/tablaOtros.jsp">Otros Costos</a></li>
                                <li><a href="#">Informe Costos Est.</a></li>
                              </ul>

                         </div>     

                        <br>
                        <button type="button" class="btn btn-success btn-lg" onclick="location.href='.html'">Costos Variables</button><br>
                        <button type="button" class="btn btn-success btn-lg" onclick="location.href='../jsp/cerrarSesion.jsp'">Cerrar Sesion</button>
                        

                    </div>


                </div>

                <div class="col-xs-7 row" id="central" >
                    
                       <br><br><br>
                       <center><h2 class="verde hidden-xs">Lote <span class="naranja"> <%=facadeMauricio.consultarCodigoLote(idLote)%></span></h2></center>
                       <br><br>
                       
                       <div id="load">
                                
                            </div>
                       
                       <center><%=facadeMauricio.tablaNivelacionNutrientes(idLote)%></center>
                       
                       
                       <%--PARA LA PAGINACION--%>
                       <div id="navegacion"></div>
        <script type="text/javascript">
            var pager = new Pager("tablaNivelacion",5);
            pager.init();
            pager.showPageNav('pager','navegacion');
            pager.showPage(1);
        </script>
        <%--PARA LA PAGINACION--%>
        
                        <br>
                        <br>
                            
                            <button class="btn btn-lg btn-success" data-toggle="modal" data-target="#agregarCosto">Agregar</button>
                            <br>
                            <br>
                            <br>

                            <%=facadeMauricio.tablaSubtotalesNivelacionNutriente(idLote)%>
                            
            
                </div>
          </div>
   
                            
                
                <!-- Modal -->
<div class="modal fade" id="agregarCosto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">     
      <div class="modal-body">
                      <form action="../jsp/validarRegistroNivelacionNutriente.jsp" method="post">
                                <div class="recuadro row blanco">  

                                    <button type="button" class="close" data-dismiss="modal">
                                    <span aria-hidden="true">&times;</span>
                                    <span class="sr-only">Close</span></button>
                                    <center><h2 class="verde">Registro Nivelacion Nutriente</h2></center>
                                    <br>
                                    

                                <div class="col-sm-6">
                                        <label>Fecha<label class="requerido"></label></label>
                                        <input type="date" id="fechaAplicacion" name="fechaAplicacion" class="form-control" placeholder="" required><br>
                                        <label>Area de Lote<label class="requerido"></label></label>
                                        <input type="text" id="areaLote" name="areaLote" class="form-control" placeholder="" onchange="cargarSubtotal3_registrar(this, '#cantidadFertilizante', '#precioFertilizante')" required value="1"><br>
                                        
                                        <label>Fertilizante<label class="requerido">*</label></label>
                                        <select  id="idFertilizante" name="idFertilizante" class="form-control" onchange="habilitarCampoOtroF_registro()" >
                                        <%=facadeMauricio.selectFertilizantes()%>
                                       
                                        <br>
                                    <div class="otroF">
                                        <label>¿Otro? indicanos cual</label>
                                            <input type="text" id="otroF" name="otroF" disabled = "disabled" class="form-control" placeholder="otro fertilizante"required><br>
                                     </div>   


                                </div>
                                <div class="col-sm-6 ">
                                        
                                        <label>Cantidad por hectarea</label>
                                            <input type="text" id="cantidadFertilizante" name="cantidadFertilizante" class="form-control" onchange="cargarSubtotal3_registrar(this, '#areaLote', '#precioFertilizante')" value="1" placeholder="" required><br> 
                                        <label>   Unidad<label class="requerido">*</label></label>
                                        <SELECT name="idUnidad" id="idUnidad" class="form-control" onchange="habilitarCampoOtroU_registro()">
                                        <%=facadeMauricio.selectUnidades("peso")%>
                                        
                                        <br>
                                    <div class="otraU">
                                        <label>¿Otra? indicanos cual</label>
                                            <input type="text" id="otroU" name="otroU" disabled = "disabled" class="form-control" placeholder="otra unidad" required><br>
                                    </div>
                                        <label>Precio por unidad del Fertilizante</label>
                                        <input type="text" id="precioFertilizante" name="precioFertilizante" class="form-control" placeholder="" onchange="cargarSubtotal3_registrar(this, '#cantidadFertilizante', '#areaLote')" value="1"><br>
                                        <label>Subtotal</label>
                                         <input type="text" id="subtotal" name="subtotal" class="form-control" placeholder=""  readonly value="0"><br>
                                          <label>Costo Aplicacion</label>
                                         <input type="text" id="precioAplicacion" name="precioAplicacion" class="form-control" onclick="cargarCostoAplicacion_registrar()" placeholder=""><br>

                                        
                                    
                                        

                                </div>
                                <div class="col-sm-12">
                                    <center><input class="btn btn-success" type="submit" onclick="loading(load)" value="Guardar"><br></center><br>
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
          <%--div para cargar el formulario de edicion de la nivelacion.. se carga la info desde la BD--%>
          <div id="formEditarNivelacion">
            
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
