<%-- 
    Document   : validarRegistroHacienda
    Created on : 19/10/2014, 11:00:23 PM
    Author     : brialxsfxubun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>validaRegistro</title>
    </head>
    <body>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        
        <%
            HttpSession sesionU = request.getSession();
            String idLote = (String) sesionU.getAttribute("idLote");
            //String lote= request.getParameter("id");
            String idP=request.getParameter("documento");
            String pass=request.getParameter("clave");
            
            System.out.println("DATOS DESACTIVAR LOTE ----> "+idP+ " -- " +pass +" -- "+idLote);
            boolean desactivo = facade.desactivarLote(idLote, idP, pass) ;
            
            if(!desactivo){
            %>
            <script type="text/javascript"> 
                  alert("ERROR...NO SE HA PODIDO DESACTIVAR \n Los Datos del propietario no concuerdan\n Vuelvalo a intentar")
                  location.href="informacionPalmicultor.jsp";
              </script>
            <%
            }else{
                %>
                <script type="text/javascript"> 
                  alert("DESACTIVACION EXITOSA")
                  location.href="../jsp/informacionPalmicultor.jsp";
                </script>
                <%
            }
            %>
    </body>
</html>

