<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<html lang="es">
    <head>
        <link rel="shortcut icon"  href="../images/ico.png">
        <title>Edicion Hacienda</title>
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
                
                HttpSession sesionU = request.getSession();
                String idPropietario = (String) sesionU.getAttribute("id");
                String hacienda=(String) sesionU.getAttribute("idHacienda");
                String lote = (String) sesionU.getAttribute("idLote");
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
                <button type="button" class="btn btn-lg btn-success" onclick="location.href = 'informacionHacienda.jsp?id_hacienda=<%=hacienda%>'">Volver a Haciendas</button><br>
                <button type="button" class="btn btn-lg btn-success" onclick="location.href = 'informacionLote.jsp?idLote=<%=lote%>'">Consultar Lote</button><br>
                <button type="button" class="btn btn-lg btn-success" onclick="location.href = 'desactivarLote.jsp'"  value="<%= lote%>">Desactivar Lote</button><br>
                <button type="button" class="btn btn-lg btn-success" onclick="location.href = '../jsp/cerrarSesion.jsp'">Cerrar Sesion</button><br>
                <br>
                <br>
            </div>
        </div>

        <div class="col-xs-7 row" id="central" >
            <center>
                <h2 class="verde hidden-xs">Editar <span class="naranja"> lote</span></h2>
                <h3 class="verde visible-xs">Editar <span class="naranja"> lote</span></h3>
            </center>
            <br><br>
            
            <form action="validarActualizacionLote.jsp" method="post">
                <%= facade.editarDatosLote(lote) %>
                
                <center><input class="btn btn-success" type="submit" value="Guardar Cambios"><br></center>

            </form>
        </div>
    </div>






    <footer class="z-index-3">

        <div class="icon_box"> 

            <a href="http://ingsistemas.ufps.edu.co/"> <img src="../images/is-icon.png"/></a>
            <a href="http://www.ufps.edu.co/ufps/index.php"><img src="../images/ufps-icon.png"/></a>


        </div>

    </footer>

</body>

</html>