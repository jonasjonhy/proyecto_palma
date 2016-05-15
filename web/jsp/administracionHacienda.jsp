<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.0//ES">
<html lang="es">
    <head>
        <link rel="shortcut icon"  href="../images/ico.png">
        <title>Administrador-Hacienda</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../css/bootstrap.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <script type="text/javascript" src="../js/Funciones.js"></script>
        <script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>

    </head>

    <body>
        <%
            HttpSession sesionU = request.getSession();
            String idAdmin = (String) sesionU.getAttribute("id");

        %>

        <header class="row ">
            <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
               <div class="col-xs-4 col-xs-offset-7 col-sm-3 col-md-offset-9 row">
                    <div class="usuario z-index-2">
                         <label>Bienvenido <%=facade.consultarNombrePropietario(idAdmin)%></label><br>
                    <label>Administrador</label>
                    </div>
                </div>
                <div><img  src="../images/logo.png"   class=""/></div>
        </header>

        <div class="container-fluyd row" >
            
            <div class="col-xs-3">
                <div class="btn-group-vertical" id="menu-botones">
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = '../jsp/administrador.jsp'">Inicio</button><br>
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = '../jsp/administracionPalmicultor.jsp'">&nbsp;&nbsp;&nbsp;&nbsp;Administrar Propietario&nbsp;&nbsp;&nbsp;&nbsp;</button><br>
                    <button type="button" class="btn btn-success btn-lg" onclick="location.href = '../jsp/cerrarSesion.jsp'">Cerrar Sesion</button><br>
                </div>
            </div>

            <div class="col-xs-7" id="central" >
                <center>
                    <h2 class="verde hidden-xs">Administrar  <span class="naranja"> Hacienda</span></h2>
                    <h3 class="verde visible-xs">Modulo <span class="naranja"> Administrador</span></h3>
                </center>
                <br><br>
            </div>

            <div class="col-md-7">
                <center>
                    <label>Criterio</label>
                    <label><select id="criterio" name="criterio" class="form-control"  onchange="cargarInput()" >
                            <option>Seleccione</option>
                            <option value="0">Nombre Hacienda</option>
                            <option value="1">C&eacute;dula catastral</option>
                            <option value="2">N&uacute;cleo Palmero</option>
                            <option value="3">Departamento</option>
                            <option value="4">Municipio</option>
                            <option value="5">Nombre Palmicultor</option>
                            <option value="6">Documento Palmicultor</option>
                        </select></label>

                    <label><input type="hidden" id="tipo" name="tipo" class="form-control" value="hac"/></label>
                </center>
                <br>
            </div>

            <div class="col-md-7">
                <div class="col-md-4">
                    <label>Palabra Clave</label>
                    <lablel><input type="text" id="palabra" name="palabra" value="" disabled="disabled"/></lablel>
                </div>
                <div class="col-md-4">
                    <label>Departamento</label>
                    <lablel><select name="departamento" id="departamento" class="form-control" onChange="cargaContenido(this.id)" disabled="disabled">
                            <%= facade.cargarDepartamentos()%>
                        </select></lablel>
                </div>
                <div class="col-md-4">
                    <label>Municipio</label>
                    <lablel><select disabled="disabled" name="municipio" id="municipio" class="form-control" onChange="cargaContenido(this.id)">
                            <option value="0">Selecciona opci&oacute;n...</option>
                        </select></lablel>
                </div>
                <br/>
            </div>

            <div class="col-md-7">
                <br/>
                <center><label><button type="button"  class="btn btn-success btn-lg" onclick="cargarInformacion()">&nbsp;&nbsp;&nbsp;Buscar Dato&nbsp;&nbsp;&nbsp;</button></label></center>
                <br/>
            </div>




            <br>
            <%-- Esta parte del codigo es la que permite el recargo de los dropdown (Lineas a pegar en Registro Hacienda)--%>

            <div class="col-md-12">
                <div class="col-md-3">
                </div>
                <div id="datos" class="col-md-7">
                </div>
                <div class="col-md-2">        
                </div>
            </div>

            <%-- Esta parte del codigo es la que permite el recargo de los dropdown (Ver script complemento de estas lineas head) --%>
            <br>

        </div>






        <footer class="z-index-3">

            <div class="icon_box"> 

                <a href="http://ingsistemas.ufps.edu.co/"> <img src="../images/is-icon.png"/></a>
                <a href="http://www.ufps.edu.co/ufps/index.php"><img src="../images/ufps-icon.png"/></a>


            </div>

        </footer>

    </body>

</html>