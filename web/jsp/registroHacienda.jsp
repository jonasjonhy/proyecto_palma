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
                        String nombrePropi = facade.consultarNombrePropietario(idPropietario);
                        p.setNumDocumento(idPropietario);
                    %>
                    <label>Bienvenido <%= nombrePropi%></label>
                </div>
            </div>
            <div><img  src="../images/logo.png"   class=""/></div>
        </header>

        <div class="container-fluyd row" >
            <div class="col-xs-3">


                <div class="btn-group-vertical" id="menu-botones">
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = '../jsp/informacionPalmicultor.jsp'">Inicio propietario</button><br>
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = 'cerrarSesion.jsp'">Cerrar Sesion</button>
                </div>


            </div>

            <div class="col-xs-7 row" id="central" >

                <center>
                    <h2 class="verde hidden-xs">Registro <span class="naranja"> Hacienda</span></h2>
                    <h3 class="verde visible-xs">Registro<span class="naranja"> Hacienda</span></h3>
                </center>
                <br><br>
                <form action="validarRegistroHacienda.jsp" method="post">
                    <div class="col-sm-6">
                        <label>Cedula Catastral<label class="requerido">*</label></label>
                        <input type="text" id="cedCatastral" name="cedCatastral" class="form-control" placeholder="cedulaCatastral" required><br>
                        <label>Nombre Hacienda<label class="requerido">*</label></label>
                        <input type="text" id="nombre" name="nombre" class="form-control" placeholder="Nombre Hacienda"required><br>

                        <input type="hidden" id="propietario_cedula" name="propietario_cedula" value="<%= idPropietario%>">

                        <label>Direccion de la Hacienda<label class="requerido">*</label></label>
                        <input type="text" id="direccion" name="direccion" class="form-control" placeholder="Direccion" required><br>

                        <%-- Esta parte del codigo es la que permite el recargo de los dropdown (Lineas a pegar en Registro Hacienda)--%>
                        <label>Departamento</label>
                        <div id="demo">
                            <select name="departamento" id="departamento" class="form-control" onChange="cargaContenido(this.id)" >
                                <%= facade.cargarDepartamentos()%>
                            </select>
                            <br>
                            <label>Municipio</label>

                            <div id="demoDer">
                                <select disabled="disabled" name="municipio" id="municipio" class="form-control" onChange="cargaContenido(this.id)">
                                    <option value="0">Selecciona opci&oacute;n...</option>
                                </select>
                            </div>
                        </div>
                        <%-- Esta parte del codigo es la que permite el recargo de los dropdown (Ver script complemento de estas lineas head) --%>
                        <br>

                    </div>
                    <div class="col-sm-6 ">

                        <label>Nucleo Palmero<label class="requerido">*</label></label>
                        <input type="text" id="nucleo" name="nucleo" class="form-control" placeholder="Nucleo Palmero" required><br>
                        <label>Area total en hectareas<label class="requerido">*</label></label>
                        <input type="number" id="area" name="area" class="form-control" placeholder="Area total" required><br>
                        <label>Telefono</label>
                        <input type="number" id="telefono" name="telefono" class="form-control" placeholder="Telefono"><br>
                        <label>Descripcion</label>
                        <textarea  id="descripcion" name="descripcion" class="form-control" placeholder="Descripcion"></textarea><br>
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