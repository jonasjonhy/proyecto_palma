<%-- 
    Document   : registroPalmicultor
    Created on : 16/10/2014, 08:29:34 AM
    Author     : brialxsf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <link rel="shortcut icon"  href="../images/ico.png">
        <title>Registro Palmicultor</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../css/bootstrap.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <script src="../js/jquery-1.8.3.min.js"></script>
        
        <%-- Estas lineas permiten el recargo de los formularios --%>
        <link rel="stylesheet" type="text/css" href="../js/select_dependientes.css">
         <script type="text/javascript" src="../js/Funciones.js"></script>
        <%-- Estas lineas permiten el recargo de los formularios --%>
        
    </head>

    <body>

        <header>
            <a href="index.html"><img  src="../images/logo.png" ></a>
        </header>

        <div class="container-fluyd central">
            <br>
            <center><h2 class="verde">Inscripcion de <span class="naranja">Palmicultores</span></h2></center>
            <br>
            <section class="row">
                <div class="col-xs-2"></div>
                <div class="col-xs-8 row">

                    <form action="validarRegistroPropietario.jsp" method="post">
                        <div class="col-sm-6">
                            <label>Nombre<label class="requerido">*</label></label>
                            <input type="text" id="nombre" name="nombre" class="form-control" placeholder="Nombre" required><br>

                            <label>Apellido<label class="requerido">*</label></label>
                            <input type="text" id="apellidos" name="apellidos" class="form-control" placeholder="Apellido" required><br>

                            <label>Tipo documento</label>
                            <select id="tipoDocumento" name="tipoDocumento" class="form-control">
                                <option value="0">Cedula  Ciudadania</option>
                                <option value="1">Cedula extranjeria</option>
                                <option value="2">Tarjeta de Identidad</option>
                                <option value="3">Pasaporte</option>
                            </select><br>

                            <label>Documento<label class="requerido">*</label></label>
                            <input type="text" id="numDocumento" name="numDocumento" class="form-control" placeholder="Documento" required><br>

                            <label>Genero</label>
                            <select id="genero" name="genero" class="form-control">
                                <option value="0">Femenino</option>
                                <option value="1">Masculino</option>
                            </select><br>

                            <label>Direccion de Residencia<label class="requerido">*</label></label>
                            <input type="text" id="direccionResidencia" name="direccionResidencia" class="form-control" placeholder="Direccion" required><br>
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
                                <label>Telefono de Residencia<label class="requerido">*</label></label>
                                <input type="number" id="telefono" name="telefono" class="form-control" placeholder="Telefono" required><br>

                                <label>Celular</label>
                                <input type="number" id="celular" name="celular" class="form-control" placeholder="celular" required><br>



                                <label>Correo Electronico<label class="requerido">*</label></label>
                                <input type="email" id="email" name="email" class="form-control" required><br>


                                <label>Zona Palmera<label class="requerido">*</label></label>
                                <input type="text" id="zonaPalmera" name="zonaPalmera" class="form-control" placeholder="Zona Palmera" required><br>
                                <label>Cedula Palmera<label class="requerido">*</label></label>
                                <input type="text" id="cedulaPalmera" name="cedulaPalmera" class="form-control" placeholder="Cedula Palmera" required><br>
                                <label>Contraseña<label class="requerido">*</label></label>
                                <input type="password" id="password" name="password" class="form-control" placeholder="Contraseña" required><br>

                                




                            </div>
                                <br>
                                <br>
                                <br>
                                <br>
                                <center><input class="btn btn-success" type="submit" value="Finalizar Registro" />&nbsp;&nbsp;&nbsp;
                                    <input type="submit" class="btn btn-success" value="Cancelar" onclick="location.href = '../html/index.html'"/>
                                </center>
                    </form>
                </div>    

                <div class="col-xs-2" ></div><br>

            </section>            






        </div>




        <footer>

            <div class="icon_box"> <!-- redes sociales-->

                <a href="http://ingsistemas.ufps.edu.co/"> <img src="../images/is-icon.png"/></a>
                <a href="http://www.ufps.edu.co/ufps/index.php"><img src="../images/ufps-icon.png"/></a>


            </div>

        </footer>

    </body>

</html>
