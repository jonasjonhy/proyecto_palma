<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.0//ES">
<html>
    <head>
        <link rel="shortcut icon"  href="../images/ico.png">
        <title>Desactivar Hacienda</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../css/bootstrap.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        
        
    </head>

    <body>
        
        <header>
            <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
            <%
                HttpSession sesionU = request.getSession();
                String idLote = (String) sesionU.getAttribute("idLote");
                String lote=request.getParameter("id_lote");
                
               
            %>
            
                <img  src="../images/logo.png" >
        </header>
                    
           <div class="content row">
            
            
                <div class="col-xs-2 col-sm-3 col-md-4" ></div>
                <div class="col-xs-8 col-sm-6 col-md-4" > 
                                

                            <form action="validarDesactivarLote.jsp" method="post">
                                <div class="recuadro desactivar">  
                       
                                    <center><h2 class="verde">Desactivar Hacienda</h2></center>
                                    <p >Este lote sera desactivado, por favor introduzca su numero de documento y contraseña</p><br>
                                    <label class="naranja">Lote <label class="naranja"> <%= facade.obtenerCodigoLote(idLote) %></label>, <label class="naranja"> Hacienda </label> <label class="naranja"><%= facade.nombreHaciendaDelLote(idLote) %></label></label>
                                    <br/>
                                    <label>Documento<label class="requerido">*</label></label>
                                    <input type="text" id="documento" name="documento" class="form-control" placeholder="Documento" required><br>
                                    <input type="hidden" name="id" value="<%= lote %>"/>
                                    

                                    <label>Contraseña<label class="requerido">*</label></label>
                                    <input type="password" id="clave" name="clave" class="form-control" placeholder="Contraseña" required><br>

                                    <center><input type="submit" class="btn btn-success" value="Desactivar"/>&nbsp;&nbsp;
                                            <input type="submit" class="btn btn-success" value="Cancelar" onclick="location.href = '../jsp/informacionPalmicultor.jsp'"/>
                                    </center>
                                    <br>
                                 
                                </div> 
                            </form>
            </div>
                <div class="col-xs-4" ></div>

            


                          
                
                  
                    

            </div>
                
            
       

        <footer>
            
            <div class="icon_box"> <!-- redes sociales-->
          
               <a href="http://ingsistemas.ufps.edu.co/"> <img src="../images/is-icon.png"/></a>
               <a href="http://www.ufps.edu.co/ufps/index.php"><img src="../images/ufps-icon.png"/></a>
               
                
            </div>
            
        </footer>
            
    </body>

</html>