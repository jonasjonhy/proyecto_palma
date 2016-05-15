<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.0//ES">
<%@page session="true"%>
<html lang="es">
    <head>
        <link rel="shortcut icon"  href="../images/ico.png">
        <title>Hacienda</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../css/bootstrap.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">


    </head>

    <body>

        <header class="row ">
            <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
            <%
                String hacienda=request.getParameter("id_hacienda");
                out.println(hacienda);
                HttpSession sesionU = request.getSession();
                if(hacienda!=null){
                             
                sesionU.setAttribute("idHacienda", hacienda);
                }
                String idPropietario = (String) sesionU.getAttribute("id");
                String idHacienda = (String) sesionU.getAttribute("idHacienda");
                String tipo = "hac";
            %>
            
            <div class="col-xs-4 col-xs-offset-7 col-sm-3 col-md-offset-9 row">
                <div class="usuario z-index-2">
                    <label>Bienvenido <%= facade.consultarNombrePropietario(idPropietario) %></label><br>
                    
                </div>
            </div>
            <div><img  src="../images/logo.png"   class=""/></div>
        </header>

        <div class="container-fluyd row" >
            <div class="col-xs-3">

                <div class="btn-group-vertical" id="menu-botones">
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'informacionPalmicultor.jsp'">Inicio</button><br>
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'consultarDatosHacienda.jsp?id_hacienda=<%=idHacienda%>'">Consultar Hacienda</button><br>
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'editarDatosHacienda.jsp?id_hacienda=<%=idHacienda%>'"  name="id_hacienda" value=<%=request.getParameter("id")%> >Editar Datos Hacienda</button><br>
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'desactivarHacienda.jsp'"  name="id_hacienda" value=<%=request.getParameter("id")%> >Desactivar Hacienda</button><br>
                     <div class="btn-group">

                        <button type="button" class="btn btn-success btn-lg dropdown-toggle" data-toggle="dropdown">Costos Fijos <span class="caret"></span></button>
                                <ul class="dropdown-menu menu" role="menu">
                                <li><a href="../jsp/tablaCostoTierra.jsp">Tierra</a></li>
                                <li><a href="../jsp/tablaCFMaquinaria.jsp">Maquinaria</a></li>
                                <li><a href="../jsp/tablaCFAnimales.jsp">Animales</a></li>
                                <li><a href="../jsp/tablaCFHerramientas.jsp">Herramientas</a></li>
                                <li><a href="../jsp/tablaAdministracion.jsp">Administracion</a></li>
                                <li><a href="../jsp/generadorReporte.jsp?tipo="<%= tipo %>>Informe Costos Fijos</a></li>
                                
                              </ul>

                         </div>     
                    <br>
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'cerrarSesion.jsp'">Cerrar Sesion</button>
                     
                </div>


            </div>

            <div class="col-xs-7" id="central" >

                <center>
                    <h2 class="verde hidden-xs">Hacienda <span class="naranja"> <%=facade.consultarNombreHacienda((String) sesionU.getAttribute("idHacienda"))%> </span></h2>
                    <h3 class="verde visible-xs">Hacienda <span class="naranja"> <%=facade.consultarNombreHacienda((String) sesionU.getAttribute("idHacienda"))%> </span></h3>
                </center>
                <br><br>
                <div class="table-responsive ">
                    <table class="table table-striped">
                        <%= facade.listarLotesHacienda(hacienda) %>
                        <tr>
                            <td>
                                <button class="btn btn-success btn-lg" onclick="location.href = 'registroLote.jsp'">Agregar Lote</button>
                            </td>

                        </tr>

                    </table>
                </div>
            </div>
        </div>






        <footer class="z-index-3">

            <div class="icon_box"> 

                <a href="http://ingsistemas.ufps.edu.co/"> <img src="../images/is-icon.png"/></a>
                <a href="http://www.ufps.edu.co/ufps/index.php"><img src="../images/ufps-icon.png"/></a>


            </div>

        </footer>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
    </body>

</html>