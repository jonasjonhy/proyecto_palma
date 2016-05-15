<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.0//EN">
<%@page session="true"%>
<html lang="es">
    <head>
        <link rel="shortcut icon"  href="../images/ico.png">
        <title>Palmicultor</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../css/bootstrap.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">


    </head>

    <body>

        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <jsp:useBean id="p" scope="page" class="Dto.Propietario" />

        <header class="row ">

            <div class="col-xs-4 col-xs-offset-7 col-sm-3 col-md-offset-9 row">
                <div class="usuario z-index-2">

                    <%
                        HttpSession sesionU = request.getSession();
                        String idPropietario = (String) sesionU.getAttribute("id");
                        String nombrePropi = facade.consultarNombrePropietario(idPropietario);
                    %>

                    <label>Bienvenido <%=nombrePropi%></label>

                </div>
            </div>
            <div><img  src="../images/logo.png"   class=""/></div>
        </header>

        <div class="container-fluyd row" >
            <div class="col-xs-3">


                <div class="btn-group-vertical" id="menu-botones">
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'consultarDatosPalmicultor.jsp'">Consultar datos Personales</button><br>
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'editarPalmicultor.jsp'">Editar Datos Personales</button><br>
                    <button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#desactivarCuenta">Desactivar Cuenta</button><br>
                    <button type="button" class="btn btn-success btn-lg"onclick="location.href = 'cerrarSesion.jsp'">Cerrar Sesion</button><br>



                </div>


            </div>

            <div class="col-xs-7" id="central" >

                <center>
                    <h2 class="verde hidden-xs">Proyecto<span class="naranja"> Palma</span></h2>
                    <h3 class="verde visible-xs">Proyecto<span class="naranja"> Palma</span></h3>
                </center>
                <br><br>
                <div class="table-responsive col-xs-6 ">
                    <table class="table table-striped">
                        <%= facade.listarHaciendas(idPropietario)%>
                        <tr>
                            <td>
                                <button class="btn-success btn btn-lg" onclick="location.href = '../jsp/registroHacienda.jsp'">Agregar Hacienda</button>
                            </td>
                        </tr>
                    </table>
                </div>

            </div>
        </div>


        <!-- Modal -->
        <div class="modal fade" id="desactivarCuenta" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">


                <div class="modal-body">
                    <form action="../jsp/validarDesactivacionPropietario.jsp">
                        <div class="recuadro desactivar">  
                            <button type="button" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                                <span class="sr-only">Close</span></button>
                            <center><h2 class="verde">Desactivar Cuenta</h2></center>
                            <p>Su cuenta sera desactivada, por favor introduzca su numero de documento y contraseña</p>
                            <label>Documento<label class="requerido">*</label></label>
                            <input type="number" id="numDocumento" name="numDocumento" class="form-control" placeholder="Documento" required><br>


                            <label>Contraseña<label class="requerido">*</label></label>
                            <input type="password" id="password" name="password" class="form-control" placeholder="Contraseña" required><br>

                            <center><input class="btn btn-success" type="submit" value="Desactivar"></center>
                            <br>

                        </div> 
                    </form>
                </div>

            </div>
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