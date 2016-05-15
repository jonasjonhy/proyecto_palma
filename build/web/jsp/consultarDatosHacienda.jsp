<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.0//ES">
<html lang="es">
    <head>
        <link rel="shortcut icon"  href="../images/ico.png">
        <title>Datos Hacienda</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../css/bootstrap.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">


    </head>

    <body>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <%
            HttpSession sesionU = request.getSession();
            String idPropietario = (String) sesionU.getAttribute("id");
            String idHacienda = (String) sesionU.getAttribute("idHacienda");
            String nombrePropi = facade.consultarNombrePropietario(idPropietario);
            System.out.println("HACIENDA SIN COOKIES ---> "+idHacienda);
            %>
    <header class="row ">

        <div class="col-xs-4 col-xs-offset-7 col-sm-3 col-md-offset-9 row">
            <div class="usuario z-index-2">
                <label>Bienvenido <%=nombrePropi%></label><br>
            </div>
        </div>
        <div><img  src="../images/logo.png"   class=""/></div>
    </header>

    <div class="container-fluyd row" >
        <div class="col-xs-3">


            <div class="btn-group-vertical" id="menu-botones">
                <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'informacionHacienda.jsp?id_hacienda=<%=idHacienda%>'">Regresar</button><br>
                <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'editarDatosHacienda.jsp?id_hacienda=<%=idHacienda%>'">Editar Hacienda</button><br>
                <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'desactivarHacienda.jsp'">Desactivar Hacienda</button><br>
                <button type="button" class="btn btn-success btn-lg" onclick="">Costos Fijos</button><br>
                <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'cerrarSesion.jsp'">Cerrar Sesion</button>


            </div>


        </div>

        <div class="col-xs-7" id="central" >

            <center>
                <h2 class="verde hidden-xs">Informacion de<span class="naranja"> Hacienda</span></h2>
                <h3 class="verde visible-xs">Informacion de<span class="naranja"> Hacienda</span></h3>
            </center>
            <br><br>
            <div class="table-responsive ">
                <%
                    String id=request.getParameter("id_hacienda");
                    System.out.println("HOLA HACIENDAA ---> "+id);
                %>
                
                <table class="table table-striped">
                    <%= facade.consultarInformacionHacienda(id)%>
                </table>
            </div>
        </div>
    </div>






    <footer class="z-index-3">

        <div class="icon_box"> <!-- redes sociales-->

            <a href="http://ingsistemas.ufps.edu.co/"> <img src="../images/is-icon.png"/></a>
            <a href="http://www.ufps.edu.co/ufps/index.php"><img src="../images/ufps-icon.png"/></a>


        </div>

    </footer>

</body>

</html>