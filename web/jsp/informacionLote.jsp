<%-- 
    Document   : infoLote
    Created on : 24/11/2014, 02:23:45 AM
    Author     : mauricio uribe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.0//EN">
<html lang="es">
    <head>
        <link rel="shortcut icon"  href="../images/ico.png">
        <title>Lote</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../css/bootstrap.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">


    </head>

    <body>

        <header class="row ">
            <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
            <jsp:useBean id="facadeB" scope="page" class="Facade.FacadeBrian" />
            <jsp:useBean id="pdf" scope="page" class="util.crearPDF" />
            <%

                HttpSession sesionU = request.getSession();
                String idPropietario = (String) sesionU.getAttribute("id");
                String hacienda = (String) sesionU.getAttribute("idHacienda");
                String lote = request.getParameter("idLote");
                sesionU.setAttribute("idLote", lote);
                String idLote = (String) sesionU.getAttribute("idLote");
                String tipo ="lot";

            %>
             

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
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'informacionPalmicultor.jsp'">Regresar vista Palmicultor</button><br>
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'informacionHacienda.jsp'">Regresar vista Hacienda</button><br>
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'consultarDatosLote.jsp'">Consultar datos lote</button><br>
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'edicionLote.jsp'">Editar datos lote</button><br>
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'desactivarLote.jsp'">Desactivar Lote </button><br>

                    <div class="btn-group">
                        <button type="button" class="btn btn-success btn-lg dropdown-toggle" data-toggle="dropdown">Costos Establecimiento <span class="caret"></span></button>
                        <ul class="dropdown-menu menu" role="menu">
                            <li><a href="../jsp/tablaCorreccionSuelos.jsp">Correcion de suelos</a></li>
                            <li><a href="../jsp/tablaNivelacionNutriente.jsp">Nivelacion de Nutrientes</a></li>
                            <li><a href="../jsp/tablaPreparacionSuelo.jsp">Preparacion Suelo</a></li>
                            <li><a href="../jsp/tablaSiembraCobertura.jsp">Siembra Coberturas</a></li>
                            <li><a href="../jsp/tablaPalmaSiembra.jsp">Siembra Palmas</a></li>
                            <li><a href="../jsp/tablaLaboresSiembra.jsp">Labores Siembra</a></li>
                            <li><a href="../jsp/tablaOtrosCostos.jsp">Otros Costos</a></li>
                            <li><a href="../jsp/generadorReporte.jsp?tipo="<%= tipo %> >Informe Costos Est.</a></li>
                        </ul>
                    </div>     

                    <br>
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = '.html'">Costos Variables</button><br>
                    <button type="button" class="btn btn-success btn-lg">Cerrar Sesion</button>
                    <div id="datos"> </div>
                </div>
            </div>

            <div class="col-xs-7" id="central" >
                <center>
                    <h2 class="verde hidden-xs">Informacion <span class="naranja">Lote</span></h2>
                    <h3 class="verde visible-xs">Informacion <span class="naranja">Lote</span></h3>
                </center>
                <br><br>
                <div class="table-responsive ">
                    <table class="table table-striped">
                        <%= facade.consultarInformacionLote(lote)%>
                    </table>
                </div>
            </div>
                    
            <br/>
            <label><a href="../reportes/reporte.pdf">VER PDF</a></label>
        </div>






        <footer class="z-index-3">

            <div class="icon_box"> <!-- redes sociales-->

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
