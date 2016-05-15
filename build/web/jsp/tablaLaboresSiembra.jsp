<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.0//EN">
<html lang="es">
    <head>
        <link rel="shortcut icon"  href="../images/ico.png">
        <title>Labores Siembra</title>
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
                            <li><a href="../jsp/tablaPreparacionSuelo.jsp">Preparacion Suelo</a></li>
                            <li><a href="../jsp/tablaSiembraCobertura.jsp">Siembra Coberturas</a></li>
                            <li><a href="../jsp/tablaPalmaSiembra.jsp">Siembra Palmas</a></li>
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
                        <th colspan="9">Siembra de Palma
                        </th>
                    </tr>
                    <tr class="negra">
                        <td>Fecha</td>
                        <td>Area Lote</td>
                        <td>Labor</td>
                        <td>cant. x Hectarea</td>
                        <td>Unidad</td>
                        <td>Precio x Unidad</td>
                        <td>Subtotal</td>
                        <td>Acciones</td>
                    </tr>
                    
                    <%= facade.vistaLaborSiembra(idLote) %>

                </table>
                <br>
                <button class="btn btn-lg btn-success" data-id="1" data-toggle="modal" data-target="#agregarCosto">Agregar</button>
                <br>
                <br>
                <br>


                <table class="table table-bordered tablaP">

                    <td class="negra">Total Labores Siembra</td>
                    <td></td>
                    </tr>
                </table>






            </div>
        </div>


        <!-- Modal -->
        <div class="modal fade" id="agregarCosto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-body">
                    <script>
                        $("document").on("clik", "modal", function () {
                            var idNivel = $(this).data('id');
                            $(".modal-body #idNivel").val(idNivel);
                        })
                    </script>
                    <input type="hidden" id="idN" name="idN" value=""/>

                    <script>
                        function cargar() {
                            var nivel = $("#idN").val();
                            var url = "editarNievlNutr.jsp?id=" + nivel;
                            document.getElementById("datos").innerHTML = "";
                            jQuery("#datos").load(url);
                        }
                    </script>
                    <div id="datos">

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