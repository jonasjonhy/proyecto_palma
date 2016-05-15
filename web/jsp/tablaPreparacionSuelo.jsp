<html lang="es">
    <head>
        <link rel="shortcut icon"  href="../images/ico.png">
        <title>Preparacion Fisica del Suelo</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../css/bootstrap.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">


    </head>

    <body>
        <%
            HttpSession sesionU = request.getSession();
            String idPropietario = (String) sesionU.getAttribute("id");
            String hacienda = (String) sesionU.getAttribute("idHacienda");
            String idLote = (String) sesionU.getAttribute("idLote");
        %>
        <jsp:useBean id="facade" scope="page" class="Facade.FacadeBrian" />
        <jsp:useBean id="facade2" scope="page" class="Facade.Facade" />
        <jsp:useBean id="p" scope="session" class="Dto.Propietario" />
        <header class="row ">

            <div class="col-xs-4 col-xs-offset-7 col-sm-3 col-md-offset-9 row">
                <div class="usuario z-index-2">
                    <label>Bienvenido <%=facade2.consultarNombrePropietario(idPropietario)%></label><br>
                    <lable>Hacienda <%= facade2.consultarNombreHacienda(hacienda)%></lable>
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
                            <li><a href="../jsp/tablaNivelacionNutriente.jsp">Nivelacion de Nutrientes</a></li>
                            <li><a href="../jsp/tablaSiembraCobertura.jsp">Siembra Coberturas</a></li>
                            <li><a href="../jsp/tablaPalmaSiembra.jsp">Siembra Palmas</a></li>
                            <li><a href="../jsp/tablaLaboresSiembra.jsp">Labores Siembra</a></li>
                            <li><a href="../jsp/tablaOtrosCostos.jsp">Otros Costos</a></li>
                            <li><a href="#">Informe Costos Est.</a></li>
                        </ul>
                    </div>     
                    <br>
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = '.html'">Costos Variables</button><br>
                    <button type="button" class="btn btn-success btn-lg">Cerrar Sesion</button>
                </div>
            </div>

            <div class="col-xs-7 row" id="central" >
                <br><br><br><br><br>
                <table class="table table-bordered tablaP">
                    <tr>
                        <th colspan="9">Preparaci&oacute;n F&iacute;sica del Suelo por &Aacute;rea</th>
                    </tr>
                    <tr class="negra">
                        <td>Fecha</td>
                        <td>Labor</td>
                        <td>Area Adecuada</td>
                        <td>Precio x Hectarea</td>
                        <td>Subtotal</td>
                        <td>Acciones</td>
                    </tr>
                    <%= facade.vistaPreparacionSuelo(idLote, "a")%>

                </table>
                <br>
                <button class="btn btn-lg btn-success" data-toggle="modal" data-target="#agregarCostoArea">Agregar</button>
                <br>
                <br>
                <br>
                <table class="table table-bordered tablaP">
                    <tr>
                        <th colspan="9">Preparaci&oacute;n F&iacute;sica del Suelo por Metro L&iacute;neal</th>
                    </tr>
                    <tr class="negra">
                        <td>Fecha</td>
                        <td>Labor</td>
                        <td>Metros Lineales</td>
                        <td>Precio x Metro Lineal</td>
                        <td>Subtotal</td>
                        <td>Acciones</td>
                    </tr>

                    <%= facade.vistaPreparacionSuelo(idLote, "m")%>
                </table>
                <br>
                <button class="btn btn-lg btn-success" data-toggle="modal" data-target="#agregarCostoMetro">Agregar</button>
                <br>
                <br>
                <br>

                <table class="table table-bordered tablaP">
                    <tr >
                        <td class="negra">Subtotal Preparaci&oacute;n F&iacute;sica del Suelo por &Aacute;rea</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="negra">Subtotal Preparaci&oacute;n F&iacute;sica del Suelo por Metro L&iacute;neal</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="negra">Total Preparaci&oacute;n F&iacute;sica del Suelo</td>
                        <td></td>
                    </tr>
                </table>
            </div>
        </div>


        <!-- Modal -->
        <div class="modal fade" id="agregarCostoMetro" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-body">
                    <form action="desactivarLote.jsp" method="post">
                        <div class="recuadro row blanco">  
                            <button type="button" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                                <span class="sr-only">Close</span></button>
                            <center><h2 class="verde">Nuevo costo</h2></center>
                            <br>
                            <div class="col-sm-6">
                                <label>Fecha<label class="requerido">*</label></label>
                                <input type="text" id="fechaPreparacion" name="fecha" class="form-control" required><br>

                                <label>Labor<label class="requerido">*</label></label>
                                <select id="idLabor" name="idLabor" onchange="validarOptionLabor()"  class="form-control">
                                    <%= facade.getOptionLabores()%>
                                </select><br>

                                <label>¿Otro? indicanos cual</label>
                                <input type="text" id="otro" name="otro" class="form-control" ><br>
                            </div>
                            <div class="col-sm-6 ">
                                <label>Metros lineales<label class="requerido">*</label></label>
                                <input type="number" id="cantidadPreparado" name="cantidadPreparado" class="form-control" placeholder="" min="0"  required><br>
                                <label>Precio por metros<label class="requerido">*</label></label>
                                <input type="number" id="precio" name="precio" class="form-control" min="0"  required><br>
                                <label>Subtotal<label class="requerido">*</label></label>
                                <input type="number" id="subtotal" name="subtotal" class="form-control" min="0" required><br>
                            </div>
                            <div class="col-sm-12">
                                <center><input class="btn btn-success" type="submit" value="Guardar"><br></center>
                            </div>
                        </div> 
                    </form>
                </div>
            </div>
        </div>


        <!-- Modal -->
        <div class="modal fade" id="agregarCostoArea" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-body">
                    <form action="validarRegistroPreparacionSuelo.jsp" method="post">
                        <div class="recuadro row blanco">  

                            <button type="button" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                                <span class="sr-only">Close</span></button>
                            <center><h2 class="verde">Nuevo costo</h2></center>
                            <br>
                            <div class="col-sm-6">
                                <label>Fecha<label class="requerido">*</label></label>
                                <input type="text" id="fechaPreparacion" name="fecha" class="form-control" required><br>

                                <label>Labor<label class="requerido">*</label></label>
                                <select id="idLabor" name="idLabor" onchange="validarOptionLabor()"  class="form-control">
                                    <%= facade.getOptionLabores()%>
                                </select><br>

                                <label>¿Otro? indicanos cual</label>
                                <input type="text" id="otro" name="otro" class="form-control" ><br>
                            </div>
                            <div class="col-sm-6 ">
                                <label>Area adecuada<label class="requerido">*</label></label>
                                <input type="number" id="cantidadPreparado" name="cantidadPreparado" class="form-control" placeholder="" min="0"  required><br>
                                <label>Precio por Hectarea<label class="requerido">*</label></label>
                                <input type="number" id="precio" name="precio" class="form-control" min="0"  required><br>
                                <label>Subtotal<label class="requerido">*</label></label>
                                <input type="number" id="subtotal" name="subtotal" class="form-control" min="0" required><br>
                            </div>
                            <div class="col-sm-12">
                                <center><input class="btn btn-success" type="submit" value="Guardar"><br></center>
                            </div>
                        </div> 
                    </form>
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