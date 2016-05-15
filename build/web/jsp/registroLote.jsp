<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.0//EN">
<html lang="es">
    <head>
        <link rel="shortcut icon"  href="../images/ico.png">
        <title>Registro Hacienda</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../css/bootstrap.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

        <%-- Estas lineas permiten el recargo de los formularios --%>
        <link rel="stylesheet" type="text/css" href="../js/select_dependientes.css">
        <script type="text/javascript" src="../js/Funciones.js"></script>
        <%-- Estas lineas permiten el recargo de los formularios --%>

    </head>

    <body>

        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <jsp:useBean id="p" scope="session" class="Dto.Propietario" />

    <header class="row ">
        <div class="col-xs-4 col-xs-offset-7 col-sm-3 col-md-offset-9 row">
            <div class="usuario z-index-2">
                <%
                    HttpSession sesionU = request.getSession();
                    String idPropietario = (String) sesionU.getAttribute("id");
                    String idHacienda = (String) sesionU.getAttribute("idHacienda");
                    String nombrePropi = facade.consultarNombrePropietario(idPropietario);
                    String hacienda = request.getParameter("id_hacienda");
                %>
                <label>Bienvenido <%= nombrePropi%></label>
            </div>
        </div>
        <div><img  src="../images/logo.png"   class=""/></div>
    </header>

    <div class="container-fluyd row" >
        <div class="col-xs-3">


            <div class="btn-group-vertical" id="menu-botones">
                <button type="button" class="btn btn-success btn-lg" onclick="location.href = '../jsp/informacionPalmicultor.jsp'">Inicio</button><br>
                <button type="button" class="btn btn-success btn-lg" onclick="location.href = '../jsp/consultarDatosHacienda.jsp?id_hacienda=<%=idHacienda%>'">Consultar Datos Hacienda</button><br>
                <button type="button" class="btn btn-success btn-lg" onclick="location.href = '../jsp/desactivarHacienda.jsp'">Desactivar Hacienda</button><br>
                <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'informacionPalmicultor.html'">Cerrar Sesion</button>
            </div>


        </div>

        <div class="col-xs-7 row" id="central" >

            <center>
                <h2 class="verde hidden-xs">Registro <span class="naranja"> Lote</span></h2>
                <h3 class="verde visible-xs">Registro<span class="naranja"> Lote</span></h3>
            </center>
            <br><br>
            <form action="validarRegistroLote.jsp" method="post">
                <div class="col-sm-6">
                    <label>Area (en hectareas)<label class="requerido">*</label></label>
                    <input type="number" id="area"   min="1" name="area" class="form-control" placeholder="Area del lote" required><br>
                    <label>Capacidad de palmas<label class="requerido">*</label></label>
                    <input type="number" id="capacidad" name="capacidad"  min="1" class="form-control" placeholder="capacidad palmas"required><br>
                    <input type="hidden" id="haciendaId" name="haciendaId" value="<%= idHacienda %>"/>
                    <label>Fecha plantacion</label>
                    <input type="date" id="fechaPlantacion" name="fechaPlantacion" class="form-control" placeholder="Fecha de plantacion" ><br>
                

                    <label>Codigo zona<label class="requerido">*</label></label>
                    <input type="text" id="codigoZona" name="codigoZona" class="form-control" placeholder="Codigo zona o lote" required><br>
                    
                    <center><input class="btn btn-success" type="submit" value="Finalizar Registro" /></center>
                </div>
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